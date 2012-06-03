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

}
