import java.security.*;
public class SHA1
{
    public static String Hash(String input)throws NoSuchAlgorithmException
    {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
    
    public static boolean verifyHash(String hash, String userInput)
    {
        if(hash.equals(userInput))
            return true;
            
        else
            return false;
    }
    
}
