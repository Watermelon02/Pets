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

        if (current.listFiles().length > 0) {//如果存档位不为空,输出存档的名字和保存时间
            System.out.println("有以下存档");
            for (File file : current.listFiles()) {
                if(file.isDirectory()){
                    char c[] = new char[1024];
                    int len = 0;
                    len = new InputStreamReader(new FileInputStream(new File(file.getPath() + File.separator + "date.txt"))).read(c);//获取存档保存时的时间
                    System.out.println(new StringBuilder().append("存档:").append(file.getName()).append("；存储时间:").append(new String(c, 0, len)).toString());//输出存档名和存档时间
                }
            }
        }
        System.out.println("请输入要保存的存档序号");
        path = current.getPath() + File.separator + new Scanner(System.in).next();//初始化存档路径

        if (new File(path).exists()) {
            System.out.println("读取存档ing");
            loadTime();
            loadMoney();
            loadPets();
            loadBag();
            Thread.sleep(1000);
            new Homepage(coffee,bag).show();
        } else {
            System.out.println();
            System.out.println("没有这个存档！");
            System.out.println();
            Thread.sleep(2000);
        }
    }

    public void loadTime() throws Exception {
        File file = new File(path + File.separator + "time.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        int date[] = (int[]) ois.readObject();
        coffee.setDay(-coffee.getDay() + date[0]);//因为setter的定义问题，先减去原来的值
        coffee.setTime(-coffee.getTime() + date[1]);
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
        coffee.getPets().removeAll(coffee.getPets());
        for (int i = 0; i < pets.length; i++) {
            Pets pet = (Pets) pets[i];
            if (pet.getSpecies() == 1) {//如果是猫
                coffee.getPets().add((Cat) pet);
            } else {//否则为狗
                coffee.getPets().add((Dog) pet);
            }
        }
        ois.close();
    }

    public void loadBag() throws Exception {
        File file = new File(path + File.separator + "bag.txt");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object goods[] = (Object[]) ois.readObject();
        for (int i = 0; i < goods.length; i++) {
            Goods good = (Goods) goods[i];
            new Bag().addGood(good.getName(), good.getNumber());//调用工厂类来自动增加对应类型的物品
        }
        ois.close();
    }
}
