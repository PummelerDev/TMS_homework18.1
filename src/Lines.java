import java.util.ArrayList;
import java.util.List;

public class Lines {
    private List<String> line;

    public Lines() {
        line=new ArrayList<>();
    }

    public Lines(List<String> line) {
        this.line = line;
    }

    public List<String> getLine() {
        return line;
    }

//WTF
    public void setLine(List<String> line) {
        this.line=line;
    }
    public void setLine(String line){
        this.line.add(line);
    }
//WTF

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String item : line
        ) {
            builder.append(item).append("\n");
        }
        return builder.toString();
    }
}