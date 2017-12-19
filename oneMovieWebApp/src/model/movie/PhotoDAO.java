package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.ActorVO;
import domain.movie.PhotoVO;

public class PhotoDAO {
	private static PhotoDAO instance = new PhotoDAO(); 

	private PhotoDAO() {

	}

	public static PhotoDAO getInstance() {
		return instance;
	}
	
	//���� ������ �ϰ� ����Ѵ�.
	public void insertPhotoList(Connection connn, List<PhotoVO> photos) throws Exception {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into movie_photo(movie_photo_no, movie_photo_original_file_name, movie_photo_system_file_name, movie_photo_url, movie_no )      ");
			sql.append("values(movie_photo_no_seq.nextval, ?, ?, ?, ?)                           ");
			pstmt = conn.prepareStatement(sql.toString());
					
			for(PhotoVO photo : photos) {
				pstmt.setInt(1, photo.getMoviePhotoNo());
				pstmt.setString(2, photo.getMoviePhotoOriginalFileName());
				pstmt.setString(3, photo.getMoviePhotoSystemFileName());
				pstmt.setString(5, photo.getMoviePhotoUrl());
				pstmt.setInt(6, photo.getMovieNo());
				pstmt.addBatch();
			}	
			pstmt.executeBatch();			
			
		} finally {
			if(pstmt != null) pstmt.close();	
		}
	}
	
	//������ ��ȸ�ϴ�.
	public List<PhotoVO> selectPhotoList(int movieNo) throws Exception {
		ArrayList<PhotoVO> photos = new ArrayList<PhotoVO>();
		Connection conn = null;		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {			
			conn = DBConn.getConnection();
			
			stmt = conn.createStatement();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select movie_photo_no, movie_photo_original_file_name, movie_photo_system_file_name, movie_photo_url, movie_no      ");
			sql.append("from movie_photo 														   ");
			sql.append("order by no desc    												   ");
			//System.out.println(sql.toString());

			rs = stmt.executeQuery(sql.toString());
			
			while (rs.next()) {
				PhotoVO photo = new PhotoVO();
				photo.setMoviePhotoNo(rs.getInt(1));
				photo.setMoviePhotoOriginalFileName(rs.getString(2));
				photo.setMoviePhotoSystemFileName(rs.getString(3));
				photo.setMoviePhotoUrl(rs.getString(4));
				photo.setMovieNo(rs.getInt(5));
				photos.add(photo);
			}
			
		} finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();		
		}
		return photos;
	}
	
	//��ȭ ���� ������ �����ϴ�.
	public void removePhotoList(int movieNo) throws Exception {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		
		try {			
			conn = DBConn.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("delete from movie_photo        ");
			sql.append("where movie_photo_no = ?       ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, movieNo);

			pstmt.executeUpdate();
			
			
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();		
		}
	}
}
