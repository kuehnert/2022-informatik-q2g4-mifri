package network.wiese;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Quizserver {
    public static final int PORT = 10667;
    private ServerSocket serverSocket;
    private ArrayList<QuizserverThread> quizClientThreads;
    private String antwort;
    private Random random = new Random();

    /*
        -Bei dem ersten Beitritt wird eine Frage generiert
        -wenn die Frage beantwortet ist kommt eine neue
        -falsche antworten werden allen im chat angezeit
        - bei richtiger antwort kriegt der der es errät eine personalisierte Antwort
        - alle anderen kriegen die Nachricht: "UserXYZ hat die Frage richtig beantwortet"

        NOTIZ: sendeAnAlle kriegt vom Thread den Namen und nicht mehr den Socket (um client.getName anzuwenden), damit mann ServerNachrichten verschicken kann,
        welche kein Socket als absender haben
        (Das Problem war dass sendeAnAlle ein socket und eine nachricht benötigte, wenn man eine Nachricht aus der Hauptklasse Quizserver schicken wollte musste man ein Socket
        angeben, was natürlich nicht funkioniert und keinen Sinn ergeben hat.)

        BESTEHENDES PROBLEM:
        Nachrichten werden zu oft verschickt im chat. Meine Vermutung: Thread-0 (die erste Verbindung) ist zu oft in der ArrayList gespeichert
        Unten ist die Ausgabe wenn man in der If-schleide bei sendeAnAlle() empfaenger.getName() ausgeben lässt

            Client Nr. 1 verbunden!
            Thread-0
            Thread-0
            Thread-0
            User 1: 40
            Thread-0
            User 1: hallo
            Thread-0
     */

    public Quizserver(){
        try {
            serverSocket = new ServerSocket(PORT);
            quizClientThreads = new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Fehler beim Öffnen von Port " + PORT);
            System.exit(1);
        }
    }

    public void run(){
        System.out.println("ChatServer gestartet auf Port " + PORT);

        while (true) {
            try {
                // Warte auf Client
                Socket neueClientSocket = serverSocket.accept();
                String name = "User " + (quizClientThreads.size() + 1);
                // Wenn verbunden, starte einen ClientThread
                var neuerClient = new QuizserverThread(this, name,
                        neueClientSocket);
                quizClientThreads.add(neuerClient);
                neuerClient.start();
                System.out.println("Client Nr. " + quizClientThreads.size() + " " +
                        "verbunden!");
                generiereFrage();
            } catch (IOException e) {
                System.err.println("Fehler bei Verbindung mit Client");
            }
        }
    }

    public void generiereFrage(){
        int x = random.nextInt(100);
        int y = random.nextInt(100);

        String fragestellung = "Was ist " + x + " + " + y + "?";
        antwort = String.valueOf(x+y);

        sendeAnAlle("server", fragestellung);
    }

    public String getAntwort(){
        return antwort;
    }

    public void sendeAnAlle(String absender, String inhalt){
        for(var empfaenger: quizClientThreads){
            if(empfaenger.getName().equals(absender)){

            }else{
                empfaenger.sende(absender + ": " + inhalt);
            }
        }
    }

    public static void main(String[] args) {
        new Quizserver().run();
    }

}

class QuizserverThread extends Thread{
    private Quizserver server;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String name;

    private boolean chatAktiv = true;

    public QuizserverThread(Quizserver server, String name,  Socket clientSocket){
        this.server = server;
        this.clientSocket = clientSocket;
        this.name = name;

        try {
            name = clientSocket.getInetAddress().getCanonicalHostName();
            reader =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer =
                    new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            // TODO
            System.err.println("Fehler beim Schreiben auf dem Client " + name);
            System.exit(2);
        }
    }


    public void sende(String inhalt){
        writer.println(inhalt);
        writer.flush();
    }

    public void run() {
        try {
            reader =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer =
                    new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            writer.println("Willkommen beim Quizserver, " + name + ". Du kannst den Quizserver jederzeit mit >QUIT< verlassen.");

            while (true) {
                // Warte auf einen Input vom Client
                String response = reader.readLine();
                if (response == null || response.equalsIgnoreCase("QUIT")) {
                    // für die 2. Überprüfung können wir uns darauf
                    // verlassen, dass response nicht null ist
                    break;
                }

                if(response.equals(server.getAntwort()) ){
                    writer.println("Herzlichen Glückwunsch! Du hast das Rätsel gelöst!");
                    server.sendeAnAlle(name, "Der User " + name + " hat das Rätsel gelöst! Die Antwort war: " + server.getAntwort());
                    server.generiereFrage();
                }

                System.out.println(name + ": " + response);
                server.sendeAnAlle(name, response);
                // Sende den Input wieder zurück

                writer.println(response);
            }

            System.out.println(name + " hat die Verbindung getrennt (" + clientSocket.getInetAddress() + ").");
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen vom Client");
            // TODO: Trenne Verbindung und warte auf den nächsten Client
            System.exit(1);
        }
    }

}