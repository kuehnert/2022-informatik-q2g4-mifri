package trees;

public class BinBaum {
    Knoten wurzel;

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

        System.out.println(wurzel);

        // Bottom-Up
        Knoten n5 = new Knoten(5, new Knoten(11), null);
        Knoten n1 = new Knoten(1, new Knoten(17), n5);
        Knoten n0 = new Knoten(0, null, new Knoten(27));
        Knoten n23 = new Knoten(23, n0, new Knoten(18));
        wurzel = new Knoten(13, n1, n23);
        System.out.println(wurzel);
    }

    public static void main(String[] args) {
        new BinBaum();
    }
}
