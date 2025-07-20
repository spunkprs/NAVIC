package graph;

import java.util.Objects;

/**
 A certain bug's home is on the x-axis at position x. Help them get there from position 0.

 The bug jumps according to the following rules:

 1.) It can jump exactly a positions forward (to the right).
 2.) It can jump exactly b positions backward (to the left).
 3.) It cannot jump backward twice in a row.
 4.) It cannot jump to any forbidden positions.
 5.) The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.

 Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x,
 return the minimum number of jumps needed for the bug to reach its home.
 If there is no possible sequence of jumps that lands the bug on position x, return -1.


 Constraints:

 1.) 1 <= forbidden.length <= 1000
 2.) 1 <= a, b, forbidden[i] <= 2000
 3.) 0 <= x <= 2000
 4.) All the elements in forbidden are distinct.
 5.) Position x is not forbidden.

 Source --> Leetcode

 * */

public class MinimumJumpsToReachHome {

    public static void main(String ar[]) {
        MinimumJumpsToReachHome unit = new MinimumJumpsToReachHome();
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {

        return -1;
    }

    static class Node {
        private int destinationIndex;
        private int state;

        public Node(int destinationIndex, int state) {
            this.destinationIndex = destinationIndex;
            this.state = state;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return destinationIndex == node.destinationIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(destinationIndex);
        }
    }
}
