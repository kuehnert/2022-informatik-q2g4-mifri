package threading;

import java.util.Random;

public class Threads1 {
    public static void druckeDauer(String name, long startzeit) {
        long endzeit = System.currentTimeMillis();
        long dauer = endzeit - startzeit;
        System.out.println("Dauer " + name + ": " + dauer + " ms");
    }

    public static void main(String[] args) {
        long startzeit = System.currentTimeMillis();

        Thread meinThread1 = new BeispielThread(1, startzeit);
        Thread meinThread2 = new BeispielThread(2, startzeit);
        Thread meinThread3 = new BeispielThread(3, startzeit);

        meinThread1.start();
        meinThread2.start();
        meinThread3.start();

        System.out.println(meinThread1.isAlive());
        System.out.println(meinThread2.isAlive());
        System.out.println(meinThread3.isAlive());

        // Stelle sicher, dass alle Threads beendet sind
        try {
            meinThread1.join();
            meinThread2.join();
            meinThread3.join();
            druckeDauer("HAUPTPROGRAMM", startzeit);
        } catch (InterruptedException e) {
            System.err.println("Würg");
        }

        System.out.println(meinThread1.isAlive());
        System.out.println(meinThread2.isAlive());
        System.out.println(meinThread3.isAlive());

        // Programm endet erst, wenn alle Threads beendet sind
    }
}

// -----------------------------------------------------------

class BeispielThread extends Thread {
    private static Random generator = new Random();
    private long startzeit, endzeit;
    private int nummer;

    public BeispielThread(int nummer, long startzeit) {
        this.nummer = nummer;
        this.startzeit = startzeit;
    }

    @Override
    public void run() {
        int dauer = (int) (generator.nextInt(5000));
        System.out.println("Ich bin Thread " + nummer + ", schlafe " + (dauer / 1000) + " Sekunden...");

        // Schlafe 1 Sekunde, simuliere schwere Arbeit
        try {
            sleep(dauer);
        } catch (InterruptedException e) {
            System.err.println("Thread unterbrochen!");
        }

        Threads1.druckeDauer("Thread " + nummer, startzeit);
    }
}