package multithreading.problems.moviebooking.fetcher;


import multithreading.problems.moviebooking.entities.*;
import multithreading.problems.moviebooking.exception.ExceptionMessage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
Ideally this shall be class instead but I want single instance of BookingDetailsFetcher that's why made it ENUM

Making use of ENUM here, violates Separation of Concerns

Enums are meant to represent:
a.) Fixed constants
b.) Finite sets
c.) Immutable configurations

Not:
a.) Data stores
b.) Runtime state containers
c.) Business logic repositories

When you store a mutable collection inside an enum, it starts acting like:
a.) A service
b.) A repository
c.) A cache
d.) A global registry

That’s a different responsibility.

Source --> ChatGPT

Initial thought process was pretty good that we want singleton class for the controller hence came up with ENUM but data shall not be residing at the controller layer
rather shall be pulled from DAO's and we can't inject DAO dependency here, it being a ENUM hence will have to make it a class instead

 * */

public class BookingDetailsFetcher {

    //INSTANCE();

    private Map<String, Movie> movieMap = new ConcurrentHashMap<>();
    private Map<String, List<MovieScreenMapping>> movieScreens = new ConcurrentHashMap<>();
    private Map<String, List<MovieScreenMapping>> theatreToMovieScreens = new ConcurrentHashMap<>();

    public List<MovieScreenMapping> fetchDetailsByMovieTitle(String movieTitle) {
        //Logic to be written here
        if (movieTitle == null || movieTitle.isEmpty()) {
            throw new RuntimeException(ExceptionMessage.MOVIE_TITLE_NULL.getMessage());
        } else if (!movieMap.containsKey(movieTitle)) {
            throw new RuntimeException(ExceptionMessage.INVALID_MOVIE_TITLE.getMessage());
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

    public List<MovieScreenMapping> fetchDetailsByTheatreId(String theatreId) {
        if (theatreId == null || theatreId.isEmpty()) {
            throw new RuntimeException(ExceptionMessage.THEATRE_ID_NULL.getMessage());
        } else if (!theatreToMovieScreens.containsKey(theatreId)) {
            throw new RuntimeException(ExceptionMessage.INVALID_THEATRE_ID.getMessage());
        } else {
            List<MovieScreenMapping> movieScreenMappings = theatreToMovieScreens.get(theatreId);
            long currentTimeInMillis = System.currentTimeMillis();
            List<MovieScreenMapping> result = movieScreenMappings.stream().
                    filter(movieScreenMapping -> movieScreenMapping.getTimeSlot().getShowStartTime() > currentTimeInMillis).
                    collect(Collectors.toList());
            return result;
        }
    }

    public Ticket bookTicket(String movieTitle, String theatreId, TimeSlot timeSlot, List<Seat> askedSeats, String screenId) {
        if (theatreId == null || theatreId.isEmpty()) {
            throw new RuntimeException(ExceptionMessage.THEATRE_ID_NULL.getMessage());
        } else if (!theatreToMovieScreens.containsKey(theatreId)) {
            throw new RuntimeException(ExceptionMessage.INVALID_THEATRE_ID.getMessage());
        } else {
            List<MovieScreenMapping> movieScreenMappings = theatreToMovieScreens.get(theatreId);
            Predicate<MovieScreenMapping> predicateOne = movieScreenMapping -> movieScreenMapping.getMovie().getMovieTitle().equals(movieTitle);
            Predicate<MovieScreenMapping> predicateTwo = movieScreenMapping -> movieScreenMapping.getTimeSlot().equals(timeSlot);


            Predicate<MovieScreenMapping> finalPredicate = predicateOne.and(predicateTwo);
            if (screenId != null) {
                Predicate<MovieScreenMapping> screenPredicate = movieScreenMapping -> movieScreenMapping.getScreen().getId().equals(screenId);
                finalPredicate = finalPredicate.and(screenPredicate);
            }

            List<MovieScreenMapping> intermittentResult = movieScreenMappings.stream()
                    .filter(finalPredicate)
                    .collect(Collectors.toList());
        }
        return null;
    }

    public void setMovieMap(Map<String, Movie> movieMap) {
        this.movieMap = movieMap;
    }

    public void setMovieScreens(Map<String, List<MovieScreenMapping>> movieScreens) {
        this.movieScreens = movieScreens;
    }
}
