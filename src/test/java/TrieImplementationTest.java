import org.junit.Before;
import org.junit.Test;
import trie.TrieImplementation;

import java.util.ArrayList;
import java.util.List;

public class TrieImplementationTest {

    private TrieImplementation unit;

    @Before
    public void setUp() {
        unit = new TrieImplementation();
    }

    @Test
    public void shouldInsertAndDisplayContentsOfTrieCaseOne() {
        List<String> words = new ArrayList<String>();
        words.add("the");
        words.add("there");
        words.add("their");
        words.add("thor");
        words.add("any");
        words.add("answer");
        words.add("bye");
        words.add("b");
        words.add("then");
        words.add("throng");
        unit.insertAndDisplayContentsInsideTrie(words);
    }

    @Test
    public void shouldDisplayAutoCompletedWordsCaseOne() {
        List<String> words = new ArrayList<String>();
        words.add("their");
        words.add("thor");
        words.add("any");
        words.add("answer");
        words.add("bye");
        words.add("b");
        words.add("then");
        words.add("throng");
        unit.insertAndDisplayContentsInsideTrie(words);
        unit.displayAutoCompletedWords("the");
    }

    @Test
    public void shouldDisplayAutoCompletedWordsWhenPrefixDoesNotExistInTrie() {
        List<String> words = new ArrayList<String>();
        words.add("any");
        words.add("answer");
        words.add("bye");
        words.add("b");
        words.add("then");
        words.add("throng");
        unit.insertAndDisplayContentsInsideTrie(words);
        unit.displayAutoCompletedWords("xy");
    }

    @Test
    public void shouldDisplayAutoCompletedWordsCaseTwo() {
        List<String> words = new ArrayList<String>();
        words.add("any");
        words.add("bye");
        words.add("b");
        words.add("beneath");
        words.add("then");
        words.add("throng");
        unit.insertAndDisplayContentsInsideTrie(words);
        unit.displayAutoCompletedWords("bye");
        unit.displayAutoCompletedWords("by");
        unit.displayAutoCompletedWords("b");
    }
}
