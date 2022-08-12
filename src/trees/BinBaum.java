package trees;

public class BinBaum {
    Knoten<Integer> wurzel;

    public BinBaum() {
        wurzel = new Knoten<Integer>(13);
        System.out.println(wurzel);
    }

    public static void main(String[] args) {
        new BinBaum();
    }
}

class Knoten<T> {
    T data;
    Knoten<T> links;
    Knoten<T> rechts;

    public Knoten(T data) {
        this.data = data;
    }

    public String toString() {
        return data.toString();
    }
}
