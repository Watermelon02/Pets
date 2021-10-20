package Shop.Goods;

public class Keys implements Goods {
    private String name;
    private float price;
    private int number;

    public Keys() {
        this.name = "Keys";
        this.price = 0;
        this.number = 1;
    }

    public String getInfo() {
        String info = "店铺的钥匙，小心保管";
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
