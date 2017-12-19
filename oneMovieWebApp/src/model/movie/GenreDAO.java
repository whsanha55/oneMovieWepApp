package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.GenreVO;
import domain.movie.MovieGenreVO;

public class GenreDAO {

	private static GenreDAO instance = new GenreDAO();

	private GenreDAO() {

	}

	public static GenreDAO getInstance() {
		return instance;
	}

	// 영화 장르를 등록한다.
	public void insertGenreList(Connection conn, MovieGenreVO genre) throws Exception {
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("insert into movie_genre(movieGenreNo, genreNo, movieNo) ");
			sql.append("values(movie_genre_no_seq.nextval, ?, ?)                           ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, genre.getGenreNo());
			pstmt.setInt(2, genre.getMovieNo());

			pstmt.executeUpdate();
			pstmt.close();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}
	

	// 영화장르 테이블의 정보를 가져온다.
	public List<MovieGenreVO> selectMovieGenreList() throws Exception {
		ArrayList<MovieGenreVO> movieGenres = new ArrayList<MovieGenreVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			System.out.println(conn);
			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("select movieGenreNo, genreNo, movieNo												   ");
			sql.append("from movie_genre 														   ");
			sql.append("order by movieGenreNo asc    												   ");

			System.out.println(sql.toString());

			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				MovieGenreVO movieGenre = new MovieGenreVO();
				movieGenre.setMovieGenreNo(rs.getInt(1));
				movieGenre.setGenreNo(rs.getInt(2));
				movieGenre.setMovieNo(rs.getInt(3));
				movieGenres.add(movieGenre);
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return movieGenres;
	}

	// 장르명을 가나다순으로 오름차순 정렬하여 조회한다.
	public List<GenreVO> selectGenreList() throws Exception {
		ArrayList<GenreVO> genres = new ArrayList<GenreVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			System.out.println(conn);
			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("select genreNo, genreName												   ");
			sql.append("from genre 														   ");
			sql.append("order by genreName asc    												   ");

			System.out.println(sql.toString());

			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				GenreVO genre = new GenreVO();
				genre.setGenreNo(rs.getInt(1));
				genre.setGenreName(rs.getString(2));
				genres.add(genre);
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return genres;
	}

}
