import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private final int PORT = 8000;
    private final Scanner scanner = new Scanner(System.in);
    private final String CONNECT_TO_CLIENT = "Connection to client established.";
    private final String CONNECT_CLOSED = "Connection closed.";
    private final String EXIT_COMMAND = "/exit";
    private final String SERVER_STATUS = "Server enabled";
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println(SERVER_STATUS);
            socket = serverSocket.accept();
            System.out.println(CONNECT_TO_CLIENT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread serverThread = new Thread(() -> {
                while (true) {
                    clientMessage(scanner.nextLine());
                }
            });
            serverThread.setDaemon(true);
            serverThread.start();
            while (true) {
                String text = in.readUTF();
                if (text.equals(EXIT_COMMAND)) {
                    clientMessage(EXIT_COMMAND);
                    break;
                }
                System.out.println("Client: " + text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(CONNECT_CLOSED);
    }

    public static void main(String[] args) {
        new Server();
    }

    public void clientMessage(String text) {
        try {
            out.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
