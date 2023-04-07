import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormatNames {

    public static void main(String[] args) {
        try {
            boolean upperCase = false;
            String inputFile = args[0];
            String outputFile = args[1];

            if (args.length == 3 && args[0].equals("-u")) {
                upperCase = true;
            }

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            FileWriter writer = new FileWriter(outputFile);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                String firstName = capitalize(tokens[0]);
                String lastName = capitalize(tokens[1]);
                String dateOfBirth = tokens[2].substring(0, 2) + "/" + tokens[2].substring(2, 4) + "/" + tokens[2].substring(4);

                if (upperCase) {
                    firstName = firstName.toUpperCase();
                    lastName = lastName.toUpperCase();
                }

                writer.write(String.format("%-20s%s\n", firstName + " " + lastName, dateOfBirth));
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
