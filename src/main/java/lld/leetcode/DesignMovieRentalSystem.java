package lld.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
Problem : 1912
Level : Hard
Link : https://leetcode.com/problems/design-movie-rental-system/description/?envType=company&envId=okta&favoriteSlug=okta-all
Status : Solved

You have a movie renting company consisting of n shops. You want to implement a renting system that supports searching for,
booking, and returning movies. The system should also support generating a report of the currently rented movies.

Each movie is given as a 2D integer array entries where entries[i] = [shopi, moviei, pricei] indicates that there is a copy
of movie moviei at shop shopi with a rental price of pricei. Each shop carries at most one copy of a movie moviei.

The system should support the following functions:-

a.) Search: Finds the cheapest 5 shops that have an unrented copy of a given movie. The shops should be sorted by
price in ascending order, and in case of a tie, the one with the smaller shopi should appear first. If there are less than
5 matching shops, then all of them should be returned. If no shop has an unrented copy, then an empty list should be returned.

b.) Rent: Rents an unrented copy of a given movie from a given shop.

c.) Drop: Drops off a previously rented copy of a given movie at a given shop.

d.) Report: Returns the cheapest 5 rented movies (possibly of the same movie ID) as a 2D list res
where res[j] = [shopj, moviej] describes that the jth cheapest rented movie moviej was rented from the shop shopj.
The movies in res should be sorted by price in ascending order, and in case of a tie, the one with the smaller shopj
should appear first, and if there is still tie, the one with the smaller moviej should appear first. If there are fewer
than 5 rented movies, then all of them should be returned. If no movies are currently being rented, then an empty list
should be returned.

Implement the MovieRentingSystem class:-

a.) MovieRentingSystem(int n, int[][] entries) Initializes the MovieRentingSystem object with n shops and the movies in entries.
b.) List<Integer> search(int movie) Returns a list of shops that have an unrented copy of the given movie as described above.
c.) void rent(int shop, int movie) Rents the given movie from the given shop.
d.) void drop(int shop, int movie) Drops off a previously rented movie at the given shop.
e.) List<List<Integer>> report() Returns a list of cheapest rented movies as described above.

Note: The test cases will be generated such that rent will only be called if the shop has an unrented copy of the movie,
and drop will only be called if the shop had previously rented out the movie.
 * */

public class DesignMovieRentalSystem {

    private Map<ShopMovieCombination, Node> movieCombinationNodeMapForUnRented;
    private Map<ShopMovieCombination, Node> movieCombinationNodeMapForRented;
    private Map<Integer, TreeSet<PriceShopCombination>> mapUsedForSearchingMovies;
    private TreeSet<PriceShopMovieCombination> priceShopMovieCombinationTreeSet;

    public DesignMovieRentalSystem(int n, int[][] entries) {
        movieCombinationNodeMapForUnRented = new HashMap<>();
        movieCombinationNodeMapForRented = new HashMap<>();
        mapUsedForSearchingMovies = new HashMap<>();
        priceShopMovieCombinationTreeSet = new TreeSet<>(new ReportComparator());

        for (int i = 0; i < entries.length; i++) {
            ShopMovieCombination shopMovieCombination = new ShopMovieCombination(entries[i][0], entries[i][1]);
            Node node = new Node(entries[i][2], false);
            movieCombinationNodeMapForUnRented.put(shopMovieCombination, node);
            populateMapUsedForSearchingMovies(shopMovieCombination, node);
        }
    }

    static class SearchComparator implements Comparator<PriceShopCombination> {

        @Override
        public int compare(PriceShopCombination p1, PriceShopCombination p2) {
            return p1.price < p2.price ? -1 : p1.price > p2.price ? 1 : p1.shopId < p2.shopId ? -1 :
                    p1.shopId > p2.shopId ? 1 : 0;
        }
    }

    static class ReportComparator implements Comparator<PriceShopMovieCombination> {

        @Override
        public int compare(PriceShopMovieCombination p1, PriceShopMovieCombination p2) {
            return p1.priceId < p2.priceId ? -1 : p1.priceId > p2.priceId ? 1 : p1.shopId < p2.shopId ? -1 :
                    p1.shopId > p2.shopId ? 1 : p1.movieId < p2.movieId ? -1 : p1.movieId > p2.movieId ? 1 : 0;
        }
    }

    private void populateMapUsedForSearchingMovies(ShopMovieCombination shopMovieCombination, Node node) {
        int movieId = shopMovieCombination.movieId;
        PriceShopCombination priceShopCombination = new PriceShopCombination(node.priceId, shopMovieCombination.shopId);
        if (!mapUsedForSearchingMovies.containsKey(movieId)) {
            TreeSet<PriceShopCombination> treeSet = new TreeSet<>(new SearchComparator());
            treeSet.add(priceShopCombination);
            mapUsedForSearchingMovies.put(movieId, treeSet);
        } else {
            TreeSet<PriceShopCombination> treeSet = mapUsedForSearchingMovies.get(movieId);
            treeSet.add(priceShopCombination);
        }
    }

