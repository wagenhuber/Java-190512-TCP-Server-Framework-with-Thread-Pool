//Abstrakter Handler implentiert nicht die RUN-Methode, sondern überlässt diese dem konktreten Handler -> EchoServer
//Abstrakter Handler startet lediglich neuen Thread und führt RUN-Methode aus
//=Elternklasse von EchoHandler

package lokal.wagenhuber.guenther;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

public abstract class AbstractHandler implements Runnable{

    private Socket clientSocket;

    public Socket getClientSocket(){
        return this.clientSocket;
    }


    public void handle(Socket clientSocket, ExecutorService pool){
        this.clientSocket = clientSocket;
        pool.execute(this);
    }
    //Implementierung der RUN-Methode erfolgt im konkreten Handler -> in diesem Fall Klasse "EchoHandler"
    public abstract void run();
}
