import java.security.SecureRandom;

public class GenerateKeys {
    public static void main(String[] args) {
        try {
            byte[] genAESKey = gen256HexKey(32); // size for the AES-Key
            byte[] genPlaintext = gen512Plaintext(64); //Size for the plaintext
            byte[] genIV= gen128IV(16); //Size for Initisation vector

            String studentNum = "C3278604"; // the student number being used
            String modifiedKey = convertFirstBytes(genAESKey, studentNum); //to replace the start of the generated key with the student number
            String genPlainTextHex = byteArrayToHexString(genPlaintext); // plaintext hex conversion
            String genIVHex = byteArrayToHexString(genIV);

            System.out.println("AES-256 Key: " + modifiedKey);//displaying the generations
            System.out.println("Plaintext-512: " + genPlainTextHex);
            System.out.println("IV 128: " + genIVHex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //generating the AES-256 key
    public static byte[] gen256HexKey(int AESsize) {
        SecureRandom randomNumGen = new SecureRandom(); //randomisation of the numbers and letters
        byte[] AESKey = new byte[AESsize]; //referencing the 32 byte limit
        randomNumGen.nextBytes(AESKey); //generating the randomisation for the array
        return AESKey;
    }

    public static String byteArrayToHexString(byte[] byteArray) {
        StringBuilder result = new StringBuilder();         // representing as a string
            for (byte b : byteArray) {                      // for loop to change the value of the generated numbers and letters to hex
                result.append(String.format("%02X", b));}   // and assigns them a byte value to reach the 256 bit key limit
            return result.toString();
        }
    public static String convertFirstBytes(byte[] key, String fixedWord) {
        String keyHex = byteArrayToHexString(key); // converting the key
        return fixedWord + keyHex.substring(8); //putting in the student number in the fist 8 slots
    }

    public static byte[] gen512Plaintext(int plainTextSize){
        SecureRandom randomNumGen = new SecureRandom(); // same as byteArrayToHexString but for the plaintext
        byte[] plainText512 = new byte[plainTextSize];
        randomNumGen.nextBytes(plainText512);
        return plainText512;
    }

    public static byte[] gen128IV(int IVSize){
        SecureRandom randomNumGen = new SecureRandom();
        byte[] IV128 = new byte[IVSize];
        randomNumGen.nextBytes(IV128);
        return IV128;
    }
}

