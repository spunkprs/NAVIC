package arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestIncreasingPathInMatrix {
	
	private int longestIncreasingPath = 0;
	
	public int longestIncreasingPath(int[][] matrix) { 
		Set<Pair> set = new HashSet<Pair>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				Pair p = new Pair(i, j);
				set.add(p);
				updateLongestIncreasingPath(set.size());
				processToComputeLongestIncreasingPath(i, j, matrix, set);
				set.remove(p);
			}
		}
        return longestIncreasingPath;
    }

	private void updateLongestIncreasingPath(int size) {
		if (size > longestIncreasingPath) {
			longestIncreasingPath = size;
		}
	}

	private void processToComputeLongestIncreasingPath(int i, int j, int[][] matrix, Set<Pair> set) {
		List<Pair> children = findChildren(i, j, matrix, set);
		updateLongestIncreasingPath(set.size());
		for (Pair p : children) {
			set.add(p);
			processToComputeLongestIncreasingPath(p.x, p.y, matrix, set);
			set.remove(p);
		}
	}
	
	private List<Pair> findChildren(int i, int j, int[][] matrix, Set<Pair> set) {
		List<Pair> children = new ArrayList<Pair>();
		
		if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
			Pair p = new Pair(i + 1, j);
			if (!set.contains(p)) {
				children.add(new Pair(i + 1, j));
			}
		}
		
		if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
			Pair p = new Pair(i - 1, j);
			if (!set.contains(p)) {
				children.add(new Pair(i - 1, j));
			}
		}
		
		if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
			Pair p = new Pair(i, j + 1);
			if (!set.contains(p)) {
				children.add(new Pair(i, j + 1));
			}
		}
		
		if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
			Pair p = new Pair(i, j - 1);
			if (!set.contains(p)) {
				children.add(new Pair(i, j - 1));
			}
		}
		return children;
	}

	class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + x;
			result = prime * result + y;
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
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		private LongestIncreasingPathInMatrix getEnclosingInstance() {
			return LongestIncreasingPathInMatrix.this;
		}
		
		
		
	}

}
