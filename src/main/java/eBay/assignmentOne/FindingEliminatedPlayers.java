package eBay.assignmentOne;


import java.util.*;

/**
Source : Code Signal for eBay
 * */

public class FindingEliminatedPlayers {

    public static void main(String ar[]) {
        FindingEliminatedPlayers unit = new FindingEliminatedPlayers();
        String laps[][] = {{"Harold 154", "Gina 155", "Juan 160"},
                {"Juan 152", "Gina 153", "Harold 160"},
                {"Harold 148", "Gina 150", "Juan 151"}};
        String[] result = unit.fetchingEliminatedPlayersInTheRace(laps);
        for (String res : result) {
            System.out.println(res);
        }
    }

    public String[] fetchingEliminatedPlayersInTheRace(String [][] laps) {

        Set<String> eliminatedPlayers = new HashSet<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < laps.length; i++) {
            TreeMap<Integer, List<String>> map = new TreeMap<>();
            for (int j = 0; j < laps[i].length; j++) {

                String input = laps[i][j];
                String name = input.split(" ")[0];
                int raceTime = Integer.parseInt(input.split(" ")[1]);


                if (!eliminatedPlayers.contains(name)) {
                    if (!map.containsKey(raceTime)) {
                        List<String> names = new ArrayList<>();
                        names.add(name);
                        map.put(raceTime, names);
                    } else {
                        map.get(raceTime).add(name);
                    }
                }
            }

            List<String> fetchedNames = map.get(map.lastKey());
            List<String> intermittentResult = new ArrayList<>();
            for (String fetchedName : fetchedNames) {
                if (!eliminatedPlayers.contains(fetchedName)) {
                    eliminatedPlayers.add(fetchedName);
                    intermittentResult.add(fetchedName);
                }
            }
            Collections.sort(intermittentResult);
            result.addAll(intermittentResult);
        }
        return convertToArray(result);
    }

    private String[] convertToArray(List<String> result) {
        String resArray [] = new String[result.size()];
        int index = 0;
        for (String res : result) {
            resArray[index] = res;
            index++;
        }
        return resArray;
    }

}
