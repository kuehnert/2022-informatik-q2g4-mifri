package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ServerSocket serverSocket;
    ArrayList<ClientChatThread> clients;

    public ChatServer() {
        try {
            serverSocket = new ServerSocket(10007);
            clients = new ArrayList<>();
            System.out.println("ChatServer gestartet auf Port 10007");
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
                String name = "User " + (clients.size() + 1);
                ClientChatThread clientThread = new ClientChatThread(name,
                        clientVerbindung);
                clients.add(clientThread);
                System.out.println("Chatter Nr. " + clients.size() + ": " + clientVerbindung.getInetAddress());
                clientThread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().run();
    }
}

class ClientChatThread extends Thread {
    private Socket clientVerbindung;
    private String name;

    public ClientChatThread(String name, Socket clientVerbindung) {
        this.name = name;
        this.clientVerbindung = clientVerbindung;
    }

    public void run() {
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(clientVerbindung.getInputStream()));
            PrintWriter writer =
                    new PrintWriter(new OutputStreamWriter(clientVerbindung.getOutputStream()), true);

            writer.println("Willkommen beim EchoServer, " + name + ". Beende " +
                    "mit " + "QUIT");

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
            clientVerbindung.close();
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen vom Client");
            // TODO: Trenne Verbindung und warte auf den nächsten Client
            System.exit(1);
        }
    }
}
