package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

// TODO: Name und Passwort über Parameter einlesen

public class POP3Client {
    private static final String HOST = "10.2.129.148";
    private static final int PORT = 10110;
    private static final String USERNAME = "anna";
    private static final String PASSWORD = "geheim";

    Socket socket;
    Scanner reader;
    PrintWriter writer;

    public POP3Client() {
        verbinden();
        anmelden();
        listeMails();
        trenneVerbindung();
    }

    public void verbinden() {
        System.out.println("verbinden()");

        try {
            socket = new Socket(HOST, PORT);
            reader = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);

            String antwort = reader.nextLine();
            System.out.println(antwort);
        } catch (IOException e) {
            System.out.println("Fehler beim Verbinden mit dem POP3-Server. " + HOST + " an Port " + PORT + ". Beende Programm.");
            System.exit(1);
        }
    }

    /**
     * Melde Dich mit Namen "anna" und Passwort "geheim" an Server sendet "+..."
     * bei Erfolg und "-..." bei Fehler "dsd".startsWith("+") => hat geklappt
     * <p>
     * Sende USER anna Sende PASS geheim
     */
    public void anmelden() {
        // Sende Benutzernamen
        System.out.println("anmelden()");
        writer.println("USER " + USERNAME);
        reader.nextLine();
        String antwort = reader.nextLine();
        System.out.println(antwort);

        if (antwort.startsWith("-")) {
            System.err.println("Fehler beim Benutzernamen");
            System.exit(1);
        }

        // Sende Passwort
        writer.println("PASS " + PASSWORD);
        reader.nextLine();
        antwort = reader.nextLine();
        System.out.println(antwort);

        if (antwort.startsWith("-")) {
            System.err.println("Fehler beim Passwort");
            System.exit(1);
        }
    }

    public void listeMails() {
        System.out.println("listeMails()");

        writer.println("LIST");
        String antwort = "";
        ArrayList<Integer> mailIds = new ArrayList<>();

        do {
            antwort = reader.nextLine();
            if (antwort.equals("")) {
                // Ignoriere leere Zeilen
                continue;
            } else if (antwort.matches("^[0-9]+ .+")) {
                // antwort beginnt mit >= 1 Ziffern
                String idImString = antwort.split(" ")[0];
                int id = Integer.parseInt(idImString);
                mailIds.add(id);
            }

            System.out.println(antwort);
        } while (!antwort.equals("."));

        System.out.println("Hole Mails mit IDs " + mailIds);
        for (int id : mailIds) {
            leseMail(id);
        }

        System.out.println("ENDE listeMails()");
    }

    /**
     * Hole Inhalt von Mail mit Nummer <index>
     *
     * @param index
     */
    public void leseMail(int id) {
        System.out.println("leseMail(" + id + ")");
    }

    public void trenneVerbindung() {
        System.out.println("trenneVerbindung");
        try {
            socket.close();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        new POP3Client();
    }
}
