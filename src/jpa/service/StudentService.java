package jpa.service;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;


public class StudentService implements StudentDAO {
	

	public List<Student> getAllStudents() {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
	        try {
	        	session.beginTransaction();
	          	           
	            List<Student> query = session.createQuery("from Student",Student.class).getResultList();
	        session.getTransaction().commit();
	        System.out.println("All of the students are here: " + query);
	        return query;

    } catch (Exception e) {
        e.printStackTrace();
        session.getTransaction().rollback();
        return null;
    } finally {
        session.close();
        sessionFactory.close();
    }
	}
  
	public Student getStudentByEmail(String email) {
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
        try {
        	session.beginTransaction();
            
            Student student = session.get(Student.class, email);
            if(student !=null)
            {
            	 System.out.println("the student who has this emila is: " +email);
	            session.beginTransaction().commit();
	            return student;
            }
            else
            {
            	System.out.println("I do not have this student " +email);
	            
	            return null;
            }
        } finally {
        	sessionFactory.close();
        }
	}
	public boolean validateStudent(String sEmail, String sPassword) {
		
		SessionFactory sessionFactory = getSessionFactory();

		Session session = sessionFactory.openSession();
		try {
			// start a transaction
			session.beginTransaction();
			// query students
			Student tempStudent = session.get(Student.class, sEmail);
			boolean temBool = false;
			if (tempStudent != null && tempStudent.getsPassword().contentEquals(sPassword)) {
				System.out.println("Student #" + sEmail +"and"+ sPassword+ " was found");
				temBool = true;
			} else {
				System.out.printf("%-10s%-15s\n", sEmail, sPassword +" couldn't be found");
			}
			session.getTransaction().commit();
			return temBool;
			
		} finally {
			sessionFactory.close();
		}
		
	}

	public List<Course> getStudentCourses(String sEmail) {
		
		SessionFactory sessionFactory = getSessionFactory();

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			// call the student object 

			Student theStudent = session.get(Student.class, sEmail);
			List<Course> theCourses = theStudent.getsCourses();

			if(theCourses != null) {
			//display the courses
			System.out.println("Here are your courses  ");
			System.out.printf("%5s %30s %30s\n", "id", "name","Instructor");
			for (Course c: theCourses)
			{
				System.out.printf("%5d %30s %30s\n", c.getcId(), c.getcName(), c.getcInstructorName());

			}
			session.getTransaction().commit();
			return theCourses;
			}
			else {
				System.out.println("We can not find courses: "+theCourses);
				session.getTransaction().commit();

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			sessionFactory.close();	
			
		}
		return null;
		
			
	}

	@Override
    public void registerStudentToCourse(String sEmail, int cId) {

        SessionFactory sessionFactory = getSessionFactory();

        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();
            //get the class directly from the course list
            Course theCourse = session.get(Course.class, cId);
			// use the student email (sEmail) to get the student object (Student) from the student table
            Student theStudent = session.get(Student.class, sEmail);
            // use the getter method on the student (Student) to get the list of courses (sCourses)
            List<Course> taCourse = theStudent.getsCourses();
            if(taCourse.contains(theCourse))
            {
            	System.out.println("The course is already in there:" +taCourse);
            }
            else {
            	 // end up ADD the course object (Course) in the student's list of courses (sCourses)
            	 taCourse.add(theCourse);
     			System.out.printf("%5s %30s %30s\n", "id", "name","Instructor");
     			for (Course c: taCourse)
    			{
    				System.out.printf("%5d %30s %30s\n", c.getcId(), c.getcName(), c.getcInstructorName());

    			}
//             	System.out.println("The course is registered:" +taCourse);

            }
           session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

	private static SessionFactory getSessionFactory() 
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		return factory;
	}
	
	
}
