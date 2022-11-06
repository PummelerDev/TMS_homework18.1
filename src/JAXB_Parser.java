import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXB_Parser {
    public static Writing parse(File file) throws JAXBException {
        return parsJaxbToObj(file);
    }
    private static Writing parsJaxbToObj(File item) throws JAXBException {
        Writing writing;
        JAXBContext jaxbContext = JAXBContext.newInstance(Writing.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        writing = (Writing) jaxbUnmarshaller.unmarshal(item);
        return writing;
    }
}
