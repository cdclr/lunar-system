/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #6
 * Recitation: 03
 */
import java.io.Serializable;
import java.util.*;

public class Course implements Serializable{
	private String department;
	private int number;
	private String semester;
	public Course (String department, int number, String semester) {
		this.department = department;
		this.number = number;
		this.semester = semester;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartment() {
		return this.department;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return this.number;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSemester() {
		return this.semester;
	}
	public boolean equals(Object obj) {
		if (obj instanceof Course) {
			Course dummy = (Course) obj;
			return (this.getDepartment().equals(dummy.getDepartment()) && this.getNumber()==dummy.getNumber() && this.semester.equals(dummy.getSemester()));
		}
		else
			return false;
	}
	public String toString() {
		return this.department+"\t"+this.number+"\t"+this.semester;
	}
}
