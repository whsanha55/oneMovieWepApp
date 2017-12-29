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
import domain.movie.GenreVO;
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

   //영화 정보를 등록하다.
   public int insertMovie(Connection conn, MovieVO movie) throws Exception {
      PreparedStatement pstmt = null;
      Statement stmt = null;
      int movieNo = 0;
      try {
         
         StringBuffer sql = new StringBuffer();
         sql.append("insert into movie(movie_no, movie_title, running_time, director, grade_no, nation_no)            ");
         sql.append("values(10 + Movie_no_seq.nextval, ?, ?, ?, ?, ?)                                              				  ");
         pstmt = conn.prepareStatement(sql.toString());

         pstmt.setString(1, movie.getMovieTitle());
         pstmt.setInt(2, movie.getRunningTime());
         pstmt.setString(3, movie.getDirector());
         pstmt.setInt(4, movie.getGradeNo());
         pstmt.setInt(5, movie.getNationNo());

         pstmt.executeUpdate();

         stmt = conn.createStatement();

         sql.delete(0, sql.length());
         sql.append("select movie_no_seq.currval from dual");

         ResultSet rs = stmt.executeQuery(sql.toString());
         if (rs.next()) {
            movieNo = rs.getInt(1);
         }

      } finally {
         if (stmt != null) stmt.close();
         if (pstmt != null) pstmt.close();
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
         sql.append("select grade_no, grade_age                   ");
         sql.append("from grade                                        ");
         sql.append("order by no asc                                  ");

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
   public void modifyMovieList(Connection conn, DetailMovieVO movie) throws Exception {
      PreparedStatement pstmt = null;
      try {
         StringBuffer sql = new StringBuffer();
         sql.append("update movie                                                     ");
         sql.append("set movie_Title = ?, running_Time = ?, director = ?, story = '',     ");
         sql.append("video_Url = '123',  grade_no = ?,  nation_no = ?					 ");
         sql.append("where movie_no = ?                                              ");

         pstmt = conn.prepareStatement(sql.toString());

         pstmt.setString(1, movie.getMovieTitle());
         pstmt.setInt(2, movie.getRunningTime());
         pstmt.setString(3, movie.getDirector());
         //pstmt.setString(4, movie.getStory());
         //pstmt.setString(5, movie.getVideoUrl());
         pstmt.setInt(4, movie.getGradeNo());
         pstmt.setInt(5, movie.getNationNo());
         pstmt.setInt(6, movie.getMovieNo());

         pstmt.executeUpdate();

      } finally {
         if (pstmt != null)
            pstmt.close();
      }
   }

 //영화 목록을 전체 조회하다.
   public List<MovieVO> selectMovieList(int startRow, int endRow) throws Exception {
         ArrayList<MovieVO> movies = new ArrayList<MovieVO>();
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try {
            conn = DBConn.getConnection();
            
            StringBuffer sql = new StringBuffer();  
            sql.append(" select photo, movie_no, movie_title, running_time, director, grade_age, nation_name       ");
            sql.append(" from( select rownum as rn, movie1.*                                                    ");
            sql.append("         from (select (select mp.movie_photo_original_file_name                          ");
            sql.append("                    from movie_photo mp                                                ");
            sql.append("                   where mp.movie_photo_original_file_name like '%' || 'main' ||  '%'       ");
            sql.append("                    and mp.movie_no = m.movie_no) as photo, m.movie_no, m.movie_title, m.running_time, m.director, g.grade_age, n.nation_name      ");
            sql.append("                from movie m, grade g, nation n                                               ");
            sql.append("                where g.grade_no = m.grade_no and n.nation_no = m.nation_no                   ");
            sql.append("               order by movie_title asc )movie1) movie2                                    ");
            sql.append("where rn>=? and rn<=?                                                                        ");   
          
         pstmt = conn.prepareStatement(sql.toString());
        
         pstmt.setInt(1,  startRow);
         pstmt.setInt(2,  endRow);
         
         rs = pstmt.executeQuery();
            while (rs.next()) {
              PhotoVO photo = new PhotoVO();
               MovieVO movie = new MovieVO();
               GradeVO grade = new GradeVO();
               NationVO nation = new NationVO();
               
               photo.setMoviePhotoOriginalFileName(rs.getString(1));
               movie.setPhoto(photo);
               
               movie.setMovieNo(rs.getInt(2));
               movie.setMovieTitle(rs.getString(3));
               movie.setRunningTime(rs.getInt(4));
               movie.setDirector(rs.getString(5));
               
               grade.setGradeAge(rs.getString(6));
               movie.setGrade(grade);
               
               nation.setNationName(rs.getString(7));
               movie.setNation(nation);
               
               movies.add(movie);
            }

         } finally {
            if(pstmt != null) pstmt.close();
            if (conn != null)
               conn.close();
         }
         return movies;
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
          sql.append( "select photo, movie_no, movie_title, running_time, director, grade_age, nation_name                       ");
          sql.append( " from( select rownum as rn, movie1.*                                                                     ");
          sql.append( "        from (select  (select mp.movie_photo_original_file_name                                               ");
          sql.append( "                     from movie_photo mp                                                                ");
          sql.append("                      where mp.movie_photo_original_file_name like '%' || 'main' ||  '%'                         ");
          sql.append("                       and mp.movie_no = m1.movie_no) as photo,                                                 ");
          sql.append("               m1.movie_no, m1.movie_title, m1.director, m1.running_time, g.grade_age, n.nation_name        ");
          sql.append("               from movie m1, grade g, nation n                                                                    ");
          sql.append("               where g.grade_no = m1.grade_no and n.nation_no = m1.nation_no                                    ");

          if (keyfield.equals("MovieTitle")) {
             sql.append("             and m1.movie_title like '%' || ? ||  '%'                                                                       ");
          } else if (keyfield.equals("Director")) {
             sql.append("            and m1.director like '%' || ? ||  '%'                                                                          ");
          }
          sql.append("              order by movie_no desc )movie1) movie2                                                                ");
          
          sql.append("where rn >= ? and rn <= ?                                             ");
          pstmt = conn.prepareStatement(sql.toString());

          pstmt.setString(1, keyword);
          pstmt.setInt(2,  startRow);
        pstmt.setInt(3,  endRow);
        
          rs = pstmt.executeQuery();
          
          while (rs.next()) {   
            PhotoVO photo = new PhotoVO(); 
            MovieVO movie = new MovieVO();
            GradeVO grade = new GradeVO();
            NationVO nation = new NationVO();
            
            photo.setMoviePhotoOriginalFileName(rs.getString(1));
            movie.setPhoto(photo);
            
            movie.setMovieNo(rs.getInt(2));
            movie.setMovieTitle(rs.getString(3));
            movie.setRunningTime(rs.getInt(4));
            movie.setDirector(rs.getString(5));
            
            grade.setGradeAge(rs.getString(6));
            movie.setGrade(grade);
            
            nation.setNationName(rs.getString(7));
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
         sql.append("select mp1.movie_photo_no, (select mp.movie_photo_original_file_name                         						  							  ");
         sql.append("from movie_photo mp                          																					   ");
         sql.append("where mp.movie_photo_original_file_name like '%' || 'detail' ||  '%'                            					  ");
         sql.append("and mp.movie_no = m.movie_no),  ");
         sql.append("m.movie_no, m.movie_title, m.director, m.running_time, g.grade_age, n.nation_name, m.story, m.video_url, gen.genre_name, ac.actor_original_file_name, r.role_name, a.actor_name, a.character_name, mp1.movie_photo_original_file_name                           ");
         sql.append("from movie m, genre gen, actor a , role r, grade g, nation n, actor_photo ac, movie_photo mp1, movie_genre mg                                   ");
         sql.append("where m.movie_no = a.movie_no(+) and a.role_no = r.role_no   and g.grade_no = m.grade_no and n.nation_no = m.nation_no and a.actor_no = ac.actor_no and mp1.movie_no =  m.movie_no and gen.genre_no = mg.genre_no and mg.movie_no = m.movie_no                                                             ");
         sql.append("and m.movie_no = ?  																													 ");
         pstmt = conn.prepareStatement(sql.toString());

         System.out.println(sql.toString());

         pstmt.setInt(1, movieNo);

         rs = pstmt.executeQuery();

         int count = 1;
         while (rs.next()) {
            PhotoVO photo = new PhotoVO();
            PhotoVO detailPhoto = new PhotoVO();
            if (count == 1) {   
               GradeVO grade = new GradeVO();
               NationVO nation = new NationVO();
               
               photo.setMoviePhotoNo(rs.getInt(1));
               detailMovie.setPhoto(photo);
               
               detailPhoto.setMoviePhotoOriginalFileName(rs.getString(2));
               detailMovie.setDetailPhoto(detailPhoto);
               
               detailMovie.setMovieNo(rs.getInt(3));
               detailMovie.setMovieTitle(rs.getString(4));
               
               detailMovie.setDirector(rs.getString(5));
               detailMovie.setRunningTime(rs.getInt(6));
               
               grade.setGradeAge(rs.getString(7));
               detailMovie.setGrade(grade);
               
               nation.setNationName(rs.getString(8));
               detailMovie.setNation(nation);
               
               detailMovie.setStory(rs.getString(9));
               detailMovie.setVideoUrl(rs.getString(10));
              
            }
             
            if(rs.getString(13) != null) {
                GenreVO genre = new GenreVO();
                 ActorVO actor = new ActorVO();
                 ActorPhotoVO actorPhoto = new ActorPhotoVO();
                 RoleVO role = new RoleVO();
          
                 genre.setGenreName(rs.getString(11));
                 detailMovie.addGenre(genre);
                 
                 actorPhoto.setActorOriginalFileName(rs.getString(12));
                 actor.setActorPhoto(actorPhoto);
                 
                 role.setRoleName(rs.getString(13));
                 actor.setRole(role);
                 
                  actor.setActorName(rs.getString(14));
                  actor.setCharacterName(rs.getString(15));
                  detailMovie.addActor(actor);
                  
                  photo.setMoviePhotoOriginalFileName(rs.getString(16));
                  detailMovie.addPhoto(photo);   
                  
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
             sql.append("select (select mp.movie_photo_original_file_name 																											");
             sql.append(" from movie_photo mp 																																	");
             sql.append(" where mp.MOVIE_PHOTO_ORIGINAL_FILE_NAME like '%' || 'main' ||  '%'  																		");
             sql.append("and mp.movie_no = m.movie_no) , m.movie_no, m.movie_title, m.running_time, m.director, g.grade_age, n.nation_name          ");
             sql.append("from movie m, grade g, nation n         																											 ");
             sql.append("where g.grade_no = m.grade_no and n.nation_no = m.nation_no                             												 ");
             sql.append("order by 1                                                                               																  ");
             System.out.println(sql.toString());

             rs = stmt.executeQuery(sql.toString());

             while (rs.next()) {
            	PhotoVO photo = new PhotoVO();
                MovieVO movie = new MovieVO();
                GradeVO grade = new GradeVO();
                NationVO nation = new NationVO();
                
                photo.setMoviePhotoOriginalFileName(rs.getString(1));
                movie.setPhoto(photo);
                
                movie.setMovieNo(rs.getInt(2));
                movie.setMovieTitle(rs.getString(3));
                movie.setRunningTime(rs.getInt(4));
                movie.setDirector(rs.getString(5));
                
                grade.setGradeAge(rs.getString(6));
                movie.setGrade(grade);
                
                nation.setNationName(rs.getString(7));
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
    
  //총 게시글 수를 구하다.
    public int selectTotalPost() throws Exception {
       int totalPost = 0;
       Connection conn = null;      
       Statement stmt = null;
       ResultSet rs = null;
       try {
          conn = DBConn.getConnection();
          
          stmt = conn.createStatement();
          
          StringBuffer sql = new StringBuffer();
          sql.append("select count(*) from movie         ");      
          rs = stmt.executeQuery(sql.toString());
          if(rs.next()) {
             totalPost = rs.getInt(1);
          }         
          
       } finally {
          if(rs != null) rs.close();
          if(stmt != null) stmt.close();
          if(conn != null) conn.close();         
       }
       return totalPost;      
    }
    
   //검색에 따른 총 게시글 수를 구하다.
    public int selectTotalPost(String keyfield, String keyword) throws Exception {
       int totalPost = 0;
       Connection conn = null;      
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       try {
          conn = DBConn.getConnection();
          
          StringBuffer sql = new StringBuffer();
          sql.append("select count(*) from movie               ");   
          if (keyfield.equals("MovieTitle")) {
             sql.append("where movie_title like '%' || ? ||  '%'    ");   
          } else if (keyfield.equals("Director")) {
             sql.append("where director like '%' || ? ||  '%'          ");
          }
          
          pstmt = conn.prepareStatement(sql.toString());
        
         pstmt.setString(1,  keyword);
         
         rs = pstmt.executeQuery();
         
          if(rs.next()) {
             totalPost = rs.getInt(1);
          }         
          
       } finally {
          if(rs != null) rs.close();
          if(pstmt != null) pstmt.close();
          if(conn != null) conn.close();         
       }
       return totalPost;      
    }
    
    //상영상태에 따른 영화를 조회하다.
   public List<MovieVO> selectStateMovieList(String keyfield) throws Exception {
         ArrayList<MovieVO> movies = new ArrayList<MovieVO>();
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try {
            conn = DBConn.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append(" select distinct (select mp.movie_photo_original_file_name                                                                                       ");
            sql.append("                 from movie_photo mp                                                                                                         ");
            sql.append("                  where mp.MOVIE_PHOTO_ORIGINAL_FILE_NAME like '%' || 'main' ||  '%'                                                                ");
            sql.append("                 and mp.movie_no = m1.movie_no), temp.movie_no, m1.movie_title, m1.running_time, m1.director, g.grade_age, n.nation_name      ");
            sql.append(" from movie m1, grade g, nation n, movie_photo mp,                                                                                         ");
            sql.append("                                                    (select distinct movie_no                                                                ");
            sql.append("                                                     from screen_schedule                                                                     ");
            sql.append("                                                       group by movie_no                                                                     ");
            if (keyfield.equals("now")) {
               sql.append("                                                       having max(SCREEN_DATE) > sysdate and min(SCREEN_DATE) < sysdate +7) temp          ");
             } else if (keyfield.equals("end")) {
                sql.append("                                                       having max(SCREEN_DATE) < sysdate) temp                                              ");
             } else if (keyfield.equals("future")) {
                sql.append("                                                       having min(SCREEN_DATE) > sysdate +7) temp       ");
             }
            sql.append("where g.grade_no = m1.grade_no and n.nation_no = m1.nation_no and mp.movie_no = m1.movie_no and temp.movie_no = m1.movie_no         ");
          pstmt = conn.prepareStatement(sql.toString());
        
         rs = pstmt.executeQuery();
            while (rs.next()) {
              PhotoVO photo = new PhotoVO();
               MovieVO movie = new MovieVO();
               GradeVO grade = new GradeVO();
               NationVO nation = new NationVO();
               
               photo.setMoviePhotoOriginalFileName(rs.getString(1));
               movie.setPhoto(photo);
               
               movie.setMovieNo(rs.getInt(2));
               movie.setMovieTitle(rs.getString(3));
               movie.setRunningTime(rs.getInt(4));
               movie.setDirector(rs.getString(5));
               
               grade.setGradeAge(rs.getString(6));
               movie.setGrade(grade);
               
               nation.setNationName(rs.getString(7));
               movie.setNation(nation);
               
               movies.add(movie);
            }

         } finally {
            if(pstmt != null) pstmt.close();
            if (conn != null)
               conn.close();
         }
         return movies;
      }
   
}