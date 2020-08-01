package arrays;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslands {
	
	 	public int numIslands(char[][] grid) {
	 	 return processToComputeNumberOfIslands(grid);	
	    }

		private int processToComputeNumberOfIslands(char[][] grid) {
			int numberOfIslands = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == '1') {
						numberOfIslands++;
						grid[i][j] = 'v';
						changeStateOfNearByCoordinates(grid, i, j);
					}
				}
			}
			return numberOfIslands;
		}

		private void changeStateOfNearByCoordinates(char[][] grid, int startX, int startY) {
			for (Pair p : findNearByPairs(grid, startX, startY)) {
				grid[p.abcissa][p.ordinate] = 'v';
				changeStateOfNearByCoordinates(grid, p.abcissa, p.ordinate);
			}
		}
		
		private List<Pair> findNearByPairs(char[][] grid, int startX, int startY) {
			List<Pair> pairs = new ArrayList<Pair>();
			return pairs;
		}

		class Pair {
			int abcissa;
			int ordinate;
			int value;
			Pair (int x, int y) {
				this.abcissa = x;
				this.ordinate = y;
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
			private NumberOfIslands getEnclosingInstance() {
				return NumberOfIslands.this;
			}
			
		}

}
