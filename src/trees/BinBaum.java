package trees;

public class BinBaum {
    protected Knoten wurzel;

    public BinBaum() {
        wurzel = null;
    }

    public BinBaum(int zahl) {
        setWurzel(zahl);
    }

    // sucht nach einer Zahl gesucht
    // wenn sie vorhanden ist, gib ihren Knoten zurück
    // sonst null
    public Knoten suche(int gesucht) {
        return wurzel.suche(gesucht);
    }

    // Gesamtzahl der Knoten im Baum → Knoten
    public int anzahl() {
        return wurzel == null ? 0 : wurzel.anzahl();
    }

    // Ist die Anzahl der Kanten von der Wurzel zum
    // „tiefsten“ Blatt
    public int tiefe() {
        return wurzel == null ? -1 : wurzel.tiefe();
    }

    @Override
    public String toString() {
        return traverse(Knoten.LNR);
    }

    public String traverse(int methode) {
        return wurzel.traverse(methode);
    }

    public Knoten getWurzel() {
        return wurzel;
    }

    public void setWurzel(int zahl) {
        if (wurzel == null) {
            wurzel = new Knoten(zahl);
        }
    }
}
