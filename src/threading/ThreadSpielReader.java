package threading;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class ThreadSpielReader {
    AufgabenstellerReader aufgabenstellerReader;

    BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public ThreadSpielReader() {
        aufgabenstellerReader = new AufgabenstellerReader();
    }

    public void starteSpiel() {
        int zaehler = 10;
        System.out.println("Starte Spiel");
        // Spiel dauert 10 Sekunden
        aufgabenstellerReader.start();

        while (zaehler > 0) {
            System.out.println(zaehler);

            if (reader.)

            System.out.println(eingabe);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            zaehler--;
        }

        aufgabenstellerReader.stop();
        System.out.println("Spiel beendet");
    }

    public static void main(String[] args) {
        new ThreadSpielReader().starteSpiel();
    }
}

class AufgabenstellerReader extends Thread {
    private static Random gen = new Random();
    public char zeichen;

    public AufgabenstellerReader() {
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
