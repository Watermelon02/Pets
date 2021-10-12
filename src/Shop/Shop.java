package Shop;

import Bag.Bag;
import CoffeeShop.CoffeeShop;
import Pets.Create;
import layout.ShopPage;

import java.util.Scanner;

public class Shop {
    private Bag bag;
    private CoffeeShop coffee;

    public Shop(CoffeeShop coffee, Bag bag) {// 构造方法接收商店中的金钱和背包对象，在购买商品后返回剩余的金钱
        this.bag = bag;
        this.coffee = coffee;
    }

    public synchronized void buyGoods(String name, int number) {
        float fee;// 购买花费的费用
        fee = bag.addGood(name, number) * number;// 增加number个该物品并计算费用
        if (coffee.getMoney() >= fee) {
            coffee.setMoney(-fee);// 扣钱
            System.out.println("成功购买" + number + "个" + name);
            new ShopPage(coffee, bag).show();// 回到商店界面
        } else {
            try {
                bag.reduce(name, number);
                System.out.println("钱钱不够买" + number + "个" + name+"TvT");
                new ShopPage(coffee, bag).show();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
            }
        }
    }

    public synchronized void buyPets(int species) {
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