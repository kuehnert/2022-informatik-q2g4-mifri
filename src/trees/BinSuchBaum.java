package trees;

import java.util.Arrays;

public class BinSuchBaum extends BinBaum {
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
    public Knoten suche(int gesucht) {
        Knoten runner = wurzel;

        while (true) {
            if (gesucht == runner.data) {
                return runner;
            } else if (gesucht < runner.data) {
                if (runner.links == null) {
                    return null;
                } else {
                    runner = runner.links;
                }
            } else {
                // gesucht ist größer als die Zahl am akt. Knoten
                if (runner.rechts == null) {
                    return null;
                } else {
                    runner = runner.rechts;
                }
            }
        }
    }

    public Knoten suche2(int gesucht) {
        Knoten runner = wurzel;

        while (runner != null && runner.data != gesucht) {
            if (gesucht < runner.data) {
                runner = runner.links;
            } else {
                // gesucht ist größer als die Zahl am akt. Knoten
                runner = runner.rechts;
            }
        }

        return runner;
    }

    public static void main(String[] args) {
        BinSuchBaum bsb = new BinSuchBaum("8 5 4 6 10 2 1 3 7");
        bsb.wurzel.toString();
        System.out.println(bsb.traverse(Knoten.LNR));
        System.out.println("suche1: " + bsb.suche(6));
        System.out.println("suche2: " + bsb.suche2(6));
        System.out.println("suche1: " + bsb.suche(11));
        System.out.println("suche2: " + bsb.suche2(11));
    }
}
