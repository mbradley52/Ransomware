
package ransomware;

/**
 *
 * @author Matt
 */
import java.net.*;
import java.security.*;
import javax.crypto.*;
import java.io.*;
import java.util.*;

public class Ransomware {

    /**
     * @param args the command line arguments
     */
     private static Key key; 
    public static void main(String[] args) {
        
        key = AES.generateKey();
        System.out.println("hello");
    }
    
}
