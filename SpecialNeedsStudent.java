package spec;

public class SpecialNeedsStudent extends Student {
private boolean needsWC, isVI;
	
	public SpecialNeedsStudent() {
		super();
		this.needsWC = this.isVI = false;
	}
	
	public SpecialNeedsStudent(String rollNumber, String name, boolean needsWC, boolean isVI) {
		super(rollNumber,name);
		this.needsWC = needsWC;
		this.isVI = isVI;
	}
	
	public SpecialNeedsStudent(String rollNumber) {
		super(rollNumber);
		this.needsWC = false;
		this.isVI = false;
	}
	
	public void displayData() {
		super.displayData();
		System.out.println("---Special Needs---");
		if(this.isVI) {
			System.out.println("Student is Visually Impaired.");
		}
		if(this.needsWC) {
			System.out.println("Student requires a wheelchair.");
		}
	}
	
	public void displayData(String rno) {
		super.displayData(rno);
		System.out.println("---Special Needs---");
		if(this.isVI) {
			System.out.println("Student is Visually Impaired.");
		}
		else {
			System.out.println("Student is not Visually Impaired.");
		}
		if(this.needsWC) {
			System.out.println("Student requires a wheelchair.");
		}
		else {
			System.out.println("Student doesn't require a wheelchair.");
		}
		
	}
}

