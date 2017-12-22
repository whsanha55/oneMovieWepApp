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
   
   //��ȭ ������ ����ϴ�.
   public void addMovie(MovieVO movie) throws Exception {
     
      Connection conn = null;
      try {
         conn = DBConn.getConnection();

         // tx.begin(Ʈ������ ����)
         conn.setAutoCommit(false);
         
         //1. ��ȭ ���
         MovieDAO moiveDAO = MovieDAO.getInstance();
         int movieNo = moiveDAO.insertMovie(conn, movie);         
         System.out.println("movieNo :" + movieNo);
         
         
         //2. ��ȭ ���� ���       
         if (movie.getPhotos().size() != 0) {
            PhotoDAO photoDAO = PhotoDAO.getInstance();  
            List<PhotoVO> photos = movie.getPhotos();
            for (PhotoVO photo : photos) {
               photo.setMovieNo(movieNo);
            }            
            photoDAO.insertPhotoList(conn, photos);
         }
       
         
         //3. �⿬�� ���
         if (movie.getActors().size() != 0) {
            ActorDAO actorDAO = ActorDAO.getInstance();
            for (ActorVO actor : movie.getActors()) {
               actor.setMovieNo(movieNo);                       
            }            
       
            for(ActorVO actor :  movie.getActors()) {   
                int actorNo = actorDAO.insertActorList(conn, actor);
            	System.out.println("actorNo : " + actorNo);

	            //�⿬�� ���� ���
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
         //��ȭ �帣 ���
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
   //��ȭ�� �����ϴ�.
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
   //��ȭ�� �����ϴ�.
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
   // ��ȭ ������ �����Ѵ�.
   public void deleteMovieList(int movieNo) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();

         // tx.begin
         conn.setAutoCommit(false);
         /*
         //���� ����
         PhotoDAO photoDao = PhotoDAO.getInstance();
         photoDao.removePhotoList(conn, movieNo);
         
         //�⿬�� ���� ����
         ActorPhotoDAO actorPhotoDao = ActorPhotoDAO.getInstance();
         actorPhotoDao.removeActorPhoto(conn, movieNo);
         */
         //��ȭ ����     
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
   
   //��ȭ ������ �����ϴ�.
   public void updateMovie(MovieVO movie) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();

         // tx.begin(Ʈ������ ����)
         conn.setAutoCommit(false);
         
         //��ȭ ����
         MovieDAO moiveDAO = MovieDAO.getInstance();
         moiveDAO.modifyMovieList(conn, movie);
         
         //�⿬�� ����
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
   // ��ȭ ������ ��ȸ�ϴ�.
   public List<MovieTitleVO> retriveMovieTitle(String movieTitle) throws Exception {
	   MovieDAO movieDAO = MovieDAO.getInstance();
      return movieDAO.selectMovieTitleList(movieTitle);
   }
   
   // ��ȭ ����� ��ȸ�ϴ�.
  // public List<MovieVO> retriveMovieList(String keyfield, String keyword, int startRow, int endRow) throws Exception {
   public List<MovieVO> retriveMovieList(String keyfield, String keyword) throws Exception {
      MovieDAO dao = MovieDAO.getInstance();
     //return dao.selectMovieList(keyfield, keyword, startRow, endRow);  
      return dao.selectMovieList(keyfield, keyword);  

   }

   // �Խñ� �������� ��ȸ�ϴ�.
   public DetailMovieVO retriveMovie(int movieNo) throws Exception {
      MovieDAO movieDao = MovieDAO.getInstance();
      DetailMovieVO detailMovie = movieDao.selectMovie(movieNo);
      return detailMovie;
   }
   
   //��ȭ ����� ��ü ��ȸ�ϴ�.
 	public List<MovieVO> retrieveMovieList() throws Exception {
 		MovieDAO dao = MovieDAO.getInstance();
 		return dao.selectMovieList();
 	}
}