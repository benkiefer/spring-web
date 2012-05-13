package org.burgers.spring.web.mvc.example

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Autowired
import org.burgers.spring.web.domain.Repository
import org.burgers.spring.web.domain.Movie

@Controller
class MovieController {
    @Autowired Repository repository
    @RequestMapping("/list.do")

    ModelAndView listMovies(){
        repository.save(new Movie(title: "Jaws"))
        repository.save(new Movie(title: "Jaws 2"))
        new ModelAndView("movieList", [movies:repository.findAllMovies()])
    }
}
