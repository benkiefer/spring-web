package org.burgers.spring.web.integration

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.html.HtmlTable
import org.burgers.spring.web.domain.Movie
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
import static org.burgers.spring.web.integration.util.IntegrationUtils.*
import com.gargoylesoftware.htmlunit.html.HtmlInput
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(locations = ["classpath*:/contexts/DatabaseContext.xml"])
class RentMovieTest {
    final String RENT_MOVIE_TITLE = "Pick a Movie"
    final String VIEW_CART_TITLE = "Review Your Cart"

    @Autowired Repository repository

    WebClient webClient

    @Before
    void setUp() {
        webClient = new WebClient()
        webClient.setAjaxController(new NicelyResynchronizingAjaxController())
        cleanDatabase(repository)
    }

    @Test
    void happyPath() {
        repository.save(new Movie(title: "Jaws", rating: Rating.G))
        repository.save(new Movie(title: "Sesame Street", rating: Rating.R))

        HtmlPage page = webClient.getPage(RENTAL_START_URL)
        assert page.titleText == RENT_MOVIE_TITLE

        HtmlTable table = (HtmlTable) page.getElementById("movies")

        assertMovieInRow table, "Jaws", "G", false, 2
        assertMovieInRow table, "Sesame Street", "R", false, 3
        assertCartCount page, 0

        page = page.getElementById("add_${repository.findByTitle("Jaws").id}").click()
        assert page.titleText == RENT_MOVIE_TITLE
        assertCartCount(page, 1)

        page = page.getElementById("remove_${repository.findByTitle("Jaws").id}").click()
        assert page.titleText == RENT_MOVIE_TITLE
        assertCartCount(page, 0)

        page = page.getElementById("viewCart").click()
        assert page.titleText == VIEW_CART_TITLE
        assert page.body.textContent.contains("Your cart is empty.")
    }

    @Test
    void happyPath_cart_with_multple_items() {
        repository.save(new Movie(title: "Jaws", rating: Rating.G))
        repository.save(new Movie(title: "Sesame Street", rating: Rating.R))

        HtmlPage page = webClient.getPage(RENTAL_START_URL)
        assert page.titleText == RENT_MOVIE_TITLE

        HtmlTable table = (HtmlTable) page.getElementById("movies")

        assertMovieInRow table, "Jaws", "G", false, 2
        assertMovieInRow table, "Sesame Street", "R", false, 3
        assertCartCount page, 0

        page = page.getElementById("add_${repository.findByTitle("Jaws").id}").click()
        assert page.titleText == RENT_MOVIE_TITLE
        assertCartCount(page, 1)

        page = page.getElementById("viewCart").click()
        assert page.titleText == VIEW_CART_TITLE
        assert page.body.textContent.contains("You are renting 1 item(s).")

        table = (HtmlTable) page.getElementById("movies")
        assertRentalInRow table, "Jaws", "G", 2
    }

    private assertMovieInRow(HtmlTable table, String title, String rating, boolean checked, int rowNumber){
       def row = table.getRow(rowNumber)
       assert row.cells[0].textContent == title
       assert row.cells[1].textContent == rating
       HtmlInput input = (HtmlInput) row.cells[2].childElements.iterator().toList()[0]
       assert input.checked == checked
    }

    private assertRentalInRow(HtmlTable table, String title, String rating, int rowNumber){
       def row = table.getRow(rowNumber)
       assert row.cells[0].textContent == title
       assert row.cells[1].textContent == rating
    }

    private assertCartCount(HtmlPage page, int count) {
        assert page.getElementById("cartCount").textContent == count.toString()
    }

    @After
    void tearDown() {
        webClient.closeAllWindows()
    }

}
