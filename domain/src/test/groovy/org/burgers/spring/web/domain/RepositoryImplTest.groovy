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
        def one = new Movie(title: "bob1")
        def two = new Movie(title: "bob2")
        repository.save(one)
        repository.save(two)
        def results = repository.findAllMovieTitles()
        assert results.size() == 2
        assert results == [one.title, two.title]
    }

    @Test
    void save_delete() {
        def mine = new Movie(title: "bob")
        repository.save(mine)
        def results = repository.findAllMovies()
        assert results.size() == 1
        assert results[0].title == "bob"
        repository.delete(mine)
        assert repository.findAllMovies().isEmpty()
    }

    @Test
    void findAll_deleteAll() {
        def bob = new Movie(title: "bob")
        def larry = new Movie(title: "larry")
        repository.save(bob)
        repository.save(larry)
        def results = repository.findAllMovies()
        assert results.size() == 2
        assert results.find {it.title == "bob"}
        assert results.find {it.title == "larry"}
        repository.deleteAll()
        assert repository.findAllMovies().isEmpty()
    }

    @Test
    void findById() {
        def bob = new Movie(title: "bob")
        repository.save(bob)
        def id = repository.findAllMovies()[0].id
        assert repository.findById(id).title == "bob"
    }

    @After
    void tearDown() {
        repository.deleteAll()
        assert repository.findAllMovies().size() == 0
    }
}
