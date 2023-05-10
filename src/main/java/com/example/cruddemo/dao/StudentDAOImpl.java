package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository                                     //---- @Repository component scan and translate jdbc exceptions
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager; /* define field for entity manager */

    /* inject entity manager using constructor injection */
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);

    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        /* create query */
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student", Student.class);

        return theQuery.getResultList(); /* return list */
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        /* create query */
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);


        theQuery.setParameter("theData", lastName); /* set param */

        return theQuery.getResultList(); /* return list */
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class, id); /* find the student */

        entityManager.remove(student); /* delete the student */
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowDeleted;
    }
}
