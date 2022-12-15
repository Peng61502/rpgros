package rpgros;

public class Weapon extends Item{
    public int dmg;
    public int mana_use;
    public enum RoleLock {Warrior, Hunter, Mage, Healer, Enemy}
    public RoleLock roleLock;

    public Weapon(String name, int dmg, int mana_use, RoleLock roleLock){
        this.name = name;
        this.dmg = dmg;
        this.mana_use = mana_use;
        this.roleLock = roleLock;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getMana_use() {
        return mana_use;
    }

    public void setMana_use(int mana_use) {
        this.mana_use = mana_use;
    }
}
