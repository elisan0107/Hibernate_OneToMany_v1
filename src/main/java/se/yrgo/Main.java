package se.yrgo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Tutor tutor = new Tutor("James Blont");
        Student student1 = new Student("Greta Gris");
        Student student2 = new Student("Bill Skarsgård");
        Student student3 = new Student("Miles Davis");

        tutor.addStudent(student1);
        tutor.addStudent(student2);
        tutor.addStudent(student3);

        session.persist(tutor);

        session.getTransaction().commit();

        session.beginTransaction();
        Tutor retrievedTutor = session.get(Tutor.class, tutor.getId());
        for (Student student : retrievedTutor.getStudents()) {
            System.out.println("Student: " + student.getName());
        }
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}