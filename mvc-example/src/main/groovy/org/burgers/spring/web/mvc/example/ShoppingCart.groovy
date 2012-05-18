package org.burgers.spring.web.mvc.example

import org.burgers.spring.web.domain.Movie

class ShoppingCart implements Serializable {
    List<Movie> rentals = []

    void addItem(Movie movie) {
        rentals << movie
    }

    int getItemCount() {
   		rentals.size()
   	}

    void clear(){
        rentals.clear()
    }

}
