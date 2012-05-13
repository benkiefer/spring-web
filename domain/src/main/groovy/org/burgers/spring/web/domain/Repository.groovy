package org.burgers.spring.web.domain

public interface Repository {
    void save(Movie movie)
    void delete(Movie movie)
    Movie findById(Long id)
    List<Movie> findAllMovies()
    void deleteAll()
}