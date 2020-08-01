package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class QuestionOne {
	
	
	//Finding top competitors
	public static void main(String ar[]) {
		List<String> competitors = new ArrayList<String>();
		competitors.add("anacell");
		competitors.add("betacellular");
		competitors.add("cetracular");
		competitors.add("deltacellular");
		competitors.add("eurocell");
		
		List<String> reviews = new ArrayList<String>();
		reviews.add("Love anacell");
		reviews.add("betacellular great services");
		reviews.add("deltacellular better than betacellular");
		reviews.add("cetracular worse than eurocell");
		reviews.add("betacellular better than deltacellular");
		
		ArrayList<String> result = topNCompetitors(5, 2, competitors, 3, reviews);
	}
	
	
	
	public static ArrayList<String> topNCompetitors(int numCompetitors, int topNCompetitors, List<String> competitors, 
			int numReviews, List<String> reviews) {
		
		ArrayList<String> result = new ArrayList<String>();
		
		if (topNCompetitors >= numCompetitors) {
			result = (ArrayList<String>)competitors;
		} else {
			Map<String, Integer> frequencyMap = prepareFrequencyMap(competitors, reviews);
			TreeMap<Integer, TreeSet<String>> mapTwo = new TreeMap<Integer, TreeSet<String>>();
			
			for (String com : frequencyMap.keySet()) {
				int frequnecy = frequencyMap.get(com);
				if (!mapTwo.containsKey(frequnecy)) {
					TreeSet<String> treeSet = new TreeSet<String>();
					treeSet.add(com);
					mapTwo.put(frequnecy, treeSet);
				} else {
					TreeSet<String> treeSet = mapTwo.get(frequnecy);
					treeSet.add(com);
				}
			}
			
			int minFrequency = mapTwo.firstKey();
			int maxFrequency = mapTwo.lastKey();
			 
			
			for (int i = maxFrequency; i >= minFrequency; i--) {
				if (mapTwo.containsKey(i)) {
					TreeSet<String> computedCom = mapTwo.get(i);
					for (String c : computedCom) {
							if (result.size() < topNCompetitors) {
								result.add(c);
							} else {
								return result;
							}
					}
				}
			}
		}
		
		return result;

}


	private static Map<String, Integer> prepareFrequencyMap(List<String> competitors, List<String> reviews) {
		Set<String> comSet = new HashSet<String>();
		for (String com : competitors) {
			comSet.add(com);
		}
		
		Map<String, Integer> frequencyMap = new HashMap<String, Integer>();
		
		for (String review : reviews) {
			 for (String com : comSet) {
				 if (review.toLowerCase().contains(com.toLowerCase())) {
					 if (!frequencyMap.containsKey(com)) {
						 frequencyMap.put(com, 1);
					 } else {
						 frequencyMap.put(com, frequencyMap.get(com) + 1);
					 }
				 }
			 }
		}
		return frequencyMap;
	}

}
