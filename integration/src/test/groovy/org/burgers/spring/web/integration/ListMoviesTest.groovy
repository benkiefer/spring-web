package org.burgers.spring.web.integration

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.burgers.spring.web.domain.Rating
import org.burgers.spring.web.domain.Repository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.burgers.spring.web.integration.util.IntegrationConstants.*
import static org.burgers.spring.web.integration.util.IntegrationUtils.cleanDatabase
import org.burgers.spring.web.domain.Movie
import com.gargoylesoftware.htmlunit.html.HtmlTable

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["classpath*:/contexts/DatabaseContext.xml"])
class ListMoviesTest {
    public static final BYTES = [1,2,3] as byte[]
    @Autowired Repository repository

    WebClient webClient

    @Before
    void setUp(){
        webClient = new WebClient()
        cleanDatabase(repository)
    }

    @Test
    void happyPath(){
        repository.save(new Movie(title: "Jaws", rating: Rating.G, image: BYTES))
        repository.save(new Movie(title: "Sesame Street", rating: Rating.R, image: BYTES))

        HtmlPage page = webClient.getPage(HOME_URL + "list.do")
        assert page.titleText == "Movie List"

        HtmlTable table = (HtmlTable) page.getElementById("movies")

        assertRow 2, "Jaws", "G", table
        assertRow 3, "Sesame Street", "R", table
    }

    @Test
    void happyPath_no_movies(){
        HtmlPage page = webClient.getPage(HOME_URL + "list.do")
        assert page.titleText == "Movie List"

        assert page.body.textContent.contains("There are currently no movies. Go add one.")
    }

    private void assertRow(int index, String title, String rating, HtmlTable table){
        table.getRow(index).cells[0].textContent == title
        table.getRow(index).cells[1].textContent == rating
    }

    @After
    void tearDown(){
        webClient.closeAllWindows()
    }

}
