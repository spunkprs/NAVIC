package microsoft.bfs;

import java.util.*;
import java.util.stream.Collectors;

public class WordLadder2 {

    private int minDepth = Integer.MAX_VALUE;

    public static void main(String ar[]) {
        WordLadder2 unit = new WordLadder2();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        List<List<String>> finalResult = unit.findLadders(beginWord, endWord, wordList);
        System.out.print(finalResult);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = prepareSet(wordList);
        return processToComputeLadder(beginWord, endWord, wordSet);
    }

    private List<List<String>> processToComputeLadder(String beginWord, String endWord, Set<String> wordSet) {
        List<List<String>> finalResult = new ArrayList<>();
        char[] alphabet = new char[26];

        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) ('a' + i);
        }

        Set<String> visitedNodes = new HashSet<>();
        Node startingNode = new Node(beginWord, 0, new ArrayList<>());
        startingNode.lineage.add(beginWord);

        Queue<Node> queue = new LinkedList<>();
        queue.add(startingNode);

        while (!queue.isEmpty()) {
            Node peekedNode = queue.peek();
            visitedNodes.add(peekedNode.originWord);

            if (!peekedNode.originWord.equals(endWord)) {
                for (Node child : processToFetchPossibleChildren(peekedNode, wordSet, visitedNodes, alphabet)) {
                    queue.add(child);
                }
            } else {
                if (minDepth == Integer.MAX_VALUE) {
                    minDepth = peekedNode.depth;
                    updateFinalResult(peekedNode.lineage, finalResult);
                } else {
                    if (peekedNode.depth == minDepth) {
                        updateFinalResult(peekedNode.lineage, finalResult);
                    }
                }
            }
            queue.poll();
        }
        return finalResult;
    }

    private void updateFinalResult(List<String> lineage, List<List<String>> finalResult) {
        finalResult.add(lineage);
    }

    private List<Node> processToFetchPossibleChildren(Node parentNode, Set<String> wordSet, Set<String> visitedNodes, char[] alphabet) {
        List<Node> childList = new ArrayList<>();
        String word = parentNode.originWord;
        char wordArr[] = word.toCharArray();
        for (int i = 0; i < wordArr.length; i++) {
            StringBuilder sb = new StringBuilder(word);
            for (int j = 0; j < alphabet.length; j++) {
                sb.setCharAt(i, alphabet[j]);
                String resultantWord = sb.toString();
                if (!visitedNodes.contains(resultantWord) && wordSet.contains(resultantWord)) {
                    Node childNode = new Node(resultantWord, parentNode.depth + 1, prepareLineage(parentNode.lineage, resultantWord));
                    childList.add(childNode);
                }
            }
        }
        return childList;
    }

    private List<String> prepareLineage(List<String> lineage, String resultantWord) {
        List<String> newLineage = lineage.stream().collect(Collectors.toList());
        newLineage.add(resultantWord);
        return newLineage;
    }

    private Set<String> prepareSet(List<String> wordList) {
        return wordList.stream().collect(Collectors.toSet());
    }

    static class Node {
        private String originWord;
        private int depth;
        private List<String> lineage;

        public Node(String originWord, int depth, List<String> lineage) {
            this.originWord = originWord;
            this.depth = depth;
            this.lineage = lineage;
        }
    }
}
