package org.burgers.web

class ErrorControllerTest extends GroovyTestCase {
  void test_kaboom(){
    assert "Test Kaboom!!!" == shouldFail(RuntimeException) {new ErrorController().kaboom()}
  }
}
