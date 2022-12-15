package rpgros.heroes;

import rpgros.Combattant;
import rpgros.Weapon;
import rpgros.consumables.Food;
import rpgros.consumables.Potion;

import java.util.ArrayList;

public abstract class Spellcaster extends Hero {
    private int maxMana;
    private int mana;

    public Spellcaster(String name, int maxHp, int hp, Weapon weapon, Role role, int maxMana, int mana) {
        super(name, maxHp, hp, weapon, role);
        this.maxMana = maxMana;
        this.mana = mana;
        this.inventory = new ArrayList<>();
        addInventory(new Food());
        addInventory(new Potion());
    }

    public Spellcaster(String name) {
        super(name);
        this.inventory = new ArrayList<>();
        addInventory(new Food());
        addInventory(new Potion());
    }

    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void sendSpell(int[] spell, Combattant receiver) {
        if (this.mana-spell[2] <0){
            System.out.println("Not enough mana!");
        }
        else {
            this.mana -= spell[2];
            receiver.setHp(receiver.getHp() > spell[0] - receiver.getRes() ? receiver.getHp() - spell[0] : 0);
            this.hp = this.getHp() > spell[1] ? this.getHp()-spell[1] : 0;
        }
    }

    public int[] manaRecovery() {
        if (this.mana < maxMana-50){
            return new int[]{0, 0, -50};
        }
        else{
            return new int[]{0, 0, this.mana-this.maxMana};
        }
    }

    public void sendAtk(int[] atk, Combattant receiver) {
        if (this.getMana()-atk[2] < 0) System.out.println("Not enough mana!");
        else {
            receiver.setHp(receiver.getHp() > atk[0] ? receiver.getHp() - atk[0] : 0);
            this.hp = this.getHp() > atk[1] ? this.getHp() - atk[1] : 0;
            this.setMana(this.getMana()-atk[2]);
        }

    }


}
