package jpa.entitymodels;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;




@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int cId;
	
	@Column(name="name")
	private String cName;
	
	@Column(name="Instructor")
	private String cInstructorName;

	public Course() {
		
	}



	public Course(int cId, String cName, String cInstructorName) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}



	public int getcId() {
		return cId;
	}



	public void setcId(int cId) {
		this.cId = cId;
	}



	public String getcName() {
		return cName;
	}



	public void setcName(String cName) {
		this.cName = cName;
	}



	public String getcInstructorName() {
		return cInstructorName;
	}



	public void setcInstructor(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}
	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cName=" 
					+ cName + ", cInstructorName=" + cInstructorName
				+ "]\n";
	}


	



	
	
	
	

}
