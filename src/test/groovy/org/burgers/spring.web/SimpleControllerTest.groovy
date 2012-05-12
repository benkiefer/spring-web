package org.burgers.web

import org.junit.Test
import org.springframework.web.servlet.ModelAndView

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
