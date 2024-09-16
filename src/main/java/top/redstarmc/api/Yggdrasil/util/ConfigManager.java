package top.redstarmc.api.Yggdrasil.util;

import top.redstarmc.api.Yggdrasil.Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ConfigManager {
    private static File Config = null;
    private final Logger logger = Main.getLogger();
    String config = """
            SQLType: mysql
            MySQL:
              driver: com.mysql.cj.jdbc.Driver
              url: jdbc:mysql://localhost:3306/test?useUnicode=true&allowPublicKeyRetrieval=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false
              username: root
              password: "root"

            API-Server:""";
    public void saveConfig(){
        File file = new File("./config.yml");
        if(file.exists()) {
            Config = file;
        }else {
            try {
                file.createNewFile();
                Config = file;
                logger.info("第一次使用，正在生成配置文件");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(config);
                    writer.newLine();
                logger.info("成功生成配置文件");
                } catch (Exception e) {
                   logger.fatal("生成配置文件异常" + e.getMessage());
                }
            } catch (Exception e) {
                logger.fatal("生成配置文件异常"+e.getMessage());
            }
        }
    }

    public File readConfig(){
        return Config;
    }

}
