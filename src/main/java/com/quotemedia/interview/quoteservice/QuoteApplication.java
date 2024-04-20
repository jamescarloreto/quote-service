package com.quotemedia.interview.quoteservice;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuoteApplication {
    private static final Logger logger = LoggerFactory.getLogger(QuoteApplication.class);

    public static void main(final String[] args) {

        // String key = generateHexEncryptionKey();
        // logger.info("QuoteApplication | main | key: " + key);
        SpringApplication.run(QuoteApplication.class, args);
    }

    public static String generateHexEncryptionKey() {
        try {
            // Create a KeyGenerator instance for AES (Advanced Encryption Standard)
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");

            // SecureRandom for generating the key
            SecureRandom secureRandom = new SecureRandom();
            keyGen.init(256, secureRandom); // Initialize the KeyGenerator with a key size of 256 bits

            // Generate a secret key
            SecretKey secretKey = keyGen.generateKey();

            // Convert the secret key to a hexadecimal string
            return bytesToHex(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method to convert byte array to hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b)); // Convert each byte to hexadecimal string
        }
        return result.toString();
    }
}