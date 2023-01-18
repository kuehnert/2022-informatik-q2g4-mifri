package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DaytimeClient {
    // InputStream inputStream = sock.getInputStream();
    // InputStreamReader isreader = new InputStreamReader(inputStream);
    // BufferedReader reader = new BufferedReader(isreader);

    public static void main(String[] args) {
        try {
            // Socket sock = new Socket("10.2.129.148", 10013);
            Socket sock = new Socket("time.fu-berlin.de", 13);
            Scanner scanner = new Scanner(sock.getInputStream());

            String input = scanner.nextLine();
            System.out.println("Empfangen: " + input);

            sock.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
