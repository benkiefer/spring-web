package org.burgers.demo

import org.junit.Test
import org.springframework.web.servlet.ModelAndView
import org.burgers.demo.SimpleController

class SimpleControllerTest {
    @Test
    void hi(){
        ModelAndView result = new SimpleController().howAreYou()
        assert result.getViewName() == "hi"
    }

    @Test
    void goodBye(){
        assert "hi" == new SimpleController().hello()
    }
}
