import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindingIndexOfIntegerMain {

    public static void main(String[] args) throws IOException {
        // 1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Do you want to use program (enter any to use or `exit` to terminate program): ");
            String programStatus = br.readLine();

            if ("exit".equalsIgnoreCase(programStatus)) {
                break;
            } else {
                System.out.print("Enter an integer number: ");
                int input = Integer.valueOf(br.readLine());

                System.out.print("Enter array of integer numbers separate by `,`: ");
                List<Integer> data = Arrays.stream(br.readLine().split("\\s*,\\s*"))
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());

                String result = IntStream.range(0, data.size())
                        .filter(index -> input == data.get(index))
                        .mapToObj(String::valueOf)
                        .collect(Collectors.collectingAndThen(
                                Collectors.joining(", "),
                                str -> {
                                    if (str.isEmpty()) {
                                        return "-1";
                                    } else {
                                        return str;
                                    }
                                }
                        ));
                System.out.println("Result index(es) : " + result);
            }
        }

    }

}
