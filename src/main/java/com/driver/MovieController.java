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
    ResponseEntity addMovie(@RequestBody() Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director") // Adding Director
    ResponseEntity addDirector(@RequestBody() Director director){
        movieService.addDirector(director);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }


    @PutMapping("/movies/add-movie-director-pair")
    ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
        movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity("succeess", HttpStatus.CREATED);
    }
    @GetMapping("/movies/get-movie-by-name/{name}") // Fetching Movie by Name
    ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        return new ResponseEntity(movieService.getMovieByName(movieName), HttpStatus.OK);
    }

    @GetMapping("/movies/get-director-by-name/{name}") // Fetching Director by Name
    ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
        return new ResponseEntity(movieService.getDirectorByName(directorName), HttpStatus.OK);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}") // Displaying List of Movies by Director name
    ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        return new ResponseEntity(movieService.getMoviesByDirectorName(directorName), HttpStatus.OK);
    }
    @GetMapping("/movies/get-all-movies") // Displaying All Movies
    ResponseEntity<List<String>> findAllMovies(){
        List<String> movieList =  movieService.findAllMovies();
        return new ResponseEntity(movieList, HttpStatus.OK);
    }
    @DeleteMapping("/movies/delete-director-by-name") // Deleting director by Name
    ResponseEntity deleteDirectorByName(@RequestParam("name") String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity("Success", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    ResponseEntity deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
