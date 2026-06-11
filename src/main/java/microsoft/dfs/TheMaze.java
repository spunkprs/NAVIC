package microsoft.dfs;

import java.util.*;

/**
Problem : 490
Link : https://leetcode.com/problems/the-maze/description/?envType=problem-list-v2&envId=depth-first-search

There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol]
and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).


Constraints:-

1.) m == maze.length
2.) n == maze[i].length
3.) 1 <= m, n <= 100
4.) maze[i][j] is 0 or 1.
5.) start.length == 2
6.) destination.length == 2
7.) 0 <= startrow, destinationrow < m
8.) 0 <= startcol, destinationcol < n
9.) Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
10.) The maze contains at least 2 empty spaces.

Time Complexity = O(m * n * 4) --> O(m * n)
Space Complexity = O(m * n * 4) --> O(m * n)

Observation : This question was bit different from the usual DFS question but I enjoyed solving it !!
 * */

public class TheMaze {

    public static void main(String ar[]) {
        TheMaze unit = new TheMaze();
        int maze[][] = {{0,0,1,0,0}, {0,0,0,0,0}, {0,0,0,1,0}, {1,1,0,1,1}, {0,0,0,0,0}};
        int start[] = {0, 4};
        int end[] = {3,2};

        System.out.print("Will ball be able to reach from source to destination " + unit.hasPath(maze, start, end));
    }

    private boolean shallContinue = true;
    private boolean result = false;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {

        Index startNode = new Index(start[0], start[1], "");
        Index endNode = new Index(destination[0], destination[1], "");

        Set<Index> visitedNodes = new HashSet<>();
        visitedNodes.add(startNode);
        processToExplorePath(startNode, endNode, maze, visitedNodes, "");
        return result;
    }

    private void processToExplorePath(Index startNode, Index endNode, int maze[][], Set<Index> visitedNodes, String followedDirection) {

        if (shallContinue) {
            Map<String, Index> childrenAgainstDirection = fetchChildren(startNode, maze, endNode, visitedNodes, followedDirection);

            if (!childrenAgainstDirection.isEmpty()) {
                for (String direction : childrenAgainstDirection.keySet()) {
                    Index childNode = childrenAgainstDirection.get(direction);
                    visitedNodes.add(childNode);
                    if (childNode.indexX == endNode.indexX && childNode.indexY == endNode.indexY) {
                        String childDirection = childNode.direction;
                        if (childDirection.equals(Direction.DOWN.direction)) {
                            if (childNode.indexX == maze.length - 1) {
                                shallContinue = false;
                                result = true;
                            } else if (childNode.indexX < maze.length - 1 && maze[childNode.indexX + 1][childNode.indexY] == 1) {
                                shallContinue = false;
                                result = true;
                            } else {
                                processToExplorePath(childNode, endNode, maze, visitedNodes, childDirection);
                            }
                        } else if (childDirection.equals(Direction.UP.direction)) {
                            if (childNode.indexX == 0) {
                                shallContinue = false;
                                result = true;
                            } else if (childNode.indexX > 0 && maze[childNode.indexX - 1][childNode.indexY] == 1) {
                                shallContinue = false;
                                result = true;
                            } else {
                                processToExplorePath(childNode, endNode, maze, visitedNodes, childDirection);
                            }
                        } else if (childDirection.equals(Direction.LEFT.direction)) {
                            if (childNode.indexY == 0) {
                                shallContinue = false;
                                result = true;
                            } else if (childNode.indexY > 0 && maze[childNode.indexX][childNode.indexY - 1] == 1) {
                                shallContinue = false;
                                result = true;
                            } else {
                                processToExplorePath(childNode, endNode, maze, visitedNodes, childDirection);
                            }
                        } else if (childDirection.equals(Direction.RIGHT.direction)) {
                            if (childNode.indexY == maze[0].length - 1) {
                                shallContinue = false;
                                result = true;
                            } else if (childNode.indexY < maze[0].length - 1 && maze[childNode.indexX][childNode.indexY + 1] == 1) {
                                shallContinue = false;
                                result = true;
                            } else {
                                processToExplorePath(childNode, endNode, maze, visitedNodes, childDirection);
                            }
                        }
                    } else {
                    processToExplorePath(childNode, endNode, maze, visitedNodes, childNode.direction);
                    }
                }
            }
        }
    }

