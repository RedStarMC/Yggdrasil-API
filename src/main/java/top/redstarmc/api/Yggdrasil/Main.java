package top.redstarmc.api.Yggdrasil;

import top.redstarmc.api.Yggdrasil.SQL.MySQL;
import top.redstarmc.api.Yggdrasil.util.ConfigManager;
import top.redstarmc.api.Yggdrasil.util.Logger;

import java.io.File;

/**
 * @version  0.0.2
 * @author pingguomc
 */
public class Main {
    private static Logger logger;
    private static File Config;
    private static MySQL sql;
    private Main() {
    }

    public static void main(String[] args) {
        System.out.println("======================================================");
        System.out.println("                 Start Yggdrasil ……           ");
        System.out.println("作者：pingguomc     github：https://github.com/RedStarMC/Yggdrasil-API");
        System.out.println("======================================================");
        System.out.println("加载日志系统……");
        Logger logger = new Logger();
        setLogger(logger);

        logger.info("===========正在启动===========");
        logger.warn("当前版本为测试版本！！！");
        logger.info("读取配置文件……");
        ConfigManager configManager = new ConfigManager();
        configManager.saveConfig();
        setConfig(configManager.readConfig());

        logger.info("初始化数据库……");
//        MySQL sql = new MySQL();
//        setSql(sql);
//        sql.initSQLManager();



    }

    public static void shutDown(){
        logger.info("正在关闭 Yggdrasil 身份验证系统API");
        System.exit(0);
    }

    public static Logger getLogger() {
        return logger;
    }

    private static void setLogger(Logger logger) {
        Main.logger = logger;
    }

    public static File getConfig() {
        return Config;
    }

    private static void setConfig(File config) {
        Config = config;
    }

    public static MySQL getSql() {
        return sql;
    }

    private static void setSql(MySQL sql) {
        Main.sql = sql;
    }
}
