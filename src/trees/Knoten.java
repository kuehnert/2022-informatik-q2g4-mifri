package trees;

public class Knoten {
    public static final int LNR = 0;
    public static final int LRN = 1;
    public static final int NLR = 2;
    public int data;
    public Knoten links;
    public Knoten rechts;

    public Knoten(int data) {
        this.data = data;
    }

    public Knoten(int data, Knoten links, Knoten rechts) {
        this.data = data;
        this.links = links;
        this.rechts = rechts;
    }

    public String toString() {
        return "Knoten<" + data + ">";
    }

    public String traverse(int methode) {
        switch (methode) {
            case NLR:
                return toNLR();
            case LRN:
                return toLRN();
            default:
                return toLNR();
        }
    }

    public String toNLR() {
        String out = "" + data;

        // linkes Kind existiert
        if (links != null) { // null != 0
            // Füge den String vom (ganzen) linken TB an
            out = out + " <" + links.toNLR() + ">";
        }

        // wenn rechter TB existiert
        if (rechts != null) {
            // Füge den String vom (ganzen) rechten TB an
            out = out + " [" + rechts.toNLR() + "]";
        }

        return out;
    }

    public String toLNR() {
        String out = "";

        // 1. linkes Kind existiert
        if (links != null) { // null != 0
            // Füge den String vom (ganzen) linken TB an
            out = out + links.toLNR();
        }

        // 2. Node
        out = out + " " + data;

        // 3. wenn rechter TB existiert
        if (rechts != null) {
            // Füge den String vom (ganzen) rechten TB an
            out = out + rechts.toLNR();
        }

        return out;
    }

    public String toLRN() {
        String out = "";

        // 1. linkes Kind existiert
        if (links != null) { // null != 0
            // Füge den String vom (ganzen) linken TB an
            out = out + links.toLRN();
        }

        // 2. wenn rechter TB existiert
        if (rechts != null) {
            // Füge den String vom (ganzen) rechten TB an
            out = out + rechts.toLRN();
        }

        // 3. Node
        out = out + " " + data;

        return out;
    }

    public Knoten suche(int gesucht) {
        // Trivialfall
        if (data == gesucht) {
            return this;
        }

        // Rekursiven Fälle
        // Wenn linker TB existiert...
        if (links != null) {
            // Durchsuche den Linken TB
            Knoten erg = links.suche(gesucht);
            if (erg != null) {
                return erg;
            }
        }

        if (rechts != null) {
            return rechts.suche(gesucht);
        }

        return null;
    }

    // Gesamtzahl der Knoten im Baum → Knoten
    public int anzahl() {
        // Lösungsidee
        // Die Anzahl ist die Anzahl von Knoten im linken TB
        // plus die Anzahl Knoten im rechten TB plus 1
        int anzahl = 1;

        anzahl += links == null ? 0 : links.anzahl();
        anzahl += rechts == null ? 0 : rechts.anzahl();

        return anzahl;
    }

    // Ist die Anzahl der Kanten von der Wurzel zum
    // „tiefsten“ Blatt
    public int tiefe() {
        int t1 = links != null ? links.tiefe() + 1 : 0;
        int t2 = rechts != null ? rechts.tiefe() + 1 : 0;

        return Integer.max(t1, t2);
    }
}
