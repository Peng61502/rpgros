package rpgros.enemies;

import rpgros.Combattant;
import rpgros.Weapon;

import java.util.Random;

public abstract class Enemy extends Combattant {
    protected int[][] allAtk;
    public Enemy(String name, int maxHp, int hp, Weapon weapon) {
        super(name, maxHp, hp, weapon);
    }

    public Enemy(String name) {
        super(name);
    }

    public int[] rdmAtk(){
        return allAtk[new Random().nextInt(allAtk.length)];
    }


}
