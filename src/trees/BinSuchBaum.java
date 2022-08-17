package trees;

import java.util.Arrays;

public class BinSuchBaum {
    Knoten wurzel;

    public BinSuchBaum(String zahlenString) {
        String[] zahlen = zahlenString.split(" ");
        System.out.println(Arrays.toString(zahlen));
        wurzel = new Knoten(Integer.parseInt(zahlen[0]));

        for (int i = 1; i < zahlen.length; i++) {
            int zahl = Integer.parseInt(zahlen[i]);
            Knoten runner = wurzel;

            // Vergleiche runner.data mit zahl
            // Wenn zahl kleiner ist:
            //     Wenn runner.links existiert:
            //         Setze runner auf runner.links
            //     Sonst:
            //         Erstelle neuen Knoten mit zahl
            //         Setze runner.links auf neuen Knoten
            // Sonst:
            //     Wenn runner.rechts existiert:
            //         Setze runner auf runner.rechts
            //     Sonst:
            //         Erstelle neuen Knoten mit zahl
            //         Setze runner.rechts auf neuen Knoten

        }

    }

    @Override
    public String toString() {
        return wurzel.toString();
    }

    public static void main(String[] args) {
        BinSuchBaum b = new BinSuchBaum("8 5 4 6 10 2 1 3 7");
        System.out.println(b);
    }
}
