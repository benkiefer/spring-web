package org.burgers.spring.web.integration.util

class FileUtil {
    File findFileInClasspath(String fileName){
        new File(ClassLoader.getSystemResource(fileName).getFile())
    }
}
