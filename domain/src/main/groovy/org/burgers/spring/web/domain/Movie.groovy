package org.burgers.spring.web.domain

import javax.persistence.*

@Entity
@Table(name = "tbtMovie")
class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MovieId")
    Long id

    @Column(name = "Title")
    String title

    @Column(name = "Rating")
    @Enumerated(EnumType.STRING)
    Rating rating

    @Column(name = "Image")
    byte[] image
}
