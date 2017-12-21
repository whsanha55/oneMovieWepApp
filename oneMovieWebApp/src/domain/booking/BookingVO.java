package domain.booking;

import java.util.ArrayList;
import java.util.List;

public class BookingVO {

	String ticketNo;
	String memberNo;
	int turnNo;
	String paymentCode;
	int price;
	int status;
	List<BookingSeatVO> bookingSeats = new ArrayList<BookingSeatVO>();
	BookingRefundVO bookingRefundVO;
	// 조회시 필요
	String memberName;
	String theaterName;
	String screenName;
	String screenDate;
	String startTime;
	String endTime;
	String movieTitle;
	String withdrawDate;
	List<String> seatNames = new ArrayList<String>();

	public BookingVO() {
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public int getTurnNo() {
		return turnNo;
	}

	public void setTurnNo(int turnNo) {
		this.turnNo = turnNo;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<BookingSeatVO> getBookingSeats() {
		return bookingSeats;
	}

	public void setBookingSeats(List<BookingSeatVO> bookingSeats) {
		this.bookingSeats = bookingSeats;
	}

	public void addBookingSeat(BookingSeatVO bookingSeatVO) {
		bookingSeats.add(bookingSeatVO);

	}

	public BookingRefundVO getBookingRefundVO() {
		return bookingRefundVO;
	}

	public void setBookingRefundVO(BookingRefundVO bookingRefundVO) {
		this.bookingRefundVO = bookingRefundVO;
	}

	

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenDate() {
		return screenDate;
	}

	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(String withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public List<String> getSeatNames() {
		return seatNames;
	}

	public void setSeatNames(List<String> seatNames) {
		this.seatNames = seatNames;
	}

	public void addSeatName(String seatName) {
		seatNames.add(seatName);
	}

	@Override
	public String toString() {
		return "BookingVO [ticketNo=" + ticketNo + ", memberNo=" + memberNo + ", turnNo=" + turnNo + ", paymentCode="
				+ paymentCode + ", price=" + price + ", status=" + status + ", bookingSeats=" + bookingSeats
				+ ", bookingRefundVO=" + bookingRefundVO + ", memberName=" + memberName + ", theaterName=" + theaterName
				+ ", screenName=" + screenName + ", screenDate=" + screenDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", movieTitle=" + movieTitle + ", withdrawDate=" + withdrawDate
				+ ", seatNames=" + seatNames + "]";
	}

	
}
