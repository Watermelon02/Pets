package Shop.Goods;
import Shop.*;
public class Foods extends Goods{
    public Foods(){
        super("Foods", 5, 0);
    }
    public String getInfo(){
        String info = "食物，宠物每天会吃饭消耗食物并补充饥饿度";
        return info;
    }
}