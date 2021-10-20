package Layout;

import java.util.*;

import CoffeeShop.CoffeeShop;
import Bag.*;
import Load.Load;
import Save.Save;

public class Homepage extends Page {

    public Homepage(CoffeeShop coffee, Bag bag) {// 使用父类构造函数初始化对象,后面都用super，getter获取父类中的对象
        super(coffee, bag);
    }

    public synchronized void show() throws Exception{// 展示主页界面，提供选项返回上一界面（主界面）
        System.out.println();
        System.out.println("============================================================================");
        System.out.println("                             ¥ 咖啡店 ¥");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("总金钱:" + super.getCoffeeShop().getMoney());
        System.out.println("现在是第" + super.getCoffeeShop().getDay() + "天" + "的"
                + (super.getCoffeeShop().getTime() == 0 ? "白天" : "黑夜"));
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("1>仓库");
        System.out.println("2>商店");
        System.out.println("3>宠物");
        System.out.println("4>营业");
        System.out.println("5>洗澡");
        System.out.println("6>存档");
        System.out.println("7>读档");
        System.out.println("e>退出");
        System.out.println("============================================================================");
        System.out.println("请输入选项");
        get();
    }

    public void get() throws Exception{// 获取用户输入,根据输入显示下一界面

        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        while (!str.equals("e")) {
            switch (str) {
                case "1": {// 背包界面
                    new BagPage(super.getCoffeeShop(), super.getBag()).show();
                    break;
                }
                case "2": {// 商店界面
                    new ShopPage(super.getCoffeeShop(), super.getBag()).show();
                    break;
                }
                case "3": {// 宠物详情界面
                    new PetsPage(super.getCoffeeShop(), super.getBag()).show();
                    break;
                }
                case "4": {// 咖啡店营业
                    try {
                        super.getCoffeeShop().allWork();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "5": {// 宠物洗澡
                    try {
                        super.getCoffeeShop().allWash();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "6": {//保存功能
                    try {
                        new Save(super.getCoffeeShop(), super.getBag());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new Homepage(super.getCoffeeShop(), super.getBag()).show();
                }
                case "7": {//读取功能
                    try{
                        new Load(super.getCoffeeShop(), super.getBag());//第三个参数为用户输入的存档序号
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    new Homepage(super.getCoffeeShop(), super.getBag()).show();
                }
                default: {
                    System.out.println("没有这个选项，请重新输入");
                    str = input.nextLine();
                }
            }
        }
    }
}


