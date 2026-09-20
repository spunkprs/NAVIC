package microsoft.arrays;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class LexicographicallySmallestStringGreaterThanTarget {

    public static void main(String ar[]) {
        LexicographicallySmallestStringGreaterThanTarget unit = new LexicographicallySmallestStringGreaterThanTarget();
        String source = "baba";
        String target = "bbaa";

        System.out.println("LexicographicallySmallestStringGreaterThanTarget " + " source being "
                + source + " and target being " + target + " is " + unit.lexGreaterPermutation(source, target));
    }

    public String lexGreaterPermutation(String s, String target) {

        char arrSource[] = s.toCharArray();
        char arrTarget[] = target.toCharArray();

        if (arrSource.length == 1) {
            if (arrSource[0] <= arrTarget[0]) {
                return "";
            }
            return s;
        }

        StringBuilder sortedSource = new StringBuilder();

        IntStream.range(0, arrSource.length).mapToObj(i -> arrSource[i])
                .sorted(Comparator.reverseOrder())
                .forEach(x -> sortedSource.append(x));

        return processToComputeGreaterPermutation(arrSource, arrTarget, sortedSource.toString(), target);
    }

    private String processToComputeGreaterPermutation(char[] arrSource, char[] arrTarget, String sortedSource, String target) {

        if (sortedSource.compareTo(target) <= 0) {
            return "";
        } else {
            TreeMap<Character, Integer> treeMap = new TreeMap<>();
            prepareMap(treeMap, arrSource);

            int indexOne = 0;
            StringBuilder sb = new StringBuilder();

            while (indexOne < arrTarget.length) {
                if (treeMap.containsKey(arrTarget[indexOne])) {
                    sb.append(arrTarget[indexOne]);
                    updateMapAgainstValueDecrement(arrTarget[indexOne], treeMap);
                } else {
                    Character suitableCharacter = treeMap.higherKey(arrTarget[indexOne]);
                    if (suitableCharacter != null) {
                        sb.append(suitableCharacter);
                        updateMapAgainstValueDecrement(suitableCharacter, treeMap);
                        prepareFinalResult(sb, treeMap);
                        break;
                    } else {
                        int indexTwo = indexOne - 1;
                        while (indexTwo >= 0 && treeMap.higherKey(arrTarget[indexTwo]) == null) {
                            Character toBeRemovedCharacter = sb.charAt(indexTwo);
                            sb.deleteCharAt(indexTwo);
                            updateMapAgainstValueIncrement(toBeRemovedCharacter, treeMap);
                            indexTwo--;
                        }

                        if (indexTwo >= 0 && treeMap.higherKey(arrTarget[indexTwo]) != null) {
                            Character toBeRemovedCharacter = sb.charAt(indexTwo);
                            sb.deleteCharAt(indexTwo);
                            updateMapAgainstValueIncrement(toBeRemovedCharacter, treeMap);
                            Character toBeAdded = treeMap.higherKey(arrTarget[indexTwo]);
                            sb.append(toBeAdded);
                            updateMapAgainstValueDecrement(toBeAdded, treeMap);
                            prepareFinalResult(sb, treeMap);
                            break;
                        } else if (indexTwo < 0) {
                            break;
                        }
                    }
                }
                indexOne++;
            }

            if (sb.toString().equals(target)) {
                int index = arrSource.length - 1;
                while (index >= 0 && treeMap.higherKey(arrTarget[index]) == null) {
                    Character toBeRemovedCharacter = sb.charAt(index);
                    sb.deleteCharAt(index);
                    updateMapAgainstValueIncrement(toBeRemovedCharacter, treeMap);
                    index--;
                }

                if (index >= 0 && treeMap.higherKey(arrTarget[index]) != null) {
                    Character toBeRemovedCharacter = sb.charAt(index);
                    sb.deleteCharAt(index);
                    updateMapAgainstValueIncrement(toBeRemovedCharacter, treeMap);
                    Character toBeAdded = treeMap.higherKey(arrTarget[index]);
                    sb.append(toBeAdded);
                    updateMapAgainstValueDecrement(toBeAdded, treeMap);
                    prepareFinalResult(sb, treeMap);
                }
            }
            return sb.toString();
        }
    }

    private void prepareFinalResult(StringBuilder sb, TreeMap<Character, Integer> treeMap) {
        for (Character ch : treeMap.keySet()) {
            int frequency = treeMap.get(ch);
            if (frequency > 1) {
                int i = 1;
                while (i <= frequency) {
                    sb.append(ch);
                    i++;
                }
            } else {
                sb.append(ch);
            }
        }
    }

    private void updateMapAgainstValueDecrement(char ch, TreeMap<Character, Integer> treeMap) {
        int value = treeMap.get(ch);
        if (value > 1) {
            treeMap.put(ch, treeMap.get(ch) - 1);
        } else {
            treeMap.remove(ch);
        }
    }

    private void updateMapAgainstValueIncrement(char ch, TreeMap<Character, Integer> treeMap) {
        if (treeMap.get(ch) != null) {
            treeMap.put(ch, treeMap.get(ch) + 1);
        } else {
            treeMap.put(ch, 1);
        }
    }

    private void prepareMap(TreeMap<Character, Integer> treeMap, char[] arrSource) {
        for (Character ch : arrSource) {
            if (treeMap.containsKey(ch)) {
                treeMap.put(ch, treeMap.get(ch) + 1);
            } else {
                treeMap.put(ch, 1);
            }
        }
    }


}
