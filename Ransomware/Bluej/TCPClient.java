import java.io.*;
import java.net.*;

public class TCPClient extends Thread
{
    public static void main(String[] args)
    {
        int serverPort = 49019;
        String host = "localhost";
        Socket clientSocket = null;
        try {
            String request = "Generate Key";
            String keyString;

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            clientSocket = new Socket(host, serverPort);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Test 1");
            //outToServer.writeBytes(request);
            keyString = inFromServer.readLine();
            System.out.println("Test 2 - '"+keyString+"'");
            System.out.println(keyString);
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (Exception cse) {
                // ignore exception here
            }
        }
    }
}
