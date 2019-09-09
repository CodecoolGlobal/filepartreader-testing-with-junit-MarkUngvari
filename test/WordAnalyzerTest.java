import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WordAnalyzerTest {

    //    Paths to files including an invalid one.
    private String palindromeTest = "testSources/palindromeWords.txt";
    private String substringTest = "testSources/substringWords.txt";
    private String organizeTest = "testSources/unorganizedWords.txt";
    private String invalidTest = "testSources/nonExistentFile.txt";

    private FilePartReader reader = new FilePartReader();

    @Test
    void testWrongFile() {
        reader.setup(invalidTest, 1, 1);
        assertThrows(IOException.class, reader::read);
    }

    @Test
    void testInvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> reader.setup(invalidTest, -5, -7));
    }

    @Test
    void testRead() throws IOException {
        reader.setup(substringTest, 1, 1);
        assertEquals("These are cheesy\ntimes in the theater.", reader.read());
    }

    @Test
    void testAlphabeticalOrder() {
        reader.setup(organizeTest, 1, 3);
        FileWordAnalyzer analyzer = new FileWordAnalyzer(reader);
        String[] expected = {"animals", "are", "are", "as", "awesome", "but", "cool", "giraffes", "well.", "Zebras"};
        assertEquals(Arrays.asList(expected), analyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testAlphabeticalOrderForOneLine() {
        reader.setup(organizeTest, 2, 2);
        FileWordAnalyzer analyzer = new FileWordAnalyzer(reader);
        String[] expected = {"animals", "are", "but", "giraffes"};
        assertEquals(Arrays.asList(expected), analyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testSubstringAnalyzer() {
        reader.setup(substringTest, 1, 3);
        FileWordAnalyzer analyzer = new FileWordAnalyzer(reader);
        String[] expected = {"These", "cheesy", "the", "theater."};
        assertEquals(Arrays.asList(expected), analyzer.getWordsContainingSubstring("he"));
    }

    @Test
    void testSubstringAnalyzerForOneLine() {
        reader.setup(substringTest, 1, 1);
        FileWordAnalyzer analyzer = new FileWordAnalyzer(reader);
        String[] expected = {"These", "cheesy"};
        assertEquals(Arrays.asList(expected), analyzer.getWordsContainingSubstring("es"));
    }

    @Test
    void testPalindromeAnalyzer() {
        reader.setup(palindromeTest, 1, 4);
        FileWordAnalyzer analyzer = new FileWordAnalyzer(reader);
        String[] expected = {"Madam", "Anna", "noon", "mom"};
        assertEquals(Arrays.asList(expected), analyzer.getStringWhichPalindromes());
    }

    @Test
    void testPalindromeAnalyzerForOneLine() {
        reader.setup(palindromeTest, 3, 3);
        FileWordAnalyzer analyzer = new FileWordAnalyzer(reader);
        String[] expected = {"noon"};
        assertEquals(Arrays.asList(expected), analyzer.getStringWhichPalindromes());
    }

}