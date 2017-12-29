package domain.movie;

import java.util.ArrayList;
import java.util.List;

public class DetailMovieVO {

private int movieNo;
   private String movieTitle;
   private String director;
   private String story;
   private String videoUrl;
   private int runningTime;
   private int gradeNo;
   private int nationNo;
   private int roleNo;
   
   private List<GenreVO> genres = new ArrayList<GenreVO>();
   private List<ActorVO> actors = new ArrayList<ActorVO>();
   private List<PhotoVO> photos = new ArrayList<PhotoVO>();
   
   private PhotoVO detailPhoto = new PhotoVO();
   private PhotoVO photo = new PhotoVO();
   private GradeVO grade = new GradeVO();
   private NationVO nation = new NationVO();

   public DetailMovieVO() {
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

   public void addGenre(GenreVO genre) {
	   boolean isExsit = false;
	      for(int i = 0; i < genres.size(); i++) {
	    	  GenreVO temp = genres.get(i);
	         if(genre.getGenreName().equals(temp.getGenreName())) { //중복된 데이터가 있을 경우
	            isExsit = true;
	            break;
	         }

	      }
	      if(isExsit) { //isExsit=true

	      } else {
	    	 genres.add(genre);
	         System.out.println(genres);
	      }
   }

   public List<GenreVO> getGenres() {
      return genres;
   }

   public void setGenres(List<GenreVO> genres) {
      this.genres = genres;
   }

   public List<ActorVO> getActors() {
      return actors;
   }
   
   public void addActor1(ActorVO actor) {
		actors.add(actor);
   }

   
   public void addActor(ActorVO actor) {
      boolean isExsit = false;
      for(int i = 0; i < actors.size(); i++) {
         ActorVO temp = actors.get(i);
         if(actor.getActorName().equals(temp.getActorName())) { //중복된 데이터가 있을 경우
            isExsit = true;
           // break;
         }
      }
      if(isExsit) { //isExsit=true

      } else {
         actors.add(actor);
         System.out.println(actors);
      }
      
   }

   public void setActors(List<ActorVO> actors) {
      this.actors = actors;
   }

   public void addPhoto(PhotoVO photo) {
      boolean isExsit = false;
      for(int i = 0; i < photos.size(); i++) {
         PhotoVO temp = photos.get(i);
         if(photo.getMoviePhotoOriginalFileName().equals(temp.getMoviePhotoOriginalFileName())) { //중복된 데이터가 있을 경우
            isExsit = true;
            break;
         }

      }
      if(isExsit) { //isExsit=true
         //photos.add(obj);
      } else {
         photos.add(photo);
         //System.out.println(photos);
      }
   }

   public List<PhotoVO> getPhotos() {
      
      return photos;
   }

   public void setPhotos(List<PhotoVO> photos) {
      this.photos = photos;
   }
   
   public void addPhoto1(PhotoVO photo) {
	      photos.add(photo);
   }
   
   public String getDirector() {
      return director;
   }

   public void setDirector(String director) {
      this.director = director;
   }

   public int getRunningTime() {
      return runningTime;
   }

   public void setRunningTime(int runningTime) {
      this.runningTime = runningTime;
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

   public String getStory() {
      return story;
   }

   public void setStory(String story) {
      this.story = story;
   }

   public int getRoleNo() {
      return roleNo;
   }

   public void setRoleNo(int roleNo) {
      this.roleNo = roleNo;
   }

   public String getVideoUrl() {
      return videoUrl;
   }

   public void setVideoUrl(String videoUrl) {
      this.videoUrl = videoUrl;
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

   public PhotoVO getPhoto() {
      return photo;
   }

   public void setPhoto(PhotoVO photo) {
      this.photo = photo;
   }


   @Override
	public String toString() {
		return "DetailMovieVO [movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", genres=" + genres + ", actors="
				+ actors + ", photos=" + photos + ", detailPhoto=" + detailPhoto + ", photo=" + photo + ", grade="
				+ grade + ", nation=" + nation + ", director=" + director + ", runningTime=" + runningTime
				+ ", gradeNo=" + gradeNo + ", nationNo=" + nationNo + ", story=" + story + ", roleNo=" + roleNo
				+ ", videoUrl=" + videoUrl + "]";
	}

   public PhotoVO getDetailPhoto() {
		return detailPhoto;
	}

	public void setDetailPhoto(PhotoVO detailPhoto) {
		this.detailPhoto = detailPhoto;
	}
	

}