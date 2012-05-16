package org.burgers.spring.web.mvc.example

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.beans.factory.annotation.Autowired
import org.burgers.spring.web.domain.Repository
import org.burgers.spring.web.domain.Movie
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.validation.BindingResult
import javax.validation.Valid
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class MovieController {
    @Autowired Repository repository
    @Autowired MovieValidator validator
    @Autowired MovieFactory factory

    MovieController(){

    }

    @RequestMapping("/list.do")
    ModelAndView listMovies() {
        new ModelAndView("movieList", [movies: repository.findAllMovies()])
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    ModelAndView onSubmit(@ModelAttribute("command") MovieCommand movie, BindingResult result) {
        validator.validate(movie, result)
        if (result.hasErrors()){
            return new ModelAndView("addMovie", "command", movie)
        } else{
            repository.save(factory.createFrom(movie))
            return new ModelAndView("success", [title: movie.title])
        }
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    ModelAndView add(){
        new ModelAndView("addMovie", "command", new MovieCommand())
    }

    @RequestMapping("/options.do")
    void options(){}
}
