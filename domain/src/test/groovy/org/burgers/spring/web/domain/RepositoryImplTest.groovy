package org.burgers.spring.web.domain

import org.springframework.test.context.ContextConfiguration
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test
import org.junit.After

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations = ["classpath:contexts/DatabaseContext.xml"])
class RepositoryImplTest {

    @Autowired
    Repository repository

    void setUp() {
        repository.deleteAll()
    }

    @Test
    void findAllMovieTitles(){
        def one = new Movie(title: "bob1", rating: Rating.G)
        def two = new Movie(title: "bob2", rating: Rating.G)
        repository.save(one)
        repository.save(two)
        def results = repository.findAllMovieTitles()
        assert results.size() == 2
        assert results == [one.title, two.title]
    }

    @Test
    void save_delete() {
        def mine = new Movie(title: "bob", rating: Rating.G)
        repository.save(mine)
        def results = repository.findAllMovies()
        assert results.size() == 1
        assert results[0].title == "bob"
        repository.delete(mine)
        assert repository.findAllMovies().isEmpty()
    }

    @Test
    void findAll_deleteAll() {
        def bob = new Movie(title: "bob", rating: Rating.G)
        def larry = new Movie(title: "larry", rating: Rating.G)
        repository.save(bob)
        repository.save(larry)
        def results = repository.findAllMovies()
        assert results.size() == 2
        assert results.find {it.title == "bob" && it.rating == Rating.G}
        assert results.find {it.title == "larry" && it.rating == Rating.G}
        repository.deleteAll()
        assert repository.findAllMovies().isEmpty()
    }

    @Test
    void findById() {
        def bob = new Movie(title: "bob", rating: Rating.G)
        repository.save(bob)
        def id = repository.findAllMovies()[0].id
        def result = repository.findById(id)
        assert result.title == "bob"
        assert result.rating == Rating.G
    }

    @After
    void tearDown() {
        repository.deleteAll()
        assert repository.findAllMovies().size() == 0
    }
}
