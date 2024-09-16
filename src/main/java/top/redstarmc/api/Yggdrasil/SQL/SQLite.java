package top.redstarmc.api.Yggdrasil.SQL;

import cc.carm.lib.easysql.EasySQL;
import cc.carm.lib.easysql.api.SQLManager;
import top.redstarmc.api.Yggdrasil.Main;
import top.redstarmc.api.Yggdrasil.util.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
暂时搁置 2024/10/2
 */
public class SQLite {
    private static final Logger logger = Main.getLogger();

    private SQLManager sqlManager;
    private final File config = Main.getConfig();

    /**
     * 初始化数据库
     */
    public void initSQLManager(){
        logger.info("数据库类型：SQLite");
        try {
            File backup = new File("./backups/"+getDateTime()+".db");
            if (backup.exists()) {
                logger.info("[SQLite]文件已经存在");
            } else {
                try {
                    var dir = new File("./backups");
                    if (!dir.exists()) dir.mkdirs();
                    backup.createNewFile();
                    logger.info("[SQLite]文件创建成功");
                } catch (IOException e) {
                    logger.warn("[SQLite]文件创建失败"+e.getMessage());
                }
            }
            String diver = "org.sqlite.JDBC";
            String url = "jdbc:sqlite:./backups/"+getDateTime()+".db";
            //创建连接
            sqlManager = EasySQL.createManager(diver,url,null,null);
        }catch (Exception e){
            logger.fatal("数据库链接失败！！"+e.getMessage());
        }

    }

    /**
     * 备份数据库
     */
    public void backup(){
        logger.info("[异步线程] 正在备份MySQL至SQL数据库……");
        long timeMillis_1 = System.currentTimeMillis();
        //进行备份操作




        long timeMillis_2 = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("ss.s");
        Date date = new Date(timeMillis_2 - timeMillis_1);
        logger.info("[异步线程] 备份MySQL至SQL数据库完成！("+formatter.format(date)+"s）");
    }
    /**
     * 获得日期
     */
    private String getDateTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
