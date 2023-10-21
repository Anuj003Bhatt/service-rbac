package com.decimal.rbac.util;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public abstract class EncryptionUtil {
    private static final Integer ITERATIONS = 1000;
    private static final String SECRET = "ThisIsATestSecret";
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final SecretKeyFactory KEY_FACTORY;
    private static final Integer KEY_LENGTH = 256;

    private static final Integer SALT_LENGTH = 30;

    private static final String ENCRYPTION_ALGORITHM = "PBKDF2WithHmacSHA1";


    static {
        try {
            KEY_FACTORY = SecretKeyFactory.getInstance(ENCRYPTION_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to load EncryptionUtil, failed to get dependency 'SecretKeyFactory'");
        }
    }

    private static SecretKeySpec prepareSecreteKey() {
        MessageDigest sha = null;
        try {
            byte[] key = SECRET.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not prepare secret");
        }

    }

    public static String encryptWithSecret(String originalString) {
        try {
            SecretKeySpec secretKey = prepareSecreteKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(originalString.getBytes("UTF-8")));
        } catch (Exception e) {
            log.error("Could not encrypt provided string. Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static String decryptWithSecret(String encryptedString) {
        try {
            SecretKeySpec secretKey = prepareSecreteKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedString)));
        } catch (Exception e) {
            log.error("Could not decrypt provided string. Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * This method generates a random salt using which the encryption of the input is done.
     *
     * @return The generated salt value
     */
    private static String generateSalt() {
        return Stream.generate(
                () -> CHARACTERS.charAt(SECURE_RANDOM.nextInt(CHARACTERS.length()))
        ).limit(EncryptionUtil.SALT_LENGTH).toString();
    }

    private static byte[] hash(String input, String salt) {
        return hash(input.toCharArray(), salt.getBytes());
    }
    private static byte[] hash(char[] input, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(input, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(input, Character.MIN_VALUE);
        try {
            return KEY_FACTORY.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            log.error("Error while generating the hash for input. Error: {}", e.getMessage());
            throw new RuntimeException("Error while generating hash for input");
        } finally {
            spec.clearPassword();
        }
    }

    public static Map<String, String> encryptWithSalt(String input) {
        // get salt
        String salt = generateSalt();
        // generate hash & base 64 encode
        String encoded = Base64.getEncoder().encodeToString(hash(input, salt));
        // return salt and hash
        return Map.of(
                "value", encoded,
                "salt", salt
        );
    }

    private static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    public static boolean verifyPassword(String password, Map<String, String> passwordFromDb) {
        if (null == password || "".equals(password)) {
            log.error("Password is blank/empty: {}", password);
            throw new IllegalArgumentException("Password verification initiated with empty password");
        }

        String encoded = Base64.getEncoder().encodeToString(hash(password, passwordFromDb.get("salt")));
        return passwordFromDb.get("value").equals(encoded);
    }
}