package com.ecl.popcensus.util;

import java.security.SecureRandom;
import java.util.Random;


public class GenerateUniqueCode {
    // Define the characters used in the code: uppercase letters and digits
    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NON_ALPHANUMERIC_CHARACTERS = "1234567890";

    // Create a SecureRandom instance for better randomness
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates a random alphanumeric code with the specified maximum length.
     *
     * @return A random alphanumeric code of length up to maxLength.
     */
    public static String generateCode(int maxLength) {
        return generateRandomCode(ALPHANUMERIC_CHARACTERS, maxLength);
    }

    /**
     * Generates a random non-alphanumeric code with the specified maximum length.
     *
     * @return A random non-alphanumeric code of length up to maxLength.
     */
    public static String generateNonAlphanumericCode(int maxLength) {
        return generateRandomCode(NON_ALPHANUMERIC_CHARACTERS, maxLength);
    }

    /**
     * Generates a random code based on the given character set and length.
     *
     * @param characters The characters to use for generating the code.
     * @param maxLength  The maximum length of the code.
     * @return A random code of the specified length.
     */
    private static String generateRandomCode(String characters, int maxLength) {
        StringBuilder code = new StringBuilder(maxLength);
        for (int i = 0; i < maxLength; i++) {
            // Randomly pick a character from the characters string
            int index = RANDOM.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }
    /**
     * Generates a random alphanumeric code with the specified maximum length.
     *
     * @return A random alphanumeric code of length up to MAX_LENGTH.
     */

    /**
     * Generates a client header account number with random 4 digit unique code at the end
     *
     * @return A client header account number
     */
    public static String generateHeaderAccountNumber(String clientTypeCode, String headerAccountCode) {
        // Generate a unique 4-digit number
        Random random = new Random();
        int uniqueNumber = 1000 + random.nextInt(9000); // ensures a 4-digit number
        return clientTypeCode + headerAccountCode + uniqueNumber;
    }
}
