package org.burgers.spring.web.integration

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.burgers.spring.web.domain.Repository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.burgers.spring.web.integration.util.IntegrationConstants.HOME_URL
import static org.burgers.spring.web.integration.util.IntegrationUtils.cleanDatabase

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations=["classpath*:/contexts/DatabaseContext.xml"])
class HomePageTest {
    @Autowired Repository repository

    WebClient webClient

    @Before
    void setUp(){
        webClient = new WebClient()
        cleanDatabase(repository)
    }

    @Test
    void verifyHomePage(){
        HtmlPage page = webClient.getPage(HOME_URL)
        assert page.titleText == "Movie Options"
    }

    @Test
    void checkHomePageLinks(){
        HtmlPage page = webClient.getPage(HOME_URL)
        page = page.getElementById("addMovie").click()
        assert page.titleText == "Add a Movie"

        page = page.getElementById("Home").click()
        page = page.getElementById("listMovies").click()
        assert page.titleText == "Movie List"

        page = page.getElementById("Home").click()
        page = page.getElementById("rentMovie").click()
        assert page.titleText == "Pick a Movie"
    }

    @After
    void tearDown(){
        webClient.closeAllWindows()
    }

}
