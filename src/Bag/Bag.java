package Bag;

import Shop.Goods.Foods;
import Shop.Goods.Goods;

import java.util.ArrayList;

public class Bag {

    private ArrayList<Goods> bag = new ArrayList<Goods>();// 仓库物品动态数组

    public Bag(){
        Foods food = new Foods();
        bag.add(food);// 调用工厂类
        bag.get(0).setNumber(10);
    }

    public synchronized int search(String name) {// 仓库物品搜索功能,返回物品在数组中的索引
        int index = 0;
        for (int i = 0; i < bag.size(); i++) {
            if (bag.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public synchronized void show() {// 展示仓库库存
        System.out.println("一共有" + bag.size() + "个物品");
        if (bag.get(0).getName() != null) {// 如果仓库不为空，展示仓库库存
            for (int i = 0; i < bag.size(); i++) {
                if (bag.get(i).getNumber() > 0) {// 如果该物品数量大于0，显示
                    System.out.println(bag.get(i).getName());
                    System.out.println("物品数量：" + bag.get(i).getNumber());
                    System.out.println(bag.get(i).getInfo());
                } else {// 物品数量小于0，删除
                    bag.remove(i);
                }
            }
        } else {// 仓库为空
            System.out.println("仓库里一无所有");
        }

    }

    public synchronized float add(String name, int number) {// 新增物品功能,返回物品的价格，方便商店扣费
        float price;
        if (bag.get(search(name)) != null) {// 如果仓库里已经有该物品，增加该物品数量
            bag.get(search(name)).setNumber(number);// 增加该物品数量；
            price = bag.get(search(name)).getPrice();// 设置物品价格
        } else {//如果仓库中没有该物品
            bag.add(factory(name));// 调用工厂类
            bag.get(search(name)).setNumber(number);//设置物品数量
            price = bag.get(search(name)).getPrice();// 设置物品价格
        }
        return price;
    }

    public synchronized float readuce (String name, int number) throws Exception{// 减少物品功能,可用于商场贩卖道具，或是日常消耗道具；返回物品的价格，方便商店增加金钱
        float price;
        if(bag.get(search(name)).getNumber() >= number){//如果该物品数量大于减少数量
            bag.get(search(name)).setNumber(-number);
            price = bag.get(search(name)).getPrice()/2;//贩卖价格减半；
        }else{
            throw new Exception(name+"不足，不能进行此操作");//抛出异常
        }
        return price;
    }

    public synchronized Goods factory(String name) {// 工厂类，当增加新商品时，修改此方法
        Goods goods = null;
        switch (name) {
            case "Foods" -> {
                goods = new Foods();
                break;
            }
            default -> System.out.println("错误！！没有该物品类");
        }
        return goods;//
    }

    public synchronized int getSize(){
        return bag.size();
    }

    public synchronized ArrayList<Goods> getBag(){
        return bag;
    }

}