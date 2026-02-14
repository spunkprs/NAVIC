package multithreading.problems.moviebooking.entities;

import java.util.List;

public class Movie {
    private String movieId;
    private String movieTitle;
    private double movieDuration;
    private List<String> actorNames;
    private String movieLanguage;

    public String getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public double getMovieDuration() {
        return movieDuration;
    }

    public List<String> getActorNames() {
        return actorNames;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }
}
