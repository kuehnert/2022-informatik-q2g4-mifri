package network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class EchoClientThreaded {
    private Socket socket;
    private BufferedWriter writer;
    private Scanner tastatur;

    public EchoClientThreaded() {
        tastatur = new Scanner(System.in);
        BufferedReader reader;

        try {
            socket = new Socket("10.2.129.148", 10007);
            reader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer =
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // starte Empfangsthread
            new EchoClientThread(reader).start();
            senden();
        } catch (IOException e) {
            System.err.println("Fehler beim Verbinden!");
            System.exit(1);
        }
    }

    private void senden() {
        System.out.println("Starte Senden...");
        System.out.println("Gib QUIT ein, um zu beenden");

        while (true) {
            // 1. Empfange eine Eingabe über die Tastatur
            String eingabe = tastatur.nextLine();

            // 2. Gucke ob wir beenden sollen
            if (eingabe.equalsIgnoreCase("quit")) {
                // TODO: stoppe Empfangsthread
                // wenn ja, beende Programm
                break;
            }

            try {
                writer.write(eingabe + "\n");
                writer.flush();
            } catch (IOException e) {
                break;
            }
        }

        // 3. Ansonsten: schicke Eingabe an Server
        try {
            socket.close();
        } catch (IOException e) {
        }

        System.out.println("Exiting...");
        System.exit(0); // Ende ohne Fehler
    }

    public static void main(String[] args) {
        new EchoClientThreaded();
    }
}

class EchoClientThread extends Thread {
    private BufferedReader reader;

    public EchoClientThread(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        while (true) {
            String response = null;
            try {
                response = reader.readLine();
            } catch (IOException e) {
                break;
            }

            System.out.println("Empfangen: " + response);
        }
    }
}