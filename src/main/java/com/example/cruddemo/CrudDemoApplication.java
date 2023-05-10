package com.example.cruddemo;

import com.example.cruddemo.dao.StudentDAO;
import com.example.cruddemo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class CrudDemoApplication {
    Logger logger = LoggerFactory.getLogger(CrudDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            // createStudent(studentDAO);
             createMultipleStudent(studentDAO);
            // readStudent(studentDAO);
            // queryForStudent(studentDAO);
            // queryForStudentByLastName(studentDAO);
            // updateStudent(studentDAO);
            // deleteStudent(studentDAO);
            // deleteAll(studentDAO);
        };
    }

    private void deleteAll(StudentDAO studentDAO) {
        int rowDeleted = studentDAO.deleteAll();
        logger.info("Number of row Deleted: " + rowDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int id = 5;

        logger.info("Deleting student with id: " + id);
        studentDAO.delete(id);
    }

    private void updateStudent(StudentDAO studentDAO) {
        int id = 3;

        logger.info("Getting student details with id: " + id);                  /* get details of Student by id*/
        Student student = studentDAO.findById(id);
        logger.info("Student details:  " + student.toString());
                                                                                /* set new details of Student*/
        student.setLast_name("doo");
        student.setEmail("doobee@yahoo.com");

        studentDAO.update(student);                                             /* update details of Student*/
    }

    private void queryForStudentByLastName(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findByLastName("Ter-Stegan");        /* get a list of Student by last name*/

        for (Student student : students) {                        /* display student */
            logger.info(student.toString());
        }
    }

    private void queryForStudent(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();        /* get a list of Student */

        for (Student student : students) {                        /* display student */
            logger.info(student.toString());
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        logger.info("Creating new student object....");
        Student tmpStudent1 = new Student("Mark-andre", "Ter-Stegan", "keeper@gmail.com");
        logger.info("Saving new student object....");
        studentDAO.save(tmpStudent1);

        Integer id = tmpStudent1.getId();  /* get id of the object saved */

        Student student = studentDAO.findById(id); /* find the object and return */
        logger.info(student.toString());
    }

    private void createMultipleStudent(StudentDAO studentDAO) {
        logger.info("Creating new student object....");
        Student tmpStudent1 = new Student("Rodrigo", "dePaul", "depaul@gmail.com");
        Student tmpStudent2 = new Student("Leo", "Messi", "lm10@gmail.com");
        Student tmpStudent3 = new Student("Julian", "Alvarez", "alvarez9@gmail.com");

        logger.info("Saving new student object....");
        studentDAO.save(tmpStudent1);
        studentDAO.save(tmpStudent2);
        studentDAO.save(tmpStudent3);

        logger.info("Saved 3 Student Object");                /* display the saved obj */
    }


    private void createStudent(StudentDAO studentDAO) {
        logger.info("Creating new student object....");
        Student tmpStudent = new Student("Rodrigo", "dePaul", "depaul@gmail.com");

        logger.info("Saving new student object....");
        studentDAO.save(tmpStudent);

        logger.info("Saved Student. \nGenerated id: " + tmpStudent.getId());        /* display the saved obj */
    }
}

