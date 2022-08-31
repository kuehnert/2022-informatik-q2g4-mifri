package trees;

public class BinBaum {
    public Knoten getWurzel() {
        return wurzel;
    }

    protected Knoten wurzel;

    public BinBaum() {
        // Top-Down
        wurzel = new Knoten(13);
        wurzel.links = new Knoten(1);
        wurzel.links.links = new Knoten(17);
        wurzel.links.rechts = new Knoten(5);
        wurzel.links.rechts.links = new Knoten(18);

        wurzel.rechts = new Knoten(23);
        wurzel.rechts.links = new Knoten(0);
        wurzel.rechts.links.rechts = new Knoten(27);
        wurzel.rechts.rechts = new Knoten(18);
        // System.out.println(wurzel);

        // Bottom-Up
        Knoten n5 = new Knoten(5, new Knoten(11), null);
        Knoten n1 = new Knoten(1, new Knoten(17), n5);
        Knoten n0 = new Knoten(0, null, new Knoten(27));
        Knoten n23 = new Knoten(23, n0, new Knoten(18));
        wurzel = new Knoten(13, n1, n23);
        // System.out.println(wurzel);
    }

    // sucht nach einer Zahl gesucht
    // wenn sie vorhanden ist, gib ihren Knoten zurück
    // sonst null
    public Knoten suche(int gesucht) {
        return wurzel.suche(gesucht);
    }

    // Gesamtzahl der Knoten im Baum → Knoten
    public int anzahl() {
        return wurzel.anzahl();
    }

    // Ist die Anzahl der Kanten von der Wurzel zum
    // „tiefsten“ Blatt
    public int tiefe() {
        return wurzel.tiefe();
    }

    @Override
    public String toString() {
        return traverse(Knoten.LNR);
    }

    public String traverse(int methode) {
        return wurzel.traverse(methode);
    }

    public static void main(String[] args) {
        BinBaum bb = new BinBaum();
        System.out.println(bb.suche(13));
        System.out.println(bb.suche(1));
        System.out.println(bb.suche(17));
        System.out.println(bb.suche(5));
        System.out.println(bb.suche(11));
        System.out.println(bb.suche(23));
        System.out.println(bb.suche(0));
        System.out.println(bb.suche(27));
        System.out.println(bb.suche(18));
        System.out.println(bb.suche(-99999));
        System.out.println(bb.suche(99999));
        System.out.println(bb.toString());
    }
}
