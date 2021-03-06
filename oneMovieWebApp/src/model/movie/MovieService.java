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

	// 영화 정보를 등록하다.
	public void addMovie(MovieVO movie) throws Exception {

		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// tx.begin(트랜젝션 시작)
			conn.setAutoCommit(false);

			// 1. 영화 등록
			MovieDAO moiveDAO = MovieDAO.getInstance();
			int movieNo = moiveDAO.insertMovie(conn, movie);
			System.out.println("movieNo :" + movieNo);

			// 2. 영화 사진 등록
			if (movie.getPhotos().size() != 0) {
				PhotoDAO photoDAO = PhotoDAO.getInstance();
				List<PhotoVO> photos = movie.getPhotos();
				for (PhotoVO photo : photos) {
					photo.setMovieNo(movieNo);
				}
				photoDAO.insertPhotoList(conn, photos);
			}

			// 3. 출연진 등록
			if (movie.getActors().size() != 0) {
				ActorDAO actorDAO = ActorDAO.getInstance();
				for (ActorVO actor : movie.getActors()) {
					actor.setMovieNo(movieNo);
				}

				for (ActorVO actor : movie.getActors()) {
					int actorNo = actorDAO.insertActorList(conn, actor, 0);
					System.out.println("actorNo : " + actorNo);

					// 출연진 사진 등록
					if (actor.getActorPhoto() != null) {
						System.out.println("call insertActorPhoto");
						ActorPhotoVO actorPhoto = actor.getActorPhoto();
						actorPhoto.setActorNo(actorNo);
						ActorPhotoDAO actorPhotoDAO = ActorPhotoDAO.getInstance();
						actorPhotoDAO.insertActorPhoto(conn, actorPhoto);
					}
				}
			}

			// 영화 장르 등록
			if (movie.getMovieGenres().size() != 0) {
				GenreDAO genreDAO = GenreDAO.getInstance();
				List<MovieGenreVO> genres = movie.getMovieGenres();
				for (MovieGenreVO genre : movie.getMovieGenres()) {
					genre.setMovieNo(movieNo);
				}
				genreDAO.insertGenreList(conn, genres);
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

	// 영화 정보를 일괄 삭제한다.
	public void deleteMovieList(int[] noList) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// tx.begin
			conn.setAutoCommit(false);

			// 영화 삭제
			MovieDAO articleDao = MovieDAO.getInstance();
			articleDao.removeMovieList(conn, noList);

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	// 영화사진을 삭제한다
	public void deleteMoviePhoto(int moviePhotoNo) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// tx.begin
			conn.setAutoCommit(false);

			// 사진 삭제
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

	// 출연진 정보를 삭제한다
	public void deleteActorList(int movieNo) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// tx.begin
			conn.setAutoCommit(false);

			// 출연진 사진 삭제
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

	// 영화 정보를 수정하다.
	   public void updateMovie(DetailMovieVO movie) throws Exception {
	      Connection conn = null;
	      try {
	         conn = DBConn.getConnection();

	         // tx.begin(트랜젝션 시작)
	         conn.setAutoCommit(false);
	         
	         ActorDAO actorDao = ActorDAO.getInstance();
	         actorDao.removeActor(conn, movie.getMovieNo());

	         // 영화 수정
	         MovieDAO moiveDAO = MovieDAO.getInstance();
	         moiveDAO.modifyMovieList(conn, movie);

	         // 출연진 등록
	         if (movie.getActors().size() != 0) {
	            ActorDAO actorDAO = ActorDAO.getInstance();
	            for (ActorVO actor : movie.getActors()) {
	               actor.setMovieNo(movie.getMovieNo());
	            }

	            for (ActorVO actor : movie.getActors()) {
	               int actorNo = actorDAO.insertActorList(conn, actor, movie.getMovieNo());
	            
	               // 출연진 사진 등록
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

	// 영화 제목을 조회하다.
	public List<MovieTitleVO> retriveMovieTitle(String movieTitle) throws Exception {
		MovieDAO movieDAO = MovieDAO.getInstance();
		return movieDAO.selectMovieTitleList(movieTitle);
	}

	// 영화 목록을 조회하다.
	public List<MovieVO> findMovieList(String keyfield, String keyword, int startRow, int endRow) throws Exception {
		MovieDAO dao = MovieDAO.getInstance();
		return dao.selectMovieList(keyfield, keyword, startRow, endRow);
	}

	// 영화 상세정보를 조회하다.
	public DetailMovieVO retriveMovie(int movieNo) throws Exception {
		MovieDAO movieDao = MovieDAO.getInstance();
		DetailMovieVO detailMovie = movieDao.selectMovie(movieNo);
		return detailMovie;
	}

	// 영화 목록을 전체 조회하다.
	public List<MovieVO> retrieveMovieList(int startRow, int endRow) throws Exception {
		MovieDAO dao = MovieDAO.getInstance();
		return dao.selectMovieList(startRow, endRow);
	}

	// 총 게시글 수를 구하다.
	public int retrieveMovieCount() throws Exception {
		MovieDAO dao = MovieDAO.getInstance();
		return dao.selectTotalPost();
	}

	// 검색에 따른 총 게시글 수를 구하다.
	public int retrieveMovieCount(String keyfield, String keyword) throws Exception {
		MovieDAO dao = MovieDAO.getInstance();
		return dao.selectTotalPost(keyfield, keyword);
	}

	// 상영상태에 따른 영화를 조회하다.
	public List<MovieVO> retrieveStateMovieList(String keyfield) throws Exception {
		MovieDAO dao = MovieDAO.getInstance();
		return dao.selectStateMovieList(keyfield);
	}
}