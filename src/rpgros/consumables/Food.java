package rpgros.consumables;

import rpgros.heroes.Hero;

public class Food extends Consumable {
    public static void useFood(Hero hero) {
        if (hero.getHp() > hero.getMaxHp() -(hero.getInventory().get(0).getBuffAmount())) hero.setHp(hero.getMaxHp());
        else hero.setHp(hero.getHp() + hero.getInventory().get(0).getBuffAmount());
        hero.getInventory().get(0).setAmount(hero.getInventory().get(0).getAmount()-1);
    }

    public Food() {
        this.name = "Food";
        this.amount = 1;
        this.buffAmount = 40;
    }
}
