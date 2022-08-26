package trees;

class Knoten {
    public static final int LNR = 0;
    public static final int LRN = 1;
    public static final int NLR = 2;
    int data;
    Knoten links;
    Knoten rechts;

    public Knoten(int data) {
        this.data = data;
    }

    public Knoten(int data, Knoten links, Knoten rechts) {
        this.data = data;
        this.links = links;
        this.rechts = rechts;
    }

    public String toString() {
        return toString(LNR);
    }

    public String toString(int methode) {
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
}
