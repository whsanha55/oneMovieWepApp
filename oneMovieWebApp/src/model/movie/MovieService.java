package model.movie;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.ActorPhotoVO;
import domain.movie.ActorVO;
import domain.movie.MovieGenreVO;
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
		ArrayList<ActorVO> actors = new ArrayList<ActorVO>();
		ArrayList<PhotoVO> photos = new ArrayList<PhotoVO>();
		ActorVO actor = new ActorVO();
		Connection conn = null;
		int actorNo = 0;
		try {
			conn = DBConn.getConnection();

			// tx.begin(Ʈ������ ����)
			conn.setAutoCommit(false);
			
			//��ȭ ���
			MovieDAO moiveDAO = MovieDAO.getInstance();
<<<<<<< HEAD
			int moiveNo = moiveDAO.insertArticle(conn, movie);

=======
			int moiveNo = moiveDAO.insertMovie(conn, movie);
			
			//�⿬�� ���
>>>>>>> refs/remotes/origin/master
			if (movie.getActors().size() != 0) {
				ActorDAO actorDAO = ActorDAO.getInstance();
				for (ActorVO actor1 : movie.getActors()) {
					actor1.setMovieNo(moiveNo);
					actors.add(actor1);				
				}
				actorNo = actorDAO.insertActorList(conn, actors);
			}
			
			//�⿬�� ���� ���
			ActorPhotoDAO actorPhotoDAO = ActorPhotoDAO.getInstance();
			ActorPhotoVO actorPhoto = actor.getActorPhoto();
			actorPhoto.setActorNo(actorNo);
			actorPhotoDAO.insertActorPhoto(conn, actorPhoto);
			
			//���� ���
			if (movie.getPhotos().size() != 0) {
				PhotoDAO photoDAO = PhotoDAO.getInstance();
				for (PhotoVO photo : movie.getPhotos()) {
					photo.setMovieNo(moiveNo);
					photos.add(photo);				
				}
				photoDAO.insertPhotoList(conn, photos);
			}
			
			//��ȭ �帣 ���
			GenreDAO genreDAO = GenreDAO.getInstance();
			MovieGenreVO genre = movie.getMovieGenre();
			genre.setMovieNo(moiveNo);
			genreDAO.insertGenreList(conn, genre);
			
			conn.commit();

		} catch (Exception ex) {
			conn.rollback();
			throw ex;
		} finally {
			if (conn != null)
				conn.close();
		}

	}
	// ��ȭ ������ �����Ѵ�.
   public void deleteMovie(int movieNo) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();

         // tx.begin
         conn.setAutoCommit(false);
         
         //���� ����
         PhotoDAO photoDao = PhotoDAO.getInstance();
         photoDao.removePhotoList(conn, movieNo);
         
         //�⿬�� ���� ����
         ActorPhotoDAO actorPhotoDao = ActorPhotoDAO.getInstance();
         actorPhotoDao.removeActorPhoto(conn, movieNo);
         
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
			ActorDAO actorDAO = ActorDAO.getInstance();		
			ActorVO actor = movie.getActor();
			actor.setMovieNo(movie.getMovieNo());
			actorDAO.modifyActorList(conn, actor);
			
			conn.commit();

		} catch (Exception ex) {
			conn.rollback();
			throw ex;
		} finally {
			if (conn != null)
				conn.close();
		}
   }
}