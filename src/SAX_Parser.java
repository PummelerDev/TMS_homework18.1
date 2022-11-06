import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAX_Parser {
    public static Writing parse(File file) throws ParserConfigurationException, IOException, SAXException {
        return parsSaxToObj(file);
    }

    private static Writing parsSaxToObj(File item) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(item, handler);
        return handler.getWriting();
    }
    public static class XMLHandler extends DefaultHandler {
        private String lastElementName;
        private Writing writing;
        private Author author;
        private Lines lines;
        private String title;

        public Writing getWriting() {
            return this.writing;
        }

        @Override
        public void startDocument() throws SAXException {
            writing = new Writing();
            author = new Author();
            lines = new Lines();
            title = "";
        }

        @Override
        public void endDocument() throws SAXException {
            writing.setAuthor(author);
            writing.setLines(lines);
            writing.setTitle(title);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length).replaceAll("\n", "").trim();
            if (lastElementName.equals("line") & !information.equals("")) {
                this.lines.setLine(information);
            }
            if (lastElementName.equals("lastName") & author.getLastName().equals("")) {
                this.author.setLastName(information);
            }
            if (lastElementName.equals("firstName") & author.getFirstName().equals("")) {
                this.author.setFirstName(information);
            }
            if (lastElementName.equals("title")) {
                this.title += information;
            }
        }
    }
}
