package rpgros.consumables;

import rpgros.Item;

public abstract class Consumable extends Item {
    public int amount;

    public int buffAmount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBuffAmount() {
        return buffAmount;
    }

    public void setBuffAmount(int buffAmount) {
        this.buffAmount = buffAmount;
    }
}
