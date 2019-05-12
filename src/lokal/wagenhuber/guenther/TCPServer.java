package lokal.wagenhuber.guenther;

import com.sun.tools.internal.ws.wsdl.parser.AbstractExtensionHandler;

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
    private ExecutorService pool;

    public TCPServer(int port, Class<?> handlerClass) {
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
                Socket clientSocket = serverSocket.accept();
                handle(clientSocket);
            }
        } catch (IOException e) {
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

    private void handle(Socket clientSocket){
        AbstractHandler handler = ((AbstractHandler) handlerClass.newInstance());
        handler.handle(clientSocket, pool);
    }
}
