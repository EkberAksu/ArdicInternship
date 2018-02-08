/**
 * Created by A.Aziz on 2/7/2018.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class MyServerSocket {
    private int port = 9878;
    private ServerSocket server;

    public MyServerSocket(String bindAddr) throws Exception {
        this.server = new ServerSocket(port, 1, InetAddress.getByName(bindAddr));
    }
    private void listen() throws Exception {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        while ( (data = in.readLine()) != null ) {
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
        }
    }
    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return port;
        //return this.server.getLocalPort();
    }
    public static void main(String[] args) throws Exception {
        String hostIp = "192.168.2.10";

        MyServerSocket app = new MyServerSocket(hostIp);

        System.out.println("\r\nRunning Server: " +
                "Host=" + app.getSocketAddress().getHostAddress() +
                " Port=" + app.getPort());

        app.listen();
    }
}
