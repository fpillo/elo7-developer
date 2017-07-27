package com.elo7.search.controllers;

import com.elo7.search.domains.Movie;
import com.elo7.search.usecases.SaveMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    private final SaveMovie saveMovie;

    @Autowired
    public MovieController(final SaveMovie saveMovie) {
        this.saveMovie = saveMovie;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Movie save(@RequestBody final Movie movie) {
        return saveMovie.save(movie);
    }


}
