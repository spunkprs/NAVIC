package arrays.shortestworddistance;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/*
*
Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.

Implement the WordDistance class:

WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.

* */

public class ShortestWordDistanceTwo {

    private String[] dictionary;
    private Map<String, List<Integer>> map;

    public ShortestWordDistanceTwo(String[] wordsDict) {
        this.dictionary = wordsDict;
        this.map = new HashMap();

        for (int i = 0; i < this.dictionary.length; i++) {
            insertWordIntoMap(map, this.dictionary[i], i);
        }
    }

    /*
     *
     * Following are the detailing around time && space complexity
     * a.) Brute force approach would give O(pow(n, 2)) time complexity && O(1) space complexity
     * b.) Time Complexity = O(n)
     * c.) Space Complexity = O(n)
     * */

    public int shortest(String word1, String word2) {
        int minDistance = Integer.MAX_VALUE;

        List<Integer> wordOneIndexList = this.map.get(word1);
        List<Integer> wordTwoIndexList = this.map.get(word2);

        int indexOne = 0;
        int indexTwo = 0;

        while (indexOne < wordOneIndexList.size() && indexTwo < wordTwoIndexList.size()) {
            minDistance = updateMinDistance(wordOneIndexList.get(indexOne), wordTwoIndexList.get(indexTwo), minDistance);
            if (wordOneIndexList.get(indexOne) < wordTwoIndexList.get(indexTwo)) {
                indexOne++;
            } else {
                indexTwo++;
            }
        }
        return minDistance;
    }

    private int updateMinDistance(int indexWordOne, int indexWordTwo, int minDistance) {
        int distance = Math.abs(indexWordOne - indexWordTwo);
        return distance < minDistance ? distance : minDistance;
    }

    private void insertWordIntoMap(Map<String, List<Integer>> map, String word, int index) {
        if (!map.containsKey(word)) {
            List<Integer> indexList = new ArrayList();
            indexList.add(index);
            map.put(word, indexList);
        } else {
            map.get(word).add(index);
        }
    }

}
