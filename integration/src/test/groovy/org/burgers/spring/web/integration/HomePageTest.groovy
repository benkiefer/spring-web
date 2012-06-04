package org.burgers.spring.web.integration

import org.junit.Test

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.burgers.spring.web.domain.Repository
import org.junit.Before

import static org.burgers.spring.web.integration.util.IntegrationUtils.*
import static org.burgers.spring.web.integration.util.IntegrationConstants.*
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.junit.After

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
