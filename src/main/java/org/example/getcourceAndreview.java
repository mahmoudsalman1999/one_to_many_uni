package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/onetomany?useSSL=false";
        String user = "root";
        String pass = "5826";
        Connection connection = DriverManager.getConnection(url , user , pass);
        if (connection!= null)
        {
            System.out.println("DOM .....");
        }
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(instrauctor.class)
                                .addAnnotatedClass(instraucrtor_Detail.class)
                .addAnnotatedClass(course.class)
                .addAnnotatedClass(review.class)
                                                .buildSessionFactory();
        Session session =factory.getCurrentSession();
        try {
            session.beginTransaction();
            course courses = new course("ICDL");
            courses.addreview(new review("good"));
            courses.addreview(new review("exlant"));
            System.out.println("saving The cource");
            System.out.println(courses);
            System.out.println(courses.getReviews());
            session.save(courses);
            session.getTransaction().commit();

        }finally {
            session.close();
            factory.close();
        }
        connection.close();
    }
}