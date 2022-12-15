package rpgros.SafeScanner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SafeScanner {
    Scanner  sc;

    public SafeScanner() {
       this.sc = new Scanner(System.in);
    }

    public int getInt() {
        boolean inputValid = false;
        int entier = 0;
        while (!inputValid) {
            try {
                entier = sc.nextInt();
                inputValid = true;
            } catch (InputMismatchException e) {
                sc.next();
            }
        }
        return (entier);
    }

    public String getString() {
        String string;
        System.out.println("Entrer du texte");
        string = sc.next();
        System.out.println("Le texte est '" + string + "'");
        return (string);
    }

    public void closeScanner() {
        sc.close();
    }

}

