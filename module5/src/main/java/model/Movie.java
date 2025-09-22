package model;

public class Movie {
    private int movieId;
    private String title;
    private String director;
    private Integer releaseYear;
    private String genre;
    private Double rating;

    public Movie() {}

    public Movie(String title, String director, Integer releaseYear, String genre, Double rating) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
    }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}

