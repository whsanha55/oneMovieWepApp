package domain.movie;

import java.util.ArrayList;
import java.util.List;

public class MovieVO {
	private int movieNo;
	private int runningTime;
	private int gradeNo;
	private int nationNo;
	private String movieTitle;
	private String director;
	private String story;
	private String videoUrl;
	
	private ActorVO actor = new ActorVO();
	private GradeVO grade = new GradeVO();
	private NationVO nation = new NationVO();
	private PhotoVO photo = new PhotoVO(); 
	
	private List<ActorVO> actors = new ArrayList<ActorVO>();// 한 영화에 대해 출연진들은 여러명 존재
	private List<PhotoVO> photos = new ArrayList<PhotoVO>();// 한 영화에 대해 사진(포토)은 여러개 존재
	private MovieGenreVO movieGenre = new MovieGenreVO();
	private List<MovieGenreVO> movieGenres = new ArrayList<MovieGenreVO>();

	public MovieVO() {
		super();
	}
	
	public MovieVO(int movieNo, String movieTitle, int runningTime, String director, String story, String videoUrl,int gradeNo, int nationNo) {
		super();
		this.movieNo = movieNo;
		this.movieTitle = movieTitle;
		this.runningTime = runningTime;
		this.director = director;
		this.story = story;
		this.videoUrl = videoUrl;
		this.gradeNo = gradeNo;
		this.nationNo = nationNo;
	}

	public MovieVO(String movieTitle, int runningTime, String director, int gradeNo, int nationNo) {
		super();
		this.movieTitle = movieTitle;
		this.runningTime = runningTime;
		this.director = director;
		this.gradeNo = gradeNo;
		this.nationNo = nationNo;
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

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
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

	public void addActor(ActorVO actor) {
		actors.add(actor);
	}

	public ActorVO getActor() {
		return actor;
	}

	public void setActor(ActorVO actor) {
		this.actor = actor;
	}

	public void addPhoto(PhotoVO photo) {
		photos.add(photo);
	}

	public List<PhotoVO> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoVO> photos) {
		this.photos = photos;
	}

	public MovieGenreVO getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(MovieGenreVO movieGenre) {
		this.movieGenre = movieGenre;
	}

	public List<ActorVO> getActors() {
		return actors;
	}

	public void setActors(List<ActorVO> actors) {
		this.actors = actors;
	}

	public void addGenre(GenreVO genre) {
		// add(genre);
	}

	public GradeVO getGrade() {
		return grade;
	}

	public void setGrade(GradeVO grade) {
		this.grade = grade;
	}

	public NationVO getNation() {
		return nation;
	}

	public void setNation(NationVO nation) {
		this.nation = nation;
	}	
	
	public void addMovieGenre(MovieGenreVO movieGenre) {
		movieGenres.add(movieGenre);
	}

	public List<MovieGenreVO> getMovieGenres() {
		return movieGenres;
	}

	public void setMovieGenres(List<MovieGenreVO> movieGenres) {
		this.movieGenres = movieGenres;
	}


	public PhotoVO getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoVO photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "MovieVO [movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", runningTime=" + runningTime
				+ ", director=" + director + ", story=" + story + ", videoUrl=" + videoUrl + ", gradeNo=" + gradeNo
				+ ", nationNo=" + nationNo + ", actor=" + actor + ", grade=" + grade + ", nation=" + nation + ", photo="
				+ photo + ", actors=" + actors + ", photos=" + photos + ", movieGenre=" + movieGenre + "]";
	}
}