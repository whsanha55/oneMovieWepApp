package domain.movie;

public class MovieVO {
	private int movieNo;
	private String movieTitle;
	private String runningTime;
	private String director;
	private String story;
	private String videoUrl;
	private int gradeNo;
	private int nationNo;
	
	public MovieVO() {
		super();
	}
	
	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	public int getNationNo() {
		return nationNo;
	}

	public void setNationNo(int nationNo) {
		this.nationNo = nationNo;
	}
	
	@Override
	public String toString() {
		return "MovieVO [movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", runningTime=" + runningTime
				+ ", director=" + director + ", story=" + story + ", videoUrl=" + videoUrl + ", gradeNo=" + gradeNo
				+ ", nationNo=" + nationNo + "]";
	}	
}
