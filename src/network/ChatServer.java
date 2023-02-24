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
                ClientChatThread clientThread = new ClientChatThread(this, name,
                        clientVerbindung);
                clients.add(clientThread);
                System.out.println("Chatter Nr. " + clients.size() + ": " + clientVerbindung.getInetAddress());
                clientThread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendeAnAlle(ClientChatThread client, String botschaft) {
        for (ClientChatThread empfaenger: clients) {
            empfaenger.sende(client.getName() + ": " + botschaft);
        }
    }

    public static void main(String[] args) {
        new ChatServer().run();
    }
}

class ClientChatThread extends Thread {
    private ChatServer server;
    private Socket clientVerbindung;
    private String name;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientChatThread(ChatServer server, String name,
                            Socket clientVerbindung) {
        this.server = server;
        this.name = name;
        this.clientVerbindung = clientVerbindung;
    }

    public void sende(String botschaft) {
        writer.println(botschaft);
    }

    public void run() {
        try {
            reader =
                    new BufferedReader(new InputStreamReader(clientVerbindung.getInputStream()));
            writer =
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

                System.out.println(name + ": " + response);
                server.sendeAnAlle(this, response);
                // Sende den Input wieder zurück

                writer.println(response);
            }

            System.out.println(name + " hat die Verbindung getrennt (" + clientVerbindung.getInetAddress() + ").");
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
