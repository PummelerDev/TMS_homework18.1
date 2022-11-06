import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Writing {
    @XmlElement
    private  Author author;
    @XmlElement
    private  String title;
    @XmlElement(name = "lines")
    private  Lines lines;

    public Writing() {
        this.author= new Author();
        this.lines = new Lines();
        this.title="";
    }

    public Writing(Author author, String title, Lines lines) {
        this.author = author;
        this.title = title;
        this.lines = lines;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Lines getLines() {
        return lines;
    }

    public void setLines(Lines lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Writing{" +
                "author=" + author.getLastName() + " " + author.getFirstName() +
                ", title='" + title + '\'' +
                ", lines=" + lines +
                '}';
    }
}