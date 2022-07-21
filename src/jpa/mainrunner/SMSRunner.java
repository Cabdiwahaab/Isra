package jpa.mainrunner;

import java.util.List;
import java.util.Scanner;

import jpa.service.StudentService;
import jpa.entitymodels.Course;
import jpa.service.CourseService;

public class SMSRunner {

	public static void main(String[] args) {
		
		StudentService sS = new StudentService();
		CourseService cS = new CourseService();
		
	
		Scanner scan = new Scanner(System.in);
		show();
			int command = 0;
		
		while(command != 5) {
			
			command = scan.nextInt();
			scan.nextLine();
			switch(command) {
			case 1:
				System.out.println("Enter your email");
				String email = scan.nextLine();
				System.out.println("Enter your password");
				String pass = scan.nextLine();
				
				if(sS.validateStudent(email,pass))
				{
					System.out.println("My classes:");

					System.out.println("# COURSE NAME  INSTRUCTOR NAME\n");
								sS.getStudentCourses(email);
					
					// this is where the second menu goes
					System.out.println("1: Register for a course");
					System.out.println("2: Logout");
					
					command = scan.nextInt();
					scan.nextLine();
					
					switch (command) {
					case 1:
						System.out.println("All classes:");

						//System.out.printf("%-5s%-15s%-50s\n", "#", "COURSE NAME", "INSTRUCTOR NAME");
						List<Course> courseList = cS.getAllCourses();
						for (int i = 0; i < courseList.size(); i++) {
							System.out.printf("%-5d%-15s%-50s\n", (i + 1), courseList.get(i).getcName(),
									courseList.get(i).getcInstructorName());
						}
						System.out.print("Which course: ");
						int courseId = scan.nextInt();
						scan.nextLine();
						sS.registerStudentToCourse(email, courseId);
						
					case 2:
						System.out.println("You have been signed out");
						System.exit(0);
						break;
					}
				} else {
					System.out.println("Invalid email or password");
					System.out.println("You quited!");
				 }
				break;
			case 2:
				System.out.println("You have been quit");
				System.exit(0);
				break;
				
			}
			scan.close();
		}
	}
	private static void show() {
		System.out.println("Are you a(n)");
		System.out.println("1: Student");
		System.out.println("2: quit");	
	}

}
