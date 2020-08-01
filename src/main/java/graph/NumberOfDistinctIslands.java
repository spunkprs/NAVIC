package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumberOfDistinctIslands {
	
	private Set<Coordinates> setOne = new HashSet<Coordinates>();
	private Set<String> setTwo = new HashSet<String>();
	
	private Map<Integer, Map<Integer, Pair>> mapOne = new HashMap<Integer, Map<Integer, Pair>>();
	
	public int numDistinctIslands(int[][] grid) {
		processToComputeNumberOfIslands(grid);
		return setTwo.size();
    }

	private void processToComputeNumberOfIslands(int[][] grid) {
		int k = 1;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					Coordinates parentCoordinate = new Coordinates(i, j);
					
					if (!setOne.contains(parentCoordinate)) {
						if (!mapOne.containsKey(k)) {
							Map<Integer, Pair> map = new HashMap<Integer, Pair>();
							mapOne.put(k, map);
						}
						furtherProcessToComputeNumberOfIslands(parentCoordinate, grid, k);
						String processedString = prepareProcessedString(k, grid);
						if (!setTwo.contains(processedString)) {
							setTwo.add(processedString);
						}
						mapOne = new HashMap<Integer, Map<Integer, Pair>>();
						k++;
					}
				}
			}
		}
	}
	
	private String prepareProcessedString(int islandNumber, int grid[][]) {
		StringBuilder sb = new StringBuilder();
		Map<Integer, Pair> map = mapOne.get(islandNumber);
		for (int i = 0; i < grid.length; i++) {
			if (map.containsKey(i)) {
				Pair p = map.get(i);
				for (int j = p.minimum; j <= p.maximum; j++) {
					if (grid[i][j] == 1) {
						sb.append(String.valueOf(1));
					} else {
						sb.append(String.valueOf(0));
					}
				}
				sb.append("\n");
			}
			
		}
		return sb.toString();
	}

	private void furtherProcessToComputeNumberOfIslands(Coordinates parentCoordinate, int[][] grid, int islandNumber) {
		for (Coordinates child : fetchChildren(parentCoordinate, grid, islandNumber)) {
			furtherProcessToComputeNumberOfIslands(child, grid, islandNumber);
		}
	}

	private List<Coordinates> fetchChildren(Coordinates parentCoordinate, int[][] grid, int islandNumber) {
		setOne.add(parentCoordinate);
		Map<Integer, Pair> map = mapOne.get(islandNumber);
		if (!map.containsKey(parentCoordinate.abcissa)) {
			Pair p = new Pair();
			p.minimum = parentCoordinate.ordinate;
			p.maximum = parentCoordinate.ordinate;
			map.put(parentCoordinate.abcissa, p);
		} else {
			Pair p = map.get(parentCoordinate.abcissa);
			if (parentCoordinate.ordinate < p.minimum) {
				p.minimum = parentCoordinate.ordinate;
			}
			
			if (parentCoordinate.ordinate > p.maximum) {
				p.maximum = parentCoordinate.ordinate;
			}
		}

		List<Coordinates> children = new ArrayList<Coordinates>();
		
		int parentX = parentCoordinate.abcissa;
		int parentY = parentCoordinate.ordinate;
		
		if (parentY + 1 <= grid[0].length - 1 && grid[parentX][parentY + 1] == 1) {
			Coordinates child = new Coordinates(parentX, parentY + 1);
			if (!setOne.contains(child)) {
				children.add(child);
			}
		}
		
		if (parentY - 1 >= 0 && grid[parentX][parentY - 1] == 1) {
			Coordinates child = new Coordinates(parentX, parentY - 1);
			if (!setOne.contains(child)) {
				children.add(child);
			}
		}
		
		if (parentX - 1 >= 0 && grid[parentX - 1][parentY] == 1) {
			Coordinates child = new Coordinates(parentX - 1, parentY);
			if (!setOne.contains(child)) {
				children.add(child);
			}
		}
		
		if (parentX + 1 <= grid.length - 1 && grid[parentX + 1][parentY] == 1) {
			Coordinates child = new Coordinates(parentX + 1, parentY);
			if (!setOne.contains(child)) {
				children.add(child);
			}
		}
		return children;
	}

	class Coordinates {
		private int abcissa;
		private int ordinate;
		private int num;
		
		public Coordinates(int abcissa, int ordinate) {
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
			Coordinates other = (Coordinates) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (abcissa != other.abcissa)
				return false;
			if (ordinate != other.ordinate)
				return false;
			return true;
		}
		
		private NumberOfDistinctIslands getEnclosingInstance() {
			return NumberOfDistinctIslands.this;
		}
	}
	
	class Pair {
		private int maximum;
		private int minimum;
	}

}
