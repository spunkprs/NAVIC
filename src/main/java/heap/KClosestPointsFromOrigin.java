package heap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class KClosestPointsFromOrigin {
	
	private ContainerHeap root = null;
	private List<ContainerHeap> heap = new ArrayList<ContainerHeap>();
	
	public int[][] kClosest(int[][] points, int K) {
		
		int result[][] = new int[K][2];
		prepareContainerHeap(points, K);
		prepareResult(result);
		return result;   
    }
	
	private void prepareResult(int result[][]) {
		int i = 0;
		while (i < heap.size()) {
			Container container = heap.get(i).container;
			result[i][0] = container.x;
			result[i][1] = container.y;
			i++;
		}
	}

	private void prepareContainerHeap(int[][] points, int K) {
		for (int i = 0; i < points.length; i++) {
			Container c = new Container(points[i][0], points[i][1]);
			ContainerHeap containerHeapNode = new ContainerHeap(i, c);
			if (root == null) {
				root = containerHeapNode;
				heap.add(root);
			} else {
				int parentIndex = Math.abs((heap.size()-1)/2);
				ContainerHeap parentNode = heap.get(parentIndex);
				if (heap.size() < K) {
					heap.add(containerHeapNode);
					if (parentNode.container.distanceFromOriginInDouble < containerHeapNode.container.distanceFromOriginInDouble) {
						reheapifyWhenAddition(parentNode.index, containerHeapNode.index);
					}
				} else if (heap.size() == K) {
					if (heap.get(0).container.distanceFromOriginInDouble > containerHeapNode.container.distanceFromOriginInDouble) {
						heap.set(0, containerHeapNode);
						containerHeapNode.index = 0;
						reheapifyWhenDeletion(0);
					}
				} 
				
			}
		} 
	}
	
	private void reheapifyWhenDeletion(int parentIndex) {
		ContainerHeap parentNode = heap.get(parentIndex);
		ContainerHeap childNodeOne = null; 
		if (2 * parentIndex + 1 < heap.size()) {
			childNodeOne = heap.get(2 * parentIndex + 1);
		}		
		ContainerHeap childNodeTwo = null; 
		if (2 * parentIndex + 2 < heap.size()) {
			childNodeTwo = heap.get(2 * parentIndex + 2);
		}
		
		if (childNodeOne != null && childNodeTwo != null) {
			if ((parentNode.container.distanceFromOriginInDouble < childNodeOne.container.distanceFromOriginInDouble) || 
					(parentNode.container.distanceFromOriginInDouble < childNodeTwo.container.distanceFromOriginInDouble)) {
				if (childNodeOne.container.distanceFromOriginInDouble >= childNodeTwo.container.distanceFromOriginInDouble) {
					int childIndex = childNodeOne.index;
					swapElementsInHeap(childNodeOne, parentNode);
					reheapifyWhenDeletion(childIndex);
				} else {
					int childIndex = childNodeTwo.index;
					swapElementsInHeap(childNodeTwo, parentNode);
					reheapifyWhenDeletion(childIndex);
				}
			}
		} else if (childNodeOne != null && childNodeTwo == null) {
			if (parentNode.container.distanceFromOriginInDouble < childNodeOne.container.distanceFromOriginInDouble) {
				int childIndex = childNodeOne.index;
				swapElementsInHeap(childNodeOne, parentNode);
				reheapifyWhenDeletion(childIndex);
			}
		}
	}

	private void swapElementsInHeap(ContainerHeap childNode, ContainerHeap parentNode) {
		int childIndex = childNode.index;
		int parentIndex = parentNode.index;
		
		heap.set(parentIndex, childNode);
		heap.set(childIndex, parentNode);
		childNode.index = parentIndex;
		parentNode.index = childIndex;
	}

	private void reheapifyWhenAddition(int parentIndex, int childIndex) {
		
		while ((heap.get(parentIndex).container.distanceFromOriginInDouble < heap.get(childIndex).container.distanceFromOriginInDouble) && (parentIndex != childIndex)) {
			 int parentNodeIndex = heap.get(parentIndex).index;
			 ContainerHeap parentNode = heap.get(parentIndex);
			 ContainerHeap childNode = heap.get(childIndex);
			 heap.set(parentNode.index, childNode);
			 heap.set(childNode.index, parentNode);
			 parentNode.index = childNode.index;
			 childNode.index = parentNodeIndex;
			 
			 childIndex = parentIndex;
			 parentIndex = Math.abs((parentIndex - 1)/2);
		}
	}

	class ContainerHeap {
		private int index;
		private Container container;
		
		public ContainerHeap(int index, Container container) {
			this.index = index;
			this.container = container;
		}
	}


	class Container {
        private int x;
        private int y;
        private BigDecimal distanceFromOrigin;
        private Double distanceFromOriginInDouble;
        
        public Container(int x, int y) {
            this.x = x;
            this.y = y;
            
            distanceFromOrigin = new BigDecimal(Math.sqrt((this.x * this.x) + (this.y * this.y)));
            distanceFromOrigin = distanceFromOrigin.setScale(4, RoundingMode.HALF_UP);
            distanceFromOriginInDouble = distanceFromOrigin.doubleValue();
        }
    }

}
