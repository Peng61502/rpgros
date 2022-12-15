package rpgros.enemies;

import rpgros.Combattant;
import rpgros.Weapon;

public class Boss extends Enemy {
    public Boss(String name, int maxHp, int hp, Weapon weapon, Role role) {
        super(name, maxHp, hp, weapon);
        this.allAtk = new int[][]{this.mainAtk()};
        this.role = Role.Enemy;
        this.res = 0;
    }

    public Boss(String name) {
        super(name);
        this.hp = this.maxHp = 600;
        this.weapon = new Weapon("Boss weapon", 30, 0, Weapon.RoleLock.Enemy);
        this.role = Combattant.Role.Enemy;
        this.res = 0;
    }

    public int[] mainAtk() {
        //String message = ("Enemy launched a normal attack");
        return new int[] {Boss.weapon.dmg, 0};
    }

    public int[] aoeAtk() {
        return new int[] {Boss.weapon.dmg/2, 0};
        // Actual AOE implemented in Game > bossTurn()
    }

}
