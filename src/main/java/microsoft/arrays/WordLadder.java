package microsoft.arrays;

import java.util.*;

public class WordLadder {

    private int minDistance = Integer.MAX_VALUE;
    private Set<String> visitedNodes = new HashSet<>();
    private List<Character> possibleCharacters;
    private boolean shallContinue = true;


    public static void main(String ar[]) {
        WordLadder unit = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        System.out.println("Min distance for converting " + beginWord + " to " + endWord + " is " + unit.ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        possibleCharacters = preparePossibleCharacters();
        visitedNodes.add(beginWord);
        Set<String> dictionary = prepareSet(wordList);
        processWithBfsApproach(beginWord, endWord, dictionary);
        return minDistance == Integer.MAX_VALUE ? 0 : minDistance;
    }

    static class Node {
        private String word;
        int distance;

        public Node(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }
    }

    private void processWithBfsApproach(String startWord, String endWord, Set<String> dictionary) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startWord, 1));
        visitedNodes.add(startWord);

        while (!queue.isEmpty() && shallContinue) {
            for (Node childNode : fetchLegitimateChildren(queue.peek(), dictionary)) {
                if (childNode.word.equals(endWord)) {
                    shallContinue = false;
                    minDistance = childNode.distance < minDistance ? childNode.distance : minDistance;
                } else {
                    queue.add(childNode);
                }
            }
            queue.poll();
        }
    }

    private List<Node> fetchLegitimateChildren(Node peekedNode, Set<String> dictionary) {
        List<Node> children = new ArrayList<>();
        StringBuilder sb = new StringBuilder(peekedNode.word);
        char arr[] = peekedNode.word.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                String intermittentString = sb.substring(1, arr.length);
                for (Character ch : possibleCharacters) {
                    String finalString = ch + intermittentString;
                    if (!visitedNodes.contains(finalString) && dictionary.contains(finalString)) {
                        visitedNodes.add(finalString);
                        children.add(new Node(finalString, peekedNode.distance + 1));
                    }
                }
            } else if (i == arr.length - 1) {
                String intermittentString = sb.substring(0, arr.length - 1);
                for (Character ch : possibleCharacters) {
                    String finalString = new StringBuilder(intermittentString).append(ch).toString();
                    if (!visitedNodes.contains(finalString) && dictionary.contains(finalString)) {
                        visitedNodes.add(finalString);
                        children.add(new Node(finalString, peekedNode.distance + 1));
                    }
                }
            } else {
                String intermittentStringOne = sb.substring(0, i);
                String intermittentStringTwo = sb.substring(i + 1, arr.length);
                for (Character ch : possibleCharacters) {
                    String finalString = new StringBuilder(intermittentStringOne).append(ch).append(intermittentStringTwo).toString();
                    if (!visitedNodes.contains(finalString) && dictionary.contains(finalString)) {
                        visitedNodes.add(finalString);
                        children.add(new Node(finalString, peekedNode.distance + 1));
                    }
                }
            }
        }
        return children;
    }

    private void processWithDfsApproach(String startWord, int distance, String endWord, Set<String> dictionary) {
        char arr[] = startWord.toCharArray();
        StringBuilder sb = new StringBuilder(startWord);

        if (startWord.equals(endWord)) {
            minDistance = distance < minDistance ? distance : minDistance;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) {
                    String intermittentString = sb.substring(1, arr.length);
                    for (Character ch : possibleCharacters) {
                        String finalString = ch + intermittentString;
                        if (!visitedNodes.contains(finalString) && dictionary.contains(finalString)) {
                            visitedNodes.add(finalString);
                            processWithDfsApproach(finalString, distance + 1, endWord, dictionary);
                        }
                    }
                } else if (i == arr.length - 1) {
                    String intermittentString = sb.substring(0, arr.length - 1);
                    for (Character ch : possibleCharacters) {
                        String finalString = new StringBuilder(intermittentString).append(ch).toString();
                        if (!visitedNodes.contains(finalString) && dictionary.contains(finalString)) {
                            visitedNodes.add(finalString);
                            processWithDfsApproach(finalString, distance + 1, endWord, dictionary);
                        }
                    }
                } else {
                    String intermittentStringOne = sb.substring(0, i);
                    String intermittentStringTwo = sb.substring(i + 1, arr.length);
                    for (Character ch : possibleCharacters) {
                        String finalString = new StringBuilder(intermittentStringOne).append(ch).append(intermittentStringTwo).toString();
                        if (!visitedNodes.contains(finalString) && dictionary.contains(finalString)) {
                            visitedNodes.add(finalString);
                            processWithDfsApproach(finalString, distance + 1, endWord, dictionary);
                        }
                    }
                }
            }
        }
    }

    private Set<String> prepareSet(List<String> wordList) {
        Set<String> dictionary = new HashSet<>();
        wordList.forEach(x -> dictionary.add(x));
        return dictionary;
    }

    private List<Character> preparePossibleCharacters() {
        List<Character> possibleCharacters = new ArrayList<>();
        for (int i = 97; i <= 122; i++) {
            possibleCharacters.add((char) i);
        }
        return possibleCharacters;
    }
}
