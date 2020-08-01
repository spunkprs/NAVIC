import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    class Node {
        Character character;
        Node next;
        Node previous;
        int index;

        Node(char character, int index) {
            this.character = character;
            this.index = index;
        }
    }

    public int lengthOfLongestSubstring(String s) {

        Node head = null;
        Node tail = null;

        char arr[] = s.toCharArray();
        Map<Character, Node> map = new HashMap<Character, Node>();
        int max_length = 0;

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                Node node = new Node(arr[i], i);
                map.put(new Character(arr[i]), node);
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    tail.next = node;
                    node.previous = tail;
                    tail = node;
                }
            } else {
                if (!tail.character.equals(arr[i])) {
                    Node node = map.get(arr[i]);
                    if (node.index >= head.index) {
                        head = node.next;
                    }
                    node.index = i;
                    tail.next = node;
                    node.previous = tail;
                    tail = node;
                } else {
                    tail.index = i;
                    head = tail;
                }
            }
            max_length = updateMaxLength(tail.index - head.index + 1, max_length);
        }
        return max_length;
    }

    private int updateMaxLength(int length, int max_lenth) {
        return length > max_lenth ? length : max_lenth;
    }
}
