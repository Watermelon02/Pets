package Shop.Goods;

public class Keys extends Goods{
    public Keys(){
        super("Keys", 0, 1);
    }
    public String getInfo(){
        String info = "店铺的钥匙，小心保管";
        return info;
    }
}
