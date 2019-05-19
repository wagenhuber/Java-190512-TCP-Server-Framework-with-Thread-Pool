package lokal.wagenhuber.guenther;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) {

        Socket socket = null;

        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            socket = new Socket(host, port);

            //BufferedReader: Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //PrintWriter: Prints formatted representations of objects to a text-output stream (Zeichenorientiert)
            //Zeichen (PrintWriter) werden in Bytes umgewandelt (OutputSTREAM) und übertragen
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String msg = in.readLine();
            System.out.println(msg);
            String line;
            while (true) {
                line = input.readLine();
                if (line == null || line.equals("q")) {
                    break;
                }
                out.println(line);
                System.out.println(in.readLine());

            }
            in.close();
            out.close();
            input.close();
        } catch (IOException e) {
            System.err.println(e);
        } finally {

            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
            }
        }

    }

}
