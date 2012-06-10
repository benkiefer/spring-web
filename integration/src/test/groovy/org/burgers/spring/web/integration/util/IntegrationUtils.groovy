package org.burgers.spring.web.integration.util

import org.burgers.spring.web.domain.Repository

class IntegrationUtils {
    static void cleanDatabase(Repository repository){
        repository.deleteAll()
        assert repository.findAllMovies().size() == 0
    }
}
