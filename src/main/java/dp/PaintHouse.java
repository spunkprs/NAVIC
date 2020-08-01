package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaintHouse {
	
	public int minCost(int[][] costs) {
		
		if (costs.length > 1 && costs[0].length == 3) {
			int minimumCost = Integer.MAX_VALUE;
			
			Map<Pair, Integer> map = new HashMap<Pair, Integer>();
			
			for (int j = 0; j < costs[0].length; j++) {
				minimumCost = Math.min(minimumCost, processToComputeMinimumCost(j, 0, map, costs));
			}
	        return minimumCost;
		} else {
			int minCost = Integer.MAX_VALUE;
			if (costs.length == 1 && costs[0].length > 0) {
				for (int j = 0; j < costs[0].length; j++) {
					minCost = Math.min(minCost, costs[0][j]);
				}
				return minCost;
			} 
			return 0;
		}
    }
	
	private int processToComputeMinimumCost(int j, int i, Map<Pair, Integer> map, int[][] costs) {
		Pair p = null;
		p = new Pair(i, j);
		if (map.containsKey(p)) {
			return map.get(p);
		} else {
			if (i == costs.length - 1) {
				p = new Pair(i, j);
				if (!map.containsKey(p)) {
					map.put(p, costs[i][j]);
				} else {
					return map.get(p);
				}
			} else {
				int minCost = Integer.MAX_VALUE;
				for (Pair pair : getChildPair(j, i, costs)) {
					minCost = Math.min(minCost, processToComputeMinimumCost(pair.ordinate, pair.abcissa, map, costs));
				}
				map.put(p, minCost + costs[i][j]);
			}
			return map.get(p);
		}
	}

	private List<Pair> getChildPair(int j, int i, int[][] costs) {
		
		List<Pair> pairs = new ArrayList<Pair>();
		for (int k = 0; k < costs[0].length; k++) {
			if (k != j) {
				pairs.add(new Pair(i + 1, k));
			}
		}
		return pairs;
	}

	class Pair {
		int abcissa;
		int ordinate;
		
		Pair(int abcissa, int ordinate) {
			this.abcissa = abcissa;
			this.ordinate = ordinate;
		}
	}
	
	
public int minCostApproachTwo(int[][] costs) {
	if (costs.length > 1 && costs[0].length == 3) {
		for (int i = costs.length - 2; i >= 0; i--) {
			for (int j = 0; j < costs[0].length; j++) {
				if (j == 0) {
					costs[i][j] = Math.min(costs[i + 1][1], costs[i + 1][2]) + costs[i][j];
				} else if (j == 1) {
					costs[i][j] = Math.min(costs[i + 1][0], costs[i + 1][2]) + costs[i][j];
				} else {
					costs[i][j] = Math.min(costs[i + 1][0], costs[i + 1][1]) + costs[i][j];
				}
			}
		}
		int minValue = Integer.MAX_VALUE;
		for (int j = 0; j < costs[0].length; j++) {
			if (costs[0][j] < minValue) {
				minValue = costs[0][j];
			}
		}
		return minValue;
	} else {
		int minCost = Integer.MAX_VALUE;
		if (costs.length == 1 && costs[0].length > 0) {
			for (int j = 0; j < costs[0].length; j++) {
				minCost = Math.min(minCost, costs[0][j]);
			}
			return minCost;
		} 
		return 0;
	}	
    }
}
