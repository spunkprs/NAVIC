package dp;

import java.util.HashMap;
import java.util.Map;

public class PaintFence {

	private int maxDepth = 0;
	private int maxNumberOfColors = 0;
	
	Map<Pair, Integer> map = new HashMap<Pair, Integer>();
	
	public int numWays(int n, int k) {
		maxDepth = n;
		maxNumberOfColors = k;
		
		if (n > 1) {
			Pair parent = new Pair(0, 1, 1);
			return processToComputeNumberOfWays(parent, 1) * k;
		} else if (n == 1 && k >= n) {
			return k;
		} 
		return 0;
    }

	private int processToComputeNumberOfWays(Pair parent, int previousColorNumberCount) {
			Pair p = null;
			int numberOfWays = 0;
			if (!map.containsKey(parent)) {
				if (parent.depth != maxDepth) {
					for (int i = 1; i <= maxNumberOfColors; i++) {
						if (i == parent.child) {
							if (previousColorNumberCount < 2) {
								p = new Pair(parent.child, i, parent.depth + 1);
								if (!map.containsKey(p)) {
									numberOfWays += processToComputeNumberOfWays(p, previousColorNumberCount + 1);
								} else {
									numberOfWays += map.get(p);
								}
							}
						} else {
							p = new Pair(parent.child, i, parent.depth + 1);
							if (!map.containsKey(p)) {
								numberOfWays += processToComputeNumberOfWays(p, 1);
							} else {
								numberOfWays += map.get(p);
							}
						}
					}
				
			} else {
				return 1;
			}
			} else {
				map.get(parent);
			}
			map.put(parent, numberOfWays);
			return map.get(parent);
	}
	
	class Pair {
		
		private int parent;
		private boolean isChildSameAsParent;
		private int depth;
		private int child; 
		
		Pair(int parent, int child, int depth) {
			this.parent = parent;
			this.child = child;
			this.depth = depth;
			this.isChildSameAsParent = parent == child ? true : false;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + depth;
			result = prime * result + (isChildSameAsParent ? 1231 : 1237);
			result = prime * result + parent;
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
			if (depth != other.depth)
				return false;
			if (isChildSameAsParent != other.isChildSameAsParent)
				return false;
			if (parent != other.parent)
				return false;
			return true;
		}
		private PaintFence getEnclosingInstance() {
			return PaintFence.this;
		}	
	}
	
}
