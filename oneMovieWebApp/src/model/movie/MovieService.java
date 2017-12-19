package model.movie;

import java.sql.Connection;
import java.util.List;

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
	
	//��ȭ ������ ����ϴ�.
	public void addMovie(MovieVO movie) throws Exception {
		Connection conn = null;
		try {
			conn = DBConn.getConnection();

			// tx.begin(Ʈ������ ����)
			conn.setAutoCommit(false);

			MovieDAO moiveDAO = MovieDAO.getInstance();
			int moiveNo = moiveDAO.insertArticle(conn, movie);

			if (movie.getActors().size() != 0) {
				ActorDAO actorDAO = ActorDAO.getInstance();
				for (ActorVO actor : movie.getActors()) {
					actor.setMovieNo(moiveNo);
					//ActorDAO.insertActorList(conn, actors);//conn�� ���ƾ���
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
