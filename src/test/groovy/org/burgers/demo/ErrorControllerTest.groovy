package org.burgers.demo

import org.junit.Test

class ErrorControllerTest extends GroovyTestCase {
  void test_kaboom(){
    assert "Test Kaboom!!!" == shouldFail(RuntimeException) {new ErrorController().kaboom()}
  }
}
