package Save;

import Bag.Bag;
import CoffeeShop.CoffeeShop;
import layout.Homepage;
import layout.Page;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Save {
    private static CoffeeShop coffee;
    private static Bag bag;
    private File current = new File(this.getClass().getResource(File.separator).getPath() + File.separator + "Save" + File.separator + "Archives");//当前文件
    private static String path;

    public Save(CoffeeShop coffee, Bag bag) throws Exception {
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
            System.out.println("确认覆盖该存档？1>yes/2>no");
            if (new Scanner(System.in).nextInt() == 1) {
                boolean i=new File(path + File.separator + "time.txt").delete();
                new File(path + File.separator + "date.txt").delete();
                new File(path + File.separator + "money.txt").delete();
                new File(path + File.separator + "pets.txt").delete();
                new File(path + File.separator + "bag.txt").delete();
                new File(path).delete();//先删除原来的数据
                new File(path).mkdir();//再重新保存所有数据
                saveTime();
                saveBag();
                savePets();
                saveMoney();
                saveDate();
                System.out.println("进度保存中ing");
                System.out.println();
            } else {
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
            saveTime();
            Thread.sleep(1000);
            System.out.println("进度已保存");
        }
        new Homepage(coffee, bag).show();
    }

    public void saveTime() throws Exception {//保存游戏中的day和time属性
        int date[] = {coffee.getDay(), coffee.getTime()};
        File file = new File(path + File.separator + "time.txt");
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

    public void saveDate() throws Exception {//保存存档时的时间
        File file = new File(path + File.separator + "date.txt");
        String pattern = "yyyy年MM月dd日HH时mm分ss秒";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String str = sdf.format(new Date());
        PrintStream ps =  new PrintStream(new FileOutputStream(file));
        ps.println(str);
        ps.close();
    }
}
