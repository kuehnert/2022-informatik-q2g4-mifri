package threading;

import java.util.Random;
import java.util.Scanner;

public class ThreadSpiel {
    Aufgabensteller aufgabensteller;
    Scanner tastatur = new Scanner(System.in);

    public ThreadSpiel() {
        aufgabensteller = new Aufgabensteller();
    }

    public void starteSpiel() {
        int zaehler = 10;
        System.out.println("Starte Spiel");
        // Spiel dauert 10 Sekunden
        aufgabensteller.start();

        while (zaehler > 0) {
            System.out.println(zaehler);
            String eingabe = tastatur.nextLine();
            System.out.println(eingabe);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            zaehler--;
        }

        aufgabensteller.stop();
        System.out.println("Spiel beendet");
    }

    public static void main(String[] args) {
        new ThreadSpiel().starteSpiel();
    }
}

class Aufgabensteller extends Thread {
    private static Random gen = new Random();
    public char zeichen;

    public Aufgabensteller() {
    }

    public void run() {
        // Endlosschleife
        while (true) {
            // Sende alle 1 - 5 Sekunden ein zufälliges Zeichen
            zeichen = (char) ('A' + gen.nextInt(26));
            System.out.println("Drücke Taste: '" + zeichen + "'");
            try {
                sleep(gen.nextInt(5000));
            } catch (InterruptedException e) {
            }
        }
    }
}
