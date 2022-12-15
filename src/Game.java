
import rpgros.*;
import rpgros.consumables.Consumable;
import rpgros.consumables.Potion;
import rpgros.enemies.Boss;
import rpgros.enemies.Enemy;
import rpgros.enemies.Grunt;
import rpgros.heroes.*;
import rpgros.SafeScanner.*;

import java.util.ArrayList;
import java.util.Random;

import static rpgros.consumables.Food.useFood;


public class Game {


    // Hero init
    static Weapon warWeapon = new Weapon("Warrior Sword", 15, 0, Weapon.RoleLock.Warrior);
    static Warrior warrior = new Warrior("Warrior", 200, 200, warWeapon, Combattant.Role.Warrior);

    static Weapon hunWeapon = new Weapon("Hunter Bow", 15, 0, Weapon.RoleLock.Hunter);
    static Hunter hunter = new Hunter("Hunter", 200, 200, hunWeapon, Combattant.Role.Hunter, 20, 20);

    static Weapon magWeapon = new Weapon("Mage Staff", 20, 20, Weapon.RoleLock.Mage);
    static Mage mage = new Mage("Mage", 200, 200, magWeapon, Combattant.Role.Mage, 150, 150);

    static Weapon heaWeapon = new Weapon("Healer Staff", 20, 20, Weapon.RoleLock.Healer);
    static Healer healer = new Healer("Healer", 200, 200, heaWeapon, Combattant.Role.Healer, 150, 150);


    // Enemy init
    static Weapon e1Weapon = new Weapon("Evil Weapon 1", 10, 0, Weapon.RoleLock.Enemy);
    static Enemy enemy1 = new Grunt("Enemy 1", 150, 150, e1Weapon, Combattant.Role.Enemy);


    // Hero creator
    public static Hero createHero(int i, int num) {
        switch (i) {
            case 1:
                return new Warrior("Warrior " + num);
            case 2:
                return new Hunter("Hunter " + num);
            case 3:
                return new Mage("Mage " + num);
            case 4:
                return new Healer("Healer " + num);
            default:
                return null;
        }
    }

    // Party Creator
    //TODO SIZE LIMIT NOT IMPLEMENTED
    public static ArrayList<Hero> createParty(int n) {
        ArrayList<Hero> returnParty = new ArrayList<>();
        SafeScanner scanner = new SafeScanner();
        while (returnParty.size() != n) {
            loop1:
            while (true) {
                System.out.println("Choose a class :\n1. Warrior\n2. Hunter\n3. Mage\n4. Healer");
                int i = scanner.getInt();
                switch (i) {
                    case 1, 2, 3, 4:
                        returnParty.add(createHero(i, returnParty.size() + 1));
                        break loop1;
                    default:
                        System.out.println("Wrong choice, try again");
                        break;
                }
            }
        }
        return returnParty;
    }

    // Is party dead ?
    public static boolean isPartyDead(ArrayList<Combattant> party) {

        int hpSum = 0;
        for (Combattant combattant : party) {
            hpSum += combattant.getHp();
        }
        return hpSum == 0;
    }

    // Is dead ?
    public static boolean isDead(ArrayList<Hero> heroParty, ArrayList<Enemy> enemyParty) {
        ArrayList<Combattant> hP = new ArrayList<>(heroParty);
        ArrayList<Combattant> eP = new ArrayList<>(enemyParty);
        return isPartyDead(hP) || isPartyDead(eP);
    }


    // Hero Send Attack
    public static void heroAtk(Hero sender, Combattant receiver) {
        if (sender instanceof Warrior) {
            Warrior.fightWarrior((Warrior) sender, (Enemy) receiver);
        } else if (sender instanceof Hunter) {
            Hunter.fightHunter((Hunter) sender, (Enemy) receiver);
        } else if (sender instanceof Mage) {
            Mage.fightMage((Mage) sender, (Enemy) receiver);
        } else if (sender instanceof Healer) {
            Healer.fightHealer((Healer) sender, (Hero) receiver);
        }
    }


