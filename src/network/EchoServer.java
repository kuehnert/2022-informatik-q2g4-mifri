package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    ServerSocket serverSocket;

    public EchoServer() {
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
                behandleClient(clientVerbindung);
                clientVerbindung.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void behandleClient(Socket clientVerbindung) {
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

    public static void main(String[] args) {
        new EchoServer().run();
    }
}
