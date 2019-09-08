import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePartReader {
    private String filepath;
    private Integer fromLine;
    private Integer toLine;

    private IllegalArgumentException IllegalArgumentException;


    public FilePartReader() {
        filepath = "invalid";
        fromLine = -5;
        toLine = -7;
    }

    public void setup(String filepath, Integer fromLine, Integer toLine) throws IllegalArgumentException {
        if (fromLine < 1 || toLine < fromLine) {
            throw IllegalArgumentException;
        }
        this.filepath = filepath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() {
        try {
            return Files.readString(Paths.get(filepath));
        } catch (IOException e) {
            System.out.println("Error! Can't read file!\n" + e);
        }
        return "";
    }

    public String readLines() {
        List<String> text = new ArrayList<>();
        try {
            text = Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            System.out.println("Error! Can't read file!\n" + e);
        }
        for (String line : text) {
            if (text.indexOf(line) < fromLine || text.indexOf(line) > toLine){
                text.remove(line);
            }
        }
        return text.toString();
    }

}
