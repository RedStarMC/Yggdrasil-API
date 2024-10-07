package top.redstarmc.api.Yggdrasil.SQL;

import cc.carm.lib.easysql.api.SQLManager;
import top.redstarmc.api.Yggdrasil.Main;
import top.redstarmc.api.Yggdrasil.util.Logger;

import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicBoolean;

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
     * 查询数据表是否存在
     * 返回true表示该表存在
     * @param sqlManager 数据库管理器
     * @param name 数据表名称
     */
    public static boolean queryTable(SQLManager sqlManager,String name) {
        AtomicBoolean a = new AtomicBoolean(false);
        sqlManager.createQuery().inTable(name).build().execute(
                (query) -> {
                    ResultSet resultSet = query.getResultSet();
                    if (resultSet != null){
                        a.set(true);
                    }
                    return a;
                },
                ((exception,sqlAction) -> {
                    a.set(false);
                })
        );
        return a.get();
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
