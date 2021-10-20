import Bag.Bag;
import CoffeeShop.CoffeeShop;
import Load.Load;
import Pets.Cat;
import Pets.Pets;
import Layout.Homepage;

import java.util.Scanner;

public class Main {
    static Bag bag = new Bag();// 实例化背包
    static CoffeeShop coffee = new CoffeeShop(1, 0, 100.0f, bag);// 实例化店铺

    public static void main(String[] args) throws Exception {
        System.out.println("游戏启动！");
        System.out.println("你要：");
        System.out.println("1>新开游戏");
        System.out.println("2>载入存档");
        switch (new Scanner(System.in).next()) {
            case "1":
                newGame();
            case "2":
                loadGame();
            default:
                System.out.println("没有这个选项，请重新输入");
        }
    }

    public static void newGame() throws Exception{
        Pets firstPet = new Cat(10, 10, 10, 10, 8, 8, "default");//默认第一只宠物
        System.out.println("欢迎来到你的新店，为你的第一只宠物取个名字吧");
        Scanner scanner = new Scanner(System.in);
        firstPet.setName(scanner.nextLine());//设置初始宠物名称
        coffee.getPets().add(firstPet);
        new Homepage(coffee, bag).show();//显示主界面
    }

    public static void loadGame() throws Exception {
        new Load(coffee, bag);//调用读档方法
    }
}

