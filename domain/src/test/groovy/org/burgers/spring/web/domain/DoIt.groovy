package org.burgers.spring.web.domain

import org.junit.Test
import org.springframework.context.support.ClassPathXmlApplicationContext

class DoIt {
    @Test
    public void x(){
        def context = new ClassPathXmlApplicationContext("classpath:contexts/DatabaseContext.xml")
        context.getBeanDefinitionNames().each{
            println it
        }
    }

}
