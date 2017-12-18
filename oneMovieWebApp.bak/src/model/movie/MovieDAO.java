package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import domain.movie.MovieVO;

public class MovieDAO {
	private static MovieDAO instance = new MovieDAO();
	
	private MovieDAO() {

	}

	public static MovieDAO getInstance() {
		return instance;
	}
	
	public int insertArticle(Connection conn, MovieVO movie) throws Exception {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		int movieNo = 0;
		try {			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into movie(movie_no, movie_title, runningtime, director, grade_no, nation_no)     ");
			sql.append("values(movie_no_seq.nextval, ?, ?, ?, ?, ?)                           ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, movie.getMovieTitle());
			pstmt.setString(2, movie.getRunningTime());
			pstmt.setString(3, movie.getDirector());
			pstmt.setInt(4, movie.getGradeNo());
			pstmt.setInt(5, movie.getNationNo());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			
			stmt = conn.createStatement();
			
			//sql.delete(0, sql.length());			
			//sql.append("select article_seq.currval from dual");
			
			ResultSet rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				movieNo = rs.getInt(1);
			}
			
		} finally {
			if(stmt != null) stmt.close();			
		}			
		return movieNo;
	}
}
