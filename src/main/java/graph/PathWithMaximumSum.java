package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathWithMaximumSum {
	
	private int pathWithMaximumSum = 0;
	
	public int getMaximumGold(int[][] grid) {
		Set<Pair> visitedIndexes = new HashSet<Pair>();
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] != 0) {
					Pair p = new Pair(i, j);
					int maximumPathSum = 0;
					visitedIndexes = new HashSet<PathWithMaximumSum.Pair>();
					processToComputePathWithMaximumSum(visitedIndexes, grid, maximumPathSum, p);
				}
			}
		}
        return pathWithMaximumSum;
    }
	
	private void processToComputePathWithMaximumSum(Set<Pair> visitedIndexes, int[][] grid, int maximumPathSum, Pair parent) {
		visitedIndexes.add(parent);
		maximumPathSum += grid[parent.x][parent.y];
		List<Pair> children = findChildren(parent, visitedIndexes, grid);
		if (children.isEmpty()) {
			updatePathWithMaximumSum(maximumPathSum);
		} else {
			for (Pair child : children) {
				processToComputePathWithMaximumSum(visitedIndexes, grid, maximumPathSum, child);
			}
		}
		visitedIndexes.remove(parent);
	}

	private void updatePathWithMaximumSum(int maximumPathSum) {
		if (maximumPathSum > pathWithMaximumSum) {
			pathWithMaximumSum = maximumPathSum;
		}
	}

	private List<Pair> findChildren(Pair p, Set<Pair> visitedIndexes, int[][] grid) {
		List<Pair> children = new ArrayList<Pair>();
		
		if (p.x + 1 < grid.length) {
			Pair child = new Pair(p.x + 1, p.y);
			if (grid[p.x + 1][p.y] != 0 && !visitedIndexes.contains(child)) {
				children.add(child);
			}
		}
		
		if (p.x - 1 >= 0) {
			Pair child = new Pair(p.x - 1, p.y);
			if (grid[p.x - 1][p.y] != 0 && !visitedIndexes.contains(child)) {
				children.add(child);
			}
		}
		
		if (p.y + 1 < grid[0].length) {
			Pair child = new Pair(p.x, p.y + 1);
			if (grid[p.x][p.y + 1] != 0 && !visitedIndexes.contains(child)) {
				children.add(child);
			}
		}
		
		if (p.y - 1 >= 0) {
			Pair child = new Pair(p.x, p.y - 1);
			if (grid[p.x][p.y - 1] != 0 && !visitedIndexes.contains(child)) {
				children.add(child);
			}
		}
		return children;
	}

	class Pair {
		int x;
		int y;
		
		Pair(int x, int y) {
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
		private PathWithMaximumSum getEnclosingInstance() {
			return PathWithMaximumSum.this;
		}
	}

}
