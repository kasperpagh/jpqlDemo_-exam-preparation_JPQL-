/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Student;
import entity.Studypoint;
import facade.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kaspe
 */
public class Tester
{

    public static void main(String[] args)
    {
        //Find all students
        Controller c = new Controller();
        c.findAllStudents();

        //Find all Jan
        c.findByFirstName("jan");

        //Find all olsen
        c.findByLastName("olsen");

        //Find total studypoint
        c.findTotalStudyPointByStudentId(1);

        //Find total studypoint for alle
        c.getTotalStudyPoints();

        //Højeste studypoint
        c.findHighestStudyPoint();

        //Laveste score
        c.findLowestStudyPoint();

        //Create student
        Student s = new Student("Testtovitz", "Von Testistan", null);
        c.createNewStudent(s);

        //Add StudyPoint tester (jeg bruger ovenstående test burger (s) til at afprøve addStudyPoint())
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
//        EntityManager em = emf.createEntityManager();
        Studypoint sp = new Studypoint("godt arbejde", 11, 5, (Student) c.findByFirstName("Testtovitz").get(0));

        System.out.println("Her er testos studypoint collection førend addStudyPoint()" + s.getStudypointCollection().toString());
        s.addStudyPoint(sp);
        System.out.println("Her er testos studypoint collection efter addStudyPoint()" + c.findTotalStudyPointByStudentId(3));
//        em.getTransaction().begin();
//        Student asdf =(Student) c.findByFirstName("Testtovitz").get(0);
//        asdf.addStudyPoint(sp);
//        em.persist(asdf);
////        em.persist(sp);
//        em.getTransaction().commit();
//        em.close();

    }

}
