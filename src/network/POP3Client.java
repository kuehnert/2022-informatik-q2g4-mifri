package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class POP3Client {
    Socket socket;
    Scanner reader;
    PrintWriter writer;
    Scanner tastatur;

    public POP3Client() {
        try {
            socket = new Socket("10.2.129.148", 10110);
            reader = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);
            tastatur = new Scanner(System.in);
        } catch (IOException e) {
            System.out.println("Fehler beim Verbinden mit dem POP3-Server. " +
                    "Beende Programm.");
            System.exit(1);
        }
    }

    /**
     * Melde Dich mit Namen "anna" und Passwort "geheim" an
     * Server sendet "+..." bei Erfolg und "-..." bei Fehler
     * "dsd".startsWith("+") => hat geklappt
     *
     * Sende USER anna
     * Sende PASS geheim
     */
    public void anmelden() {

    }

    public void listeMails() {

    }

    /**
     * Hole Inhalt von Mail mit Nummer <index>
     * @param index
     */
    public void leseMail(int index) {

    }

    //     socket.close();

    public static void main(String[] args) {
        new POP3Client();
    }
}
