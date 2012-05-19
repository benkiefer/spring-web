package org.burgers.spring.web.mvc.example

import org.burgers.spring.web.domain.Movie
import org.burgers.spring.web.mvc.example.movie.rental.MovieRental

class ShoppingCart implements Serializable {
    List<MovieRental> rentals = []

    void addItem(MovieRental rental) {
        if (!rentals.find {it.id == rental.id}) rentals << rental
    }

    int getItemCount() {
   		rentals.size()
   	}

    void clear(){
        rentals.clear()
    }

}
