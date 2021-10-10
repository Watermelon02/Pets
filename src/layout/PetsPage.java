package layout;

import Bag.Bag;
import CoffeeShop.CoffeeShop;
import Pets.Pets;

import java.util.Collections;

public class PetsPage extends Page {

    public PetsPage(CoffeeShop coffee, Bag bag) {
        super(coffee, bag);
    }

    public void show() {// 宠物界面
        System.out.println();
        System.out.println("============================================================================");
        System.out.println("                            0v0 宠物 (๑•̀ㅂ•́)و✧");
        System.out.println("----------------------------------------------------------------------------");
        Collections.sort(super.getCoffeeShop().getPets());
        for (int i = 0; i < super.getCoffeeShop().getPets().size(); i++) {// 依次打印宠物店中各个宠物的信息
            Pets pet = (Pets) super.getCoffeeShop().getPets().get(i);
            String species = pet.getSpecies() == 1 ? "Cat" : "Dog";// 三目运算符判断物种
            System.out.println(species + "｜" + pet.getName() + "的状态");
            System.out.println("生命值：" + pet.getHp());
            System.out.println("饥饿度：" + pet.getHunger());
            System.out.println("清洁度：" + pet.getCleanliness());
            System.out.println("心情值：" + pet.getMood());
            System.out.println("可爱度：" + pet.getLoveliness());
            System.out.println("特殊值：" + pet.getSp());
            System.out.println("--------------------------------------------");
        } // 调用宠物信息展示功能
        System.out.println("============================================================================");
        System.out.println();
        System.out.println("请输入下一步操作");
        System.out.println("b>返回");
        super.get();// 获取输入
    }

}
