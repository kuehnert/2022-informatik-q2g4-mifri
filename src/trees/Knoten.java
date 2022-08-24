package trees;

class Knoten {
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
        String out = "" + data;

        // linkes Kind existiert
        if (links != null) { // null != 0
            // Füge den String vom (ganzen) linken TB an
            out = out + " <" + links.toString() + ">";
        }

        // wenn rechter TB existiert
        if (rechts != null) {
            // Füge den String vom (ganzen) rechten TB an
            out = out + " [" + rechts.toString() + "]";
        }

        return out;
    }

    public String toStringSortiert() {
        String out = "";

        // linkes Kind existiert
        if (links != null) { // null != 0
            // Füge den String vom (ganzen) linken TB an
            out = out + links.toStringSortiert();
        }

        out = out + " " + data;

        // wenn rechter TB existiert
        if (rechts != null) {
            // Füge den String vom (ganzen) rechten TB an
            out = out + rechts.toStringSortiert();
        }

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
