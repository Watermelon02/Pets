package Shop.Goods;

import java.io.Serializable;

public interface Goods extends Serializable {//定义商品模板
    void setNumber(int number);
    String getInfo();
    String getName();
    float getPrice();
    int getNumber();
}


