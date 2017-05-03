import java.io.*;
import java.util.*;
import java.security.*;
import java.io.File;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt
{
    public static void main(String[] args)throws IOException, NoSuchAlgorithmException
    {   
        //--------Decryption---------------------------------------------------------     

        //Path to directory
        String directoryPath = "C:/Users/Matt/Desktop/CS Independant study/TestFolder";
        String hashDirectory = "C:/Users/Matt/Desktop/CS Independant study/ransomHash.txt";

        String passInput, passHash;

        boolean hasPassword = false;
        while(hasPassword == false)
        {
            // Get user entered password:
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the password in order to decrypt files:");
            passInput = sc.nextLine();

            //Hash users inputed password
            passHash = SHA1.Hash(passInput).toString();

            //Get stored hash of password from text file
            Encrypt.readHash(hashDirectory);
            //Gets ID and Hash Values
            String storedHash = Encrypt.getHash();
            int ID = Encrypt.getID();

            //System.out.println(storedHash);
           // System.out.println(ID);
            //compare hashes
            boolean hashVerification = SHA1.verifyHash(storedHash, passHash);

            if(hashVerification == true)
            {
                byte[] key = Arrays.copyOf(passInput.getBytes(),16);
                IterateDirectory.iterateDir(directoryPath, key, Cipher.DECRYPT_MODE);
                System.out.println("-----------------------decryption Done.----------------------");
                hasPassword = true;
            }
            else
                System.out.println("Wrong Password");
        }
        //--------Decryption--------------------------------------------------------- 

    }

}
