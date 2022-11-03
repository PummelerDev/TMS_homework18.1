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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class FileCreator {

    public static void create() throws JAXBException, SAXException, ParserConfigurationException {
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
                        2 - SAX""");
                int sc = getSc();
                Writing writing = new Writing();
                switch (sc) {
                    case 1 -> {
                        writing = parsJaxbToObj(file);
                        toWrite(file, writing, "by JAXB");
                        System.out.println("The creating is done by JAXB.");
                    }
                    case 2 -> {
                        writing = parsSaxToObj(file);
                        System.out.println("The creating is done by SAX.");
                        toWrite(file, writing, "by SAX");
                    }
                }
            } catch (NullPointerException | IOException e) {
                System.out.println("File doesn't exist!");
            }
        }
    }


    private static int getSc() {
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                int sc = scanner.nextInt();
                if (sc == 1 | sc == 2) {
                    result = sc;
                    return result;
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

    private static Writing parsSaxToObj(File item) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(item, handler);
        return handler.getWriting();
    }


    private static Writing parsJaxbToObj(File item) throws JAXBException {
        Writing writing = null;
        JAXBContext jaxbContext = JAXBContext.newInstance(Writing.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        writing = (Writing) jaxbUnmarshaller.unmarshal(item);
        return writing;
    }

    private static String getPath() {
        while (true) {
            System.out.println("""
                    Enter "exit" to close the program.
                    Enter a directory path to parse XML:
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
}