    private Map<String, Index> fetchChildren(Index parentNode, int maze[][], Index destinationNode, Set<Index> visitedNodes, String followedDirection) {
        Map<String, Index> childrenAgainstDirection = new HashMap<>();

        if (!followedDirection.isEmpty()) {
            childrenAgainstDirection =  pullChildren(followedDirection, maze, parentNode, visitedNodes);
        } else {
            childrenAgainstDirection = addChildAccordingToDirection("", maze, parentNode, visitedNodes);
        }

        return childrenAgainstDirection;
    }

    private Map<String, Index> pullChildren(String followedDirection, int[][] maze, Index parentNode, Set<Index> visitedNodes) {
        Map<String, Index> childrenAgainstDirection = new HashMap<>();
        if (followedDirection.equals(Direction.DOWN.direction)) {
            Index childNode = new Index(parentNode.indexX + 1, parentNode.indexY, followedDirection);
            if (childNode.indexX < maze.length && maze[childNode.indexX][childNode.indexY] == 0 && !visitedNodes.contains(childNode)) {
                childrenAgainstDirection.put(Direction.DOWN.direction, childNode);
            } else if (childNode.indexX < maze.length && maze[childNode.indexX][childNode.indexY] == 1) {
                childrenAgainstDirection = addChildAccordingToDirection(Direction.DOWN.direction, maze, parentNode, visitedNodes);
            } else if (parentNode.indexX == maze.length - 1) {
                childrenAgainstDirection = addChildAccordingToDirection(Direction.DOWN.direction, maze, parentNode, visitedNodes);
            }
        } else if (followedDirection.equals(Direction.UP.direction)) {
            Index childNode = new Index(parentNode.indexX - 1, parentNode.indexY, followedDirection);
            if (childNode.indexX >= 0 && maze[childNode.indexX][childNode.indexY] == 0 && !visitedNodes.contains(childNode)) {
                childrenAgainstDirection.put(Direction.UP.direction, childNode);
            } else if (childNode.indexX >= 0 && maze[childNode.indexX][childNode.indexY] == 1) {
                childrenAgainstDirection = addChildAccordingToDirection(Direction.UP.direction, maze, parentNode, visitedNodes);
            } else if (parentNode.indexX == 0) {
                childrenAgainstDirection = addChildAccordingToDirection(Direction.UP.direction, maze, parentNode, visitedNodes);
            }
        } else if (followedDirection.equals(Direction.LEFT.direction)) {
            Index childNode = new Index(parentNode.indexX, parentNode.indexY - 1, followedDirection);
            if (childNode.indexY >= 0 && maze[childNode.indexX][childNode.indexY] == 0 && !visitedNodes.contains(childNode)) {
                childrenAgainstDirection.put(Direction.LEFT.direction, childNode);
            } else if (childNode.indexY >= 0 && maze[childNode.indexX][childNode.indexY] == 1) {
                childrenAgainstDirection = addChildAccordingToDirection(Direction.LEFT.direction, maze, parentNode, visitedNodes);
            } else if (parentNode.indexY == 0) {
                childrenAgainstDirection = addChildAccordingToDirection(Direction.LEFT.direction, maze, parentNode, visitedNodes);
            }
        } else {
            Index childNode = new Index(parentNode.indexX, parentNode.indexY + 1, followedDirection);
            if (childNode.indexY < maze[0].length && maze[childNode.indexX][childNode.indexY] == 0 && !visitedNodes.contains(childNode)) {
                childrenAgainstDirection.put(Direction.RIGHT.direction, childNode);
            } else if (childNode.indexY < maze[0].length && maze[childNode.indexX][childNode.indexY] == 1) {
                childrenAgainstDirection = addChildAccordingToDirection(Direction.RIGHT.direction, maze, parentNode, visitedNodes);
            } else if (parentNode.indexY == maze[0].length - 1) {
                childrenAgainstDirection = addChildAccordingToDirection(Direction.RIGHT.direction, maze, parentNode, visitedNodes);
            }
        }

        return childrenAgainstDirection;
    }

