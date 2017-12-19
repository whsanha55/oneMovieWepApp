package model.movie;

import java.sql.Connection;

import conn.DBConn;
import domain.movie.ActorVO;
import domain.movie.MovieVO;

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

			MovieDAO moiveDAO = MovieDAO.getInstance();
			int moiveNo = moiveDAO.insertMovie(movie);

			if (movie.getActors().size() != 0) {
				ActorDAO actorDAO = ActorDAO.getInstance();
				for (ActorVO actor : movie.getActors()) {
					//actor.setArticleNo(articleNo);
					//articleFileDAO.insertArticleFile(conn, articleFile);//conn이 같아야함
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
		
}
