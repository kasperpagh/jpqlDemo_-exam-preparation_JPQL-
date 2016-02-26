/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Student;
import entity.Studypoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author kaspe
 */
public class Controller
{

    public List findAllStudents()
    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {

            Query query = em.createNamedQuery("Student.findAll", Student.class);

            List<Student> llama = query.getResultList();

            System.out.println("Her er all students som List: " + llama);
            return llama;
        }
        finally
        {
            em.close();

        }
    }

    public List findByFirstName(String name)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            Student s = new Student();
            Query query = em.createNamedQuery("Student.findByFirstname", Student.class);

            List<Student> llama = query.setParameter("firstname", name).getResultList();

            System.out.println("Her er all students som hedder jan: " + llama);
            return llama;
        }
        finally
        {
            em.close();

        }
    }

    public List findByLastName(String name)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            Student s = new Student();
            Query query = em.createNamedQuery("Student.findByLastname", Student.class);

            List<Student> llama = query.setParameter("lastname", name).getResultList();

            System.out.println("Her er all students som hedder olsen (efternavn): " + llama);
            return llama;
        }
        finally
        {
            em.close();

        }
    }

    public int findTotalStudyPointByStudentId(int id)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            Studypoint sp = new Studypoint();
            Query query1 = em.createNamedQuery("Student.findById", Student.class);
            Student s = (Student) query1.setParameter("id", id).getSingleResult();
            Query query = em.createNamedQuery("Studypoint.findByStudentId", Studypoint.class);

            List<Studypoint> llama = query.setParameter("studentId", s).getResultList();
            int res = 0;
            System.out.println("Her er all students de studypoints som det angive ID har: " + llama);
            for (Studypoint studypoint : llama)
            {
                res += studypoint.getScore();
            }
            System.out.println("Her er total antal study points: " + res);
            return res;
        }
        finally
        {
            em.close();

        }
    }

    public int getTotalStudyPoints()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            Query query = em.createNamedQuery("Studypoint.findAll", Student.class);
            List<Studypoint> llama = query.getResultList();
            int res = 0;
            for (Studypoint studypoint : llama)
            {
                res += studypoint.getScore();
            }
            System.out.println("ALLE studypoints sum er: " + res);
            return res;

        }
        finally
        {
            em.close();

        }
    }

    public Student findHighestStudyPoint()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            List<Student> bob = findAllStudents();

            Map<Integer, Student> john = new HashMap();
            for (Student student : bob)
            {
                john.put(findTotalStudyPointByStudentId(student.getId()), student);
            }
            int comp = 0;
            for (Integer score : john.keySet())
            {
                if (score > comp)
                {
                    comp = score;
                }
            }
            System.out.println("her er den højeste score " + comp + " og den tilhøre: " + john.get(comp).getFirstname() + " " + john.get(comp).getLastname());

            return john.get(comp);
        }
        finally
        {
            em.close();

        }
    }

    public Student findLowestStudyPoint()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            List<Student> bob = findAllStudents();

            Map<Integer, Student> john = new HashMap();
            for (Student student : bob)
            {
                john.put(findTotalStudyPointByStudentId(student.getId()), student);
            }
            int comp = Collections.max(john.keySet());
            for (Integer score : john.keySet())
            {
                if (score < comp)
                {
                    comp = score;
                }
            }
            System.out.println("llama");
            System.out.println("her er den laveste score " + comp + " og den tilhøre: " + john.get(comp).getFirstname() + " " + john.get(comp).getLastname());

            return john.get(comp);
        }
        finally
        {
            em.close();

        }
    }

    public void createNewStudent(Student s)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            System.out.println("Jeg er igennem commit i createNewPerson");
        }
        finally
        {
            em.close();
        }
    }

}
