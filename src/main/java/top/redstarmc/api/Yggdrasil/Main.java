package top.redstarmc.api.Yggdrasil;

import top.redstarmc.api.Yggdrasil.SQL.MySQL;
import top.redstarmc.api.Yggdrasil.util.ConfigManager;
import top.redstarmc.api.Yggdrasil.util.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version  0.0.3
 * @author pingguomc
 */
public class Main {
    private static final String version = "0.0.3";
    private static Logger logger;
    private static File Config;
    private static MySQL sql;
    private Main() {
    }

    public static void main(String[] args) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy 年 MM 月 dd 日 E    HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("========================================================================");
        System.out.println("                          Start Yggdrasil ……           ");
        System.out.println(" 今天是："+formatter.format(date)+"    当前版本为："+version);
        System.out.println("作者：pingguomc     github：https://github.com/RedStarMC/Yggdrasil-API");
        System.out.println("========================================================================");
        System.out.println("加载日志系统……");
        Logger logger = new Logger();
        setLogger(logger);

        logger.info("==============正在启动==============");
        long timeMillis_1 = System.currentTimeMillis();
        logger.warn("当前版本为测试版本！！！");
        logger.info("读取配置文件……");
        ConfigManager configManager = new ConfigManager();
        configManager.saveConfig();
        setConfig(configManager.readConfig());

        logger.info("初始化数据库……");
        MySQL sql = new MySQL();
        setSql(sql);
        sql.initSQLManager();



        //启动完毕
        long timeMillis_2 = System.currentTimeMillis();
        SimpleDateFormat _formatter = new SimpleDateFormat("ss.s");
        Date _date = new Date(timeMillis_2 - timeMillis_1);
        logger.info("启动成功！（耗时："+_formatter.format(_date)+"秒）");
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
