package Pets;

import Pets.*;
import CoffeeShop.*;
import Bag.*;

public class Cat extends Pets {

    public Cat(int hp, int hunger, int cleanliness, int mood, int loveliness, int sp, String name) {
        super(hp, hunger, cleanliness, mood, loveliness, sp, name);// 后面还要加上毛色，眼珠颜色，耳朵等更多细节；
        super.setSpecies(1);
    }

    public float work() {
        float money = 0;// 营业所能赚到的钱
        int negative = 0;// 负面值，降低该宠物营业所能增加的money
        if (super.getCleanliness() < 4) {
            negative += 1;
        }
        if (super.getMood() < 4) {
            negative += 1;
        }
        return money = super.getLoveliness() - negative;
    }

    // public void eat(Bag bag) {//为实现多线程，该方法被Eater取代
    // int number = bag.getBag().get(bag.search("Foods")).getNumber();//剩余食物数量
    // if(number >= 1 && super.getHunger() < 10){// 当剩余食物并饱食度不满时，消耗一个食物并增加一点饥饿度
    // bag.getBag().get(bag.search("Foods")).setNumber(-1);
    // super.setHunger(1);
    // System.out.println(this.getName()+"好好吃饭增加了一点饱食度");
    // }else if(number >= 1 && super.getHunger() == 10){
    // bag.getBag().get(bag.search("Foods")).setNumber(-1);
    // System.out.println(this.getName()+"吃得肚子鼓鼓的");
    // }else if(number <= 0){//食物不足
    // super.setHunger(-1);
    // System.out.println("食物不够了，"+this.getName()+"没有吃饭而减少了一点饱食度");
    // }
    // };// 为实现多线程，该方法被Eater取代

    public void play() {
        // 扣除时间
        if (super.getMood() <= 10) {
            super.setMood(4);
        } // 增加心情
    };// 玩

}
