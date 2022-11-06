import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOM_Parser {

    public static Writing parse(File file) throws ParserConfigurationException, IOException, SAXException {
        DOM_Parser DOMParser = new DOM_Parser();
        return DOMParser.parsDomToObj(file);
    }

    private Writing parsDomToObj(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        Writing writing = new Writing();
        writing.setAuthor(new Author(
                document.getElementsByTagName("lastName").item(0).getTextContent(),
                document.getElementsByTagName("firstName").item(0).getTextContent(),
                document.getElementsByTagName("nationality").item(0).getTextContent(),
                document.getElementsByTagName("yearOfBirth").item(0).getTextContent(),
                document.getElementsByTagName("yearOfDeath").item(0).getTextContent()
        ));
        writing.setTitle(
                document.getElementsByTagName("title").item(0).getTextContent()
        );
        Lines lines = new Lines();
        NodeList lineElements = document.getDocumentElement().getElementsByTagName("line");
        for (int i = 0; i < lineElements.getLength(); i++) {
            lines.setLine(lineElements.item(i).getTextContent());
        }
        writing.setLines(lines);
        return writing;
    }
}
