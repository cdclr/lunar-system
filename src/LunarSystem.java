/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #6
 * Recitation: 03
 */
import java.io.*;
import java.util.*;

public class LunarSystem {
	//key is string, can binary search an alphabetically sorted database
	private static HashMap<String, Student> database = new HashMap<String, Student>();
	private static List<Course> courses = new ArrayList<Course>();
	private static boolean programContinues = true;
	private static boolean menuContinues;
	private static Scanner input = new Scanner(System.in);
	private static String registrarString = "REGISTRAR";
	public static void registrarMenu() {
		System.out.println("Welcome Registrar.");
		while (menuContinues) {
			System.out.println("Options:\n\tR) Register a student\n\tD) De-register a student\n\tE) View course enrollment\n\tL) Logout\nPlease select an option: ");
			char menuSelection = Character.toUpperCase(input.next().charAt(0));
			String studentID = "";
			switch (menuSelection) {
				case 'R': 
					System.out.println("Please enter a webid for the new student: ");
					studentID = input.next();
					Student student = new Student(studentID);
					database.put(studentID.toLowerCase(), student);
					System.out.println(studentID+ " registered.");
					break;
				case 'D':
					System.out.println("Please enter a webid for the student to be deregistered: ");
					studentID = input.next();
					database.remove(studentID.toLowerCase());
					System.out.println(studentID+ " deregistered.");
					break;
				case 'E':
					//REVISE AFTER FINISHING STUDENT METHODS
					System.out.println("Please enter the course's department name: ");
					String department = input.next().toUpperCase();
					if (!department.matches("^[A-Z]{3}")) {
						System.out.println("Please enter a valid three-letter course department.");
						break;
					}
					int number = -1;
					System.out.println("Please enter the course number: ");
					if (input.hasNextInt())
						number = input.nextInt();
					else {
						System.out.println("Please enter a valid integer as the course number.");
						break;
					}
					String output = "";
					for (Map.Entry<String, Student> entry: database.entrySet()) {
						Student studentTemp = entry.getValue();
						if (studentTemp.isEnrolledIn(department, number)) {
							output+=studentTemp.courseToString(department, number);
						}
					}
					System.out.println("Students Registered in "+department+" "+number+":\nStudent \t Semester\n----------------------------\n"+output);
					break;
				case 'L':
					menuContinues = false;
					System.out.println("Registrar logged out.");
					break;
				default:
					System.out.println("Please enter a valid menu option");
					break;
			}
		}
	}
	public static void StudentMenu(Student student) {
		String department = "";
		int number = -1;
		String semester = "";
		String semesterSeason = "";
		Course course;
		while (menuContinues) {
			System.out.println("Options:\n\tA) Add a class\n\tD) Drop a class\n\tC) View your classes sorted by course name/department\n\tS) View your courses sorted by semester\n\tL) Logout");
			char menuSelection = Character.toUpperCase(input.next().charAt(0));
			switch (menuSelection) {
				case 'A':
					System.out.println("Please enter the course's department name: ");
					department = input.next().toUpperCase();
					if (!department.matches("^[A-Z]{3}")) {
						System.out.println("Please enter a valid three-letter course department.");
						break;
					}
					number = -1;
					System.out.println("Please enter the course number: ");
					if (input.hasNextInt())
						number = input.nextInt();
					else {
						System.out.println("Please enter a valid integer as the course number.");
						break;
					}
					System.out.println("Please select a semester: ");
					semester = input.next().toUpperCase();
					if ((semester.charAt(0)!='F' && semester.charAt(0)!='S') || !semester.matches("^[A-Z]\\d{4}")) {
						System.out.println("Please enter a valid semester (F/S+Year)");
						break;
					}
					if (semester.charAt(0)=='F')
						semesterSeason = "Fall";
					if (semester.charAt(0)=='S')
						semesterSeason = "Spring";
					course = new Course(department, number, semester);
					//if the course doesn't exist
					if (!courses.contains(course))
						courses.add(course);
					//if it does exist
					student.addCourse(courses.get(courses.indexOf(course)));
					System.out.println(department+" "+number+" added in "+semesterSeason+" "+semester.substring(1));
					break;
				case 'D':
					System.out.println("Please enter the course's department name: ");
					department = input.next().toUpperCase();
					if (!department.matches("^[A-Z]{3}")) {
						System.out.println("Please enter a valid three-letter course department.");
						break;
					}
					number = -1;
					System.out.println("Please enter the course number: ");
					if (input.hasNextInt())
						number = input.nextInt();
					else {
						System.out.println("Please enter a valid integer as the course number.");
						break;
					}
					System.out.println("Please select a semester: ");
					semester = input.next().toUpperCase();
					if ((semester.charAt(0)!='F' && semester.charAt(0)!='S') || !semester.matches("^[A-Z]\\d{4}")) {
						System.out.println("Please enter a valid semester (F/S+Year)");
						break;
					}
					if (semester.charAt(0)=='F')
						semesterSeason = "Fall";
					if (semester.charAt(0)=='S')
						semesterSeason = "Spring";
					course = new Course(department, number, semester);
					if (!courses.contains(course)) {
						System.out.println("There is no course "+department+" "+number+" in "+semesterSeason+" "+semester.substring(1));
						break;
					}
					else {
						student.removeCourse(courses.get(courses.indexOf(course)));
						System.out.println(department+" "+number+" dropped from "+semesterSeason+" "+semester.substring(1));
					}
					break;
				case 'C':
					System.out.println("Dept. Course Semester\n-----------------------------------");
					Collections.sort(student.getCourses(), new CourseNameComparator());
					for (int i = 0; i<student.getCourses().size(); i++) {
						System.out.println(student.getCourses().get(i).toString());
					}
					break;
				case 'S':
					System.out.println("Dept. Course Semester\n-----------------------------------");
					Collections.sort(student.getCourses(), new SemesterComparator());
					for (int i = 0; i<student.getCourses().size(); i++) {
						System.out.println(student.getCourses().get(i).toString());
					}
					break;
				case 'L':
					menuContinues = false;
					System.out.println(student.getWebID()+" logged out.");
					break;
				default:
					System.out.println("Please enter a valid menu option");
					break;
			}
		}
	}
	public static void saveState() {
		try {
			FileOutputStream file = new FileOutputStream("database.obj");
			ObjectOutputStream outStream = new ObjectOutputStream(file);
			outStream.writeObject(database);
			outStream.close();
			file = new FileOutputStream("courses.obj");
			outStream = new ObjectOutputStream(file);
			outStream.writeObject(courses);
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			FileInputStream file = new FileInputStream("database.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);
			database = (HashMap<String, Student>) inStream.readObject();
			inStream.close();
			file = new FileInputStream("courses.obj");
			inStream = new ObjectInputStream(file);
			courses = (List<Course>) inStream.readObject();
			inStream.close();
			System.out.println("Previous data loaded.");
		} catch (FileNotFoundException e) {
			System.out.println("No previous Data Found.");
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Welcome to the Lunar Registration System.");
		while (programContinues) {
			System.out.println("Menu:\n\tL) Login\n\tX) Save state and quit\n\tQ) Quit without saving state\nPlease select an option: ");
			char menuSelection = Character.toUpperCase(input.next().charAt(0));
			switch (menuSelection) {
				case 'L':
					System.out.println("Please enter webid: ");
					String webID = input.next();
					if (webID.toUpperCase().equals(registrarString)) {
						menuContinues = true;
						registrarMenu();
						break;
					}
					else
						if (database.containsKey(webID.toLowerCase())) {
							System.out.println("Welcome "+database.get(webID.toLowerCase()).getWebID());
							menuContinues = true;
							StudentMenu(database.get(webID.toLowerCase()));
						}
						else
							System.out.println("No user with the webID "+webID+" exists");
					break;
				case 'X':
					saveState();
					System.out.println("System state saved, system shut down for maintenance.");
					programContinues = false;
					break;
				case 'Q':
					System.out.println("Shutting down system--hope you saved!");
					programContinues = false;
					break;
			}
		}
	}
}
