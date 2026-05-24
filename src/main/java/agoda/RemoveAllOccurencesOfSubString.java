package agoda;

public class RemoveAllOccurencesOfSubString {

    public static void main(String ar[]) {
        RemoveAllOccurencesOfSubString unit = new RemoveAllOccurencesOfSubString();
        String word = "axxxxyyyyb";
        String containedWord = "xy";

        System.out.print("Word post removing all occurences of " + containedWord + " is " + unit.removeOccurrences(word, containedWord));
    }

    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        int startIndex = sb.indexOf(part);

        while (startIndex != -1) {
            sb.delete(startIndex, startIndex + part.length());
            startIndex = sb.indexOf(part);
        }
        return sb.toString();
    }
}
