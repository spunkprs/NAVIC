package amazon;

import java.util.HashSet;
import java.util.Set;

public class NumberOfStepsToShareFilesAcross {
	
	public int numberOfStepsToShareFilesAcross(int m, int n, int matrix[][]) { 
		Pair [][] pairArray = new Pair[m][n];
		int counter = 0;
		Set<Pair> setOne = new HashSet<Pair>();
		Set<Pair> setTwo = new HashSet<Pair>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				pairArray[i][j] = new Pair(i, j, matrix[i][j]);
				if (matrix[i][j] == 1) {
					setOne.add(pairArray[i][j]);
				}
			}
		}
		
		while (!setOne.isEmpty()) {
			
			for (Pair p : setOne) {
				prepareChild(setTwo, p, pairArray);
				p.value = -1;
				pairArray[p.abcissa][p.ordinate] = p;
			}
			counter++;
			setOne = setTwo;
			setTwo = new HashSet<Pair>();
		}
		
		return counter;
	}
	
	private void prepareChild(Set<Pair> setTwo, Pair parent, Pair [][] grid) {
		int startX = parent.abcissa;
		int startY = parent.ordinate;
		
		Pair child = null;
		
		if (startX + 1 < grid.length && grid[startX + 1][startY].value == 0) {
			child = new Pair(startX + 1, startY, 1);
			if (!setTwo.contains(child)) {
				setTwo.add(child);
			}
		}
		
		if (startX - 1 >= 0 && grid[startX - 1][startY].value == 0) {
			child = new Pair(startX - 1, startY, 1);
			if (!setTwo.contains(child)) {
				setTwo.add(child);
			}
		}
		
		if (startY + 1 < grid[0].length && grid[startX][startY + 1].value == 0) {
			child = new Pair(startX, startY + 1, 1);
			if (!setTwo.contains(child)) {
				setTwo.add(child);
			}
		}
		
		if (startY - 1 >= 0 && grid[startX][startY - 1].value == 0) {
			child = new Pair(startX, startY - 1, 1);
			if (!setTwo.contains(child)) {
				setTwo.add(child);
			}
		}
		
	}

	class Pair {
		int abcissa;
		int ordinate;
		int value;
		
		Pair(int x, int y, int value) {
			this.abcissa = x;
			this.ordinate = y;
			this.value = value;
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

		private NumberOfStepsToShareFilesAcross getEnclosingInstance() {
			return NumberOfStepsToShareFilesAcross.this;
		}
	}

}
