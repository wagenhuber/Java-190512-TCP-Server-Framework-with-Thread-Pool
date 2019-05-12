//Klasse dient zum Starten des Servers und zur Festlegung einer Funktionalität, die durch den Server ausgeführt werden soll

package lokal.wagenhuber.guenther;

import java.io.IOException;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);

        // Erzeugen eines TCP-Servers und Übergabe der funktionalen Klasse -> in diesem Fall "EchoHandler"
        // Es könnten beliebige funktionale Klassen erstellt werden, und dem Server zur Ausführung übergeben werden
        TCPServer tcpServer = new TCPServer(port, EchoHandler.class);

        tcpServer.start();
        System.out.println("Server gestartet...");

        //Lesen von der Standardeingabe, passend zur Funktionalität der Klasse "Echohandler"
        System.in.read();

        tcpServer.stopServer();
        System.out.println("Server gestoppt...");
    }

}
