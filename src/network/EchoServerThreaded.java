package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerThreaded {
    ServerSocket serverSocket;

    public EchoServerThreaded() {
        try {
            serverSocket = new ServerSocket(10007);
        } catch (IOException e) {
            System.err.println("FEHLER beim Starten vom EchoServer auf Port " + "10007");
            System.exit(1);
        }
    }

    public void run() {
        while (true) {
            // Warte auf eine Verbindung von einem Client
            try {
                Socket clientVerbindung = serverSocket.accept();
                System.out.println("Neue Verbindung mit " + clientVerbindung.getInetAddress());

                EchoServerThread clientThread =
                        new EchoServerThread(clientVerbindung);
                clientThread.start();
                // clientVerbindung.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new EchoServerThreaded().run();
    }
}

class EchoServerThread extends Thread {
    private Socket clientVerbindung;

    public EchoServerThread(Socket clientVerbindung) {
        this.clientVerbindung = clientVerbindung;
    }

    public void run() {
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(clientVerbindung.getInputStream()));
            PrintWriter writer =
                    new PrintWriter(new OutputStreamWriter(clientVerbindung.getOutputStream()), true);

            writer.println("Willkommen beim EchoServer. Beende mit QUIT");

            while (true) {
                // Warte auf einen Input vom Client
                String response = reader.readLine();
                if (response == null || response.equalsIgnoreCase("QUIT")) {
                    // für die 2. Überprüfung können wir uns darauf
                    // verlassen, dass response nicht null ist
                    break;
                }

                System.out.println("Empfangen: " + response);

                // Sende den Input wieder zurück
                writer.println(response);
            }

            System.out.println("Verbindung zu Client " + clientVerbindung.getInetAddress() + " getrennt.");
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen vom Client");
            // TODO: Trenne Verbindung und warte auf den nächsten Client
            System.exit(1);
        }
    }
}

/*
 * TODO:
 *  1. Erstelle class EchoServerThread
 *  2. Dort eine Methode run()
 *  3. Dort kommt alles von behandleClient hinein
 *  4. Baue eine Konstruktor und übergebe die clientVerbindung
 *  5. Das war's
 *  6. (Fast)
 */

