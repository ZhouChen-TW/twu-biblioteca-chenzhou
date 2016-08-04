package com.twu.biblioteca.model;

public class Movie {
    private String name;
    private String year;
    private String director;
    private String movieRating;

    public Movie(String name, String year, String director, String movieRating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.movieRating = movieRating;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getMovieRating() {
        return movieRating;
    }
}
