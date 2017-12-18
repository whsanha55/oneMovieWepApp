package domain.movie;

public class PhotoVO {
	private int moviePhotoNo;
	private String moviePhotoOriginalFileName;
	private String moviePhotoSystemFileName;
	private String moviePhotoUrl;
	private int movieNo;
	
	public PhotoVO() {
		super();
	}
	
	public int getMoviePhotoNo() {
		return moviePhotoNo;
	}

	public void setMoviePhotoNo(int moviePhotoNo) {
		this.moviePhotoNo = moviePhotoNo;
	}

	public String getMoviePhotoOriginalFileName() {
		return moviePhotoOriginalFileName;
	}

	public void setMoviePhotoOriginalFileName(String moviePhotoOriginalFileName) {
		this.moviePhotoOriginalFileName = moviePhotoOriginalFileName;
	}

	public String getMoviePhotoSystemFileName() {
		return moviePhotoSystemFileName;
	}

	public void setMoviePhotoSystemFileName(String moviePhotoSystemFileName) {
		this.moviePhotoSystemFileName = moviePhotoSystemFileName;
	}

	public String getMoviePhotoUrl() {
		return moviePhotoUrl;
	}

	public void setMoviePhotoUrl(String moviePhotoUrl) {
		this.moviePhotoUrl = moviePhotoUrl;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	@Override
	public String toString() {
		return "PhotoVO [moviePhotoNo=" + moviePhotoNo + ", moviePhotoOriginalFileName=" + moviePhotoOriginalFileName
				+ ", moviePhotoSystemFileName=" + moviePhotoSystemFileName + ", moviePhotoUrl=" + moviePhotoUrl
				+ ", movieNo=" + movieNo + "]";
	}
}
