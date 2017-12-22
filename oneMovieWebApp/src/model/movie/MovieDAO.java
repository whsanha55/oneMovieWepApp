 package model.movie;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.ActorPhotoVO;
import domain.movie.ActorVO;
import domain.movie.DetailMovieVO;
import domain.movie.GradeVO;
import domain.movie.MovieTitleVO;
import domain.movie.MovieVO;
import domain.movie.NationVO;
import domain.movie.PhotoVO;
import domain.movie.RoleVO;

public class MovieDAO {
   private static MovieDAO instance = new MovieDAO();

   private MovieDAO() {

   }

   public static MovieDAO getInstance() {
      return instance;
   }

   // 영화 정보를 등록하다.
   public int insertMovie(Connection conn, MovieVO movie) throws Exception {
      PreparedStatement pstmt = null;
      Statement stmt = null;
      int movieNo = 0;
      try {
         
         StringBuffer sql = new StringBuffer();
         sql.append(
               "insert into movie(movie_no, movie_title, running_time, director, grade_no, nation_no)            ");
         sql.append(
               "values(10 + Movie_no_seq.nextval, ?, ?, ?, ?, ?)                                                ");
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
         sql.append("select movie_no_seq.currval from dual");

         ResultSet rs = stmt.executeQuery(sql.toString());
         if (rs.next()) {
            movieNo = rs.getInt(1);
         }

      } finally {
         if (stmt != null) stmt.close();
      }
      return movieNo;
   }


   // 제한등급을 오름차순으로 정렬하여 조회한다.
   public List<GradeVO> selectGradeList() throws Exception {
      ArrayList<GradeVO> grades = new ArrayList<GradeVO>();
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
         conn = DBConn.getConnection();

         stmt = conn.createStatement();

         StringBuffer sql = new StringBuffer();
         sql.append("select grade_no, grade_age                              ");
         sql.append("from grade                                              ");
         sql.append("order by no asc                                           ");

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

   // 국가명을 오름차순으로 정렬하여 조회한다.
   public List<NationVO> selectNationList() throws Exception {
      ArrayList<NationVO> nations = new ArrayList<NationVO>();
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
         conn = DBConn.getConnection();

         stmt = conn.createStatement();

         StringBuffer sql = new StringBuffer();
         sql.append("select nation_no, nation_name                              ");
         sql.append("from nation                                               ");
         sql.append("order by no asc                                           ");

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

   // 영화 제목을 조회하다.
   public List<MovieTitleVO> selectMovieTitleList(String movieTitle) throws Exception {
      ArrayList<MovieTitleVO> titles = new ArrayList<MovieTitleVO>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         conn = DBConn.getConnection();

         StringBuffer sql = new StringBuffer();
         sql.append("select nation_no, nation_name                              ");
         sql.append("from nation                                               ");
         sql.append("where movie_title = ?                                    ");
         sql.append("order by no asc                                           ");
         pstmt = conn.prepareStatement(sql.toString());

         pstmt.setString(1, movieTitle);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            MovieTitleVO title = new MovieTitleVO();
            title.setMovieNo(rs.getInt(1));
            title.setMovieTitle(rs.getString(2));
            titles.add(title);
         }
         pstmt.executeUpdate();

      } finally {
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      }
      return titles;
   }

   // 영화 정보를 수정하다.
   public void modifyMovieList(Connection conn, MovieVO movie) throws Exception {
      PreparedStatement pstmt = null;
      try {
         StringBuffer sql = new StringBuffer();
         sql.append("update movie m                                                  ");
         sql.append("set movieTitle=?, runningTime=?, director=?, story=?, videoUrl=?, ");
         sql.append("grade_no=(select grade_no                                      ");
         sql.append("from grade g                                                 ");
         sql.append("where g.grade_age = ?),                                         ");
         sql.append("nation_no=(select nation_no                                   ");
         sql.append("from nation n                                                ");
         sql.append("where n.nation_name=?)                                         ");
         sql.append("where movie_no=?                                              ");

         pstmt = conn.prepareStatement(sql.toString());

         pstmt.setString(1, movie.getMovieTitle());
         pstmt.setInt(2, movie.getRunningTime());
         pstmt.setString(3, movie.getDirector());
         pstmt.setString(4, movie.getStory());
         pstmt.setString(5, movie.getVideoUrl());
         pstmt.setInt(6, movie.getGradeNo());
         pstmt.setInt(7, movie.getNationNo());
         pstmt.setInt(8, movie.getMovieNo());

         pstmt.executeUpdate();

      } finally {
         if (pstmt != null)
            pstmt.close();
      }
   }

