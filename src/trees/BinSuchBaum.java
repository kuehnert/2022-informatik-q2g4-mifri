package trees;

import java.util.Arrays;

public class BinSuchBaum {
    Knoten wurzel;

    public BinSuchBaum(String zahlenString) {
        String[] zahlen = zahlenString.split(" ");
        System.out.println(Arrays.toString(zahlen));
        wurzel = new Knoten(Integer.parseInt(zahlen[0]));

        for (int i = 1; i < zahlen.length; i++) {
            einfuegen(Integer.parseInt(zahlen[i]));
        }
    }

    public void einfuegen(int zahl) {
        Knoten runner = wurzel;
        // Erstelle neuen Knoten mit zahl
        Knoten neuerKnoten = new Knoten(zahl);
        boolean fertig = false;

        while (!fertig) {
            // Vergleiche runner.data mit zahl
            // Wenn zahl kleiner ist:
            if (zahl < runner.data) {
                // Wenn runner.links existiert:
                if (runner.links != null) {
                    // Setze runner auf runner.links
                    runner = runner.links;
                } else {
                    // Setze runner.links auf neuen Knoten
                    runner.links = neuerKnoten;
                    fertig = true;
                }
            } else {
                // Sonst: Wenn runner.rechts existiert:
                if (runner.rechts != null) {
                    runner = runner.rechts;
                    // Setze runner auf runner.rechts
                } else {
                    // Setze runner.rechts auf neuen Knoten
                    runner.rechts = neuerKnoten;
                    fertig = true;
                }
            }
        }
    }

    @Override
    public String toString() {
        return wurzel.toString();
    }

    public String toStringSortiert() {
        return wurzel.toStringSortiert();
    }

    public static void main(String[] args) {
        BinSuchBaum b = new BinSuchBaum("8 5 4 6 10 2 1 3 7");
        System.out.println(b.toStringSortiert());
    }
}
