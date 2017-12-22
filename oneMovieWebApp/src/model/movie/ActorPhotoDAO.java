package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import conn.DBConn;
import domain.movie.ActorPhotoVO;
import domain.movie.PhotoVO;

public class ActorPhotoDAO {
	private static ActorPhotoDAO instance = new ActorPhotoDAO();

	private ActorPhotoDAO() {

	}

	public static ActorPhotoDAO getInstance() {
		return instance;
	}

	// �⿬�� ������ ����Ѵ�.
	public void insertActorPhoto(Connection conn, ActorPhotoVO actorPhoto) throws Exception {
		PreparedStatement pstmt = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"insert into actor_photo(actor_photo_no, actor_original_file_name, actor_system_file_name, photo_url, actor_no )   ");
			sql.append(
					"values(actor_photo_no_seq.nextval, ?, ?, 'C:\\\\io', ?)                                                           ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, actorPhoto.getActorOriginalFileName());
			pstmt.setString(2, actorPhoto.getActorSystemFileName());
			pstmt.setInt(3, actorPhoto.getActorNo());

			pstmt.executeUpdate();			

		} finally {
			if (pstmt != null) pstmt.close();
		}
	}

	// �⿬�� ���� ������ �����ϴ�.
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

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}
}
