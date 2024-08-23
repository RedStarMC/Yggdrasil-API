package top.redstarmc.api.Yggdrasil.SQL;

import cc.carm.lib.easysql.api.SQLManager;
import cc.carm.lib.easysql.api.SQLQuery;
import org.jetbrains.annotations.Nullable;
import top.redstarmc.api.Yggdrasil.Main;
import top.redstarmc.api.Yggdrasil.util.Logger;

import java.sql.ResultSet;

public class OperateTable {
    private final static Logger logger = Main.getLogger();
    /**
     * 查询
     * 登陆操作
     * @param sqlManager 数据库管理器
     */
    public static String querySkin(SQLManager sqlManager, String username) {
        sqlManager.createQuery() // 创建一个查询
                .inTable("YGGDRASIL_USER") // 指定表名
                .selectColumns("username", "password") // 选择 两个列
                .addCondition("username", username) // 限定条件，"name" 必须是 传入的
                .build()/*构建查询体*/.executeAsync(
                        (query) -> { /*处理查询结果-SQLQuery*/
                                ResultSet resultSet = query.getResultSet();

                            },
                        ((exception, sqlAction) -> { /*SQL异常处理-SQLExceptionHandler*/
                                logger.error("SQL异常"+exception.getMessage());
                            })
                ); // 异步查询~~~~
        return username;
    }
    /**
     * 查询
     * @param sqlManager 数据库管理器
     */
    public static void query(SQLManager sqlManager) {
        sqlManager.createQuery() // 创建一个查询
                .inTable("table_name") // 指定表名
                .selectColumns("name", "sex", "age") // 选择 "name", "sex", "age" 三个列
                .addCondition("name", "Bob") // 限定条件，"name" 必须是 Bob
                .build()/*构建查询体*/.executeAsync(
                        (query) -> { /*处理查询结果-SQLQuery*/ },
                        ((exception, sqlAction) -> { /*SQL异常处理-SQLExceptionHandler*/ })
                ); // 异步查询~~~~
    }
    /**
     * 查询
     * @param sqlManager 数据库管理器
     */
    public static void Query(SQLManager sqlManager) {
        sqlManager.createQuery() // 创建一个查询
                .inTable("table_name") // 指定表名
                .selectColumns("name", "sex", "age") // 选择 "name", "sex", "age" 三个列
                .addCondition("name", "Bob") // 限定条件，"name" 必须是 Bob
                .build()/*构建查询体*/.executeAsync(
                        (query) -> { /*处理查询结果-SQLQuery*/ },
                        ((exception, sqlAction) -> { /*SQL异常处理-SQLExceptionHandler*/ })
                ); // 异步查询~~~~
    }
}
