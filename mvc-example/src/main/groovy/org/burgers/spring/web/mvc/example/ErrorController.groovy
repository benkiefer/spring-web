package org.burgers.spring.web.mvc.example

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class ErrorController {
  @RequestMapping("/technicalDifficulties.do")
  void technicalDifficulties(){}

  @RequestMapping("/kaboom.do")
  void kaboom(){
    throw new RuntimeException("Test Kaboom!!!")
  }

}
