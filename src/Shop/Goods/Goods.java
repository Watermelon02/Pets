package Shop.Goods;

import java.io.Serializable;

public abstract class Goods implements Serializable {//定义商品模板

    private String name;
    private float price;
    private int number;

    public Goods(String name, float price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
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

    public abstract String getInfo();
}


