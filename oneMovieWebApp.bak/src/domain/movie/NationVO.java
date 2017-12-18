package domain.movie;

public class NationVO {
	private int nationNo;
	private String nationName;
	
	public NationVO() {
		super();
	}
	public int getNationNo() {
		return nationNo;
	}
	public void setNationNo(int nationNo) {
		this.nationNo = nationNo;
	}
	public String getNationName() {
		return nationName;
	}
	public void setNationName(String nationName) {
		this.nationName = nationName;
	}
	
	@Override
	public String toString() {
		return "NationVO [nationNo=" + nationNo + ", nationName=" + nationName + "]";
	}
}