    // Hero Attack Turn
    public static void heroAtkTurn(Hero hero, ArrayList<Enemy> enemyParty, ArrayList<Hero> heroParty) {
        SafeScanner scanner = new SafeScanner();
        if (!(hero instanceof Healer)) {
            System.out.println("Choose a target :");
            for (Enemy enemy : enemyParty) {
                if (enemy.getHp() == 0 && !enemy.getName().contains("[DEAD]")) {
                    enemy.setName(enemy.getName() + " [DEAD]");
                }
                System.out.print((enemyParty.indexOf(enemy) + 1) + ". " + enemy.getName());
                System.out.println(" (" + enemy.getHp() + ")");
            }
            while (true) {
                int choice1 = scanner.getInt();
                if (choice1 <= enemyParty.size() && choice1 > 0 && enemyParty.get(choice1 - 1).getHp() != 0) {
                    heroAtk(hero, enemyParty.get(choice1 - 1));
                    break;
                } else if (choice1 <= enemyParty.size() && choice1 > 0 && enemyParty.get(choice1 - 1).getHp() == 0) {
                    System.out.println("Enemy is dead, choose another target");
                } else {
                    System.out.println("Wrong choice, try again");
                }
            }
        } else {
            System.out.println("Choose an ally to heal :");
            for (Hero friend : heroParty) {
                if (friend.getHp() == 0 && !friend.getName().contains("[DEAD]")) {
                    friend.setName(friend.getName() + " [DEAD]");
                }
                System.out.print((heroParty.indexOf(friend) + 1) + ". " + friend.getName());
                System.out.println(" (" + friend.getHp() + ")");
            }
            while (true) {
                int choice1 = scanner.getInt();
                if (choice1 <= heroParty.size() && choice1 > 0 && heroParty.get(choice1 - 1).getHp() != 0
                        && !heroParty.get(choice1 - 1).equals(hero)) {
                    heroAtk(hero, heroParty.get(choice1 - 1));
                    break;
                } else if (choice1 <= heroParty.size() && choice1 > 0 && heroParty.get(choice1 - 1).getHp() == 0
                        && !heroParty.get(choice1 - 1).equals(hero)) {
                    System.out.println("You can't revive the dead, choose another target");
                } else if (heroParty.get(choice1 - 1).equals(hero)) {
                    System.out.println("You can't heal yourself, choose another target");
                } else {
                    System.out.println("Wrong choice, try again");
                }
            }
        }
    }


    // Hero Consumable Turn
    public static void heroConsTurn(Hero hero) {
        SafeScanner scanner = new SafeScanner();
        loop1:
        while (true) {
            if (hero.getInventory().isEmpty()) {
                System.out.println("EMPTY INVENTORY");
                break;
            } else {
                System.out.println("Chose an Item");
                for (Consumable consumable : hero.getInventory()) {
                    System.out.println((hero.getInventory().indexOf(consumable) + 1) + ". " + consumable.getName()
                            + " (" + consumable.getAmount() + ")");
                }
                while (true) {
                    int choice2 = scanner.getInt();
                    switch (choice2) {
                        case 1:
                            if (hero.getInventory().get(0).getAmount() > 0) {
                                useFood(hero);
                                if (hero instanceof Spellcaster) {
                                    System.out.println(hero.getName() + " (" + hero.getHp() + ")"
                                            + " (" + ((Spellcaster) hero).getMana() + ")");
                                } else
                                    System.out.println(hero.getName() + " (" + hero.getHp() + ")");
                                break loop1;
                            } else System.out.println("You don't have any, try again");
                            break;
                        case 2:
                            if (hero.getInventory().get(1).getAmount() > 0) {
                                Potion.usePopo((Spellcaster) hero);
                                System.out.println(hero.getName() + " (" + hero.getHp() + ")"
                                        + " (" + ((Spellcaster) hero).getMana() + ")");
                                break loop1;
                            } else System.out.println("You don't have any, try again");
                            break;

                        default:
                            System.out.println("Wrong choice, try again");
                            break;
                    }
                }

            }
        }
    }

    // Enemy Turn
    public static void enemyTurn(ArrayList<Enemy> enemyParty, ArrayList<Hero> heroParty) {
        ArrayList<Hero> aliveParty = new ArrayList<>();
        for (Hero hero : heroParty) {
            if (hero.getHp() != 0) aliveParty.add(hero);
        }
        Random rand = new Random();
        for (Enemy enemy : enemyParty) {
            Hero randomHero = aliveParty.get(rand.nextInt(aliveParty.size()));
            enemy.sendAtk(enemy.rdmAtk(), randomHero);
            if (randomHero.getHp() < 0) randomHero.setHp(0);
        }
    }


    // Boss Turn
    public static void bossTurn(Boss boss, ArrayList<Hero> heroParty, int choice) {
        Random rand = new Random();
        Hero randomHero = heroParty.get(rand.nextInt(heroParty.size()));
        if (choice == 1) boss.sendAtk(boss.mainAtk(), randomHero);
        else for (Hero hero : heroParty) boss.sendAtk(boss.aoeAtk(), hero);
    }

