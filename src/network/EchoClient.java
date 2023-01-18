package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("10.2.129.148", 10007);
            Scanner reader = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),
                    true);
            Scanner tastatur = new Scanner(System.in);

            // Begrüßung
            String networkInput = reader.nextLine();
            System.out.println("Empfangen: " + networkInput);
            // BUG: Server sendet eine Leerzeile zu viel
            networkInput = reader.nextLine();

            boolean weiter = true;
            while (weiter) {
                System.out.print("Eingabe: ");
                String userInput = tastatur.nextLine();

                if (userInput.equalsIgnoreCase("quit")) {
                    weiter = false;
                } else {
                    // Sende Benutzereingabe
                    writer.println(userInput);

                    // Empfange
                    networkInput = reader.nextLine();
                    System.out.println("Empfangen: " + networkInput);
                }
            }

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
