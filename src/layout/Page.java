package layout;

import Bag.*;
import CoffeeShop.*;
import java.util.*;

public abstract class Page {
    private Bag bag;
    private CoffeeShop coffee;

    public Page(CoffeeShop coffee, Bag bag){
        this.bag = bag;
        this.coffee = coffee;
    }

    public abstract void show();//显示该界面

    public synchronized void back(CoffeeShop coffee, Bag bag){//返回主界面
        Homepage homepage = new Homepage(coffee, bag);
        homepage.show();
        homepage.get();
    }

    public void get(){//获取用户输入功能
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        while(str != "e"){
            switch(str){
                case "b":{
                    back(coffee,bag);
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

    public synchronized CoffeeShop getCoffeeShop() {
        return this.coffee;
    }

    public synchronized Bag getBag() {
        return this.bag;
    }
}
