package backtracking;

import java.util.*;

/**
 Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 Return the answer in any order.

 A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

 Constraints:

 1.) 0 <= digits.length <= 4
 2.) digits[i] is a digit in the range ['2', '9'].

 Source : LeetCode

 * */

public class LetterCombinationsOfPhoneNumber {

    private Map<Node, List<String>> intermittentResultsOne = new HashMap<>();


    public static void main(String ar[]) {
        LetterCombinationsOfPhoneNumber unit = new LetterCombinationsOfPhoneNumber();
        String inputString = "234";
        List<String> result = unit.generateLetterCombinations(inputString);
        System.out.println("Printing final combinations for input string " + inputString);
        for (String res : result) {
            System.out.println(res);
        }
    }

    public List<String> generateLetterCombinations(String digits) {
        List<String> finalResult = new ArrayList<>();
        Map<Integer, List<Character>> mapping = fetchMappings();
        char inputArr[] = digits.toCharArray();

        if (!digits.isEmpty()) {
            for (int i = 0; i < 1; i++) {
                int num = Integer.parseInt(String.valueOf(inputArr[0]));
                List<Character> characterMappings = mapping.get(num);
                for (Character character : characterMappings) {
                    Node node = new Node(character, 0);
                    if (digits.length() > 1) {
                        processToGenerateCombinations(node, inputArr, mapping);
                    } else {
                        intermittentResultsOne.put(node, Arrays.asList(String.valueOf(node.character)));
                    }
                }
            }

            for (Node node : intermittentResultsOne.keySet()) {
                if (node.depth == 0) {
                    finalResult.addAll(intermittentResultsOne.get(node));
                }
            }
        }
        return finalResult;
    }

    private void processToGenerateCombinations(Node node, char inputArr[], Map<Integer, List<Character>> mapping) {
        if (node.depth + 1 < inputArr.length - 1) {
            int num = Integer.parseInt(String.valueOf(inputArr[node.depth + 1]));
            List<Character> characterMappings = mapping.get(num);
            for (Character character : characterMappings) {
                Node childNode = new Node(character, node.depth + 1);
                if (!intermittentResultsOne.containsKey(childNode)) {
                    processToGenerateCombinations(childNode, inputArr, mapping);
                }

                List<String> result = new ArrayList<>();
                List<String> resultSubStrings = intermittentResultsOne.get(childNode);
                for (String resultSubString : resultSubStrings) {
                    result.add(node.character + resultSubString);
                }

                if (!intermittentResultsOne.containsKey(node)) {
                    intermittentResultsOne.put(node, result);
                } else {
                    intermittentResultsOne.get(node).addAll(result);
                }
            }
        } else {
            int num = Integer.parseInt(String.valueOf(inputArr[node.depth + 1]));
            List<Character> characterMappings = mapping.get(num);
            List<String> result = new ArrayList<>();

            for (Character character : characterMappings) {
                result.add(node.character + String.valueOf(character));
            }
            if (!intermittentResultsOne.containsKey(node.character)) {
                intermittentResultsOne.put(node, result);
            }
        }
    }

    private Map<Integer, List<Character>> fetchMappings() {
        Map<Integer, List<Character>> mapping = new HashMap<>();
        mapping.put(2, Arrays.asList('a', 'b','c'));
        mapping.put(3, Arrays.asList('d', 'e','f'));
        mapping.put(4, Arrays.asList('g', 'h','i'));
        mapping.put(5, Arrays.asList('j', 'k','l'));
        mapping.put(6, Arrays.asList('m', 'n','o'));
        mapping.put(7, Arrays.asList('p', 'q','r', 's'));
        mapping.put(8, Arrays.asList('t','u', 'v'));
        mapping.put(9, Arrays.asList('w','x', 'y', 'z'));
        return mapping;
    }

    static class Node {
        private Character character;
        private int depth;

        public Node(Character character, int depth) {
            this.character = character;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return depth == node.depth && Objects.equals(character, node.character);
        }

        @Override
        public int hashCode() {
            return Objects.hash(character, depth);
        }
    }
}
