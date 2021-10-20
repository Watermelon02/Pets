package Layout;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class SetPath {

    public static void main(String[] args) throws Exception {//用于创建goods.properties的方法
        Class<SetPath> cla = SetPath.class;
        String propertiesPath = cla.getResource(File.separator).getPath().split("out/production/PetsCoffee/")[0] + "src/Layout/goods.properties";
        File f = new File(propertiesPath);
        Properties pro = new Properties();
        pro.setProperty("1", "Foods|$:5|食物，宠物每天会吃饭消耗食物并补充饥饿度");
        pro.setProperty("2", "Cat|$:2000|领养一只新的猫猫，更多宠物，更多毛毛，更多钱钱");
        pro.setProperty("3", "Dog|$:2000|领养一只新的狗狗，更多宠物，更多毛毛，更多钱钱");
        pro.store(new FileOutputStream(f), "商店界面展示的货物信息");
    }
}
