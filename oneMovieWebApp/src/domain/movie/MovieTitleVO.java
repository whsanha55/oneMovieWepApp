package domain.movie;

public class MovieTitleVO {
	private int movieNo;
	private String movieTitle;
	
	public MovieTitleVO() {
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
	@Override
	public String toString() {
		return "MovieTitleVO [movieNo=" + movieNo + ", movieTitle=" + movieTitle + "]";
	}
}
