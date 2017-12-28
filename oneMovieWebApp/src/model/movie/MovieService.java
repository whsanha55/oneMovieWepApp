package model.movie;

import java.sql.Connection;
import java.util.List;

import conn.DBConn;
import domain.movie.ActorPhotoVO;
import domain.movie.ActorVO;
import domain.movie.DetailMovieVO;
import domain.movie.MovieGenreVO;
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

	// ��ȭ ������ ����ϴ�.
	public void addMovie(MovieVO movie) throws Exception {

		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// tx.begin(Ʈ������ ����)
			conn.setAutoCommit(false);

			// 1. ��ȭ ���
			MovieDAO moiveDAO = MovieDAO.getInstance();
			int movieNo = moiveDAO.insertMovie(conn, movie);
			System.out.println("movieNo :" + movieNo);

			// 2. ��ȭ ���� ���
			if (movie.getPhotos().size() != 0) {
				PhotoDAO photoDAO = PhotoDAO.getInstance();
				List<PhotoVO> photos = movie.getPhotos();
				for (PhotoVO photo : photos) {
					photo.setMovieNo(movieNo);
				}
				photoDAO.insertPhotoList(conn, photos);
			}

<<<<<<< HEAD
			// 3. �⿬�� ���
			if (movie.getActors().size() != 0) {
				ActorDAO actorDAO = ActorDAO.getInstance();
				for (ActorVO actor : movie.getActors()) {
					actor.setMovieNo(movieNo);
				}
=======
            for (ActorVO actor : movie.getActors()) {
               int actorNo = actorDAO.insertActorList(conn, actor, 0);
               System.out.println("actorNo : " + actorNo);
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

				for (ActorVO actor : movie.getActors()) {
					int actorNo = actorDAO.insertActorList(conn, actor, 0);
					System.out.println("actorNo : " + actorNo);

<<<<<<< HEAD
					// �⿬�� ���� ���
					if (actor.getActorPhoto() != null) {
						System.out.println("call insertActorPhoto");
						ActorPhotoVO actorPhoto = actor.getActorPhoto();
						actorPhoto.setActorNo(actorNo);
						ActorPhotoDAO actorPhotoDAO = ActorPhotoDAO.getInstance();
						actorPhotoDAO.insertActorPhoto(conn, actorPhoto);
					}
				}
			}
=======
         // ��ȭ �帣 ���
         if (movie.getMovieGenres().size() != 0) {
            GenreDAO genreDAO = GenreDAO.getInstance();
            List<MovieGenreVO> genres = movie.getMovieGenres();
            for (MovieGenreVO genre : movie.getMovieGenres()) {
               genre.setMovieNo(movieNo);
            }
            genreDAO.insertGenreList(conn, genres);
         }
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

<<<<<<< HEAD
			// ��ȭ �帣 ���
			if (movie.getMovieGenres().size() != 0) {
				GenreDAO genreDAO = GenreDAO.getInstance();
				List<MovieGenreVO> genres = movie.getMovieGenres();
				for (MovieGenreVO genre : movie.getMovieGenres()) {
					genre.setMovieNo(movieNo);
				}
				genreDAO.insertGenreList(conn, genres);
			}

			conn.commit();
=======
         conn.commit();
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

		} catch (Exception ex) {
			conn.rollback();
			throw ex;
		} finally {
			if (conn != null)
				conn.close();
		}

<<<<<<< HEAD
	}

	// ��ȭ ������ �ϰ� �����Ѵ�.
	public void deleteMovieList(int[] noList) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
=======
   }
    
   // ��ȭ ������ �ϰ� �����Ѵ�.
   public void deleteMovieList(int[] noList) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

<<<<<<< HEAD
			// tx.begin
			conn.setAutoCommit(false);
			
			// ��ȭ ����
			MovieDAO articleDao = MovieDAO.getInstance();
			articleDao.removeMovieList(conn, noList);
=======
         // tx.begin
         conn.setAutoCommit(false);
         
         //��ȭ ����     
         MovieDAO articleDao = MovieDAO.getInstance();
         articleDao.removeMovieList(conn, noList);
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

			conn.commit();

<<<<<<< HEAD
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}
=======
      } catch (Exception e) {
         conn.rollback();
         throw e;
      } finally {
         if (conn != null)
            conn.close();
      }
   }
   // ��ȭ������ �����Ѵ�
   public void deleteMoviePhoto(int moviePhotoNo) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

<<<<<<< HEAD
	// ��ȭ������ �����Ѵ�
	public void deleteMoviePhoto(int moviePhotoNo) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
