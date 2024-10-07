package top.redstarmc.api.Yggdrasil.SQL.Tables;

import cc.carm.lib.easysql.api.SQLManager;
import cc.carm.lib.easysql.api.SQLTable;
import cc.carm.lib.easysql.api.builder.TableCreateBuilder;
import cc.carm.lib.easysql.api.enums.IndexType;
import cc.carm.lib.easysql.api.enums.NumberType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.redstarmc.api.Yggdrasil.Main;
import top.redstarmc.api.Yggdrasil.util.Logger;

import java.sql.SQLException;
import java.util.function.Consumer;

public enum Profiles implements SQLTable {
    YGGDRASIL_PROFILES((table) -> {
        table.addAutoIncrementColumn("id", NumberType.INT, true, true);
        table.addColumn("uuid", "TEXT NOT NULL");
        table.addColumn("name","CHAR NOT NULL");
        table.addColumn("modelType","ENUM（'default'，'slim'）NOT NULL");
        table.addColumn("SKIN","TEXT");
        table.addColumn("CAPE","TEXT");

        table.setIndex("uuid", IndexType.UNIQUE_KEY); //索引
        table.setIndex("name", IndexType.UNIQUE_KEY);
//        table.setIndex(IndexType.INDEX, "contact", "email", "phone"); //添加联合索引 (示例)
    });

    private final Consumer<TableCreateBuilder> builder;
    private @Nullable String tablePrefix;
    private @Nullable SQLManager manager;
    private final static Logger logger = Main.getLogger();
    Profiles(Consumer<TableCreateBuilder> builder) {
        this.builder = builder;
    }

    @Override
    public @Nullable SQLManager getSQLManager() {
        return this.manager;
    }

    @Override
    public @NotNull String getTableName() {
        // 直接选择用枚举的名称作为table的主名称
        return (tablePrefix != null ? tablePrefix : "") + name().toLowerCase();
    }

    @Override
    public boolean create(SQLManager sqlManager) throws SQLException {
        return create(sqlManager, null);
    }

    public boolean create(@NotNull SQLManager sqlManager, @Nullable String tablePrefix) throws SQLException {
        if (this.manager == null) this.manager = sqlManager;
        this.tablePrefix = tablePrefix;

        TableCreateBuilder tableBuilder = sqlManager.createTable(getTableName());
        if (builder != null) builder.accept(tableBuilder);
        return tableBuilder.build().executeFunction(l -> l > 0, false);
    }

    public static void initialize(@NotNull SQLManager manager, @Nullable String tablePrefix) {
        for (Profiles value : values()) {
            try {
                value.create(manager, tablePrefix);
            } catch (SQLException e) {
                // 提示异常
                logger.error(String.valueOf(e));
                logger.fatal("创建数据库表失败");
            }
        }
    }
}
