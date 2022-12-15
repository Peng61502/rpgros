package rpgros;

public abstract class Combattant {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected static Weapon weapon;
    protected int res;

    public enum Role {Warrior, Hunter, Mage, Healer, Enemy}
    protected Role role;

    public Combattant(String name, int hp, int maxHp, Weapon weapon){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.weapon = weapon;
    }

    public Combattant(String name) {
        this.name = name;
    }

    public void sendAtk(int[] atk, Combattant receiver) {
        receiver.hp = receiver.getHp() > atk[0]-receiver.getRes() ? receiver.getHp()-atk[0] : 0;
        this.hp = this.getHp() > atk[1] ? this.getHp()-atk[1] : 0;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return this.hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public static Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}


