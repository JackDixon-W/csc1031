package W8;

public class EncryptionRunner {
    public static void main(String[] args) {
      EncryptionService service = new EncryptionService();

      // Create a Caesar Cipher encryption strategy with a shift of 3
      EncryptionStrategy caesar = new CaesarCipherEncryption(3);

      // Set the encryption strategy to Caesar Cipher
      service.setEncryptionStrategy(caesar);

      // Encrypt three different strings using the same Caesar Cipher strategy
      System.out.println(service.encrypt("HelloWorld"));       // Output: Encrypted text with shift 3
      System.out.println(service.encrypt("StrategyPattern")); // Output: Encrypted text with shift 3
      System.out.println(service.encrypt("EncryptionLab"));   // Output: Encrypted text with shift 3

      // Change the encryption strategy to Reverse String Encryption
      service.setEncryptionStrategy(new ReverseStringEncryption());

      // Encrypt a different string using the Reverse String strategy
      System.out.println(service.encrypt("Password")); // Output: "drowssaP" (reversed)
    }
}

