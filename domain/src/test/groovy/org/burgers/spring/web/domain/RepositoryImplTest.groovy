package org.burgers.spring.web.domain

import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations = ["classpath:contexts/DatabaseContext.xml"])
class RepositoryImplTest {
    public static final byte[] BYTES = [1, 2, 3] as byte[]

    @Autowired
    Repository repository

    void setUp() {
        repository.deleteAll()
    }

    @Test
    void findAllMovieTitles(){
        def one = new Movie(title: "bob1", rating: Rating.G, image: BYTES)
        def two = new Movie(title: "bob2", rating: Rating.G, image: BYTES)
        repository.save(one)
        repository.save(two)
        def results = repository.findAllMovieTitles()
        assert results.size() == 2
        assert results == [one.title, two.title]
    }

    @Test
    void save_delete() {
        def mine = new Movie(title: "bob", rating: Rating.G, image: BYTES)
        repository.save(mine)
        def results = repository.findAllMovies()
        assert results.size() == 1
        assert results[0].title == "bob"
        repository.delete(mine)
        assert repository.findAllMovies().isEmpty()
    }

    @Test
    void findAll_deleteAll() {
        def bob = new Movie(title: "bob", rating: Rating.G, image: BYTES)
        def larry = new Movie(title: "larry", rating: Rating.G, image: BYTES)
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
        def sam = new Movie(title: "sam", rating: Rating.G, image: BYTES)
        def bob = new Movie(title: "bob", rating: Rating.G, image: BYTES)
        repository.save(sam)
        repository.save(bob)

        def allMovies = repository.findAllMovies().sort{it.id}
        assert repository.findById(allMovies[0].id).title == sam.title
        assert repository.findById(allMovies[1].id).title == bob.title
    }

    @Test
    void findByName() {
        def sam = new Movie(title: "sam", rating: Rating.G, image: BYTES)
        repository.save(sam)

        def allMovies = repository.findAllMovies().sort{it.id}
        assert repository.findByTitle("sam").rating == sam.rating
    }

    @After
    void tearDown() {
        repository.deleteAll()
        assert repository.findAllMovies().size() == 0
    }
}
