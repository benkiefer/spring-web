package org.burgers.spring.web.mvc.example

import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class SimpleController {
    @RequestMapping("/howAreYou.do")
    ModelAndView howAreYou(){
        new ModelAndView("hi")
    }

    @RequestMapping("/hi.do")
    void hi(){}

    @RequestMapping("/hello.do")
    String hello(){"hi"}

}