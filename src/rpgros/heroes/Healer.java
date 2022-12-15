package rpgros.heroes;

import rpgros.Combattant;
import rpgros.SafeScanner.SafeScanner;
import rpgros.Weapon;
import rpgros.consumables.Food;
import rpgros.consumables.Potion;

import java.util.ArrayList;

public class Healer extends Spellcaster {
    public Healer(String name, int maxHp, int hp, Weapon weapon, Role role, int maxMana, int mana) {
        super(name, maxHp, hp, weapon, role, maxMana, mana);
        this.inventory = new ArrayList<>();
        addInventory(new Food());
        addInventory(new Potion());
        this.res = 0;
    }

    public Healer(String name){
        super(name);
        this.hp = this.maxHp = 200;
        this.weapon = new Weapon("Healer Staff", 20, 20, Weapon.RoleLock.Healer);
        this.role = Combattant.Role.Healer;
        this.setMaxMana(150);
        this.setMana(150);
        this.inventory = new ArrayList<>();
        addInventory(new Food());
        addInventory(new Potion());
        this.res = 0;
    }


    public int[] mainHeal() {
        if (this.role.toString().equals(Weapon.RoleLock.Healer.toString())) {
            System.out.println("Role check");
            return new int[]{this.getWeapon().getDmg()*(-1), 0, this.getWeapon().getMana_use()};
        }
        else {
            System.out.println("No Role check");
            return new int[]{0, 0, 0};
        }
    }

    public int[] overHeal() {
        if (this.role.toString().equals(Weapon.RoleLock.Healer.toString())) {
            System.out.println("Role check");
            return new int[]{(this.getWeapon().getDmg()+this.getHp())*(-1) /4, this.getHp()/4, 30};
        }
        else {
            System.out.println("No Role check");
            return new int[]{0, 0, 0};
        }
    }


    public static void fightHealer(Healer healer, Hero receiver) {
        SafeScanner scanner = new SafeScanner();
        System.out.println("Your HP : " + healer.getHp() + "\nEnemy HP : " + receiver.getHp() + "\n");
        System.out.println("Choose an action :\n1. Normal Heal\n2. Over Heal");
        loop1:
        while (true) {
            int choice = scanner.getInt();
            switch (choice) {
                case 1:
                    System.out.println("\nYou launch Normal Heal");
                    healer.sendSpell(healer.mainHeal(), receiver);
                    break loop1;
                case 2:
                    System.out.println("\nYou launch Over Heal");
                    healer.sendSpell(healer.overHeal(), receiver);
                    break loop1;
                default:
                    System.out.println("Wrong choice, try again");
                    break;
            }
        }
        System.out.println("Your HP : " + healer.getHp() + "\nAlly HP : " + receiver.getHp() + "\n");
    }

}


