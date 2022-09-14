package trees;

import java.util.Locale;
import java.util.Scanner;

public class Expertensystem {
    protected Scanner eingabe;
    protected Frage wurzel;

    public Expertensystem() {
        Frage fBeine = new Frage("Laeuft es auf vier Beinen",
                new Frage("Loewe"), new Frage("Kaenguru"));
        Frage fInsekt = new Frage("Ist es ein Insekt", new Frage("Fliege"),
                new Frage("Papagei"));
        wurzel = new Frage("Ist es ein Saeugetier", fBeine, fInsekt);
        eingabe = new Scanner(System.in);
    }

    public void spiele() {
        System.out.println("Willkommen beim selbstlernenden Expertensystem");
        boolean weiter = true;

        while (weiter) {
            spieleRunde();
            weiter = jaNeinFrage("Noch eine Runde");
        }
    }

    // 1. Robins Idee
    // lrl|Ist es ein Saegetier?
    // lrll|Affe
    // lrlr|Borkenkaefer

    // 2. Marius' Idee
    // Speichere als NLR (einfach), lese ein (schwierig)

    // 3. Max' Idee
    // Verwende Java-Serialisierung

    // 4. Besser nicht JSON

    // 5. Finns Idee: Im Dateisystem ablegen

    public void spieleRunde() {
        // Schleife "willst Du nochmal spielen?"
        Frage runner = wurzel;

        while (true) {
            if (!runner.istBlatt()) {
                runner = jaNeinFrage(runner.inhalt) ? runner.ja : runner.nein;
            } else {
                boolean antwortJa =
                        jaNeinFrage("Ist es etwa ein(e) " + runner.inhalt);

                String altesTier = runner.inhalt;

                if (antwortJa) {
                    System.out.println("Hurra, ich habe Dein Tier geraten");
                    // nochmal spielen?
                    // fange wieder von vorne
                    return;
                } else {
                    System.out.println("Oh je, ich kenne Dein Tier nicht.");
                    System.out.println("An welches Tier hast Du gedacht? ");
                    String neuesTierStr = eingabe.nextLine();
                    System.out.println("Gib eine Frage, wie sich " + altesTier + " " + "und " + neuesTierStr + " von einander unterscheiden, und" + "für die für " + neuesTierStr + " die Antwort 'ja' ist? ");
                    String neueFrageStr = eingabe.nextLine();
                    runner.ja = new Frage(neuesTierStr);
                    runner.nein = new Frage(altesTier);
                    runner.inhalt = neueFrageStr;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        // new Expertensystem().spiele();
        Expertensystem es = new Expertensystem();
        String in = es.exportieren();
        System.out.println(in);
    }

    public boolean jaNeinFrage(String frage) {
        System.out.print(frage + " (j/n)? ");
        return eingabe.nextLine().toLowerCase(Locale.ROOT).startsWith("j");
    }

    public String exportieren() {
        return wurzel.exportieren();
    }
}

