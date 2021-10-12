package Load;

import Bag.Bag;
import CoffeeShop.CoffeeShop;
import Pets.*;
import Shop.Goods.Goods;
import layout.Homepage;

import java.io.*;
import java.util.Scanner;

public class Load {
    private static CoffeeShop coffee;
    private static Bag bag;
    private File current = new File(this.getClass().getResource(File.separator).getPath() + File.separator + "Save" + File.separator + "Archives");//当前文件
    private static String path;

    public Load(CoffeeShop coffee, Bag bag) throws Exception {
        this.coffee = coffee;
        this.bag = bag;
        System.out.println("有以下存档");
        for (File file : new File(this.getClass().getResource(File.separator).getPath() + File.separator + "Save" + File.separator + "Archives").listFiles()) {
            System.out.println("存档:" + file.getName());//初始化存档路径
        }
        System.out.println("请输入要读取的存档序号");
        path = current.getPath() + File.separator + new Scanner(System.in).next();

        if (new File(path).exists()) {
            System.out.println("读取存档ing");
            loadDate();
            loadMoney();
            loadPets();
            loadBag();
            Thread.sleep(1000);
        } else {
            System.out.println();
            System.out.println("没有这个存档！");
            System.out.println();
            Thread.sleep(2000);
        }
        new Homepage(coffee, bag).show();//重新回到主界面
    }

    public void loadDate() throws Exception {
        File file = new File(path + File.separator + "date.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        int date[] = (int[]) ois.readObject();
        coffee.setDay(-coffee.getDay()+date[0]);//因为setter的定义问题，先减去原来的值
        coffee.setTime(-coffee.getTime()+date[1]);
        ois.close();
    }

    public void loadMoney() throws Exception {
        File file = new File(path + File.separator + "money.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        coffee.setMoney(-coffee.getMoney() + (float) ois.readObject());//因为setter的定义问题，先减去原来的money
        ois.close();
    }

    public void loadPets() throws Exception {
        File file = new File(path + File.separator + "pets.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object pets[] = (Object[]) ois.readObject();
        for(int i=0;i<pets.length;i++){
            Pets pet = (Pets) pets[i];
            if(pet.getSpecies()==1){//如果是猫
                coffee.getPets().add((Cat)pet);
            }else {//否则为狗
                coffee.getPets().add((Dog)pet);
            }
        }
        ois.close();
    }

    public void loadBag() throws Exception {
        File file = new File(path + File.separator + "bag.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object goods[] = (Object[]) ois.readObject();
        for(int i = 0;i<goods.length;i++){
            Goods good = (Goods) goods[i];
            new Bag().addGood(good.getName(),good.getNumber());//调用工厂类来自动增加对应类型的物品
        }
        ois.close();
    }
}
