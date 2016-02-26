/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kaspe
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries(
        {
            @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
            @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
            @NamedQuery(name = "Student.findByFirstname", query = "SELECT s FROM Student s WHERE s.firstname = :firstname"),
            @NamedQuery(name = "Student.findByLastname", query = "SELECT s FROM Student s WHERE s.lastname = :lastname")
        })
public class Student implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @OneToMany(mappedBy = "studentId")
    private Collection<Studypoint> studypointCollection;

    public Student()
    {
    }

    public Student(String firstname, String lastname, Collection<Studypoint> studypointCollection)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.studypointCollection = studypointCollection;
    }

    public void addStudyPoint(Studypoint sp)
    {
        System.out.println("vi tager testtovitz med nul studyPoints");

        if (this.studypointCollection == null)
        {
            this.studypointCollection = new ArrayList();
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpqlDemo__exam-preparation_JPQL_PU");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            studypointCollection.add(sp);
            em.persist(sp);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    @XmlTransient
    public Collection<Studypoint> getStudypointCollection()
    {
        return studypointCollection;
    }

    public void setStudypointCollection(Collection<Studypoint> studypointCollection)
    {
        this.studypointCollection = studypointCollection;
    }

}
