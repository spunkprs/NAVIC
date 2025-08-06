package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time

 Constraints :-
 1.) m == grid.length
 2.) n == grid[i].length
 3.) 1 <= m, n <= 200
 4.) 0 <= grid[i][j] <= 200

 * */

public class MinimumPathSum {
	
	private Map<Pair, Integer> map = new HashMap<Pair, Integer>();

	public static void main(String ar[]) {
        MinimumPathSum unit = new MinimumPathSum();

        //int grid[][] = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int grid[][] = {{1, 2, 3}, {4, 5, 6}};

        System.out.print("Minimum path sum for the given matrix is :: " + unit.minPathSumOne(grid));
    }

    //Top down approach && recursive solution
	public int minPathSum(int[][] grid) {
        Pair origin = new Pair(0, 0);
        Pair destination = new Pair(grid.length - 1, grid[0].length - 1);
        map.put(destination, grid[grid.length - 1][grid[0].length - 1]);
        return processToComputeMinimumPathSum(grid, origin); 
    }

    //Pretty simple solution using Bottom Up approach && loop based solution
    public int minPathSumOne(int[][] grid) {
	    if (grid.length == 1 && grid[0].length == 1) {
	        return grid[0][0];
        } else {
	        int intermittentGrid[][] = new int[grid.length][grid[0].length];
	        for (int i = 0; i < grid.length; i++) {
	            for (int j = 0; j < grid[0].length; j++) {
	                if (i == 0 && j == 0) {
	                    intermittentGrid[i][j] = grid[i][j];
                    } else {
	                    persistMinValue(intermittentGrid, grid, i , j);
                    }
                }
            }
	        return intermittentGrid[grid.length-1][grid[0].length - 1];
        }
    }

    private void persistMinValue(int[][] intermittentGrid, int[][] grid, int i, int j) {
	    int numOne = -1;
        int numTwo = -1;

	    if (j - 1 >= 0) {
	        numOne = intermittentGrid[i][j-1] + grid[i][j];
        }

	    if (i - 1 >= 0) {
            numTwo = intermittentGrid[i-1][j] + grid[i][j];
        }

	    if (numOne != -1 && numTwo != -1) {
	        intermittentGrid[i][j] = Math.min(numOne, numTwo);
        } else if (numOne != -1 && numTwo == -1) {
            intermittentGrid[i][j] = numOne;
        } else {
            intermittentGrid[i][j] = numTwo;
        }
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
