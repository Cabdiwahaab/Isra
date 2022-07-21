package jpa.mainrunner;

import jpa.service.CourseService;
import jpa.service.StudentService;

public class TestRunner {

	public static void main(String[] args) {
		StudentService student = new StudentService();
		CourseService c = new CourseService();
		//student.registerStudentToCourse("M", 2);
		//student.getAllStudents();
		//student.getStudentByEmail("aiannitti7@is.gd");
//		student.validateStudent("aiannitti7@is.gd","TWP4hf5j");
//		student.validateStudent("aiannitti7@is.gd","TWP4hf34j");
//		student.validateStudent("aiannitti29@is.gd","TWP4hf5j");
//		student.getStudentCourses("cjaulme9@bing.com");
		//student.registerStudentToCourse("cstartin3@flickr.com", 3);
		//student.getStudentCourses("M");
		c.getAllCourses();

		//student.getStudentCourses("M");
	}

}
