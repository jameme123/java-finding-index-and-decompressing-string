import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecompressingStringMain {

    public static void main(String[] args) throws IOException {
        // 2
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Please enter compress string (enter `exit` to terminate program): ");
            String input = br.readLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            } else {
                System.out.println("Decompressed String result: " + decompressString(input));
            }
        }
    }

    private static String decompressString(String input) {
        StringBuilder result = new StringBuilder();
        int state = -1;
        int startIndex = -1;
        int endIndex = -1;
        String times = "";

        for (int index = 0; index < input.length(); index++) {
            char currentChar = input.charAt(index);
            if (currentChar == '[') {
                if (state == -1) {
                    startIndex = index;
                    state = 1;
                } else {
                    state++;
                }
            } else if (currentChar == ']') {
                state--;
                if (state == 0) {
                    endIndex = index;
                }
            } else if (state == -1) {
                if (Character.isDigit(currentChar)) {
                    times += String.valueOf(currentChar);
                } else {
                    if (!times.isEmpty()) {
                        result.append(times);
                        times = "";
                    }
                    result.append(String.valueOf(currentChar));
                }
            }

            if (state == 0) {
                String subResult = input.substring(startIndex + 1, endIndex);
                if (subResult.contains("[")) {
                    subResult = decompressString(subResult);
                }

                int timesAsNumber = Integer.valueOf(times);
                int count = 0;
                while (count < timesAsNumber) {
                    result.append(subResult);
                    count++;
                }

                state = -1;
                startIndex = -1;
                endIndex = -1;
                times = "";
            }
        }
        return result.toString();
    }

}
