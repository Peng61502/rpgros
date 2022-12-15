package rpgros.heroes;

import rpgros.Combattant;
import rpgros.Weapon;
import rpgros.consumables.Consumable;
import rpgros.consumables.Food;
import rpgros.consumables.Potion;

import java.util.ArrayList;

public class Hero extends Combattant {


    protected ArrayList<Consumable> inventory;

    public Hero(String name, int maxHp, int hp, Weapon weapon, Role role) {
        super(name, maxHp, hp, weapon);
        this.role = role;
        this.inventory = new ArrayList<>();
        this.addInventory(new Food());
    }

    public Hero(String name) {
        super(name);
        this.inventory = new ArrayList<>();
        addInventory(new Food());
    }

    public ArrayList<Consumable> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Consumable> inventory) {
        this.inventory = inventory;
    }

    public void addInventory(Consumable item) {
        this.inventory.add(item);
    }


    public int countFood() {
        int i = 0;
        for (Consumable consumable : this.getInventory()) {
            if (consumable instanceof Food) i++;
        }
        return i;
    }

    public int countPopo() {
        int i = 0;
        for (Consumable consumable : this.getInventory()) {
            if (consumable instanceof Potion) i++;
        }
        return i;
    }
}
