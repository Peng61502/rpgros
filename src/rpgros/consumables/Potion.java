package rpgros.consumables;
import rpgros.heroes.Spellcaster;

public class Potion extends Consumable {
    public static void usePopo(Spellcaster spellcaster) {
        if ((spellcaster.getRole()).toString().equals("Mage") || (spellcaster.getRole()).toString().equals("Healer")){
            if (spellcaster.getMana() > spellcaster.getInventory().get(1).getBuffAmount()) spellcaster.setMana(spellcaster.getMaxMana());
            else spellcaster.setMana(spellcaster.getMana()+spellcaster.getInventory().get(1).getBuffAmount());
            spellcaster.getInventory().get(1).setAmount(spellcaster.getInventory().get(1).getAmount()-1);
        }

    }

    public Potion() {
        this.name = "Potion";
        this.amount = 1;
        this.buffAmount = 60;
    }
}
