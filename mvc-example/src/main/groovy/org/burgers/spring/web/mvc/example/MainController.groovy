package org.burgers.spring.web.mvc.example

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MainController {
    @RequestMapping("/options.do")
    void options() {}
}
