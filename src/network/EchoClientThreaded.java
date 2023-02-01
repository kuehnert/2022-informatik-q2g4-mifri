package network;

import java.io.IOException;
import java.net.Socket;

public class EchoClientThreaded {
    private Socket socket;

    public EchoClientThreaded() {
        try {
            socket = new Socket("10.2.129.148", 10007);
        } catch (IOException e) {
            System.exit(1);
        }

        // starte Empfangsthread
        senden();
    }

    private void senden() {
        while (true) {
            System.out.println("Sende...");
        }
    }

    public static void main(String[] args) {

    }
}
