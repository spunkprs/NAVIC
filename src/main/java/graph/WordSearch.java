package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {
	
	private Set<Pair> pairSet = new HashSet<Pair>();
	private boolean continueWithSearch = true;
	
	public boolean exist(char[][] board, String word) {
		char arr[] = word.toCharArray();
		if (board.length == 1 && board[0].length == 1 && word.length() == 1) {
			if (board[0][0] == arr[0]) {
				return true;
			}
			return false;
		} else {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					if (arr[0] == board[i][j]) {
						processToCheckIfWordExistsInBoard(board, i, j, arr, 0);
					}
				}
			}
			return !continueWithSearch;
		}
    }

	private void processToCheckIfWordExistsInBoard(char[][] board, int abcissaBoard, int ordinateBoard, char arr[], int alphabetPosition) {
		Pair parent = new Pair(abcissaBoard, ordinateBoard);
		pairSet.add(parent);
		if (alphabetPosition + 1 < arr.length) {
			List<Pair> neighbours = fetchNeighbours(board, abcissaBoard, ordinateBoard);
			for (Pair neighbour : neighbours) {
				if (board[neighbour.abcissa][neighbour.ordinate] == arr[alphabetPosition + 1] && (alphabetPosition + 1) < arr.length && continueWithSearch) {
					if (alphabetPosition + 1 == arr.length - 1) {
						continueWithSearch = false;
					} else {
						processToCheckIfWordExistsInBoard(board, neighbour.abcissa, neighbour.ordinate, arr, alphabetPosition + 1);
					}
				}
			}	
		} else {
			if (alphabetPosition == arr.length - 1) {
				continueWithSearch = false;
			}
		}
		pairSet.remove(parent);
	}
	
	
	private List<Pair> fetchNeighbours(char[][] board, int abcissaBoard, int ordinateBoard) {
		List<Pair> neighbours = new ArrayList<Pair>();
		if (abcissaBoard + 1 < board.length) {
			Pair p = new Pair(abcissaBoard + 1, ordinateBoard);
			if (!pairSet.contains(p)) {
				neighbours.add(p);
			}
		}
		
		if (abcissaBoard - 1 >= 0) {
			Pair p = new Pair(abcissaBoard - 1, ordinateBoard);
			if (!pairSet.contains(p)) {
				neighbours.add(p);
			}
		}
		
		if (ordinateBoard + 1 < board[0].length) {
			Pair p = new Pair(abcissaBoard, ordinateBoard + 1);
			if (!pairSet.contains(p)) {
				neighbours.add(p);
			}
		}
		
		if (ordinateBoard - 1 >= 0) {
			Pair p = new Pair(abcissaBoard, ordinateBoard - 1);
			if (!pairSet.contains(p)) {
				neighbours.add(p);
			}
		}
		return neighbours;
	}


	class Pair {
		private int abcissa;
		private int ordinate;
		
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

		private WordSearch getEnclosingInstance() {
			return WordSearch.this;
		}
	}

}
