package domain.movie;

public class GenreVO {
	private int genreNo;
	private String genreName;
	
	public GenreVO() {
		super();
	}
	public int getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	@Override
	public String toString() {
		return "GenreVO [genreNo=" + genreNo + ", genreName=" + genreName + "]";
	}
	
}
