package network;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {
    public static void main(String[] args) {
        // 1. Der Server öffnet sein Geschäft
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10007);
        } catch (IOException e) {
            System.err.println("FEHLER: Server konnte nicht erstellt werden.");
            System.exit(1);
        }

        System.out.println("Daytime-Server gestartet.");

        while (true) {
            // 2. Warte auf einen Kunden und verbinde Dich mit ihm
            try {
                Socket clientVerbindung = serverSocket.accept();
                System.out.println("Neue Verbindung mit Client");
                PrintWriter writer =
                        new PrintWriter(new OutputStreamWriter(clientVerbindung.getOutputStream()));
                String uhrzeit = new Date().toString();
                writer.println(uhrzeit);
                writer.flush();
                System.out.println(clientVerbindung.getInetAddress().toString().substring(1) + ": " + uhrzeit);
                clientVerbindung.close();
            } catch (IOException e) {
                System.err.println("FEHLER: Verbindung mit Client konnte " +
                        "nicht erstellt werden.");
                try {
                    serverSocket.close();
                    break;
                } catch (IOException e2) {
                }
            }
        }

        System.out.println("Server beendet.");
    }
}
