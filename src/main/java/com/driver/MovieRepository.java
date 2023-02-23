package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String, Movie> movie_db = new HashMap<>();
    Map<String, Director> director_db = new HashMap<>();
    Map<String, List<String>> directorMovie_db= new HashMap<>();

//    1. Add a movie: POST /movies/add-movie
//    Pass the Movie object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovie
    public void addMovie(Movie movie){
        movie_db.put(movie.getName(), movie);
    }





//    2. Add a director: POST /movies/add-director
//    Pass the Director object as request body
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addDirector
    public void addDirector(Director director){
        director_db.put(director.getName(), director);
    }








//    3. Pair an existing movie and director: PUT /movies/add-movie-director-pair
//    Pass movie name and director name as request parameters
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - addMovieDirectorPair
    public void addMovieDirectorPair(String movieName, String directorNname){
        if(movie_db.containsKey(movieName) && director_db.containsKey(directorNname)){
            List<String> movieList = new ArrayList<>();
            if(directorMovie_db.containsKey(directorNname)){
                movieList = directorMovie_db.get(directorNname);
            }
            movieList.add(movieName);
            directorMovie_db.put(directorNname, movieList);
        }
    }






//    4. Get Movie by movie name: GET /movies/get-movie-by-name/{name}
//    Pass movie name as path parameter
//    Return Movie object wrapped in a ResponseEntity object
//    Controller Name - getMovieByName

    public Movie getMovieByName(String movieName){
        return  movie_db.get(movieName);
    }




//    5. Get Director by director name: GET /movies/get-director-by-name/{name}
//    Pass director name as path parameter
//    Return Director object wrapped in a ResponseEntity object
//    Controller Name - getDirectorByName
//    Feting Director by its Name
    public Director getDirectorByName(String directorName){
        return director_db.get(directorName);
    }

//    6. Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
//    Pass director name as path parameter
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - getMoviesByDirectorName

    public  List<String> getMoviesByDirectorName(String directorName){
        List<String> movies = new ArrayList<>();
        if(directorMovie_db.containsKey(directorName)){
            movies  = directorMovie_db.get(directorName);
        }
        return movies;
    }


//    7. Get List of all movies added: GET /movies/get-all-movies
//    No params or body required
//    Return List of movies name(List()) wrapped in a ResponseEntity object
//    Controller Name - findAllMovies
    // Returning List of All Movies by API end point
    public List<String> findAllMovies(){
       return new ArrayList<>(movie_db.keySet());
    }






//    8. Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
//    Pass director’s name as request parameter
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteDirectorByName
    public void deleteDirectorByName(String directorName){
        List<String> movieList;
        if(directorMovie_db.containsKey(directorName)) {
            movieList = directorMovie_db.get(directorName);
            for (String movie : movieList) {
                if(movie_db.containsKey(movie)) movie_db.remove(movie);
            }
            if(director_db.containsKey(directorName)) directorMovie_db.remove(directorName);
            directorMovie_db.remove(directorName);
        }
    }


//    9. Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
//    No params or body required
//    Return success message wrapped in a ResponseEntity object
//    Controller Name - deleteAllDirectors
//            (Note that there can be some movies on your watchlist that aren’t mapped to any of the director. Make sure you do not remove them.)

    public void deleteAllDirectors(){
        HashSet<String> movieSet = new HashSet<>();
        for(String director : directorMovie_db.keySet()){
            movieSet.addAll(directorMovie_db.get(director));
        }
        for (String movie : movieSet){
            if(movie_db.containsKey(movie)){
                movie_db.remove(movie);
            }
        }

    }

}
