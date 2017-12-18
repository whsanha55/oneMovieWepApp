package domain.movie;

public class MovieGenreVO {
	private int movieGenreNo;
	private int genreNo;
	private int movieNo;
	
	public MovieGenreVO() {
		super();
	}

	public int getMovieGenreNo() {
		return movieGenreNo;
	}

	public void setMovieGenreNo(int movieGenreNo) {
		this.movieGenreNo = movieGenreNo;
	}

	public int getGenreNo() {
		return genreNo;
	}

	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	@Override
	public String toString() {
		return "MovieGenreVO [movieGenreNo=" + movieGenreNo + ", genreNo=" + genreNo + ", movieNo=" + movieNo + "]";
	}
	
	
	
}
