package org.burgers.spring.web.domain

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate3.HibernateTemplate
import org.springframework.stereotype.Repository

@Repository
class RepositoryImpl implements org.burgers.spring.web.domain.Repository {
    SessionFactory sessionFactory
    HibernateTemplate hibernateTemplate

    @Autowired
    void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory
        this.hibernateTemplate = new HibernateTemplate(sessionFactory)
    }

    void save(Movie movie) {
        hibernateTemplate.saveOrUpdate(movie)
        hibernateTemplate.flush()
    }

    void delete(Movie movie) {
        hibernateTemplate.delete(movie)
    }

    Movie findById(Long id) {
        hibernateTemplate.find("from Movie where id = ?", id)[0]
    }

    Movie findByTitle(String title) {
        hibernateTemplate.find("from Movie where title = ?", title)[0]
    }

    List<Movie> findAllMovies() {
        hibernateTemplate.find("from Movie")
    }

    void deleteAll() {
        hibernateTemplate.bulkUpdate("delete from Movie")
    }

    List<String> findAllMovieTitles(){
        hibernateTemplate.find("select title from Movie")
    }

}
