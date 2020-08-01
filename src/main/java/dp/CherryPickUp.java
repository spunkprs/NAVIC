package dp;

import java.util.*;

public class CherryPickUp {

    public int cherryPickup(int[][] grid) {
        Map<Pair, Data> map = new HashMap<Pair, Data>();
        Pair destination = new Pair(grid.length - 1, grid.length - 1);
        if (grid[grid.length - 1][grid.length - 1] == 0) {
            map.put(destination, new Data(0, true));
        } else if (grid[grid.length - 1][grid.length - 1] == 1) {
            map.put(destination, new Data(1, true));
        }

        Pair source = new Pair(0, 0);
        Data countOne = collectCherriesWhenMovingTopToBottom(grid, map, source);
        if (countOne.isPathPossible) {
            restructureGrid(map, source, grid);
        }
        map = new HashMap<Pair, Data>();
        Pair destinationOne = new Pair(0, 0);
        if (grid[0][0] == 0) {
            map.put(destinationOne, new Data(0, true));
        } else if (grid[0][0] == 1) {
            map.put(destinationOne, new Data(1, true));
            //grid[0][0] = 0;
        }
        Data countTwo = collectCherriesWhenMovingBottomToTop(grid, map, new Pair(grid.length - 1, grid.length - 1));
        return countOne.value + countTwo.value;
    }

    private void restructureGrid(Map<Pair, Data> map, Pair source, int grid[][]) {
        int x = source.x;
        int y = source.y;
        if (grid[x][y] == 1) {
            grid[x][y] = 0;
        }
        while (x != grid.length - 1 || y != grid.length - 1) {
            Pair rightChild = null;
            Pair bottomChild = null;
            if (y + 1 <= grid.length - 1) {
                rightChild = new Pair(x, y + 1);
                if (!map.containsKey(rightChild)) {
                    rightChild = null;
                }
            }

            if (x + 1 <= grid.length - 1) {
                bottomChild = new Pair(x + 1, y);
                if (!map.containsKey(bottomChild)) {
                    bottomChild = null;
                }
            }

            if (rightChild != null && bottomChild != null) {
                Data valOne = map.get(rightChild);
                Data valTwo = map.get(bottomChild);

                if (valOne.isPathPossible && valTwo.isPathPossible && valTwo.value >= valOne.value) {
                    x = x + 1;
                    if (grid[x][y] == 1) {
                        grid[x][y] = 0;
                    }
                } else if (valOne.isPathPossible && valTwo.isPathPossible && valTwo.value < valOne.value) {
                    y = y + 1;
                    if (grid[x][y] == 1) {
                        grid[x][y] = 0;
                    }
                } else if (!valOne.isPathPossible && valTwo.isPathPossible) {
                    x = x + 1;
                    if (grid[x][y] == 1) {
                        grid[x][y] = 0;
                    }
                } else if (valOne.isPathPossible && !valTwo.isPathPossible) {
                    y = y + 1;
                    if (grid[x][y] == 1) {
                        grid[x][y] = 0;
                    }
                }
                continue;
            }

            if (bottomChild == null && rightChild != null && map.get(rightChild).isPathPossible) {
                y = y + 1;
                if (grid[x][y] == 1) {
                    grid[x][y] = 0;
                }
                continue;
            }

            if (bottomChild != null && rightChild == null && map.get(bottomChild).isPathPossible) {
                x = x + 1;
                if (grid[x][y] == 1) {
                    grid[x][y] = 0;
                }
                continue;
            }
        }

        if (grid[grid.length - 1][grid.length - 1] == 1) {
            grid[grid.length - 1][grid.length - 1] = 0;
        }
    }

    private Data collectCherriesWhenMovingBottomToTop(int[][] grid, Map<Pair, Data> map, Pair source) {
        List<Pair> children = null;
        if (!map.containsKey(source)) {
            int collectedCherries = 0;
            children = fetchChilrenWhenMovingBottomToTop(source, grid);
            if (children.size() > 0) {
                for (Pair child : children) {
                    Data data = collectCherriesWhenMovingBottomToTop(grid, map, child);
                    if (data.isPathPossible) {
                        int count = data.value;
                        if (count >= collectedCherries) {
                            collectedCherries = count;
                        }
                        if (grid[source.x][source.y] == 1) {
                            map.put(source, new Data(collectedCherries + 1, true));
                        } else {
                            map.put(source, new Data(collectedCherries, true));
                        }
                    }
                }
                if (!map.containsKey(source)) {
                    map.put(source, new Data(0, false));
                }
            } else {
                map.put(source, new Data(0, false));
            }
        }
        return map.get(source);
    }

    private List<Pair> fetchChilrenWhenMovingBottomToTop(Pair source, int[][] grid) {
        List<Pair> children = new ArrayList<Pair>();
        int x = source.x;
        int y = source.y;

        if (x - 1 <= grid.length - 1 && x - 1 >= 0 && grid[x - 1][y] != -1) {
            Pair p = new Pair(x - 1, y);
            children.add(p);
        }

        if (y - 1 <= grid.length - 1 && y - 1 >= 0 && grid[x][y - 1] != -1) {
            Pair p = new Pair(x, y - 1);
            children.add(p);
        }
        return children;
    }

    private Data collectCherriesWhenMovingTopToBottom(int[][] grid, Map<Pair, Data> map, Pair source) {
        List<Pair> children = null;
        if (!map.containsKey(source)) {
            int collectedCherries = 0;
            children = fetchChilrenWhenMovingTopToBottom(source, grid);
            if (children.size() > 0) {
                for (Pair child : children) {
                    Data data = collectCherriesWhenMovingTopToBottom(grid, map, child);
                    if (data.isPathPossible) {
                        int count = data.value;
                        if (count >= collectedCherries) {
                            collectedCherries = count;
                        }
                        if (grid[source.x][source.y] == 1) {
                            map.put(source, new Data(collectedCherries + 1, true));
                        } else {
                            map.put(source, new Data(collectedCherries, true));
                        }
                    }
                    }
                    if (!map.containsKey(source)) {
                    map.put(source, new Data(0, false));
                    }
            } else {
                map.put(source, new Data(0, false));
            }
        }
        return map.get(source);
    }

    private List<Pair> fetchChilrenWhenMovingTopToBottom(Pair source, int[][] grid) {
        List<Pair> children = new ArrayList<Pair>();
        int x = source.x;
        int y = source.y;

        if (x + 1 <= grid.length - 1 && grid[x + 1][y] != -1) {
            Pair p = new Pair(x + 1, y);
            children.add(p);
        }

        if (y + 1 <= grid.length - 1 && grid[x][y + 1] != -1) {
            Pair p = new Pair(x, y + 1);
            children.add(p);
        }
        return children;
    }

    class Pair {
        int x,y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    class Data {
        int value;
        boolean isPathPossible;
        Data(int value, boolean isPathPossible) {
            this.value = value;
            this.isPathPossible = isPathPossible;
        }
    }
}
