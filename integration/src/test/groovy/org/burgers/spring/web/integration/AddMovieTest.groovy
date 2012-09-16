package org.burgers.spring.web.integration

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlFileInput
import com.gargoylesoftware.htmlunit.html.HtmlInput
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.html.HtmlSelect
import org.burgers.spring.web.domain.Rating
import org.burgers.spring.web.domain.Repository
import org.burgers.spring.web.integration.util.FileUtil
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.burgers.spring.web.integration.util.IntegrationConstants.MOVIE_BASE_URL
import static org.burgers.spring.web.integration.util.IntegrationUtils.cleanDatabase

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["classpath*:/contexts/DatabaseContext.xml"])
class AddMovieTest {
    @Autowired Repository repository

    WebClient webClient

    @Before
    void setUp(){
        webClient = new WebClient()
        cleanDatabase(repository)
    }

    @Test
    void happyPath(){
        HtmlPage page = webClient.getPage(MOVIE_BASE_URL + "add.do")
        assert page.titleText == "Add a Movie"

        HtmlInput input = (HtmlInput) page.getElementById("title")
        input.setValueAttribute("Thor")

        HtmlSelect select = (HtmlSelect) page.getElementById("rating")
        select.setSelectedAttribute(select.getOptionByText("PG13"), true)

        HtmlFileInput fileInput = (HtmlFileInput) page.getElementById("image")
        fileInput.setValueAttribute(new FileUtil().findFileInClasspath("Thor.jpg").absolutePath)

        page = page.getElementById("submit").click()

        def results = repository.findAllMovies()

        assert results.size() == 1

        assert results[0].title == "Thor"
        assert results[0].rating == Rating.PG13

        assert page.titleText == "Add a Movie"

        assert page.body.textContent.contains("Movie added: Thor")
    }

    @Test
    void validationFailure(){
        HtmlPage page = webClient.getPage(MOVIE_BASE_URL + "add.do")
        assert page.titleText == "Add a Movie"

        HtmlInput input = (HtmlInput) page.getElementById("title")
        input.setValueAttribute("Jaws")

        page = page.getElementById("submit").click()

        assert repository.findAllMovies().size() == 0

        assert page.titleText == "Add a Movie"

        assert page.body.textContent.contains("Rating is required.")
    }

    @After
    void tearDown(){
        webClient.closeAllWindows()
    }

}
