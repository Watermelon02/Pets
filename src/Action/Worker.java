package Action;

import CoffeeShop.*;
import Pets.Pets;

import java.util.Random;

public class Worker implements Runnable{
    private Pets pet;
    private CoffeeShop coffee;
    private float fee;// 营业额
    private Random random = new Random();

    public Worker(CoffeeShop coffee, Pets pet) {
        this.coffee = coffee;
        this.pet = pet;
    }

    public void run() {
        if (pet.getHunger() > 0) {// 饥饿度不为0
            int roll = random.nextInt() % 20;// 随机事件值
            while (roll <= 0) {
                roll = random.nextInt() % 20;
            }
            if (roll == 0) {// 被乱rua事件
                pet.setMood(-1);
                fee = (pet.getLoveliness() * 2) - ((10 - pet.getCleanliness()) / 5) - ((10 - pet.getMood()) / 10);
                // 营业额=（可爱度x2 -(不清洁度/5)-（不好心情/10））
                coffee.setMoney(fee);
                System.out.println(pet.getName() + "被粗暴的客人狠狠地rua来rua去，心情值-2 o(≧口≦)o");
            } else if (roll == 1) {// 被弄脏事件
                pet.setCleanliness(-1);
                fee = (pet.getLoveliness() * 2) - ((10 - pet.getCleanliness()) / 5) - ((10 - pet.getMood()) / 10);
                System.out.println(pet.getName() + "上蹿下跳地玩耍弄得自己一身灰尘，清洁度-2 (* ~︿~)");
            } else if (roll == 2) {//扣钱事件
                fee = -30;
                System.out.println(pet.getName() + "打翻了客人的饮品,为客人进行了赔偿，钱钱-30 (艹皿艹 )");
            }
            else if (roll > 2 && roll < 18) {// 安稳地在某个地点度过事件
                fee = (pet.getLoveliness() * 2) - ((10 - pet.getCleanliness()) / 5) - ((10 - pet.getMood()) / 10);
                System.out.println(pet.getName() + "在" + getPlace() + "渡过了懒散的一段时光 ( >ρ < ”)");
            } else if(roll == 18){//额外收入事件
                fee = (pet.getLoveliness() * 3) - ((10 - pet.getCleanliness()) / 5) - ((10 - pet.getMood()) / 10);
                System.out.println(pet.getName()+"遇见了慷慨的客人，获得额外收入 ¥v¥");
            } else if(roll == 19){//增加心情事件
                if(pet.getMood()<10){
                    pet.setMood(1);
                    fee = (pet.getLoveliness() * 2) - ((10 - pet.getCleanliness()) / 5) - ((10 - pet.getMood()) / 10);
                    System.out.println(pet.getName()+"今天运气满满，心情+1 (๑•̀ㅂ•́)و✧");
                }else{
                    fee = (pet.getLoveliness() * 2) - ((10 - pet.getCleanliness()) / 5) - ((10 - pet.getMood()) / 10);
                    System.out.println(pet.getName()+"今天运气满满，心情大好 (๑•̀ㅂ•́)و✧");
                }
            }
        }

        float extra = random.nextInt() %3 ;// 随机收入浮动值
            while (extra <= 0) {
                extra = random.nextInt() % 3;
            }
        coffee.setMoney(fee+extra);//增加/减少money
        System.out.println(pet.getName()+"为你带来了$"+(fee+extra)+"收入");
    }

    public String getPlace() {// 获取随机地点方法
        int place;
        String str = "test";
        Random r = new Random();
        place = r.nextInt()%10;
        while(place<0){
            place = r.nextInt()%10;
        }
        switch (place) {
            case 0:
                str = "阳台";
                break;
            case 1:
                str = "沙发上";
                break;
            case 2:
                str = "桌子上";
                break;
            case 3:
                str = "桌子下";
                break;
            case 4:
                str = "楼梯上";
                break;
            case 5:
                str = "墙边";
                break;
            case 6:
                str = "吧台";
                break;
            case 7:
                str = "橱窗";
                break;
            case 8:
                str = "门口";
                break;
            case 9:
                str = "房檐下";
                break;
        }
        return str;
        
    }
}


