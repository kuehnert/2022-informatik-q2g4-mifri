package trees;

class Knoten {
    int data;
    Knoten links;
    Knoten rechts;

    public Knoten(int data) {
        this.data = data;
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
}
