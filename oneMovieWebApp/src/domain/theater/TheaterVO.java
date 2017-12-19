package domain.theater;

import java.util.ArrayList;
import java.util.List;

public class TheaterVO {
	int theaterNo;		//±ØÀå¹øÈ£
	String theaterName;	//±ØÀåÀÌ¸§
	List<ScreenVO>list = new ArrayList<ScreenVO>();
	
	public void addScreenVO(ScreenVO screenVO) {
		list.add(screenVO);
	}
	
	public List<ScreenVO> getList() {
		return list;
	}

	public void setList(List<ScreenVO> list) {
		this.list = list;
	}

	public TheaterVO() {
		super();
	}

	public int getTheaterNo() {
		return theaterNo;
	}

	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	
	
}
