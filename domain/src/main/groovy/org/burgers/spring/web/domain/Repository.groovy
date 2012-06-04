package org.burgers.spring.web.domain

public interface Repository {
    void save(Movie movie)
    void delete(Movie movie)
    Movie findById(Long id)
    Movie findByTitle(String title)
    List<Movie> findAllMovies()
    void deleteAll()
    List<String> findAllMovieTitles()
}