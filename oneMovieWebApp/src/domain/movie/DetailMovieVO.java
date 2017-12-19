package domain.movie;

import java.util.ArrayList;
import java.util.List;



public class DetailMovieVO {
   private int movieNo;
   private String movieTitle;
   private List<GenreVO> genres = new ArrayList<GenreVO>();
   private List<ActorVO> actors = new ArrayList<ActorVO>();
   private List<PhotoVO> photos = new ArrayList<PhotoVO>();
   private String director;
   private int runningTime;
   private int gradeNo;
   private int nationNo;
   private String story;
   private int roleNo;
   private String videoUrl;
   
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
      genres.add(genre);
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
   
   public void addActor(ActorVO actor) {
      actors.add(actor);
   }

   public void setActors(List<ActorVO> actors) {
      this.actors = actors;
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

   @Override
   public String toString() {
      return "DetailMovieVO [movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", actors=" + actors + ", director="
            + director + ", runningTime=" + runningTime + ", gradeNo=" + gradeNo + ", nationNo=" + nationNo
            + ", story=" + story + ", roleNo=" + roleNo + ", videoUrl=" + videoUrl + "]";
   }
   
   
   

}