package volm.journal.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SecurityUtil {


    public static String getSecurePassword(String pass, String salt) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            md.update(salt.getBytes());

            byte[] bytes = md.digest(pass.getBytes());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String generateRandomSalt() {
        String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz1234567890";
        int size = 4;
        char[] salt = new char[size];
        for (int i = 0; i < size; i++) {
            salt[i] = source.charAt(new Random().nextInt(source.length()));
        }
        return new String(salt);
    }
}
