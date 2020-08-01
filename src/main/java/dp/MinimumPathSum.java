package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumPathSum {
	
	private Map<Pair, Integer> map = new HashMap<Pair, Integer>();
	
	public int minPathSum(int[][] grid) {
        
        Pair origin = new Pair(0, 0);
        Pair destination = new Pair(grid.length - 1, grid[0].length - 1);
        map.put(destination, grid[grid.length - 1][grid[0].length - 1]);
        return processToComputeMinimumPathSum(grid, origin); 
    }
	
	private int processToComputeMinimumPathSum(int grid[][], Pair pair) {
        if (map.containsKey(pair)) {
            return map.get(pair);
        } else {
            List<Pair> children = getChildren(grid, pair);
            int sum = Integer.MAX_VALUE;
            for (Pair child : children) {
                int val = processToComputeMinimumPathSum(grid, child);
                if (val < sum) {
                    sum = val;
                }
            }
            map.put(pair, sum + grid[pair.abcissa][pair.ordinate]);
            return map.get(pair);
        }
    }
	
	
	private List<Pair> getChildren(int grid[][], Pair parent) {
        List<Pair> children = new ArrayList<Pair>();
        if (parent.abcissa + 1 <= grid.length - 1) {
            children.add(new Pair(parent.abcissa + 1, parent.ordinate));
        }
        
        if (parent.ordinate + 1 <= grid[0].length - 1) {
            children.add(new Pair(parent.abcissa, parent.ordinate + 1));
        }
        return children;
    }
	
	
	class Pair {
        int abcissa;
        int ordinate;
        
        public Pair(int abcissa, int ordinate) {
            this.abcissa = abcissa;
            this.ordinate = ordinate;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + abcissa;
			result = prime * result + ordinate;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (abcissa != other.abcissa)
				return false;
			if (ordinate != other.ordinate)
				return false;
			return true;
		}

		private MinimumPathSum getEnclosingInstance() {
			return MinimumPathSum.this;
		}
    }

}
