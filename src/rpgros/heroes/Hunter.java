package rpgros.heroes;

import rpgros.Combattant;
import rpgros.SafeScanner.SafeScanner;
import rpgros.Weapon;
import rpgros.consumables.Food;
import rpgros.enemies.Enemy;

import java.util.ArrayList;

public class Hunter extends Hero {

    private int maxArrow, arrow;

    public Hunter(String name, int maxHp, int hp, Weapon weapon, Role role, int maxArrow, int arrow) {
        super(name, maxHp, hp, weapon, role);
        this.maxArrow = maxArrow;
        this.arrow = arrow;
        this.inventory = new ArrayList<>();
        this.addInventory(new Food());
        this.res = 0;
    }

    public Hunter(String name){
        super(name);
        this.hp = this.maxHp = 200;
        this.weapon = new Weapon("Hunter Bow", 15, 0, Weapon.RoleLock.Hunter);
        this.role = Combattant.Role.Hunter;
        this.maxArrow = 20;
        this.arrow = 20;
        this.inventory = new ArrayList<>();
        this.addInventory(new Food());
        this.res = 0;
    }

    public int getArrow() {
        return arrow;
    }
    public void setArrow(int arrow) {
        this.arrow = arrow;
    }

    public int getMaxArrow() {
        return maxArrow;
    }
    public void setMaxArrow(int maxArrow) {
        this.maxArrow = maxArrow;
    }

    public int[] mainAtk() {
        if(this.role.toString().equals(Weapon.RoleLock.Hunter.toString())) {
            System.out.println("Role check");
            return new int[]{this.getWeapon().getDmg(), 0, 1};
        }
        else {
            System.out.println("No Role check");
            return new int[]{0,0, 0};
        }
    }

    public int[] tripleShot() {
        if(this.role.toString().equals(Weapon.RoleLock.Hunter.toString())) {
            System.out.printf("Role check");
            return new int[]{this.getWeapon().getDmg()*3, 0 , 3};
        }
        else {
            System.out.println("No Role check");
            return new int[]{0, 0, 0};
        }
    }

    public int[] daggerAtk() {
        if(this.role.toString().equals(Weapon.RoleLock.Hunter.toString())) {
            System.out.println("Role check");
            return new int[]{this.getWeapon().getDmg()/2, 0, 0};
        }
        else {
            System.out.println("No Role check");
            return new int[]{0, 0, 0};
        }
    }

    public void sendAtk(int[] atk, Combattant receiver) {
        receiver.setHp(receiver.getHp()-atk[0]);
        this.setHp(this.getHp()-atk[1]);
        if (this.arrow == 0){
            System.out.println("Not enough arrows!");
        }
        else {
            receiver.setHp(receiver.getHp() > atk[0] ? receiver.getHp() - atk[0] : 0);
            this.hp = this.getHp() > atk[1] ? this.getHp()-atk[1] : 0;
            this.setArrow(this.getArrow()-atk[2]);
        }
    }




    public static void fightHunter(Hunter hunter, Enemy enemy) {
        SafeScanner scanner = new SafeScanner();
        System.out.println("Your HP : " + hunter.getHp() + "\nEnemy HP : " + enemy.getHp() + "\n");
        System.out.println("Choose an action :\n1. Normal Attack\n2. Triple Shot\n3. Dagger Attack");
        loop1:
        while (true) {
            int choice = scanner.getInt();
            switch (choice) {
                case 1:
                    System.out.println("\nYou launch a Normal Attack");
                    hunter.sendAtk(hunter.mainAtk(), enemy);
                    break loop1;
                case 2:
                    System.out.println("\nYou launch a Triple Shot");
                    hunter.sendAtk(hunter.tripleShot(), enemy);
                    break loop1;
                case 3:
                    System.out.println("\nYou launch a Dagger Attack");
                    hunter.sendAtk(hunter.daggerAtk(), enemy);
                    break loop1;
                default:
                    System.out.println("Wrong choice, try again");
                    break;
            }
        }
        System.out.println("Your HP : " + hunter.getHp() + "\nEnemy HP : " + enemy.getHp() + "\n");

    }


}
