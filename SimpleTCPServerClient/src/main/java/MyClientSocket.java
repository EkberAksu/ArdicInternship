/**
 * Created by A.Aziz on 2/7/2018.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
public class MyClientSocket {
    private Socket socket;
    private Scanner scanner;
    private MyClientSocket(InetAddress serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }
    public static void main(String[] args) throws Exception {
        String hostIp = "192.168.2.10";
        String port = "51207";
        MyClientSocket client = new MyClientSocket(
                InetAddress.getByName(hostIp),
                Integer.parseInt(port));

        System.out.println("\r\nConnected to: " + client.socket.getInetAddress());
        client.start();
    }
    private void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }
    }
}
