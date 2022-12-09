package spielundspass;

public class Reihe {
    public static void reihe() {
        int nenner = 1;
        int vorzeichen = 1;
        double summe = 0;

        while (nenner < 1000) {
            summe = summe + 4.0 * vorzeichen / nenner;
            System.out.printf("%d: %.6f", nenner, summe);

            nenner = nenner +2;
            vorzeichen = vorzeichen * -1;

        }

    }

    public static void main(String[] args) {
        reihe();
    }

}
