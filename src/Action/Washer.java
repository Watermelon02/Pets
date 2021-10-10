package Action;

import CoffeeShop.*;
import Pets.Pets;

public class Washer implements Runnable{
    private Pets pet;

    public Washer(Pets pet) {
        this.pet = pet;
    }

    public void run(){
        if(pet.getCleanliness() < 10){
            pet.setCleanliness(10-pet.getCleanliness());
            pet.setMood(-1);
            System.out.println(pet.getName()+"被摁住洗了个澡之后，重新焕发生机(清洁度恢复，心情-1)");
        }else{
            System.out.println(pet.getName()+"已经足够干净了，xiu地一下跑掉了");
        }
    }
}
