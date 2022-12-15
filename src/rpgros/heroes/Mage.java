package rpgros.heroes;

import rpgros.Combattant;
import rpgros.SafeScanner.SafeScanner;
import rpgros.Weapon;
import rpgros.consumables.Food;
import rpgros.consumables.Potion;
import rpgros.enemies.Enemy;

import java.util.ArrayList;

public class Mage extends Spellcaster {
    public Mage(String name, int maxHp, int hp, Weapon weapon, Role role, int maxMana, int mana) {
        super(name, maxHp, hp, weapon, role, maxMana, mana);
        this.inventory = new ArrayList<>();
        addInventory(new Food());
        addInventory(new Potion());
        this.res = 0;
    }

    public Mage(String name){
        super(name);
        this.hp = this.maxHp = 200;
        this.weapon = new Weapon("Mage Staff", 20, 20, Weapon.RoleLock.Mage);
        this.role = Combattant.Role.Mage;
        this.setMaxMana(150);
        this.setMana(150);
        this.inventory = new ArrayList<>();
        addInventory(new Food());
        addInventory(new Potion());
        this.res = 0;
    }



    public int[] mainAtk() {
        if (this.role.toString().equals(Weapon.RoleLock.Mage.toString())) {
            System.out.println("Role check");
            return new int[]{this.getWeapon().getDmg(), 0, this.getWeapon().getMana_use()};
        } else {
            System.out.println("No Role check");
            return new int[]{0, 0, 0};
        }
    }

    public int[] divineWind() {
        if (this.role.toString().equals(Weapon.RoleLock.Mage.toString())) {
            System.out.println("Role check");
            return new int[]{this.getWeapon().getDmg()*6, this.getMaxHp() *6, this.getMana()};
        } else {
            System.out.println("No Role check");
            return new int[]{0, 0, 0};
        }
    }


    public static void fightMage(Mage mage, Enemy enemy) {
        SafeScanner scanner = new SafeScanner();
        System.out.println("Your HP : " + mage.getHp() + "\nEnemy HP : " + enemy.getHp() + "\n");
        System.out.println("Choose an action :\n1. Normal Attack\n2. Divine Wind");
        loop1:
        while (true) {
            int choice = scanner.getInt();
            switch (choice) {
                case 1:
                    System.out.println("\nYou launch a Normal Attack");
                    mage.sendSpell(mage.mainAtk(), enemy);
                    break loop1;
                case 2:
                    System.out.println("\nBANZAIIIIIIIIIIII");
                    mage.sendSpell(mage.divineWind(), enemy);
                    break loop1;
                default:
                    System.out.println("Wrong choice, try again");
                    break;
            }
        }
        System.out.println("Your HP : " + mage.getHp() + "\nEnemy HP : " + enemy.getHp() + "\n");
    }

}
