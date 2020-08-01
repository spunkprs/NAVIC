package trie;

import java.util.ArrayList;
import java.util.List;

public class TrieImplementation {

    private Trie root;

    class Trie {
        char ch;
        boolean isEndOfWord;
        List<Trie> children;

        Trie(char ch) {
            this.ch = ch;
        }
    }


    public void displayAutoCompletedWords(String word) {
        doAutoCompletionJob(root, word.toCharArray(), word, 0);
    }

    private void doAutoCompletionJob(Trie node, char ch[], String content, int index) {
        List<Trie> children = node.children;
        if (index < ch.length) {
            if (children != null) {
                boolean flag = false;
                for (Trie child : children) {
                    if (child.ch == ch[index]) {
                        if (content.length() == index + 1 && child.isEndOfWord) {
                            System.out.println(content);
                        }
                        doAutoCompletionJob(child, ch, content, index + 1);
                        flag = true;
                    }
                }
                if (node == root && !flag) {
                    System.out.println("No words to autofill !!");
                }
            }
        } else {
            if (children != null) {
                for (Trie child : children) {
                    content += child.ch;
                    if (child.isEndOfWord) {
                        System.out.println(content);
                    }
                    doAutoCompletionJob(child, ch, content, index + 1);
                    content = content.substring(0, content.length() - 1);
                }
            }
        }

    }

    public void insertAndDisplayContentsInsideTrie(List<String> words) {
        for (String word : words) {
            insertWordIntoTrie(word);
        }
        displayContents(root, "");
    }

    private void displayContents(Trie node, String content) {
        List<Trie> children = node.children;
        if (children != null) {
            for (Trie child : children) {
                content += child.ch;
                if (child.isEndOfWord) {
                    System.out.println(content);
                }
                displayContents(child, content);
                content = content.substring(0, content.length() - 1);
            }
        }
    }

    public void insertWordIntoTrie(String word) {
        char ch[] = word.toCharArray();
        if (root == null) {
            root = new Trie('*');
            insertIntoTrie(root, ch, 0);
        } else {
            insertIntoTrie(root, ch, 0);
        }
    }

    private void insertIntoTrie(Trie node, char ch[], int index) {
        if (index <= ch.length - 1) {
            List<Trie> children = node.children;
            if (children == null) {
                children = new ArrayList<Trie>();
                if (index != ch.length - 1) {
                    Trie trie = new Trie(ch[index]);
                    trie.isEndOfWord = false;
                    children.add(trie);
                    insertIntoTrie(trie, ch, index + 1);
                } else {
                    Trie trie = new Trie(ch[index]);
                    trie.isEndOfWord = true;
                    children.add(trie);
                }
                node.children = children;
            } else {
                boolean flag = false;
                for (Trie child : children) {
                    if (child.ch == ch[index]) {
                        flag = true;
                        if (index == ch.length - 1) {
                            child.isEndOfWord = true;
                        }
                        insertIntoTrie(child, ch, index + 1);
                    }
                }
                if (!flag) {
                    Trie trie = new Trie(ch[index]);
                    if (index != ch.length - 1) {
                        trie.isEndOfWord = false;
                        insertIntoTrie(trie, ch, index + 1);
                    } else {
                        trie.isEndOfWord = true;
                    }
                    node.children.add(trie);
                }
            }
        }
    }
}
