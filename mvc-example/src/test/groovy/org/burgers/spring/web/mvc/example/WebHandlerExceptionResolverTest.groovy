package org.burgers.spring.web.mvc.example

import groovy.mock.interceptor.MockFor
import org.junit.Before
import org.junit.Test

class WebHandlerExceptionResolverTest {
    WebHandlerExceptionResolver resolver
    private logger

    @Before
    void setUp(){
        resolver = new WebHandlerExceptionResolver()
        logger = new MockFor(org.apache.log4j.Logger.class)
    }

    private void finalizeSetUp(){
         resolver.logger = logger.proxyInstance()
    }

    @Test
    void resolveException() {
        def error = new RuntimeException("Test Kaboom!!!")
        logger.demand.error(){arg1, arg2 ->
            assert arg1 == error.message
            assert arg2 == error
        }
        finalizeSetUp()
        def result = resolver.resolveException(null, null, null, error)
        assert result.viewName == "technicalDifficulties"
    }

}
