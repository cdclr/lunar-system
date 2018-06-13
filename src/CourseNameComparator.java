/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #6
 * Recitation: 03
 */
import java.util.Comparator;

public class CourseNameComparator implements Comparator<Course>{
	public int compare(Course left, Course right) {
		if (left.getDepartment().charAt(0)<right.getDepartment().charAt(0))
			return -1;
		if (left.getDepartment().charAt(0)>right.getDepartment().charAt(0))
			return 1;
		if (left.getDepartment()==right.getDepartment()) {
			if (left.getNumber()<right.getNumber())
				return -1;
			if (left.getNumber()>right.getNumber())
				return 1;
		}
		return 0;
	}
}
