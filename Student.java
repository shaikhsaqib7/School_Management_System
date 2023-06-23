package spec;
public class Student {
	protected String rollNumber;
	protected String name;
	
	//Constructor to add default values
	
	public Student() {
		this.rollNumber = "N/A";
		this.name = "N/A";
	}
	
	public Student(String rollNumber, String name) {
		this.rollNumber = rollNumber;
		this.name = name;
	}
	
	public Student(String rollNumber) {
		this.rollNumber = rollNumber;
		this.name = "N/A";
	}
	
	//GETTER and SETTER for rollNumber
	
	public String getRollNumber() {
		return this.rollNumber;
	}
	
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	
	//GETTER and SETTER for name
	
	public String getName() {
		return this.name;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	
	public void displayData() {
		System.out.println("Roll Number: " + this.rollNumber);
		System.out.println("Name: " + this.name);
	}
	
	public void displayData(String rno) {
		System.out.println("The name of student with roll number " + this.rollNumber + " is " + this.name);
	}
}
