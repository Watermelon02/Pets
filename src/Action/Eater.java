package Action;

import Bag.Bag;
import Pets.Pets;

public class Eater implements Runnable {// 实现多宠物同时开饭
    private Pets pet;
    private Bag bag;

    public Eater(Pets pet, Bag bag) {
        this.pet = pet;
        this.bag = bag;
    }

    public void run() {
        int number = bag.getBag().get(bag.search("Foods")).getNumber();// 剩余食物数量
        if (number >= 1 && pet.getHunger() < 10) {// 当剩余食物并饱食度不满时，消耗一个食物并增加一点饱食度和一点心情
            bag.getBag().get(bag.search("Foods")).setNumber(-1);
            pet.setHunger(1);
            pet.setMood(1);
            System.out.println(pet.getName() + "好好吃饭并增加了一点饱食度 OvO");
        } else if (number >= 1 && pet.getHunger() == 10) {// 食物充足且饱食度满，消耗食物不增加饱食度,恢复一点健康度和两点心情
            bag.getBag().get(bag.search("Foods")).setNumber(-1);
            if (pet.getHp() < 10) {
                pet.setHp(1);
                pet.setMood(2);
                System.out.println(pet.getName() + "吃得肚子鼓鼓的,并恢复了一点健康度 OvO");//hp不满
            } else {
                System.out.println(pet.getName() + "吃得肚子鼓鼓的 OvO");//hp满
            }
        } else if (number < 1 && pet.getHunger() > 2) {// 食物不足
            pet.setHunger(-1);
            System.out.println("食物不够了，" + pet.getName() + "没有吃饭而减少了一点饱食度 TvT");
        } else if (number < 1 && pet.getHunger() <= 2 && pet.getHunger() > 0) {//食物不足且饱食度小于2
            pet.setHunger(-1);
            pet.setHp(-1);
            pet.setMood(-1);
            System.out.println(pet.getName() + "已经饿得不行了," + pet.getName() + "开始扣除健康度 TvT");
        } else if (number <= 0 && pet.getHunger() == 0) {
            pet.setHp(-2);
            pet.setMood(-3);
            System.out.println(pet.getName() + "已经饿得瘫倒了");//宠物不能工作
        }
        // 吃

    }
}
