package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.GradeVO;
import domain.movie.MovieTitleVO;
import domain.movie.MovieVO;
import domain.movie.NationVO;

public class MovieDAO {
	private static MovieDAO instance = new MovieDAO();
	
	private MovieDAO() {

	}

	public static MovieDAO getInstance() {
		return instance;
	}
	
	//영화 정보를 등록하다.
	public int insertArticle(MovieVO movie) throws Exception {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		Statement stmt = null;
		int movieNo = 0;
		
		try {			
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into movie(movie_no, movie_title, runningtime, director, grade_no, nation_no)     ");
			sql.append("values(movie_no_seq.nextval, ?, ?, ?,                 										         ");
			sql.append("(select grade_no                          																 ");
			sql.append("from grade"																								 );
			sql.append("where grade_age=?),  																					");
			sql.append("(select nation_no         															                    ");
			sql.append("from nation"																		 						 );
			sql.append("where nation_name=?)); 																				 ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, movie.getMovieTitle());
			pstmt.setInt(2, movie.getRunningTime());
			pstmt.setString(3, movie.getDirector());
			pstmt.setInt(4, movie.getGradeNo());
			pstmt.setInt(5, movie.getNationNo());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			stmt = conn.createStatement();
			
			sql.delete(0, sql.length());			
			sql.append("select article_seq.currval from dual");
			
			ResultSet rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				movieNo = rs.getInt(1);
			}
			
		} finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();		
		}			
		return movieNo;
	}
	
	//제한등급을 오름차순으로 정렬하여 조회한다.
	public List<GradeVO> selectGradeList() throws Exception {
		ArrayList<GradeVO> grades = new ArrayList<GradeVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("select grade_no, grade_age   								   ");
			sql.append("from grade 														   ");
			sql.append("order by no asc    												   ");

			System.out.println(sql.toString());

			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				GradeVO grade = new GradeVO();
				grade.setGradeNo(rs.getInt(1));
				grade.setGradeAge(rs.getString(2));
				grades.add(grade);
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return grades;	
	}
	
	//국가명을 오름차순으로 정렬하여 조회한다.
	public List<NationVO> selectNationList() throws Exception {
		ArrayList<NationVO> nations = new ArrayList<NationVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			
			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append("select nation_no, nation_name   								   ");
			sql.append("from nation  														   ");
			sql.append("order by no asc    												   ");

			System.out.println(sql.toString());

			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				NationVO nation = new NationVO();
				nation.setNationNo(rs.getInt(1));
				nation.setNationName(rs.getString(2));
				nations.add(nation);
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return nations;	
	}
	
	//영화 제목을 조회하다.
	public List<MovieTitleVO> selectMovieTitleList(String movieTitle) throws Exception {
		ArrayList<MovieTitleVO> titles = new ArrayList<MovieTitleVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select nation_no, nation_name   								   ");
			sql.append("from nation  														   ");
			sql.append("where movie_title = ?											   ");
			sql.append("order by no asc    												   ");	
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, movieTitle);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MovieTitleVO title = new MovieTitleVO();
				title.setMovieNo(rs.getInt(1));
				title.setMovieTitle(rs.getString(2));
				titles.add(title);
			}			
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)  pstmt.close();
			if (conn != null) conn.close();
		}
		return titles;	
	}
	
	//영화 정보를 수정하다.
	public void modifyMovieList(MovieVO movie) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {	
			StringBuffer sql = new StringBuffer();
			sql.append("update movie m																  ");
			sql.append("set movieTitle=?, runningTime=?, director=?, story=?, videoUrl=?, ");
			sql.append("grade_no=(select grade_no 												 ");
			sql.append("from grade g																 ");
			sql.append("where g.grade_age = ?), 													 ");
			sql.append("nation_no=(select nation_no  											");
			sql.append("from nation n																");
			sql.append("where n.nation_name=?)	  												");
			sql.append("where movie_no=? 															");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1,  movie.getMovieTitle());
			pstmt.setInt(2,  movie.getRunningTime());
			pstmt.setString(3,  movie.getDirector());
			pstmt.setString(4,  movie.getStory());
			pstmt.setString(5,  movie.getVideoUrl());
			pstmt.setInt(6,  movie.getGradeNo());
			pstmt.setInt(7,  movie.getNationNo());
			pstmt.setInt(8,  movie.getMovieNo());
			
			pstmt.executeUpdate();
			
		} finally {
			if(pstmt != null) pstmt.close();
		}
	}
	// 검색 조건에 해당하는 영화목록을 오름차순으로 조회한다.
   public List<MovieVO> selectMovieList(String keyfield, String keyword, int startRow, int endRow) throws Exception {
      ArrayList<MovieVO> movies = new ArrayList<MovieVO>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         conn = DBConn.getConnection();

         StringBuffer sql = new StringBuffer();
         sql.append("select movieNo, movieTitle, director,  runningTime, gradeNo, nationNo");      //genreNo, actorNo,
         sql.append("from (select rownum as rn, movie1.*                          ");
         sql.append("      from (select *                                         ");
         sql.append("            from movie                                     ");
         sql.append("            order by no desc) movie1 )                         ");

         if (keyfield.equals("all")) {
            sql.append("where   ");
         } else if (keyfield.equals("MovieTitle")) {
            sql.append("where MovieTitle like '%' || ? ||  '%'  ");
         } else if (keyfield.equals("Director")) {
            sql.append("where MovieTitle like '%' || ? ||  '%'  ");
         }

         sql.append("and rn >= ? and rn <= ?                                             ");
         pstmt = conn.prepareStatement(sql.toString());

         pstmt.setString(1, keyfield);
         pstmt.setString(2, keyword);
         pstmt.setInt(3, startRow);
         pstmt.setInt(4, endRow);

         rs = pstmt.executeQuery();

         while (rs.next()) {
            MovieVO movie = new MovieVO();
            /*GenreVO genre = new GenreVO();
            ActorVO actor = new ActorVO();*/
            movie.setMovieNo(rs.getInt(1));
            movie.setMovieTitle(rs.getString(2));
            /*genre.setGenreNo(rs.getInt(3));
            actor.setActorNo(rs.getInt(4));*/
            movie.setDirector(rs.getString(3));
            movie.setRunningTime(rs.getInt(4));
            movie.setGradeNo(rs.getInt(5));
            movie.setNationNo(rs.getInt(6));
            movies.add(movie);
         }

      } finally {
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      }
      return movies;
   }
}
