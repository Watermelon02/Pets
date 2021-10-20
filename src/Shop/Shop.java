package Shop;

import Bag.Bag;
import CoffeeShop.CoffeeShop;
import Pets.Create;
import Layout.ShopPage;

import java.util.Scanner;

public class Shop {//实现了单例设计模式
    private static Shop shop = new Shop();

    public static Shop getInstance() {
        return shop;
    }

    public synchronized void buyGoods(String name, int number, CoffeeShop coffee, Bag bag) throws Exception{//通过背包中的addGood方法来实现
        float fee;// 购买花费的费用
        fee = bag.addGood(name, number) * number;// 增加number个该物品并计算费用
        if (coffee.getMoney() >= fee) {
            coffee.setMoney(-fee);// 扣钱
            System.out.println("成功购买" + number + "个" + name);
            new ShopPage(coffee, bag).show();// 回到商店界面
        } else {
            try {
                bag.reduce(name, number);
                System.out.println("钱钱不够买" + number + "个" + name + "TvT");
                new ShopPage(coffee, bag).show();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public synchronized void buyPets(int species, CoffeeShop coffee, Bag bag) throws Exception{
        if (coffee.getMoney() >= 100) {
            System.out.println("");
            System.out.println("请给这只" + (species == 1 ? "猫猫" : "狗狗") + "起一个新名字");
            System.out.println("");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            new Create(coffee, name, species);//
            coffee.setMoney(-100);// 扣费
            System.out.println("");
            System.out.println("现在您有了一只新" + (species == 1 ? "猫猫" : "狗狗") + name);
            System.out.println("");
            new ShopPage(coffee, bag).show();
        } else {
            System.out.println("");
            System.out.println("钱钱不够TVT");
            System.out.println("");
            new ShopPage(coffee, bag).show();
        }
    }
}