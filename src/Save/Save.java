package Save;

import Bag.Bag;
import CoffeeShop.CoffeeShop;
import layout.Homepage;
import layout.Page;

import java.io.*;
import java.util.Scanner;

public class Save {
    private static CoffeeShop coffee;
    private static Bag bag;
    private File current = new File(this.getClass().getResource(File.separator).getPath() + File.separator + "Save" + File.separator + "Archives");//当前文件
    private static String path;

    public Save(CoffeeShop coffee, Bag bag) throws Exception {
        this.coffee = coffee;
        this.bag = bag;

        System.out.println("有以下存档");
        for (File file : new File(this.getClass().getResource(File.separator).getPath() + File.separator + "Save" + File.separator + "Archives").listFiles()) {
            System.out.println("存档:" + file.getName());
        }
        System.out.println("请输入要保存的存档序号");
        path = current.getPath() + File.separator + new Scanner(System.in).next();//初始化存档路径
        
        if (new File(path).exists()) {
            System.out.println("确认覆盖该存档？1>yes/2>no");
            if (new Scanner(System.in).next() == "1") {
                new File(path+"date.txt").delete();
                new File(path+"money.txt").delete();
                new File(path+"pets.txt").delete();
                new File(path+"bag.txt").delete();//先删除原来的数据
                saveDate();//再重新保存所以数据
                saveBag();
                savePets();
                saveMoney();
                System.out.println("进度保存中ing");
                System.out.println();
                Thread.sleep(1000);
                new Homepage(coffee, bag).get();
            } else {
                new Homepage(coffee, bag).get();
                System.out.println("正在返回主页");
                Thread.sleep(1000);
            }
        } else {
            System.out.println("创建新存档ing");
            new File(path).mkdir();
            saveDate();
            saveBag();
            savePets();
            saveMoney();
            Thread.sleep(1000);
            System.out.println("进度已保存");
        }
        System.out.println(path);
    }

    public void saveDate() throws Exception {
        int date[] = {coffee.getDay(),coffee.getTime()};
        File file = new File(path + File.separator + "date.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(date);
        oos.close();
    }

    public void saveMoney() throws Exception {
        File file = new File(path + File.separator + "money.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject((Object) coffee.getMoney());
        oos.close();
    }

    public void savePets() throws Exception {
        File file = new File(path + File.separator + "pets.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject((Object[]) coffee.getPets().toArray());
        oos.close();
    }

    public void saveBag() throws Exception {
        File file = new File(path + File.separator + "bag.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject((Object[]) bag.getBag().toArray());
        oos.close();
    }
}
