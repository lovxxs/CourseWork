package FilesProject;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started");
            System.out.println("============================");
            while(true) {
                try ( ConnectionServer connection = new ConnectionServer(server))
                {
                    String request = connection.readLine();
                    System.out.println(request);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
