package layout;

import CoffeeShop.CoffeeShop;
import Shop.*;
import Bag.*;
import java.util.*;
public class ShopPage extends Page{
    private Shop shop = new Shop(super.getCoffeeShop(), super.getBag());//实例化商店对象，以调用其功能

    public ShopPage(CoffeeShop coffee, Bag bag){
        super(coffee,bag);
    }
    public void show(){//商店界面
        System.out.println();
        System.out.println("============================================================================");
        System.out.println("                             $ 商店 $");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("1>Foods");//第一项商品foods
        System.out.println("$:5");
        System.out.println("宠物们的食物，宠物每天会消耗食物来补充饥饿度");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("2>Cat");
        System.out.println("$:2000");
        System.out.println("领养一只新的猫猫，更多宠物，更多毛毛，更多钱钱");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("3>Dog");
        System.out.println("$:2000");
        System.out.println("领养一只新的狗狗，更多宠物，更多毛毛，更多钱钱");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("============================================================================");
        System.out.println();
        System.out.println("请输入下一步操作");
        System.out.println("b>返回");
        get();// 获取输入
    }

    public void get(){//获取用户输入功能
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        while(str != "e"){
            switch(str){
                case "1":{//买食物
                    System.out.println("请输入想要购买的数量：");
                    int number = input.nextInt();
                    shop.buyGoods("Foods", number);
                    break;
                }
                case "2":{//买猫猫
                    shop.buyPets(1);
                    break;
                }
                case "3":{//买狗狗
                    shop.buyPets(2);
                    break;
                }
                case "b":{
                    super.back(super.getCoffeeShop(),super.getBag());//返回主界面
                    break;
                }
                default:{
                    System.out.println("没有这个选项，请重新输入");
                    str = input.nextLine();
                    continue;
                }
            }
        }
    }
}
