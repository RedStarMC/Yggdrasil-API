package top.redstarmc.api.Yggdrasil;

import top.redstarmc.api.Yggdrasil.util.Logger;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Main main = new Main();
        Logger logger = new Logger();
        logger.info("测试");
        logger.warn("测试");
        logger.error("测试");

    }

}
