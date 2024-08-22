package top.redstarmc.api.Yggdrasil.SQL;

import cc.carm.lib.easysql.EasySQL;
import cc.carm.lib.easysql.api.SQLManager;
import org.yaml.snakeyaml.Yaml;
import top.redstarmc.api.Yggdrasil.Main;
import top.redstarmc.api.Yggdrasil.util.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MySQL {
    private SQLManager sqlManager;
    private final File config = Main.getConfig();
    private final Logger logger = Main.getLogger();
    public void initSQLManager(){
        logger.info("数据库类型：MySQL");
        try (InputStream inputStream = new FileInputStream(config)) {
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            Map<String,Object> sqlConfig = (Map<String, Object>) config.get("MySQL");
            String url = (String) sqlConfig.get("url");
            String diver = (String) sqlConfig.get("driver");
            String username = (String) sqlConfig.get("username");
            String password = (String) sqlConfig.get("password");

            logger.info("数据库链接地址："+url);
            try {
                //创建连接
                sqlManager = EasySQL.createManager(diver,url,username,password);
            }catch (Exception e){
                logger.fatal("数据库链接失败！！"+e.getMessage());
            }

        }catch (IOException e){
            logger.fatal("加载数据库时IO异常"+e.getMessage());
        }

        //开始链接
        try {
            if(!sqlManager.getConnection().isValid(5)){
                logger.fatal("数据库链接超时！！！");
            }

        }catch (Exception e){
            logger.fatal("无法链接到数据库！！！");
        }
    }

    /**
     *
     */






}
