import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePartReader {
    private String filepath;
    private Integer fromLine;
    private Integer toLine;


    public FilePartReader() {
        filepath = "invalid";
        fromLine = -5;
        toLine = -7;
    }

    public void setup(String filepath, Integer fromLine, Integer toLine) throws IllegalArgumentException {
        if (fromLine < 1 || toLine < fromLine) {
            throw new IllegalArgumentException("Invalid numbers");
        }
        this.filepath = filepath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() throws IOException{
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(filepath));
            return new String(encoded);
        } catch (IOException e) {
            throw new IOException("File not found");
        }
    }

    public String readLines() throws IOException{
        List<String> text = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            text = Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            throw new IOException("File not found");
        }
        for (String line : text) {
            if (text.indexOf(line) >= fromLine-1 && text.indexOf(line) <= toLine-1){
                sb.append(line+" ");
            }
        }
        return sb.toString().trim();
    }

}
