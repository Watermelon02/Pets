package Layout;

import CoffeeShop.CoffeeShop;
import Shop.*;
import Bag.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
public class ShopPage extends Page{
    private Shop shop = Shop.getInstance();//实例化商店对象，以调用其功能

    public ShopPage(CoffeeShop coffee, Bag bag){
        super(coffee,bag);
    }
    public void show() throws Exception {//商店界面
        Properties pro = new Properties();
        File f = new File("/Users/xigua/Desktop/PetsCoffee/src/Layout/goods.properties");//保存商店界面展示的货物信息路径
        try{
            pro.load(new FileInputStream(f));//读取properties文件
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("============================================================================");
        System.out.println("                             $ 商店 $");
        System.out.println("----------------------------------------------------------------------------");
        for (int i = 1;i< pro.size()+1;i++){
            System.out.println(String.valueOf(i)+">"+pro.getProperty(String.valueOf(i)));//输出properties中的商品信息
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("============================================================================");
        System.out.println();
        System.out.println("请输入下一步操作");
        System.out.println("b>返回");
        get();// 获取输入
    }

    public void get() throws Exception{//获取用户输入功能
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        while(!str.equals("e")){
            switch(str){
                case "1":{//买食物
                    System.out.println("请输入想要购买的数量：");
                    int number = input.nextInt();
                    shop.buyGoods("Foods", number,super.getCoffeeShop(),super.getBag());
                    break;
                }
                case "2":{//买猫猫
                    shop.buyPets(1,super.getCoffeeShop(),super.getBag());
                    break;
                }
                case "3":{//买狗狗
                    shop.buyPets(2,super.getCoffeeShop(),super.getBag());
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
