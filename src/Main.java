import Bag.Bag;
import CoffeeShop.CoffeeShop;
import Pets.Cat;
import layout.Homepage;

import java.util.Scanner;

public class Main {
    static Bag bag = new Bag();// 实例化背包
    static CoffeeShop coffee = new CoffeeShop(1, 0, 100.0f,bag);// 实例化店铺
    public static void main(String[] args) {
        Cat firstPet = new Cat(10, 10, 10, 10, 8, 8,"default");//默认第一只宠物
        System.out.println("欢迎来到你的新店，为你的第一只宠物取个名字吧");
        Scanner scanner = new Scanner(System.in);
        firstPet.setName(scanner.nextLine());//设置初始宠物名称
        coffee.getPets().add(firstPet);
        new Homepage(coffee, bag).show();//显示主界面
    }
}