    // End of Turn Reward
    public static void endReward(ArrayList<Hero> heroParty, ArrayList<Enemy> enemyParty) {
        System.out.println("Congratulations, choose a reward for each of your heroes");
        for (Hero hero : heroParty) {
            if (hero.getHp() != 0) {
                // Text
                SafeScanner scanner = new SafeScanner();
                System.out.println("Current Hero : " + hero.getName());
                if (hero instanceof Spellcaster) {
                    System.out.println("1. Improve Spell efficiency");
                } else System.out.println("1. Increase damage");
                System.out.println("2. Increase damage resistance\n3. Increase consumable efficiency");
                System.out.println("4. Receive 2 food\n5. Receive 2 Potions");
                if (hero instanceof Hunter) System.out.println("6. Receive 10 arrows");
                if (hero instanceof Spellcaster) System.out.println("6. Reduce Mana cost");

                // Code
                int choice = scanner.getInt();
                loop:
                while (true) {
                    switch (choice) {
                        case 1:
                            Weapon newWeapon = hero.getWeapon();
                            newWeapon.setDmg(hero.getWeapon().getDmg() + 10);
                            hero.setWeapon(newWeapon);
                            break loop;
                        case 2:
                            hero.setRes(hero.getRes() + 5);
                            break loop;
                        case 3:
                            hero.getInventory().get(0).setBuffAmount(hero.getInventory().get(0).getBuffAmount() + 10);
                            if (hero instanceof Spellcaster) {
                                hero.getInventory().get(1).setBuffAmount(hero.getInventory().get(1).getBuffAmount() + 10);
                            }
                            break loop;
                        case 4:
                            hero.getInventory().get(0).setAmount(hero.getInventory().get(0).getAmount() + 2);
                            break loop;
                        case 5:
                            if (hero instanceof Spellcaster) {
                                hero.getInventory().get(1).setAmount(hero.getInventory().get(1).getAmount() + 2);
                                break loop;
                            } else System.out.println("This hero can't use potions, choose something else");
                            break;
                        case 6:
                            if (hero instanceof Spellcaster) {
                                Weapon newWeapon1 = hero.getWeapon();
                                newWeapon1.setMana_use(hero.getWeapon().getMana_use() - 5);
                                hero.setWeapon(newWeapon1);
                                break loop;
                            } else if (hero instanceof Hunter) {
                                ((Hunter) hero).setArrow(((Hunter) hero).getArrow() + 10);
                                break loop;
                            } else System.out.println("Wrong choice, try again");
                            break;

                    }
                }
            }
        }
    }


    // Rounds
    public static ArrayList<Hero> round(ArrayList<Hero> heroParty, int turn) {
        ArrayList<Enemy> enemyParty = new ArrayList<>();
        ArrayList<Hero> returnParty = new ArrayList<>();

        // Create Enemy party
        while (enemyParty.size() != heroParty.size()) {
            enemyParty.add(new Grunt("Enemy " + (enemyParty.size() + 1)));
        }

        //Create Boss
        Boss boss = new Boss("Boss");

        // Fight
        SafeScanner scanner = new SafeScanner();
        boolean isDead;
        boolean aoeTime = true;
        loopglobal:
        while (true) {
            for (Hero hero : heroParty) {
                isDead = isDead(heroParty, enemyParty);
                if (hero.getHp() == 0 && !hero.getName().contains("[DEAD]")) {
                    hero.setName(hero.getName() + " [DEAD]");
                }
                if (hero.getHp() > 0 && !isDead) {
                    loop:
                    while (true) {
                        if (hero instanceof Spellcaster) {
                            System.out.println("Current Hero : " + hero.getName() + " (" + hero.getHp() + ")" + " ("
                                    + ((Spellcaster) hero).getMana() + ")");
                        } else System.out.println("Current Hero : " + hero.getName() + " (" + hero.getHp() + ")");
                        System.out.println("Choose an action :\n1. Attack\n2. Use an Item");
                        int choice = scanner.getInt();
                        switch (choice) {
                            case 1:
                                heroAtkTurn(hero, enemyParty, heroParty);
                                break loop;
                            case 2:
                                heroConsTurn(hero);
                                break loop;
                            default:
                                System.out.println("Wrong choice, try again");
                                break;
                        }
                    }
                } else if (!isDead) {
                    if (hero instanceof Spellcaster && !isDead) {
                        System.out.println("Current Hero : " + hero.getName() + " (" + hero.getHp() + ")" + " ("
                                + ((Spellcaster) hero).getMana() + ")");
                    } else System.out.println("Current Hero : " + hero.getName() + " (" + hero.getHp() + ")");
                    System.out.println("Hero is dead, next hero");
                }
                isDead = isDead(heroParty, enemyParty);
                if (isDead) break loopglobal;
                if (hero.equals(heroParty.get(heroParty.size() - 1))) {
                    switch (turn) {
                        case 1, 2, 3, 4:
                            enemyTurn(enemyParty, heroParty);
                        case 5:
                            if (boss.getHp() < 200 && aoeTime) {
                                bossTurn(boss, heroParty, 1);
                                aoeTime = false;
                            } else {
                                bossTurn(boss, heroParty, 0);
                                aoeTime = true;
                            }
                    }
                }
            }
        }
        isDead = isDead(heroParty, enemyParty);
        if (!isDead) endReward(heroParty, enemyParty);
        boolean isHeroDead = isPartyDead(new ArrayList<>(heroParty));
        if(isHeroDead) System.out.println("GAME OVER\nYou suck..."); System.exit(0);
        return returnParty;
    }

    public static void fight(ArrayList<Hero> party) {
        for (int i = 1; i <= 5; i++) round(party, i);
    }

    public static void game() {
        SafeScanner scanner = new SafeScanner();
        System.out.println("Choose the size of your party (between 2 and 4)");
        int choice = scanner.getInt();
        switch (choice) {
            case 2, 3, 4 -> {
                ArrayList<Hero> party = createParty(choice);
                fight(party);
            }
            default -> {
                System.out.println("Wrong choice, try again");
                game();
            }
        }
    }


    public static void main(String[] args) {
        game();
    }
}
