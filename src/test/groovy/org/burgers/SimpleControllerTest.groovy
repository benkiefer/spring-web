package org.burgers

import org.junit.Test
import org.springframework.web.servlet.ModelAndView

class SimpleControllerTest {
    @Test
    void hi(){
        def result = new SimpleController().howAreYou()
        assert result.class == ModelAndView
        assert result.getViewName() == "hi"
    }

    @Test
    void goodBye(){
        assert "hi" == new SimpleController().hello()
    }
}
