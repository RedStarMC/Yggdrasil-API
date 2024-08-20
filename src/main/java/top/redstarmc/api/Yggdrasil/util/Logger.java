package top.redstarmc.api.Yggdrasil.util;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public Logger(){}

    public void info(String string){
        String time = getTime();
        String info ="\u001B[97m["+time+"] "+"[\u001B[92mINFO"+"\u001B[97m] "+string;
        String log = "["+time+"] "+"[INFO] "+string;
        System.out.println(info);
        writeFile(startFile(),log);
    }
    public void warn(String string){
        String time = getTime();
        String warn ="\u001B[97m["+time+"] "+"[\u001B[93mWARN"+"\u001B[97m] "+"\u001B[93m"+string;
        String log = "["+time+"] "+"[WARN] "+string;
        System.out.println(warn);
        writeFile(startFile(),log);
    }
    public void error(String string){
        String time = getTime();
        String error ="\u001B[97m["+time+"] "+"[\u001B[91mERROR"+"\u001B[97m] "+"\u001B[91m"+string;
        String log = "["+time+"] "+"[ERROR] "+string;
        System.out.println(error);
        writeFile(startFile(),log);
    }



    private String getDateTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
    private String getTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
    private File startFile(){
        File dirs = new File("./logs");
        if (!dirs.exists()){
            boolean dr = dirs.mkdirs();
        }
        File file = new File("./logs/"+getDateTime()+"--log.txt");
        if(file.exists()) {
            return file;
        }else {
            try {
                file.createNewFile();
                return file;
            } catch (Exception e) {
                System.out.println("警告！日志IO异常"+e.getMessage());
            }
        }
        return null;
    }

    private void writeFile(File file,String str){
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(str);
                writer.newLine();
            } catch (Exception e) {
                System.out.println("警告！日志写入异常" + e.getMessage());
            }
        }catch (Exception e){
            System.out.println("警告！日志写入异常"+e.getMessage());
        }
    }
}
