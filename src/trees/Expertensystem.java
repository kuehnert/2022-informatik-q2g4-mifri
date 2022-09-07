package trees;

import java.util.Scanner;

public class Expertensystem {
    Scanner eingabe;
    Frage wurzel;

    public Expertensystem() {
        Frage fBeine = new Frage("Läuft es auf vier Beinen?", new Frage("Löwe"
        ), new Frage("Känguru"));
        Frage fInsekt = new Frage("Ist es ein Insekt?", new Frage("Fliege"),
                new Frage("Papagei"));
        wurzel = new Frage("Ist es ein Saeugetier?", fBeine, fInsekt);
        eingabe = new Scanner(System.in);
    }

    public void spiele() {
        // Schleife "willst Du nochmal spielen?"
        Frage runner = wurzel;
        Frage pred = null;

        while (true) {
            if (!runner.istBlatt()) {
                System.out.println(runner.inhalt + " (j/n): ");
                boolean antwortJa = eingabe.nextLine().startsWith("j");
                pred = runner;
                runner = antwortJa ? runner.ja : runner.nein;
            } else {
                System.out.println("Ist es etwa ein(e) " + runner.inhalt +
                        "?" + " (j/n):");
                boolean antwortJa = eingabe.nextLine().startsWith("j");
                if (antwortJa) {
                    System.out.println("Hurra, ich habe Dein Tier geraten");
                    // nochmal spielen?
                    // fange wieder von vorne
                    return;
                } else {
                    System.out.println("Oh je, ich kenne Dein Tier nicht.");
                    System.out.println("An welches Tier hast Du gedacht? ");
                    String neuesTierStr = eingabe.nextLine();
                    System.out.println("Gib eine Frage, wie sich altesTier " +
                            "und neuesTier von einander unterscheiden, und " +
                            "für die für neuesTier die Antwort 'ja' ist? ");
                    String neueFrageStr = eingabe.nextLine();
                    Frage neuesTier = new Frage(neuesTierStr);
                    Frage neueFrage = new Frage(neueFrageStr, neuesTier,
                            runner);
                    pred.ja = neueFrage;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Expertensystem().spiele();
    }
}

class Frage {
    String inhalt;
    Frage ja;
    Frage nein;

    public Frage(String inhalt) {
        this.inhalt = inhalt;
    }

    public Frage(String inhalt, Frage ja, Frage nein) {
        this.inhalt = inhalt;
        this.ja = ja;
        this.nein = nein;
    }

    public boolean istBlatt() {
        return ja == null; // && nein == null; -> unnoetig
    }
}