    private void removeEntryFromMap(ShopMovieCombination shopMovieCombination, int price) {
        int movieId = shopMovieCombination.movieId;
        if (mapUsedForSearchingMovies.containsKey(movieId)) {
            TreeSet<PriceShopCombination> treeSet = mapUsedForSearchingMovies.get(movieId);
            PriceShopCombination nodeToBeRemoved = new PriceShopCombination(price, shopMovieCombination.shopId);
            if (treeSet.contains(nodeToBeRemoved)) {
                treeSet.remove(nodeToBeRemoved);
                if (treeSet.isEmpty()) {
                    mapUsedForSearchingMovies.remove(movieId);
                }
            }
        }
    }

    private void addEntryToMap(ShopMovieCombination shopMovieCombination, Node node) {
        populateMapUsedForSearchingMovies(shopMovieCombination, node);
    }

    public List<Integer> search(int movie) {
        TreeSet<PriceShopCombination> treeSet = mapUsedForSearchingMovies.get(movie);
        if (treeSet == null || treeSet.isEmpty()) {
            return new ArrayList<>();
        }
        return treeSet.stream().map(record -> record.shopId).limit(5).collect(Collectors.toList());
    }

    static class PriceShopCombination {
        private int price;
        private int shopId;

        public PriceShopCombination(int price, int shopId) {
            this.price = price;
            this.shopId = shopId;
        }
    }

    public void rent(int shop, int movie) {
        ShopMovieCombination shopMovieCombination = new ShopMovieCombination(shop, movie);
        if (movieCombinationNodeMapForUnRented.containsKey(shopMovieCombination)) {
            Node associatedNode = movieCombinationNodeMapForUnRented.get(shopMovieCombination);
            associatedNode.isTaken = true;
            movieCombinationNodeMapForUnRented.remove(shopMovieCombination);
            movieCombinationNodeMapForRented.put(shopMovieCombination, associatedNode);
            removeEntryFromMap(shopMovieCombination, associatedNode.priceId);
            addEntryToTreeSet(shopMovieCombination, associatedNode.priceId);
        }
    }

    private void addEntryToTreeSet(ShopMovieCombination shopMovieCombination, int priceId) {
        PriceShopMovieCombination priceShopCombination = new PriceShopMovieCombination(shopMovieCombination.shopId,
                shopMovieCombination.movieId, priceId);
        if (!priceShopMovieCombinationTreeSet.contains(priceShopCombination)) {
            priceShopMovieCombinationTreeSet.add(priceShopCombination);
        }
    }

    private void removeEntryFromTreeSet(ShopMovieCombination shopMovieCombination, int priceId) {
        PriceShopMovieCombination priceShopCombination = new PriceShopMovieCombination(shopMovieCombination.shopId,
                shopMovieCombination.movieId, priceId);
        if (priceShopMovieCombinationTreeSet.contains(priceShopCombination)) {
            priceShopMovieCombinationTreeSet.remove(priceShopCombination);
        }
    }

    public void drop(int shop, int movie) {
        ShopMovieCombination shopMovieCombination = new ShopMovieCombination(shop, movie);
        if (movieCombinationNodeMapForRented.containsKey(shopMovieCombination)) {
            Node associatedNode = movieCombinationNodeMapForRented.get(shopMovieCombination);
            associatedNode.isTaken = false;
            movieCombinationNodeMapForRented.remove(shopMovieCombination);
            movieCombinationNodeMapForUnRented.put(shopMovieCombination, associatedNode);
            addEntryToMap(shopMovieCombination, associatedNode);
            removeEntryFromTreeSet(shopMovieCombination, associatedNode.priceId);
        }
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();

        if (priceShopMovieCombinationTreeSet.isEmpty()) {
            return result;
        }

        List<PriceShopMovieCombination> intermittentResult = priceShopMovieCombinationTreeSet.stream()
                .limit(5).collect(Collectors.toList());

        for (int i = 0; i < intermittentResult.size(); i++) {
            List<Integer> intermittentList = new ArrayList<>();
            intermittentList.add(intermittentResult.get(i).shopId);
            intermittentList.add(intermittentResult.get(i).movieId);
            result.add(intermittentList);
        }
        return result;
    }

    static class ShopMovieCombination {
        private int shopId;
        private int movieId;

        public ShopMovieCombination(int shopId, int movieId) {
            this.shopId = shopId;
            this.movieId = movieId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShopMovieCombination that = (ShopMovieCombination) o;
            return shopId == that.shopId && movieId == that.movieId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(shopId, movieId);
        }
    }


    static class PriceShopMovieCombination {
        private int shopId;
        private int movieId;
        private int priceId;

        public PriceShopMovieCombination(int shopId, int movieId, int priceId) {
            this.shopId = shopId;
            this.movieId = movieId;
            this.priceId = priceId;
        }
    }

    static class Node {
        private int priceId;
        private boolean isTaken;

        public Node(int priceId, boolean isTaken) {
            this.priceId = priceId;
            this.isTaken = isTaken;
        }
    }

}
