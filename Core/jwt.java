import java.security.SecureRandom;
import java.util.Base64;

class jwt {
    public static void main(String[] args) {
        System.out.println("Try clicking the Run button.");
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        System.out.println(Base64.getEncoder().encodeToString(key));
    }
}
