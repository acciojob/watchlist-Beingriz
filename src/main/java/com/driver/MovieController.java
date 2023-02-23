package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie") //Adding Movie Object
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<String>("success", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director") // Adding Director
    public ResponseEntity<String> addDirector(@RequestBody() Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }


    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
        movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>("succeess", HttpStatus.CREATED);
    }
    @GetMapping("/movies/get-movie-by-name/{name}") // Fetching Movie by Name
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        return new ResponseEntity<>(movieService.getMovieByName(movieName), HttpStatus.OK);
    }

    @GetMapping("/movies/get-director-by-name/{name}") // Fetching Director by Name
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        return new ResponseEntity<>(movieService.getDirectorByName(directorName), HttpStatus.OK);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}") // Displaying List of Movies by Director name
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName){
        return new ResponseEntity<>(movieService.getMoviesByDirectorName(directorName), HttpStatus.OK);
    }
    @GetMapping("/movies/get-all-movies") // Displaying All Movies
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movieList =  movieService.findAllMovies();
        return new ResponseEntity<List<String>>(movieList, HttpStatus.OK);
    }
    @DeleteMapping("/movies/delete-director-by-name") // Deleting director by Name
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<String>("Success", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
