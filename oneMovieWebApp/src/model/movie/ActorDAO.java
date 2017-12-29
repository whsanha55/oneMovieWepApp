package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.ActorVO;
import domain.movie.RoleVO;

public class ActorDAO {

   private static ActorDAO instance = new ActorDAO();

   private ActorDAO() {

   }

   public static ActorDAO getInstance() {
      return instance;
   }

   // 출연진을 등록한다.
   public int insertActorList(Connection conn, ActorVO actor, int movieNo) throws Exception {
      PreparedStatement pstmt = null;
      Statement stmt = null;
      ResultSet rs = null;
      int actorNo = 0;
      try {
         StringBuffer sql = new StringBuffer();
         
         if(movieNo == 0) {         
            sql.append("insert into Actor(actor_no, actor_name, character_name, role_no, movie_no)       ");
            sql.append("values(actor_no_seq.nextval, ?, ?, ?, (select max(movie_no) from movie))            ");         
         } else {
            sql.append("insert into Actor(actor_no, actor_name, character_name, role_no, movie_no)       ");
            sql.append("values(actor_no_seq.nextval, ?, ?, ?, ?)                              					        ");      
         }
         
         pstmt = conn.prepareStatement(sql.toString());

         pstmt.setString(1, actor.getActorName());
         pstmt.setString(2, actor.getCharacterName());
         pstmt.setInt(3, actor.getRoleNo());
         
         if(movieNo != 0) {
            pstmt.setInt(4, actor.getMovieNo());
         }

         pstmt.executeUpdate();
         pstmt.close();

         sql.delete(0, sql.length());

         stmt = conn.createStatement();

         sql.append("select actor_no_seq.currval from dual");

         rs = stmt.executeQuery(sql.toString());
         if (rs.next()) {
            actorNo = rs.getInt(1);
         }

      } finally {
         if (rs != null)
            rs.close();
         if (stmt != null)
            stmt.close();

      }
      return actorNo;
   }

   // 역할(주연, 조연, 엑스트라)을 조회한다.
   public List<RoleVO> selectRoleList() throws Exception {
      ArrayList<RoleVO> roles = new ArrayList<RoleVO>();
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
         conn = DBConn.getConnection();

         System.out.println(conn);
         stmt = conn.createStatement();

         StringBuffer sql = new StringBuffer();
         sql.append("select role_No, Role_Name                        ");
         sql.append("from role                                              ");
         sql.append("order by roleNo asc                                ");

         System.out.println(sql.toString());

         rs = stmt.executeQuery(sql.toString());
         while (rs.next()) {
            RoleVO role = new RoleVO();
            role.setRoleNo(rs.getInt(1));
            role.setRoleName(rs.getString(2));
            roles.add(role);
         }

      } finally {
         if (stmt != null) stmt.close();
         if (conn != null) conn.close();
      }
      return roles;
   }

   // 출연진정보를 조회한다.
   public ActorVO selectActorList(int actorNo) throws Exception {
      ActorVO actor = new ActorVO();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         conn = DBConn.getConnection();

         StringBuffer sql = new StringBuffer();
         sql.append("select actor_no, actor_name, character_name, movie_no, role_no  	          ");
         sql.append("from actor                                            									  ");
         sql.append("where actor_no= ?                                        								   ");
         pstmt = conn.prepareStatement(sql.toString());

         pstmt.setInt(1, actorNo);

         rs = pstmt.executeQuery();

         while (rs.next()) {
            actor.setActorNo(rs.getInt(1));
            actor.setActorName(rs.getString(2));
            actor.setCharacterName(rs.getString(3));
            actor.setMovieNo(rs.getInt(5));
            actor.setRoleNo(rs.getInt(6));
         }

      } finally {
         if (pstmt != null) pstmt.close();
         if (conn != null) conn.close();
      }
      return actor;
   }

   // 출연진 정보를 수정한다.
   public void modifyActorList(Connection conn, List<ActorVO> actors) throws Exception {
      PreparedStatement pstmt = null;
      try {

         StringBuffer sql = new StringBuffer();
         sql.append("update actor                 							   ");
         sql.append("set actor_name=?, character_name=?, role_no=?  ");
         sql.append("where actor_no= ? 									   ");
         pstmt = conn.prepareStatement(sql.toString());

         for (ActorVO actor : actors) {
            pstmt.setString(1, actor.getActorName());
            pstmt.setString(2, actor.getCharacterName());
            pstmt.setInt(3, actor.getRoleNo());
            pstmt.setInt(4, actor.getActorNo());
            pstmt.addBatch();
         }
         pstmt.executeBatch();

      } finally {
         if (pstmt != null) pstmt.close();
      }
   }

   // 출연진 정보를 삭제한다.
   public void removeActor(Connection conn, int movieNo) throws Exception {
      PreparedStatement pstmt = null;
      try {
         conn = DBConn.getConnection();

         StringBuffer sql = new StringBuffer();
         sql.append("delete from actor                 ");
         sql.append("where movie_No= ?              ");
         pstmt = conn.prepareStatement(sql.toString());

         pstmt.setInt(1, movieNo);

         pstmt.executeUpdate();

      } finally {
         if (pstmt != null) pstmt.close();
      }
   }
}