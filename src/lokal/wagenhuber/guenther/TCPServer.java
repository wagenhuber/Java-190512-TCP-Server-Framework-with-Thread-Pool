//Klasse stellt den TCP-Server dar, welcher eine beliebige Funktionalität ausführen kann
//Übergabe der Funktion via Class<?> handlerClass
//Funktion als Beispiele "EchoServer" in seperater Klasse implementiert


package lokal.wagenhuber.guenther;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer extends Thread{


    //By writing Class<?>, you're declaring a Class object which can be of any type
    private Class<?> handlerClass;
    private ServerSocket serverSocket;
    //interface ExecutorService: An Executor that provides methods to manage termination and methods that can produce a Future for tracking progress of one or more asynchronous tasks.
    private ExecutorService pool;

    //Die konkrete Handlerklasse wird von Klasse EchoServer (aufrufende Klasse) übergeben
    public TCPServer(int port, Class<?> handlerClass) throws IOException{
        this.handlerClass = handlerClass;
        serverSocket = new ServerSocket(port);

        //Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
        pool = Executors.newCachedThreadPool();
        
    }


    @Override
    public void run() {
        try {
            while (true){
                //Beim Aufruf von stopServer() wird eine Socket-Exception ausgelöst
                System.out.println("TCPServer run()");
                Socket clientSocket = serverSocket.accept();
                handle(clientSocket);
            }
        } catch (Exception e) {
            System.err.println();
        }
    }

    public void stopServer(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }

    private void handle(Socket clientSocket) throws Exception{
        AbstractHandler handler = ((AbstractHandler) handlerClass.newInstance());
        System.out.printf(handler.toString());
        handler.handle(clientSocket, pool);
    }
}
