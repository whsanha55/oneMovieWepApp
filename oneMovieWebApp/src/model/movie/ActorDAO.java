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

	// 출연진을 일괄 등록한다. //service에서 commit해야함(conn.close())
	public int insertActorList(Connection connn, List<ActorVO> actors) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into ActorVO(actorNo, actorName, characterName, movieNo, roleNo) ");
			sql.append("values(actor_no_seq.nextval, ?, ?, ? ?)                           ");
			pstmt = conn.prepareStatement(sql.toString());
		
			for(ActorVO actor : actors) {
				pstmt.setString(1, actor.getActorName());
				pstmt.setString(2, actor.getCharacterName());
				pstmt.setInt(3, actor.getMovieNo());
				pstmt.setInt(4, actor.getRoleNo());
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();			

		} finally {
			if (pstmt != null)
				pstmt.close();
			
		}
		return 0;
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
			sql.append("select roleNo, RoleName												   ");
			sql.append("from role 														   ");
			sql.append("order by roleNo asc    												   ");

			System.out.println(sql.toString());

			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				RoleVO role = new RoleVO();
				role.setRoleNo(rs.getInt(1));
				role.setRoleName(rs.getString(2));
				roles.add(role);
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return roles;
	}

	// 역할(주연, 조연, 엑스트라)을 조회한다.
	public List<ActorVO> selectActorList() throws Exception {
		ArrayList<ActorVO> actors = new ArrayList<ActorVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			System.out.println(conn);
			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"select ActorNo, ActorName, CharacterName, ActorPhotoNo, MovieNo, RoleNo												   ");
			sql.append("from role 														   ");
			sql.append("order by roleNo asc    												   ");

			System.out.println(sql.toString());

			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				ActorVO actor = new ActorVO();
				actor.setActorNo(rs.getInt(1));
				actor.setActorName(rs.getString(2));
				actor.setCharacterName(rs.getString(3));
				actor.setActorPhotoNo(rs.getInt(4));
				actor.setMovieNo(rs.getInt(5));
				actor.setRoleNo(rs.getInt(6));
				actors.add(actor);
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return actors;
	}

	// 출연진 정보를 수정한다.
	public void modifyActorList(ActorVO actor) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("update actor					     ");
			sql.append("set actorNo=?, actorName=?, characterName=?, movieNo=?, roleNo=?  ");
			sql.append("where no= ?  ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, actor.getActorNo());
			pstmt.setString(2, actor.getActorName());
			pstmt.setString(3, actor.getCharacterName());
			pstmt.setInt(4, actor.getMovieNo());
			pstmt.setInt(5, actor.getRoleNo());

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	}
	
	// 게시글 정보를 삭제한다.
	public void removeActor(int movieNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("delete from actor     			");
			sql.append("where movieNo= ?  				");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, movieNo);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	}

}
