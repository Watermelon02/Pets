package Pets;

import java.io.Serializable;

abstract public class Pets implements Comparable<Pets>, Serializable {
    private int hp;
    private int hunger;// 饥饿度
    private int cleanliness;// 清洁度
    private int mood;// 心情
    private int loveliness;// 可爱度
    private int sp;// 特殊值
    private String name;// 名字
    private int species;//物种属性,1为猫，2为狗

    public abstract void play();// 逗

    public abstract float work();

    public Pets(int hp, int hunger, int cleanliness, int mood, int loveliness, int sp, String name) {
        this.hp = hp;
        this.hunger = hunger;
        this.cleanliness = cleanliness;
        this.mood = mood;
        this.loveliness = loveliness;
        this.name = name;
    }

    public  int compareTo(Pets pet) {
        return Integer.compare(this.loveliness,pet.loveliness);
    }

    public int getHp() {
        return this.hp;
    }

    public int getHunger() {
        return this.hunger;
    }

    public int getCleanliness() {
        return this.cleanliness;
    }

    public int getMood() {
        return this.mood;
    }

    public int getLoveliness() {
        return this.loveliness;
    }

    public int getSp() {
        return this.sp;
    }

    public String getName() {
        return this.name;
    }

    public int getSpecies(){
        return species;
    }

    public void setHp(int num) {
        this.hp = this.hp + num;
    }

    public void setHunger(int num) {
        if(num >0){
            if(hunger+ num<=10){
                hunger += num;
            }else{
                hunger = 10;
            }
        }else{
            if(hunger + num >= 0){
                hunger += num;
            }else{
                hunger = 0;
            }
        }
    }

    public void setCleanliness(int num) {
        this.cleanliness = this.cleanliness + num;
    }

    public void setMood(int num) {
        if(num >0){
            if(mood + num<=10){
                mood += num;
            }else{
                mood = 10;
                System.out.println(name+"的心情 十分 愉悦");
            }
        }else{
            if(mood + num >= 0){
                mood += num;
            }else{
                mood = 0;
                System.out.println(name+"的心情 十分 糟糕");
            }
        }
    }

    public void setLoveliness(int num) {
        this.loveliness = this.loveliness + num;
    }

    public void setSp(int num) {
        this.sp = this.sp + num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(int species){
        this.species = species;
    }
}
