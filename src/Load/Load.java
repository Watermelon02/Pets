package Load;

import Bag.Bag;
import CoffeeShop.CoffeeShop;
import Databases.Connector;
import Pets.*;
import Shop.Goods.Goods;
import Layout.Homepage;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class Load {
    private static CoffeeShop coffee;
    private static Bag bag;

    public Load(CoffeeShop coffee, Bag bag) throws Exception {
        this.coffee = coffee;
        this.bag = bag;
        Connection connection = new Connector("root", "ai1wei2xi3").getConnection();//获得数据库connection实例

        int total = 0;//总存档数
        ResultSet rs = connection.createStatement().executeQuery("SELECT id,date FROM petscoffee");//输出已有存档编号及存档时间
        System.out.println("有以下存档");
        while (rs.next()) {
            System.out.println("存档：" + rs.getInt(1) + "  存档时间：" + rs.getString(2));
            total++;
        }

        System.out.println("请输入要读取的存档序号");
        int position = new Scanner(System.in).nextInt();
        if (position <= total) {
            System.out.println("读取存档ing");

            ResultSet resultSet = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT day,time,money,pets,bag FROM petscoffee WHERE id=" + String.valueOf(position));
           while (resultSet.next()){//执行读取各项数据操作
               coffee.setDay(resultSet.getInt(1)-coffee.getDay());//读取day,因为setter的定义问题，先减去原来的值
               coffee.setTime(resultSet.getInt(2)-coffee.getTime());//读取time,因为setter的定义问题，先减去原来的值
               coffee.setMoney(resultSet.getFloat(3)-coffee.getMoney());//读取money
               //以下将数据库中的数据读到一个byte数组中，通过ByteArrayInputStream传入ObjectInputStream中，从ObjectInputStream读出object对象并强制转换成Pets【】
               coffee.getPets().removeAll(coffee.getPets());//先删除原来ArrayList中的所有数据
               byte[] data = resultSet.getBytes(4);
               ByteArrayInputStream bais = new ByteArrayInputStream(data);
               ObjectInputStream ois = new ObjectInputStream(bais);
               Object pets[] = (Object[]) ois.readObject();
               for (int i = 0; i < pets.length - 1; i++) {//读取pets[]
                   Pets pet = (Pets) pets[i];
                   if (pet.getSpecies() == 1) {//如果是猫
                       coffee.getPets().add((Cat) pet);
                   } else {//否则为狗
                       coffee.getPets().add((Dog) pet);
                   }
               }
               //以下读取背包中的物品，并将其加入背包
               bag.getBag().removeAll(bag.getBag());//先删除原来ArrayList中的所有数据
               data = resultSet.getBytes(5);
               bais = new ByteArrayInputStream(data);
               ois = new ObjectInputStream(bais);
               Object goods[] = (Object[]) ois.readObject();
               for (int i = 0; i < goods.length - 1; i++) {
                   Goods good = (Goods) goods[i];
                   new Bag().addGood(good.getName(), good.getNumber());//调用工厂类来自动增加对应类型的物品
               }
               ois.close();
               bais.close();
           }
            rs.close();
            connection.close();
            new Homepage(coffee, bag).show();
        } else {
            System.out.println();
            System.out.println("没有这个存档！");
            System.out.println();
        }
    }
}
