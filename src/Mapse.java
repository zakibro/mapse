import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Mapse {

    static final int MAX_PORT_NUMBER = 65535;
    static final String IP_ADDRESS = "XXX"; //ex 192.168.0.1
    static final int TIMEOUT = 3000; //3s

    //List for the future usage of nmap
    static List<Integer> openPorts = new ArrayList<>();


    public static void main(String[] args) {

        for (Integer port = 1; port <= MAX_PORT_NUMBER; port++) {

            isPortOpen(IP_ADDRESS, port, TIMEOUT, openPorts);
        }
        System.out.println(openPorts.toString());
    }

    private static void isPortOpen(String ip, int port, int timeout, List<Integer> openPorts) {

        new Thread(() -> {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(ip, port), timeout);
                socket.close();
                openPorts.add(port);
                System.out.println("Connected to " + port);
            } catch (IOException ex) {
                //System.out.println("Couldn't connect to " + port);
            }
        }).start();
    }
}


