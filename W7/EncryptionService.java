package W7;

import java.util.Base64;

class EncryptionService {

    public String encrypt(String text, String method) {
        StringBuilder result = new StringBuilder();

        if (method.equals("Caesar")) {
            int shift = 3;
            for (char ch : text.toCharArray()) {
                if (Character.isLetter(ch)) {
                    char base = Character.isLowerCase(ch) ? 'a' : 'A';
                    ch = (char) ((ch - base + shift) % 26 + base);
                }
                result.append(ch);
            }
            return result.toString();
        }
        else if (method.equals("Base64")) {
            return Base64.getEncoder().encodeToString(text.getBytes());
        }
        else if (method.equals("XOR")) {
            char key = 'K';
            for (char ch : text.toCharArray()) {
                result.append((char) (ch ^ key));
            }
            return result.toString();
        }
    }
}
