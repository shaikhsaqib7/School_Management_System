 package spec;
import java.util.Scanner;
import java.sql.*;
public class StudentManagementSystem {

	public static void main(String[] args) {
		try {
			char cont, spNeeds;
			int i =1, choice;
			String 	url ="jdbc:mysql://localhost:3306/spec",
					user="root", 
					pass="Shaikh-72742";
			String rno,nm,wc,vi;
			boolean flag = false,needsWC,isVI;
			Scanner sc = new Scanner(System.in);
			int noOfStudents = 0;
			//Establishing connection with DB
			Connection conn = DriverManager.getConnection(url, user, pass);
			//Checking if DB Connection successful
			if(!conn.isClosed()) {
				System.out.println("DB Connection Successful");
			}
			//Creating a statement on the connection
			Statement stmt = conn.createStatement();
			//Executing select query
			String sql = "SELECT * FROM student_data";
			ResultSet stuCount = stmt.executeQuery(sql);
			while(stuCount.next()) {
				noOfStudents++;
			}
			//Creating a student array : int[] a = new int[5];
			Student[] s = new Student[noOfStudents];
			Student[] sCopy;
			sql = "SELECT * FROM student_data s LEFT JOIN special_needs sp ON sp.student_id = s.id";
			ResultSet stuData = stmt.executeQuery(sql);
			i = 0;
			while(stuData.next()) {
				rno = stuData.getString("roll_number");
				nm = stuData.getString("name");
				vi = stuData.getString("is_vi");
				wc = stuData.getString("needs_wc");
				if(wc == null || vi == null) {
					s[i++] = new Student(rno,nm);
				}
				else {
					isVI = vi.equals("true") ? true : false;
					needsWC = wc.equals("true") ? true : false;
					s[i++] = new SpecialNeedsStudent(rno,nm,isVI,needsWC);
				}
			}
			do {
				System.out.println("----MENU----");
				System.out.println("\t1.List All Students");
				System.out.println("\t2.Find Student Data");
				System.out.println("\t3.Edit Student Data");
				System.out.println("\t4.Remove Student Data");
				System.out.println("\t5.Add a new Student");
				System.out.print("Enter your choice: ");
				choice = sc.nextInt();
				switch(choice ) {
					case 1: System.out.println("Listing Students");
							for(int j = 0; j < noOfStudents; j++) {
								s[j].displayData();
							}
							break;
					case 2: System.out.print("Enter roll number of student: ");
							rno = sc.next();
							for(int j = 0 ; j < noOfStudents; j++) {
								if(s[j].getRollNumber().equals(rno)) {
									s[j].displayData(rno);
									flag = true;
								}
							}
							if(flag == false) {
								System.out.println("Student does not exist");
							}
							break;
					case 3: System.out.println("Editing Student");
							System.out.print("Enter roll number of student: ");
							rno = sc.next();
							for(int j = 0 ; j < noOfStudents; j++) {
								if(s[j].getRollNumber().equals(rno)) {
									System.out.print("Enter new name of the student: ");
									nm = sc.next();
									s[j].setName(nm);
									flag = true;
								}
							}
							if(flag == false) {
								System.out.println("Student does not exist");
							}
							break;
					case 4:  System.out.print("Enter roll number of student: ");
							rno = sc.next();
							for(int j = 0 ; j < noOfStudents; j++) {
								if(s[j].getRollNumber().equals(rno)) {
									s[j].setName("--removed--");
									System.out.print("Student Removed");
									flag = true;
								}
							}
							if(flag == false) {
								System.out.println("Student does not exist");
							}
							break;
					case 5: System.out.println("Adding Student");
							sCopy = new Student[noOfStudents + 1];
							for(int j = 0; j < noOfStudents; j++) {
								sCopy[j] = new Student(s[j].getRollNumber(),s[j].getName());
							}
							System.out.print("Enter roll number of new student: ");
							rno = sc.next();
							System.out.print("Enter Name of new student: ");
							nm = sc.next();
							System.out.print("Does the student have special needs? (y/n): ");
							spNeeds = sc.next().charAt(0);
							if(spNeeds == 'Y' || spNeeds == 'y') {
								System.out.print("Does the student require a wheelchair? (y/n): ");
								wc = sc.next();
								needsWC =  (wc.charAt(0) == 'Y' || wc.charAt(0) == 'y') ? true : false;
								System.out.print("Is the student visually impaired? (y/n): ");
								vi = sc.next();
								isVI =  (vi.charAt(0) == 'Y' || vi.charAt(0) == 'y') ? true : false;
								sCopy[noOfStudents++] = new SpecialNeedsStudent(rno,nm,needsWC,isVI);
							}
							else {
								sCopy[noOfStudents++] = new Student(rno,nm);
							}
							s = sCopy;
							System.out.println("New Student Created. \nTotal Number of Students: " + noOfStudents);
							break;
					
					default:System.out.println("ERROR! Enter a digit from 1-5");
							break;
				}
				System.out.print("Do you want to continue? (y/n): ");
				cont = sc.next().charAt(0);
			}while(cont != 'N'  && cont != 'n');
			System.out.println("Thank You!");
		}
		catch(Exception e) {
			System.out.print("ERROR!\nMessage: " + e.toString());
		}
		
	}
}