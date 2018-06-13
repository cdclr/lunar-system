/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #6
 * Recitation: 03
 */
import java.io.Serializable;
import java.util.*;

public class Student implements Serializable{
	private String webID;
	private List<Course> courses;
	public Student(String webID) {
		this.webID = webID;
		this.courses = new ArrayList<Course>();
	}
	public void setWebID(String webID) {
		this.webID = webID;
	}
	public String getWebID() {
		return this.webID;
	}
	public List<Course> getCourses() {
		return this.courses;
	}
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
	public boolean isEnrolledIn(String department, int number) {
		for (int i = 0; i<courses.size(); i++) {
			Course course = courses.get(i);
			if (course.getDepartment().equals(department) && course.getNumber()==number)
				return true;
		}
		return false;
	}
	//think of a better name xD
	public String courseToString(String department, int number) {
		String output = "";
		for (int i = 0; i<courses.size(); i++) {
			Course course = courses.get(i);
			if (course.getDepartment().equals(department) && course.getNumber()==number) {
				output+=webID+"\t"+course.getSemester()+"\n";
			}
		}
		return output;
	}
	public String toString() {
		return webID;
	}
}
