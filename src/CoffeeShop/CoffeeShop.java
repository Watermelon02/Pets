package CoffeeShop;

import Bag.Bag;
import Action.Eater;
import Pets.Pets;
import Action.Washer;
import Action.Worker;
import layout.Homepage;

import java.util.ArrayList;
import java.util.Scanner;

public class CoffeeShop{
    private int day;// 总天数
    private int time;// 一日两档期
    private float money;
    private Bag bag;
    private ArrayList<Pets> pets = new ArrayList<Pets>();// 宠物对象变长数组

    public CoffeeShop(int day,int time,float money,Bag bag){
        this.day = day;
        this.time = time;
        this.money = money;
        this.bag = bag;
    }

    public synchronized void allEat() throws Exception{//所有宠物一起开饭（多线程）,每天结束时自动调用；如果食物不足则会扣除宠物的饱食度
        System.out.println("一天结束了，宠物们开饭了！");
        for (Pets pet : pets) {
            new Thread(new Eater(pet, bag)).start();
        }
    }

    public synchronized void allWash() throws Exception{//消耗时间给所有宠物洗澡
        for(int i=0;i<pets.size();i++){
            new Thread(new Washer(pets.get(i))).start();
        }
        timeChange();
    }

    public void allWork() throws Exception{//咖啡店营业功能
        float total = 0.0f;
        for(int i=0;i<pets.size();i++){
            new Thread(new Worker(this, pets.get(i))).start();
        }
        timeChange();
    }

    public void timeChange() throws Exception{
        System.out.println("请按任意键继续");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (time < 1) {
            time++;// 时间推移
        } else {
            allEat();//每天结束时调用allEat方法
            System.out.println("============================================================================");
            day++;// 总天数增加
            time = 0;
        }
        if(input != null){
            new Homepage(this, bag).show();
        }
    }

    public synchronized int getDay() {
        return day;
    }

    public synchronized int getTime() {
        return time;
    }

    public synchronized float getMoney() {
        return money;
    }

    public synchronized ArrayList getPets(){
        return pets;
    }

    public synchronized void setMoney(float fee){
        this.money += fee;
    }

    public synchronized void setTime(int time){
        this.time += time;
    }

    public synchronized void setDay(int day){
        this.day += day;
    }
}