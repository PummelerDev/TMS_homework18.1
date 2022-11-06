import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {

    public static void start() throws JAXBException, SAXException, ParserConfigurationException {
        while (true) {
            try {
                String path = getPath();
                if (path.equals("exit")) {
                    System.out.println("The program is closed.");
                    return;
                }
                File file = new File(path);
                System.out.println("""
                        Which parser would you like to use?
                        Enter a number:
                        1 - JAXB,
                        2 - SAX
                        3 - DOM""");
                int sc = getNumb();
                Writing writing;
                switch (sc) {
                    case 1 -> {
                        writing = JAXB_Parser.parse(file);
                        toWrite(file, writing, "by JAXB");
                        System.out.println("The creating is done by JAXB.");
                    }
                    case 2 -> {
                        writing = SAX_Parser.parse(file);
                        toWrite(file, writing, "by SAX");
                        System.out.println("The creating is done by SAX.");
                    }
                    case 3 ->{
                        writing = DOM_Parser.parse(file);
                        toWrite(file, writing, "by DOM");
                        System.out.println("The creating is done by DOM.");
                    }
                }
            } catch (NullPointerException | IOException e) {
                System.out.println("File doesn't exist!");
            }
        }
    }

    private static String getPath() {
        while (true) {
            System.out.println("""
                    Enter a directory path to parse XML:
                    Enter "exit" to close the program.
                    """);
            Scanner scan = new Scanner(System.in);
            String str = scan.nextLine();
            if (str.equals("exit")) {
                return str;
            }
            File file = new File(str);
            if (file.isFile() & str.toLowerCase().endsWith(".xml")) {
                return str;
            }
            if (file.isDirectory() & Objects.requireNonNull(file.listFiles()).length == 0) {
                System.out.println("Directory is empty.");
                continue;
            }
            if (file.isDirectory()) {
                Stream<File> stream = Arrays.stream(Objects.requireNonNull(file.listFiles()));
                List<File> files = stream.filter(File::isFile)
                        .filter(x -> x.getName().toLowerCase().endsWith(".xml")).toList();
                if (files.size() == 0) {
                    System.out.println("Directory has no XML files.");
                    continue;
                }
                System.out.println("Choose a file to parse to XML: ");
                int i = 1;
                for (File item : files
                ) {
                    System.out.println(i++ + "  " + item.getName());
                }
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextInt()) {
                    int sc = scanner.nextInt();
                    if (sc > 0 & sc <= files.size()) {
                        return files.get(sc - 1).getPath();
                    }
                    System.out.println("Incorrect number! Try again.");
                }
            }
            System.out.println("Incorrect data! Try again.");
        }
    }

    private static int getNumb() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int sc = scanner.nextInt();
                if (sc >= 1 & sc <= 3) {
                    return sc;
                } else {
                    System.out.println("Wrong number! Try again.");
                }
            } else {
                System.out.println("You entered not a number! Try again.");
                scanner.next();
            }
        }
    }

    private static void toWrite(File file, Writing writing, String parserName) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(file.getName() + "_" +
                        parserName + "_" +
                        writing.getAuthor().getLastName() + "_" +
                        writing.getAuthor().getFirstName() + "_" +
                        writing.getTitle() + ".txt"));
        writer.write(writing.getLines().toString());
        writer.close();
    }
}