package top.redstarmc.api.Yggdrasil.SQL;

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
            String diver = (String) config.get("MySQL.driver");
            String url = (String) config.get("MySQL.url");
            String username = (String) config.get("MySQL.username");
            String password = (String) config.get("MySQL.password");

            logger.info("数据库链接地址："+url);

        }catch (IOException e){
            logger.error("加载数据库时IO异常"+e.getMessage());
        }
    }

}