=======
         // tx.begin
         conn.setAutoCommit(false);

         // ���� ����
         PhotoDAO photoDao = PhotoDAO.getInstance();
         photoDao.removePhoto(conn, moviePhotoNo);

         conn.commit();

      } catch (Exception e) {
         conn.rollback();
         throw e;
      } finally {
         if (conn != null)
            conn.close();
      }
   }
   // �⿬�� ������ �����Ѵ�
   public void deleteActorList(int movieNo) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();

         // tx.begin
         conn.setAutoCommit(false);

         // �⿬�� ���� ����
         ActorDAO actorDao = ActorDAO.getInstance();
         actorDao.removeActor(conn, movieNo);

         conn.commit();

      } catch (Exception e) {
         conn.rollback();
         throw e;
      } finally {
         if (conn != null)
            conn.close();
      }
   }
   
   // ��ȭ ������ �����ϴ�.
   public void updateMovie(DetailMovieVO movie) throws Exception {
      Connection conn = null;
      try {
         conn = DBConn.getConnection();
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

<<<<<<< HEAD
			// tx.begin
			conn.setAutoCommit(false);
=======
         // tx.begin(Ʈ������ ����)
         conn.setAutoCommit(false);
         
         ActorDAO actorDao = ActorDAO.getInstance();
         actorDao.removeActor(conn, movie.getMovieNo());

         // ��ȭ ����
         MovieDAO moiveDAO = MovieDAO.getInstance();
         moiveDAO.modifyMovieList(conn, movie);

         // �⿬�� ���
         if (movie.getActors().size() != 0) {
            ActorDAO actorDAO = ActorDAO.getInstance();
            for (ActorVO actor : movie.getActors()) {
               actor.setMovieNo(movie.getMovieNo());
            }

            for (ActorVO actor : movie.getActors()) {
               int actorNo = actorDAO.insertActorList(conn, actor, movie.getMovieNo());
            
               // �⿬�� ���� ���
               if (actor.getActorPhoto() != null) {
                  System.out.println("call insertActorPhoto");
                  ActorPhotoVO actorPhoto = actor.getActorPhoto();
                  actorPhoto.setActorNo(actorNo);
                  ActorPhotoDAO actorPhotoDAO = ActorPhotoDAO.getInstance();
                  actorPhotoDAO.insertActorPhoto(conn, actorPhoto);
               }
            }
         }

         conn.commit();
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

<<<<<<< HEAD
			// ���� ����
			PhotoDAO photoDao = PhotoDAO.getInstance();
			photoDao.removePhoto(conn, moviePhotoNo);
=======
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
	public List<MovieVO> findMovieList(String keyfield, String keyword, int startRow, int endRow) throws Exception {
		MovieDAO dao = MovieDAO.getInstance();
		return dao.selectMovieList(keyfield, keyword, startRow, endRow);
	}
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git

<<<<<<< HEAD
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// �⿬�� ������ �����Ѵ�
	public void deleteActorList(int movieNo) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// tx.begin
			conn.setAutoCommit(false);

			// �⿬�� ���� ����
			ActorDAO actorDao = ActorDAO.getInstance();
			actorDao.removeActor(conn, movieNo);

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// ��ȭ ������ �����ϴ�.
	public void updateMovie(DetailMovieVO movie) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// tx.begin(Ʈ������ ����)
			conn.setAutoCommit(false);
			
			ActorDAO actorDao = ActorDAO.getInstance();
			actorDao.removeActor(conn, movie.getMovieNo());

			// ��ȭ ����
			MovieDAO moiveDAO = MovieDAO.getInstance();
			moiveDAO.modifyMovieList(conn, movie);

			// �⿬�� ���
			if (movie.getActors().size() != 0) {
				ActorDAO actorDAO = ActorDAO.getInstance();
				for (ActorVO actor : movie.getActors()) {
					actor.setMovieNo(movie.getMovieNo());
				}

				for (ActorVO actor : movie.getActors()) {
					int actorNo = actorDAO.insertActorList(conn, actor, movie.getMovieNo());
				
					// �⿬�� ���� ���
					if (actor.getActorPhoto() != null) {
						System.out.println("call insertActorPhoto");
						ActorPhotoVO actorPhoto = actor.getActorPhoto();
						actorPhoto.setActorNo(actorNo);
						ActorPhotoDAO actorPhotoDAO = ActorPhotoDAO.getInstance();
						actorPhotoDAO.insertActorPhoto(conn, actorPhoto);
					}
				}
			}

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
	  public List<MovieVO> findMovieList(String keyfield, String keyword, int startRow, int endRow) throws Exception { 
	      MovieDAO dao = MovieDAO.getInstance();
	      return dao.selectMovieList(keyfield, keyword, startRow, endRow);  
	   }

	// ��ȭ �������� ��ȸ�ϴ�.
	public DetailMovieVO retriveMovie(int movieNo) throws Exception {
		MovieDAO movieDao = MovieDAO.getInstance();
		DetailMovieVO detailMovie = movieDao.selectMovie(movieNo);
		return detailMovie;
	}

	//��ȭ ����� ��ü ��ȸ�ϴ�.
=======
   // ��ȭ �������� ��ȸ�ϴ�.
   public DetailMovieVO retriveMovie(int movieNo) throws Exception {
      MovieDAO movieDao = MovieDAO.getInstance();
      DetailMovieVO detailMovie = movieDao.selectMovie(movieNo);
      return detailMovie;
   }
   
   //��ȭ ����� ��ü ��ȸ�ϴ�.
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git
    public List<MovieVO> retrieveMovieList(int startRow, int endRow) throws Exception {
       MovieDAO dao = MovieDAO.getInstance();
       return dao.selectMovieList(startRow, endRow);
    }
    
  //�� �Խñ� ���� ���ϴ�.
    public int retrieveMovieCount() throws Exception {
       MovieDAO dao = MovieDAO.getInstance();
       return dao.selectTotalPost();
    }
   
   //�˻��� ���� �� �Խñ� ���� ���ϴ�.
    public int retrieveMovieCount(String keyfield, String keyword) throws Exception {
       MovieDAO dao = MovieDAO.getInstance();
       return dao.selectTotalPost(keyfield, keyword);
    }
<<<<<<< HEAD
   //�󿵻��¿� ���� ��ȭ�� ��ȸ�ϴ�.
=======
	//�󿵻��¿� ���� ��ȭ�� ��ȸ�ϴ�.
>>>>>>> branch 'master' of https://github.com/whsanha55/oneMovieWepApp.git
    public List<MovieVO> retrieveStateMovieList(String keyfield) throws Exception {
       MovieDAO dao = MovieDAO.getInstance();
       return dao.selectStateMovieList(keyfield);
    }
}