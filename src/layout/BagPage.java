package layout;

import Bag.Bag;
import CoffeeShop.CoffeeShop;

public class BagPage extends Page {
    public BagPage(CoffeeShop coffee, Bag bag) {
        super(coffee, bag);
    }

    public void show() {// 仓库界面
        System.out.println();
        System.out.println("============================================================================");
        System.out.println("                             o 仓库 o");
        System.out.println("----------------------------------------------------------------------------");
        super.getBag().show();// 仓库库存展示
        System.out.println("============================================================================");
        System.out.println("请输入下一步操作");
        System.out.println("b>返回");
        super.get();// 获取输入
    }

}
