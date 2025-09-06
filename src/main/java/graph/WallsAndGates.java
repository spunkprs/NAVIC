package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WallsAndGates {

    public static void main(String ar[]) {
        WallsAndGates unit = new WallsAndGates();
        int rooms[][] = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        unit.wallsAndGates(rooms);
        System.out.println("Updated values in the rooms are " + rooms);
    }

    public void wallsAndGates(int[][] rooms) {
        List<Position> positionOfGates = new ArrayList<>();

        populatePositionOfGates(rooms, positionOfGates);

        for (Position position : positionOfGates) {
            updateDistance(position, rooms);
        }
    }

    private void updateDistance(Position position, int[][] rooms) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(position);
        while (!queue.isEmpty()) {
            for (Position child : fetchChildren(queue.peek(), rooms)) {
                if (child.val < rooms[child.x][child.y]) {
                    queue.add(child);
                    rooms[child.x][child.y] = child.val;
                }
            }
            queue.poll();
        }
    }

    private List<Position> fetchChildren(Position position, int[][] rooms) {
        List<Position> children = new ArrayList<>();

        if (position.x + 1 < rooms.length && rooms[position.x + 1][position.y] != -1 && rooms[position.x + 1][position.y] != 0) {
            children.add(new Position(position.x + 1, position.y, position.val + 1));
        }

        if (position.x - 1 >= 0 && rooms[position.x - 1][position.y] != -1 && rooms[position.x - 1][position.y] != 0) {
            children.add(new Position(position.x - 1, position.y, position.val + 1));
        }

        if (position.y + 1 < rooms[0].length && rooms[position.x][position.y + 1] != -1 && rooms[position.x][position.y + 1] != 0) {
            children.add(new Position(position.x, position.y + 1, position.val + 1));
        }

        if (position.y - 1 >= 0 && rooms[position.x][position.y - 1] != -1 && rooms[position.x][position.y - 1] != 0) {
            children.add(new Position(position.x, position.y - 1, position.val + 1));
        }
        return children;
    }

    private void populatePositionOfGates(int[][] rooms, List<Position> positionOfGates) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == 0) {
                    positionOfGates.add(new Position(i, j, 0));
                }
            }
        }
    }


    static class Position {
        private int x;
        private int y;
        private int val;

        public Position(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }


}