    private Map<String, Index> addChildAccordingToDirection(String direction, int[][] maze, Index parentNode, Set<Index> visitedNodes) {
        Map<String, Index> childrenAgainstDirection = new HashMap<>();
        if (direction.equals(Direction.DOWN.direction) || direction.equals(Direction.UP.direction)) {
            Index childNodeLeft = new Index(parentNode.indexX, parentNode.indexY - 1, Direction.LEFT.direction);
            if (childNodeLeft.indexY >= 0 && maze[childNodeLeft.indexX][childNodeLeft.indexY] == 0 && !visitedNodes.contains(childNodeLeft)) {
                childrenAgainstDirection.put(Direction.LEFT.direction, childNodeLeft);
            }

            Index childNodeRight = new Index(parentNode.indexX, parentNode.indexY + 1, Direction.RIGHT.direction);
            if (childNodeRight.indexY < maze[0].length  && maze[childNodeRight.indexX][childNodeRight.indexY] == 0
                    && !visitedNodes.contains(childNodeRight)) {
                childrenAgainstDirection.put(Direction.RIGHT.direction, childNodeRight);
            }
        } else if (direction.equals(Direction.LEFT.direction) || direction.equals(Direction.RIGHT.direction)) {
            Index childNodeUp = new Index(parentNode.indexX - 1, parentNode.indexY, Direction.UP.direction);
            if (childNodeUp.indexX >= 0 && maze[childNodeUp.indexX][childNodeUp.indexY] == 0 && !visitedNodes.contains(childNodeUp)) {
                childrenAgainstDirection.put(Direction.UP.direction, childNodeUp);
            }

            Index childNodeDown = new Index(parentNode.indexX + 1, parentNode.indexY, Direction.DOWN.direction);
            if (childNodeDown.indexX < maze.length  && maze[childNodeDown.indexX][childNodeDown.indexY] == 0
                    && !visitedNodes.contains(childNodeDown)) {
                childrenAgainstDirection.put(Direction.DOWN.direction, childNodeDown);
            }
        } else if (direction.isEmpty()) {
            Index childNodeLeft = new Index(parentNode.indexX, parentNode.indexY - 1, Direction.LEFT.direction);
            if (childNodeLeft.indexY >= 0 && maze[childNodeLeft.indexX][childNodeLeft.indexY] == 0 && !visitedNodes.contains(childNodeLeft)) {
                childrenAgainstDirection.put(Direction.LEFT.direction, childNodeLeft);
            }

            Index childNodeRight = new Index(parentNode.indexX, parentNode.indexY + 1, Direction.RIGHT.direction);
            if (childNodeRight.indexY < maze[0].length  && maze[childNodeRight.indexX][childNodeRight.indexY] == 0
                    && !visitedNodes.contains(childNodeRight)) {
                childrenAgainstDirection.put(Direction.RIGHT.direction, childNodeRight);
            }

            Index childNodeUp = new Index(parentNode.indexX - 1, parentNode.indexY, Direction.UP.direction);
            if (childNodeUp.indexX >= 0 && maze[childNodeUp.indexX][childNodeUp.indexY] == 0 && !visitedNodes.contains(childNodeUp)) {
                childrenAgainstDirection.put(Direction.UP.direction, childNodeUp);
            }

            Index childNodeDown = new Index(parentNode.indexX + 1, parentNode.indexY, Direction.DOWN.direction);
            if (childNodeDown.indexX < maze.length  && maze[childNodeDown.indexX][childNodeDown.indexY] == 0
                    && !visitedNodes.contains(childNodeDown)) {
                childrenAgainstDirection.put(Direction.DOWN.direction, childNodeDown);
            }
        }
        return childrenAgainstDirection;
    }

    static class Index {
        private int indexX;
        private int indexY;
        private String direction;

        public Index(int indexX, int indexY, String direction) {
            this.indexX = indexX;
            this.indexY = indexY;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Index index = (Index) o;
            return indexX == index.indexX && indexY == index.indexY && direction.equals(index.direction);
        }

        @Override
        public int hashCode() {
            return Objects.hash(indexX, indexY, direction);
        }
    }

    enum Direction {

        LEFT("LEFT"),
        RIGHT("RIGHT"),
        UP("UP"),
        DOWN("DOWN");

        private String direction;

        Direction(String direction) {
            this.direction = direction;
        }
    }
}
