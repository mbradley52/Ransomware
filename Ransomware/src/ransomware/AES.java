/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ransomware;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;

//FOR TESTING IN MAIN
import java.io.BufferedReader;
import java.io.PrintStream;

public class AES
{

    private static String algorithm = "AES";
    private static byte[] keyValue=new byte[] {'0','2','3','4','5','6','7','8','9','1','2','3','4','5','6','7'};
    private static Cipher cipher;
    private static byte[] iv = { 9, 0, 1, 0, 1, 1, 0, 4, 0, 2, 0, 0, 0, 7, 0, 0 };
    // Performs Encryption

    private static PrintStream os = null;
    // The input stream
    private static BufferedReader is = null;
    private static BufferedReader inputLine = null;
    
    public static void main (String[] args) {

        String plainText = "Hello this is supposed to be encrypted";
        try {
            Key key = generateKey();
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            String encryptedString = encrypt(plainText, key);
            System.out.println("Encrypted String: " + encryptedString);
            String decryptedString = decrypt(encryptedString, key);
            System.out.println("Decrypted String: " + decryptedString);
        }
        catch (Exception ie) {
            System.out.println(ie);
        }
    }

    public static String encrypt(String plainText, Key secretKey)
    throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }

    public static String decrypt(String encryptedText, Key secretKey)
    throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        byte[] iv = { 9, 0, 1, 0, 1, 1, 0, 4, 0, 2, 0, 0, 0, 7, 0, 0 };
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }

    //generateKey() is used to generate a secret key for AES algorithm
    public static Key generateKey() throws Exception 
    {
        Key key = new SecretKeySpec(keyValue, algorithm);
        return key;
    }

    
    public static String encodeKey(Key key) throws Exception {
        String keyString = Base64.getEncoder().encodeToString(key.getEncoded());
        return keyString;
    }
    
    public static Key decodeKey(String keyString) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(keyString);
        Key key = new SecretKeySpec(decoded,0,decoded.length, "AES"); 
        return key;
    }
}