import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FormatNames {
    public static void main(String[] args) {
        boolean upperCase = false;
        String inputFile, outputFile;
        if (args.length == 3) {
            if (args[0].equals("-u"))
                upperCase = true;
            else {
                System.out.println("Invalid flag.");
                return;
            }
            inputFile = args[1];
            outputFile = args[2];
        } else if (args.length == 2) {
            inputFile = args[0];
            outputFile = args[1];
        } else {
            System.out.println("Invalid number of arguments.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                String firstName = tokens[0].substring(0, 1).toUpperCase() + tokens[0].substring(1).toLowerCase();
                String middleInitial = "";
                if (tokens.length > 2 && tokens[1].length() == 1) {
                    middleInitial = " " + tokens[1].toUpperCase() + ".";
                    String lastName = tokens[2].substring(0, 1).toUpperCase() + tokens[2].substring(1).toLowerCase();
                    String dateOfBirth = tokens[3].substring(0, 2) + "/" + tokens[3].substring(2, 4) + "/" + tokens[3].substring(4);
                    if (upperCase) {
                        firstName = firstName.toUpperCase();
                        lastName = lastName.toUpperCase();
                    }
                    writer.write(String.format("%-20s%s\n", firstName + middleInitial + " " + lastName, dateOfBirth));
                } else {
                    String lastName = tokens[1].substring(0, 1).toUpperCase() + tokens[1].substring(1).toLowerCase();
                    String dateOfBirth = tokens[2].substring(0, 2) + "/" + tokens[2].substring(2, 4) + "/" + tokens[2].substring(4);
                    if (upperCase) {
                        firstName = firstName.toUpperCase();
                        lastName = lastName.toUpperCase();
                    }
                    writer.write(String.format("%-20s%s\n", firstName + " " + lastName, dateOfBirth));
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
