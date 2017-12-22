 package model.movie;
 
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.ActorPhotoVO;
import domain.movie.ActorVO;
import domain.movie.DetailMovieVO;
import domain.movie.MovieTitleVO;
import domain.movie.MovieVO;
import domain.movie.PhotoVO;

public class MovieService {
   private static MovieService instance = new MovieService();

   private MovieService() {

   }

   public static MovieService getInstance() {
      return instance;
   }
   
   //영화 정보를 등록하다.
   public void addMovie(MovieVO movie) throws Exception {
     
      Connection conn = null;
      try {
         conn = DBConn.getConnection();

         // tx.begin(트랜젝션 시작)
         conn.setAutoCommit(false);
         
         //1. 영화 등록
         MovieDAO moiveDAO = MovieDAO.getInstance();
         int movieNo = moiveDAO.insertMovie(conn, movie);         
         System.out.println("movieNo :" + movieNo);
         
         
         //2. 영화 사진 등록       
         if (movie.getPhotos().size() != 0) {
            PhotoDAO photoDAO = PhotoDAO.getInstance();  
            List<PhotoVO> photos = movie.getPhotos();
            for (PhotoVO photo : photos) {
               photo.setMovieNo(movieNo);
            }            
            photoDAO.insertPhotoList(conn, photos);
         }
       
         
         //3. 출연진 등록
         if (movie.getActors().size() != 0) {
            ActorDAO actorDAO = ActorDAO.getInstance();
            for (ActorVO actor : movie.getActors()) {
               actor.setMovieNo(movieNo);                       
            }            
       
            for(ActorVO actor :  movie.getActors()) {   
                int actorNo = actorDAO.insertActorList(conn, actor);
            	System.out.println("actorNo : " + actorNo);

	            //출연진 사진 등록
	            if (actor.getActorPhoto() != null) {
	            	System.out.println("call insertActorPhoto");
	            	ActorPhotoVO actorPhoto = actor.getActorPhoto();
	            	actorPhoto.setActorNo(actorNo);
	                ActorPhotoDAO actorPhotoDAO = ActorPhotoDAO.getInstance();                
	                actorPhotoDAO.insertActorPhoto(conn, actorPhoto);
	             }
            }         
         }
                
         
         
         /*ActorPhotoDAO actorPhotoDAO = ActorPhotoDAO.getInstance();
         ActorPhotoVO actorPhoto = actor.getActorPhotos();
         actorPhoto.setActorNo(actorNo);
         actorPhotoDAO.insertActorPhoto(conn, actorPhoto);*/
         
         /*         
         //영화 장르 등록
         GenreDAO genreDAO = GenreDAO.getInstance();
         MovieGenreVO genre = movie.getMovieGenre();
         genre.setMovieNo(moiveNo);
         genreDAO.insertGenreList(conn, genre);*/
         
         conn.commit();

      } catch (Exception ex) {
         conn.rollback();
         throw ex;
      } finally {
         if (conn != null)
            conn.close();
      }

   }
   /*
   //영화를 삭제하다.
 	public void deleteMovie(int movieNo) throws Exception {
 		Connection conn = null;
 		try {
 			conn = DBConn.getConnection();
 			
 			//tx.begin
 			conn.setAutoCommit(false);
 		
 			MovieDAO articleDao = MovieDAO.getInstance();
 			articleDao.removeMovie(conn, movieNo);
 			
 			conn.commit();
 			
 		} catch (Exception e) {
 			conn.rollback();
 			throw e;
 		} finally {
 			if(conn != null) conn.close();
 		}
 	}
 	*/
   //영화를 삭제하다.
 	public void deleteMovie(int[] noList) throws Exception {
 		Connection conn = null;
 		try {
 			conn = DBConn.getConnection();
 			
 			//tx.begin
 			conn.setAutoCommit(false);
 		
 			MovieDAO articleDao = MovieDAO.getInstance();
 			//articleDao.removeMovie(conn, noList);
 			
 			conn.commit();
 			
 		} catch (Exception e) {
 			conn.rollback();
 			throw e;
 		} finally {
 			if(conn != null) conn.close();
 		}
 	}
   // 영화 정보를 삭제한다.
   public void deleteMovieList(int movieNo) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();

         // tx.begin
         conn.setAutoCommit(false);
         /*
         //사진 삭제
         PhotoDAO photoDao = PhotoDAO.getInstance();
         photoDao.removePhotoList(conn, movieNo);
         
         //출연진 사진 삭제
         ActorPhotoDAO actorPhotoDao = ActorPhotoDAO.getInstance();
         actorPhotoDao.removeActorPhoto(conn, movieNo);
         */
         //영화 삭제     
         MovieDAO movieDao = MovieDAO.getInstance();
         List<MovieVO> movies = new ArrayList<MovieVO>();
         List<Integer>noList = null;
         for(MovieVO movie : movies) {
            noList.add(movieNo);            
         }
         movieDao.removeMovieList(conn, noList);

         conn.commit();

      } catch (Exception e) {
         conn.rollback();
         throw e;
      } finally {
         if (conn != null)
            conn.close();
      }
   }
   
   //영화 정보를 수정하다.
   public void updateMovie(MovieVO movie) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();

         // tx.begin(트랜젝션 시작)
         conn.setAutoCommit(false);
         
         //영화 수정
         MovieDAO moiveDAO = MovieDAO.getInstance();
         moiveDAO.modifyMovieList(conn, movie);
         
         //출연진 수정
         /*ActorDAO actorDAO = ActorDAO.getInstance();      
         ActorVO actor = movie.getActors();
         actor.setMovieNo(movie.getMovieNo());
         actorDAO.modifyActorList(conn, actor);*/
         
         conn.commit();

      } catch (Exception ex) {
         conn.rollback();
         throw ex;
      } finally {
         if (conn != null)
            conn.close();
      }
   }
   // 영화 제목을 조회하다.
   public List<MovieTitleVO> retriveMovieTitle(String movieTitle) throws Exception {
	   MovieDAO movieDAO = MovieDAO.getInstance();
      return movieDAO.selectMovieTitleList(movieTitle);
   }
   
   // 영화 목록을 조회하다.
  // public List<MovieVO> retriveMovieList(String keyfield, String keyword, int startRow, int endRow) throws Exception {
   public List<MovieVO> retriveMovieList(String keyfield, String keyword) throws Exception {
      MovieDAO dao = MovieDAO.getInstance();
     //return dao.selectMovieList(keyfield, keyword, startRow, endRow);  
      return dao.selectMovieList(keyfield, keyword);  

   }

   // 게시글 상세정보를 조회하다.
   public DetailMovieVO retriveMovie(int movieNo) throws Exception {
      MovieDAO movieDao = MovieDAO.getInstance();
      DetailMovieVO detailMovie = movieDao.selectMovie(movieNo);
      return detailMovie;
   }
   
   //영화 목록을 전체 조회하다.
 	public List<MovieVO> retrieveMovieList() throws Exception {
 		MovieDAO dao = MovieDAO.getInstance();
 		return dao.selectMovieList();
 	}
}