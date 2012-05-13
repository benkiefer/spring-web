package org.burgers.spring.web.domain

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column

@Entity
@Table(name = "tbtMovie")
class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MovieId")
    Long id

    @Column(name = "Title")
    String title

    @Column(name = "Rented")
    boolean rented

}