   // 검색 조건에 해당하는 영화목록을 오름차순으로 조회한다.
  // public List<MovieVO> selectMovieList(String keyfield, String keyword, int startRow, int endRow) throws Exception {
   public List<MovieVO> selectMovieList(String keyfield, String keyword) throws Exception {
      ArrayList<MovieVO> movies = new ArrayList<MovieVO>();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         conn = DBConn.getConnection();

         StringBuffer sql = new StringBuffer();
         sql.append( "select  m1.movie_no, m1.movie_title, m1.director, m1.running_time, g.grade_age, n.nation_name     ");
         sql.append("from movie m1, grade g, nation n	                        														 ");
         sql.append(" where g.grade_no = m1.grade_no and n.nation_no = m1.nation_no                                        ");

         if (keyfield.equals("MovieTitle")) {
            sql.append("and m1.movie_title like '%' || ? ||  '%'  																			");
         } else if (keyfield.equals("Director")) {
            sql.append("and m1.director like '%' || ? ||  '%' 																				 ");
         }
         sql.append("order by 1                                   																		    ");
         
         //sql.append("and rn >= ? and rn <= ?                                             ");
         //sql.append("order by 1;                                             ");
     	pstmt = conn.prepareStatement(sql.toString());

		pstmt.setString(1, keyword);

		rs = pstmt.executeQuery();
         while (rs.next()) {   
           MovieVO movie = new MovieVO();
           GradeVO grade = new GradeVO();
           NationVO nation = new NationVO();
            
           movie.setMovieNo(rs.getInt(1));
           movie.setMovieTitle(rs.getString(2));
           movie.setDirector(rs.getString(3));
           movie.setRunningTime(rs.getInt(4));
           
           grade.setGradeAge(rs.getString(5));
           movie.setGrade(grade);
           
           nation.setNationName(rs.getString(6));
           movie.setNation(nation);
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

   // 영화의 상세정보를 조회한다.
   public DetailMovieVO selectMovie(int movieNo) throws Exception {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      DetailMovieVO detailMovie = new DetailMovieVO();
      try {
         conn = DBConn.getConnection();

         StringBuffer sql = new StringBuffer();
            sql.append("select mp.movie_photo_original_file_name, m1.movie_no,m1.movie_title, m1.director, m1.running_time, g.grade_age,n.nation_name,m1.story, ac.actor_original_file_name, r.role_name, m2.actor_name, m2.character_name 	 							 ");
			sql.append("from movie m1, actor m2 , role r, grade g, nation n, actor_photo ac, movie_photo mp 	 	     										");
			sql.append("where m1.movie_no = m2.movie_no(+) and m2.role_no = r.role_no	and g.grade_no = m1.grade_no and n.nation_no = m1.nation_no and m2.actor_no = ac.actor_no and mp.movie_no =  m1.movie_no						 										");
			sql.append("and m1.movie_no = ?   ");
         pstmt = conn.prepareStatement(sql.toString());

         System.out.println(sql.toString());

         pstmt.setInt(1, movieNo);

         rs = pstmt.executeQuery();

         int count = 1;
         while (rs.next()) {
            if (count == 1) {   
               PhotoVO photo = new PhotoVO();
               GradeVO grade = new GradeVO();
               NationVO nation = new NationVO();
               
               photo.setMoviePhotoOriginalFileName(rs.getString(1));
               detailMovie.setPhoto(photo);
               
               detailMovie.setMovieNo(rs.getInt(2));
               detailMovie.setMovieTitle(rs.getString(3));
               detailMovie.setDirector(rs.getString(4));
               detailMovie.setRunningTime(rs.getInt(5));
               
               grade.setGradeAge(rs.getString(6));
               detailMovie.setGrade(grade);
               
               nation.setNationName(rs.getString(7));
               detailMovie.setNation(nation);
               
               detailMovie.setStory(rs.getString(8));
              
            }
            
            if(rs.getString(11) != null) {
            	  ActorVO actor = new ActorVO();
            	  ActorPhotoVO actorPhoto = new ActorPhotoVO();
            	  RoleVO role = new RoleVO();
            	  
            	  actorPhoto.setActorOriginalFileName(rs.getString(9));
            	  actor.setActorPhoto(actorPhoto);
            	  
            	  role.setRoleName(rs.getString(10));
            	  actor.setRole(role);
            	  
                  actor.setActorName(rs.getString(11));
                  actor.setCharacterName(rs.getString(12));
                  detailMovie.addActor(actor);
            }
            count++;
         }
         
      } finally {
         if (pstmt != null)
            pstmt.close();
         if (conn != null)
            conn.close();
      }
      return detailMovie;
   }
 
   // 영화 정보를 일괄 삭제하다.
   public void removeMovieList(Connection connn, int[] noList) throws Exception {
      PreparedStatement pstmt = null;
      
      try {
    		StringBuffer sql = new StringBuffer();
			sql.append("delete from movie                     ");
			sql.append("where movie_no = ?                    ");
			pstmt = connn.prepareStatement(sql.toString());

			for (int i = 0; i < noList.length; i++) {
	            pstmt.setInt(1, noList[i]);
	            System.out.println("번호>>>>>>>>" + noList[i]);
	            pstmt.addBatch();
	         }
			
			pstmt.executeBatch();

      } finally {
    	  if (pstmt != null) pstmt.close();
      }
   }
   
   //영화 목록을 전체 조회하다.
    public List<MovieVO> selectMovieList() throws Exception {
          ArrayList<MovieVO> movies = new ArrayList<MovieVO>();
          Connection conn = null;
          Statement stmt = null;
          ResultSet rs = null;
          try {
             conn = DBConn.getConnection();

             System.out.println(conn);
             stmt = conn.createStatement();

             StringBuffer sql = new StringBuffer();
             sql.append("select movie_no, movie_title, running_time, director, grade_age, nation_name 			  ");
             sql.append("from movie m, grade g, nation n                                            						  ");
             sql.append("where g.grade_no = m.grade_no and n.nation_no = m.nation_no                            ");
             sql.append("order by 1                                   														    ");
             System.out.println(sql.toString());

             rs = stmt.executeQuery(sql.toString());

             while (rs.next()) {
                MovieVO movie = new MovieVO();
                GradeVO grade = new GradeVO();
                NationVO nation = new NationVO();
                movie.setMovieNo(rs.getInt(1));
                movie.setMovieTitle(rs.getString(2));
                movie.setRunningTime(rs.getInt(3));
                movie.setDirector(rs.getString(4));
                
                grade.setGradeAge(rs.getString(5));
                movie.setGrade(grade);
                
                nation.setNationName(rs.getString(6));
                movie.setNation(nation);
                
                movies.add(movie);
             }

          } finally {
             if (stmt != null)
                stmt.close();
             if (conn != null)
                conn.close();
          }
          return movies;
       }
   
}