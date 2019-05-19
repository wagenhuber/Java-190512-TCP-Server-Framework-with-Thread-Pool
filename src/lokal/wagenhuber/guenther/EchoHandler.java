//Konkrete Implementierung der Funktionalität

package lokal.wagenhuber.guenther;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoHandler extends AbstractHandler {

    @Override
    public void run() {
        Socket clientSocket = getClientSocket();
        String clientAddress = clientSocket.getInetAddress().getHostAddress();
        int clientPort = clientSocket.getPort();
        System.out.println("Verbindung zu " + clientAddress + " mit Port: "+ clientPort + " aufgebaut.");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Server bereit...");
        String input;
        while ((input = in.readLine()) != null){
            out.println(input);
        }
            in.close();
        out.close();

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (clientSocket != null){
                    clientSocket.close();
                }

            }catch (IOException e){

            }
            System.out.println("Verbindung zu "+ clientAddress +":"+clientPort+"  abgebaut");
        }
    }
}
