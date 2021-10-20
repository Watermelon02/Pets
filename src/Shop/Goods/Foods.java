package Shop.Goods;

import Shop.*;

public class Foods implements Goods {
    private String name;
    private float price;
    private int number;

    public Foods(){
        this.name = "Foods";
        this.price = 5;
        this.number = 0;
    }

    public String getInfo() {
        String info = "食物，宠物每天会吃饭消耗食物并补充饥饿度";
        return info;
    }

    public void setNumber(int number) {
        this.number += number;
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getNumber() {
        return this.number;
    }
}