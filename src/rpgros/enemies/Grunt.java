package rpgros.enemies;

import rpgros.Combattant;
import rpgros.Weapon;

public class Grunt extends Enemy {
    public Grunt(String name, int maxHp, int hp, Weapon weapon, Role role) {
        super(name, maxHp, hp, weapon);
        this.allAtk = new int[][]{this.mainAtk()};
        this.role = role;
        this.res = 0;
    }

    public Grunt(String name) {
        super(name);
        this.hp = this.maxHp = 100;
        this.weapon = new Weapon("Enemy weapon", 10, 0, Weapon.RoleLock.Enemy);
        this.role = Combattant.Role.Enemy;
        this.allAtk = new int[][]{this.mainAtk()};
        this.res = 0;
    }

    public int[] mainAtk() {
        String message = ("Enemy launched a normal attack");
        return new int[] {Grunt.weapon.dmg, 0};
    }


}
