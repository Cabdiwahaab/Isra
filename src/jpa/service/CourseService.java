package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;


public class CourseService implements CourseDAO{
	

	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Course.class)
			.buildSessionFactory();
	
	// create session
			Session session = factory.openSession();

	public List<Course> getAllCourses() {
		
		List<Course> courseList = session.createQuery("from Course", Course.class).getResultList();
		//System.out.println("from course file :"+courseList);
		try {
		
		} catch (Exception ex) {
			ex.printStackTrace();
			// handle exception here
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (Exception ex) {
			}
		}
		return courseList;

	}

}
