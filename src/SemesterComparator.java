/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #6
 * Recitation: 03
 */
import java.util.Comparator;

public class SemesterComparator implements Comparator<Course>{
	public int compare (Course left, Course right) {
		if (Double.parseDouble(left.getSemester().substring(1))<Double.parseDouble(right.getSemester().substring(1)))
			return -1;
		if (Double.parseDouble(left.getSemester().substring(1))>Double.parseDouble(right.getSemester().substring(1)))
			return 1;
		if (Double.parseDouble(left.getSemester().substring(1))==Double.parseDouble(right.getSemester().substring(1))) {
			if (left.getSemester().charAt(0)>right.getSemester().charAt(0))
				return -1;
			if (left.getSemester().charAt(0)<right.getSemester().charAt(0))
				return 1;
		}
		return 0;
	}
}
