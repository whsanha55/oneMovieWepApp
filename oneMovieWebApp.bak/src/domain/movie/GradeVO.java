package domain.movie;

public class GradeVO {
	
	int gradeNo;
	String gradeAge;
	
	public GradeVO() {
		super();
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	public String getGradeAge() {
		return gradeAge;
	}

	public void setGradeAge(String gradeAge) {
		this.gradeAge = gradeAge;
	}

	@Override
	public String toString() {
		return "GradeVO [gradeNo=" + gradeNo + ", gradeAge=" + gradeAge + "]";
	}


	
	

}
