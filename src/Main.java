import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Enter length of key(bits 2^n):\n>");
        int len = scanner.nextInt();
        BigInteger two = new BigInteger("2");
        BigInteger keys = two.pow(len);

        BigInteger maxLimit = new BigInteger(keys.toString());
        BigInteger minLimit = new BigInteger("0");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        int lenRand = maxLimit.bitLength();
        BigInteger userKey = new BigInteger(lenRand, random);
        if (userKey.compareTo(minLimit) < 0) {
            userKey = userKey.add(minLimit);
        }
        if (userKey.compareTo(bigInteger) >= 0) {
            userKey = userKey.mod(bigInteger).add(minLimit);
        }

        String fullKey = String.format("0x%x", userKey);

        System.out.println("Amount of keys: " + keys);
        System.out.println("\tUsers key: " + userKey + "\n\thex ver: " + fullKey);

        long time = System.currentTimeMillis();
        BigInteger i = new BigInteger("0");
        while (i.compareTo(keys) != 0){
            if(fullKey.equalsIgnoreCase(String.format("0x%x", i))){
                time = System.currentTimeMillis() - time;
                System.out.println("Your key: " + (String.format("0x%x", i)) + " was found for "
                        + time +" ms, " + i + " keys was checked");
                return;
            }
            i = i.add(BigInteger.ONE);
        }
    }
}
