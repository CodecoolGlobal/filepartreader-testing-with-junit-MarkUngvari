import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileWordAnalyzer {
    private FilePartReader reader;

    FileWordAnalyzer(FilePartReader reader) {
        this.reader = reader;
    }

    public List<String> getWordsOrderedAlphabetically() {
        String text = reader.readLines();
        List<String> words = Arrays.asList(text.split(" "));
        Collections.sort(words);
        return words;
    }

    public List<String> getWordsContainingSubstring(String substring) {
        String text = reader.readLines();
        List<String> words = Arrays.asList(text.split(" "));
        for (String word :
                words) {
            if (!word.contains(substring)) {
                words.remove(word);
            }
        }
        return words;
    }

    public List<String> getStringWhichPalindromes() {
        String text = reader.readLines();
        List<String> words = Arrays.asList(text.split(" "));
        for (String word :
                words) {
            int n = word.length();
            for (int i = 0; i < n; i++) {
                if (word.charAt(i) != word.charAt(n - i - 1)) {
                    words.remove(word);
                }
            }
        }
        return words;

    }

}
