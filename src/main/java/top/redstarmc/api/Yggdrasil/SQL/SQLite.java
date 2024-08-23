package top.redstarmc.api.Yggdrasil.SQL;

import cc.carm.lib.easysql.api.SQLManager;
import top.redstarmc.api.Yggdrasil.Main;
import top.redstarmc.api.Yggdrasil.util.Logger;

import java.io.File;

public class SQLite {
    private static final Logger logger = Main.getLogger();

    private SQLManager sqlManager;
    private final File config = Main.getConfig();

    public void initSQLManager(){
        logger.info("数据库类型：SQLite");


    }

    public long backup(){
        logger.info("[异步线程] 正在备份MySQL至SQL数据库……");
        long timeMillis_1 = System.currentTimeMillis();


        long timeMillis_2 = System.currentTimeMillis();
        return timeMillis_2 - timeMillis_1;
    }
}
