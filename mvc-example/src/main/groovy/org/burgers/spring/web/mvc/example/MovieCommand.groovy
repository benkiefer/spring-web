package org.burgers.spring.web.mvc.example

import org.hibernate.validator.constraints.NotEmpty

class MovieCommand implements Serializable {
    String title
    boolean rented
}
