package rpgros.heroes;

import rpgros.Combattant;
import rpgros.SafeScanner.SafeScanner;
import rpgros.Weapon;
import rpgros.consumables.Food;
import rpgros.enemies.Enemy;

import java.util.ArrayList;

public class Warrior extends Hero {
    public Warrior(String name, int maxHp, int hp, Weapon weapon, Role role) {
        super(name, maxHp, hp, weapon, role);
        this.inventory = new ArrayList<>();
        this.addInventory(new Food());
        this.res = 0;
    }

    public Warrior(String name){
        super(name);
        this.hp = this.maxHp = 200;
        this.weapon = new Weapon("Warrior Sword", 15,0, Weapon.RoleLock.Warrior);
        this.role = Combattant.Role.Warrior;
        this.inventory = new ArrayList<>();
        this.addInventory(new Food());
        this.res = 0;
    }


    public int[] mainAtk(){
        //System.out.println("\nYou launch a Normal Attack");
        if(this.role.toString().equals(Weapon.RoleLock.Warrior.toString())) {
            System.out.println("Role check");
            return new int[]{this.getWeapon().getDmg(), 0};
        }
        else {
            System.out.println("No Role check");
            return new int[]{0,0};
        }

    }
    public int[] rageAtk(){
        //System.out.println("\nYou launch a Rage Attack");
        if (this.role.toString().equals(Weapon.RoleLock.Warrior.toString())) {
            return new int[]{2 * this.getWeapon().getDmg(), this.getWeapon().getDmg()};
        }
        else return new int[]{0,0};
    }


    public static void fightWarrior(Warrior warrior, Enemy enemy) {
        SafeScanner scanner = new SafeScanner();
        System.out.println("Your HP : " + warrior.getHp() + "\nEnemy HP : " + enemy.getHp() + "\n");
        System.out.println("Choose an action :\n1. Normal Attack\n2. Rage Attack");
        loop1:
        while (true) {
            int choice = scanner.getInt();
            switch (choice) {
                case 1:
                    System.out.println("\nYou launch a Normal Attack");
                    warrior.sendAtk(warrior.mainAtk(), enemy);
                    break loop1;
                case 2:
                    System.out.println("\nYou launch a Rage Attack");
                    warrior.sendAtk(warrior.rageAtk(), enemy);
                    break loop1;
                //TODO REMOVE ADMIN ATTACK
                case 9:
                    System.out.println("\nADMIN ATTACK");
                    warrior.sendAtk(new int[] {1000,0},enemy);
                    break loop1;
                default:
                    System.out.println("Wrong choice, try again");
                    break;
            }
        }
        System.out.println("Your HP : " + warrior.getHp() + "\nEnemy HP : " + enemy.getHp() + "\n");
    }
}
