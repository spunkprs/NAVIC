package multithreading.buildingbasicwebserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    /*
    Content for writing down a web-server is present here :
    https://www.youtube.com/watch?v=f9gUFy-9uCM&list=PLsdq-3Z1EPT3VjDhjMb5yBsgn0wn2-fjp&index=2
    https://www.youtube.com/watch?v=FqufxoA4m70

    SocketTimeout : Amount of time for which Server will wait for some client to connect to it
    ConnectionTimeout : Amount of time post which Server will wait for client activity, it won't wait forever for the
    client to perform some action for which Server is waiting upon
    * */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            //serverSocket.setSoTimeout(3000);
            System.out.println("Web server started && waiting for clients to connect on port 8080 !!" );
            while (true) {
                //accept is the blocking call
                Socket client = serverSocket.accept();
                Thread connection = prepareThreadToHandleConcurrentConnections(client);
                connection.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Thread prepareThreadToHandleConcurrentConnections(Socket client) {
        Runnable connection = () -> {
            try {
                System.out.println("Server accepted a client connection !!");

                InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder request = new StringBuilder();
                String line = bufferedReader.readLine();

                while (!line.isEmpty()) {
                    request.append(line + "\r\n");
                    line = bufferedReader.readLine();
                }

                System.out.println("REQUEST --> " + request);

                /*
                Adding sleep explicitly just to increase processing time
                * */
                Thread.sleep(4000);

                OutputStream responseStream = client.getOutputStream();
                //Write to the o/p stream is the blocking call
                String response = "HTTP/1.1 200 OK \r\n";
                responseStream.write(response.getBytes());
                responseStream.write("\r\n".getBytes());
                responseStream.write("Hello World \r\n".getBytes());
                responseStream.flush();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(connection);
        return t;
    }
}
