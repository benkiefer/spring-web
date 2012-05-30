package org.burgers.spring.web.mvc.example

import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.mvc.example.movie.rental.MovieRental

class ShoppingCart implements Serializable {
    Set<Long> rentals = [] as Set

    void addItem(Long movieId) {
        rentals << movieId
    }

    void removeItem(Long id){
        rentals.removeAll {it == id}
    }

    int getItemCount() {
   		rentals.size()
   	}

    void clear(){
        rentals.clear()
    }

}
