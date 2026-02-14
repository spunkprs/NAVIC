package multithreading.problems.moviebooking.fetcher;


import multithreading.problems.moviebooking.entities.Movie;
import multithreading.problems.moviebooking.entities.MovieScreenMapping;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public enum BookingDetailsFetcher {

    INSTANCE();

    private Map<String, Movie> movieMap = new ConcurrentHashMap<>();
    private Map<String, List<MovieScreenMapping>> movieScreens = new ConcurrentHashMap<>();

    public List<MovieScreenMapping> fetchDetailsByMovieTitle(String movieTitle) {
        //Logic to be written here
        if (movieTitle == null || movieTitle.isEmpty()) {
            return null;
        } else if (!movieMap.containsKey(movieTitle)) {
            return null;
        } else {
            Movie movie = movieMap.get(movieTitle);
            List<MovieScreenMapping> movieScreenMappings = movieScreens.get(movie.getMovieId());
            long currentTimeInMillis = System.currentTimeMillis();
            List<MovieScreenMapping> result = movieScreenMappings.stream().
                    filter(movieScreenMapping -> movieScreenMapping.getTimeSlot().getShowStartTime() > currentTimeInMillis).
                    collect(Collectors.toList());
            return result;
        }
    }

    public void setMovieMap(Map<String, Movie> movieMap) {
        this.movieMap = movieMap;
    }

    public void setMovieScreens(Map<String, List<MovieScreenMapping>> movieScreens) {
        this.movieScreens = movieScreens;
    }
}
