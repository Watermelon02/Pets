package Shop.Goods;

public class Keys extends Goods{
    public Keys(){
        super("keys", 0, 1);
    }
    public String getInfo(){
        String info = "食物，宠物每天会吃饭消耗食物并补充饥饿度";
        return info;
    }
}
