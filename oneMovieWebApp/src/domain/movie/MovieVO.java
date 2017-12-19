package domain.movie;

import java.util.ArrayList;
import java.util.List;

public class MovieVO {
   private int movieNo;
   private String movieTitle;
   private int runningTime;
   private String director;
   private String story;
   private String videoUrl;
   private int gradeNo;
   private int nationNo;
   private ActorVO actor = new ActorVO();
   private List<ActorVO> actors = new ArrayList<ActorVO>();//�� ��ȭ�� ���� �⿬������ ������ ����
   private List<PhotoVO> photos = new ArrayList<PhotoVO>();//�� ��ȭ�� ���� ������ ������ ����
   private MovieGenreVO movieGenre = new MovieGenreVO();
   
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

   public List<ActorVO> getActors() {
      return actors;
   }

   public void setActors(List<ActorVO> actors) {
      this.actors = actors;
   }
   
   public void addGenre(GenreVO genre) {
      //add(genre);
   }

   public MovieGenreVO getMovieGenre() {
      return movieGenre;
   }

   public void setMovieGenre(MovieGenreVO movieGenre) {
      this.movieGenre = movieGenre;
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

   public ActorVO getActor() {
      return actor;
   }

   public void setActor(ActorVO actor) {
      this.actor = actor;
   }

   @Override
   public String toString() {
      return "MovieVO [movieNo=" + movieNo + ", movieTitle=" + movieTitle + ", runningTime=" + runningTime
            + ", director=" + director + ", story=" + story + ", videoUrl=" + videoUrl + ", gradeNo=" + gradeNo
            + ", nationNo=" + nationNo + ", actor=" + actor + ", actors=" + actors + ", photos=" + photos
            + ", movieGenre=" + movieGenre + "]";
   }
}