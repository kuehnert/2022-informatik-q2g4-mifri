package testing;

import trees.Beispielbaum;
import trees.BinBaum;
import trees.Knoten;

public class TestBinBaum {
    public static void testBeispielbaum() {
        BinBaum bb = new Beispielbaum();
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

        System.out.println(bb.anzahl());
    }

    public static void testBinBaum() {

        BinBaum bn = new BinBaum(13);
        bn.getWurzel().links = new Knoten(5);
        bn.getWurzel().rechts = new Knoten(27);
        System.out.println(bn);

        System.out.println(new BinBaum().anzahl());
    }

    public static void main(String[] args) {
        testBinBaum();
    }
}
