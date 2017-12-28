package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import domain.movie.PhotoVO;

public class PhotoDAO {
	private static PhotoDAO instance = new PhotoDAO();

	private PhotoDAO() {

	}

	public static PhotoDAO getInstance() {
		return instance;
	}

	// 사진 정보를 일괄 등록한다.
	public void insertPhotoList(Connection conn, List<PhotoVO> photos) throws Exception {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"insert into movie_photo(movie_photo_no, movie_photo_original_file_name, movie_photo_system_file_name, movie_photo_url, movie_no )      ");
			sql.append("values(movie_photo_no_seq.nextval, ?, ?, 'C:\\io', (select max(movie_no) from movie))                           	");
			
			pstmt = conn.prepareStatement(sql.toString());

			for (PhotoVO photo : photos) {			
				System.out.println(photo.getMoviePhotoOriginalFileName());
				pstmt.setString(1, photo.getMoviePhotoOriginalFileName());
				pstmt.setString(2, photo.getMoviePhotoSystemFileName());
				System.out.println("photo.getMovieNo() : " + photo.getMovieNo());
				// pstmt.setString(2, photo.getMoviePhotoUrl());
				pstmt.addBatch();
				
			}
			pstmt.executeBatch();
			

		} finally {
			if (pstmt != null) pstmt.close();
		}
	}

	// 사진을 조회하다.
	public List<PhotoVO> selectPhotoList(int movieNo) throws Exception {
		ArrayList<PhotoVO> photos = new ArrayList<PhotoVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"select movie_photo_no, movie_photo_original_file_name, movie_photo_system_file_name, movie_photo_url, movie_no      ");
			sql.append("from movie_photo 														   ");
			sql.append("order by no desc    												   ");
			// System.out.println(sql.toString());

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
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return photos;
	}

	// 영화 사진 정보를 삭제하다.
	public void removePhoto(Connection conn, int moviePhotoNo) throws Exception {
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("delete from movie_photo        ");
			sql.append("where movie_photo_no = ?       ");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, moviePhotoNo);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}
}
