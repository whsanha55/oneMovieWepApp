package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conn.DBConn;
import domain.movie.ActorPhotoVO;

public class ActorPhotoDAO {
	private static ActorPhotoDAO instance = new ActorPhotoDAO(); 

	private ActorPhotoDAO() {

	}

	public static ActorPhotoDAO getInstance() {
		return instance;
	}
	
	//출연진 사진을 등록한다.
	public void insertActorPhoto(Connection conn, ActorPhotoVO actorPhoto) throws Exception {	
		PreparedStatement pstmt = null;
		
		try {			
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into actor_photo(actor_photo_no, actor_original_file_name, actor_system_file_name, photo_url, actor_no )     ");
			sql.append("values(actor_photo_no_seq.nextval, ?, ?, ?, ?, ?)                           ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, actorPhoto.getActorOriginalFileName());
			pstmt.setString(2, actorPhoto.getActorSystemFileName());
			pstmt.setString(3, actorPhoto.getPhotoUrl());
			pstmt.setInt(4, actorPhoto.getActorNo());
			
			pstmt.executeUpdate();
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();		
		}
	}
	
	//출연진 사진 정보를 삭제하다.
	public void removeActorPhoto(Connection conn, int actorNo) throws Exception {	
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("delete from actor_photo   ");
			sql.append("where actor_no = ? 	 ");	
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, actorNo);
			
			pstmt.executeUpdate();
			
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();			
		}
	}
}
