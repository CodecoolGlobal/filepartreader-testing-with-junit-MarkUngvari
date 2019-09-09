import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileWordAnalyzer {
    private FilePartReader reader;

    FileWordAnalyzer(FilePartReader reader) {
        this.reader = reader;
    }

    private List<String> generateWordList(){
        String text = null;
        try {
            text = reader.readLines();
            return Arrays.asList(text.split(" "));
        } catch (IOException e) {
            return null;
        }
    }

    public List<String> getWordsOrderedAlphabetically() {
        List<String> words = generateWordList();
        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        return words;
    }

    public List<String> getWordsContainingSubstring(String substring) {
        List<String> words = generateWordList();
        List<String> result = new ArrayList<>();
        for (String word :
                words) {
            if (word.contains(substring)) {
                result.add(word);
            }
        }
        return result;
    }

    public List<String> getStringWhichPalindromes() {
        List<String> words = generateWordList();
        List<String> result = new ArrayList<>();
        for (String word :
                words) {
            int n = word.length();
            for (int i = 0; i < n; i++) {
                if (word.toLowerCase().charAt(i) == word.toLowerCase().charAt(n - i - 1)) {
                    result.add(word);
                    break;
                }
                break;
            }
        }
        return result;

    }

}
