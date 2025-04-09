//package W7;

import java.util.Base64;

public class EncryptionService {
    private EncryptionStrategy strategy;

    public void setEncryptionStrategy(EncryptionStrategy strategy) {
        this.strategy = strategy;
    }

    public String encrypt(String text) {
        return strategy.encrypt(text);
    }
}

interface EncryptionStrategy {
    String encrypt(String text);
    
}

class CaesarCipherEncryption implements EncryptionStrategy{
    private final int shift;

    public CaesarCipherEncryption(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + this.shift) % 26 + base);
            }
            result.append(ch);
        }
        return result.toString();
    }
}

class XOREncryption implements EncryptionStrategy{
    private final char key;

    public XOREncryption(char key) {
        this.key = key;
    }

    @Override
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            result.append((char) (ch ^ this.key));
        }
        return result.toString();
    }
}

class Base64Encryption implements EncryptionStrategy{
    public String encrypt(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
}

    class ReverseStringEncryption implements EncryptionStrategy{
        public String encrypt(String text) {
            StringBuilder result = new StringBuilder();
            for (int i = text.length() - 1; i >= 0; i--) {
                char curChar = text.charAt(i);
                result.append(curChar);
            }
            return result.toString();
        }
    }

class DuplicateCharacterEncryption implements EncryptionStrategy {
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char curChar = text.charAt(i);
            result.append(curChar);
            result.append(curChar);
        }
        return result.toString();
    }
}