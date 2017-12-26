package controller;

import java.util.HashMap;

public class CommandFactory {
	// 싱글톤 패턴
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		// 회원 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// 관리자 로그인 요청
		map.put("/adminLogin.do", "controller.admin.AdminLoginCommand");
		// 관리자 로그아웃 요청
		map.put("/adminLogout.do", "controller.admin.AdminLogoutCommand");
		// 회원 관리 폼 요청
		map.put("/manageMemberForm.do", "controller.admin.ManageMemberFormCommand");
		// 회원리스트 요청
		map.put("/manageMember.do", "controller.admin.ManageMemberCommand");

		// 회원 로그인 폼 요청
		map.put("/memberLoginForm.do", "controller.member.MemberLoginFormCommand");
		// 회원 로그인 요청
		map.put("/memberLogin.do", "controller.member.MemberLoginCommand");
		// 아이디 찾기 폼 요청
		map.put("/findIdForm.do", "controller.member.FindIdFormCommand");
		// 아이디 찾기 요청
		map.put("/findId.do", "controller.member.FindIdCommand");
		// 비밀번호 찾기 폼 요청
		map.put("/findPwdForm.do", "controller.member.FindPwdFormCommand");
		// 비밀번호 찾기 요청
		map.put("/findPwd.do", "controller.member.FindPwdCommand");
		// 아이디 이메일 중복확인 요청
		map.put("/duplicate.do", "controller.member.DuplicateCommand");
		// 재로그인 폼 요청(회원정보 수정용)
		map.put("/reLoginFrom.do", "controller.member.ReLoginFormCommand");
		// 재로그인 요청
		map.put("/reLogin.do", "controller.member.ReLoginCommand");
		// 회원정보 수정 요청
		map.put("/modifyMember.do", "controller.member.ModifyMemberCommand");
		// 회원가입 폼 요청
		map.put("/joinForm.do", "controller.member.JoinFormCommand");
		// 회원가입 요청
		map.put("/join.do", "controller.member.JoinCommand");
		// 회원 탈퇴 요청
		map.put("/withdraw.do", "controller.member.WithdrawCommand");
		// 회원 로그아웃 요청
		map.put("/memberLogout.do", "controller.member.memberLogoutCommand");
		// 회원 끝====================================================

		// 영화 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		map.put("/adminBooking1.do", "controller.AdminBooking1Command");
		map.put("/adminBookingAjax.do", "controller.AdminBookingAjaxCommand");
		map.put("/adminBookingAjax2.do", "controller.AdminBookingAjax2Command");
		map.put("/memberBooking1.do", "controller.memberBookingCommand");
		map.put("/user/movie/listMovie.do", "controller.movie.ListMovieCommand");
		map.put("/user/movie/findMovie.do", "controller.movie.FindMovieCommand");
		map.put("/removeMovieForm.do", "controller.RemoveMovieFormCommand");
		map.put("/removeMovie.do", "controller.RemoveMovieCommand");
		map.put("/user/movie/detailMovie.do", "controller.movie.DetailMovieCommand");
		// 영화 끝====================================================

		// 극장 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		map.put("/admin/theater/adminTheaterForm.do", "controller.theater.AdminTheaterFormCommand");
		map.put("/admin/theater/listScheduleTurnForm.do", "controller.theater.ListScheduleTurnCommand");
		map.put("/admin/theater/addTurnForm.do", "controller.theater.AddTurnFormCommand");
		map.put("/admin/theater/addScheduleForm.do", "controller.theater.AddScheduleFormCommand");
		map.put("/admin/theater/addSchedule.do", "controller.theater.AddScheduleTurnCommand");

		map.put("/admin/theater/adminTheaterView.do", "controller.theater.AdminTheaterSearchAjaxCommand");
		// 극장 끝====================================================

		map.put("/user/movie/listMovie.do", "controller.movie.ListMovieCommand");
		map.put("/user/movie/findMovie.do", "controller.movie.FindMovieCommand");
		map.put("/user/movie/detailMovie.do", "controller.movie.DetailMovieCommand");
		map.put("/user/movie/removeMovie.do", "controller.movie.RemoveMovieCommand");
		map.put("/user/movie/listAllMovie.do", "controller.movie.ListAllMovieCommand");

		// 예약 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		map.put("/adminBooking1.do", "controller.booking.AdminBooking1Command");
		map.put("/adminBookingAjax.do", "controller.booking.AdminBookingAjaxCommand");
		map.put("/adminBookingAjax2.do", "controller.booking.AdminBookingAjax2Command");
		map.put("/memberBooking.do", "controller.booking.MemberBookingCommand");
		map.put("/movieByTheaterAndDateAjax.do", "controller.theater.MovieByTheaterAndDateAjaxCommand");
		map.put("/theaterByMovieAndDateAjax.do", "controller.theater.TheaterByMovieAndDateAjaxCommand");
		map.put("/dateByMovieAndTheaterAjax.do", "controller.theater.DateByMovieAndTheaterAjaxCommand");
		map.put("/memberBookingSelectSeat.do", "controller.booking.MemberBookingSelectSeatCommand");
		map.put("/memberBookingPayForm.do", "controller.booking.MemberBookingPayFormCommand");
		map.put("/memberExecuteBooking.do", "controller.booking.MemberExecuteBookingCommand");
		map.put("/memberBookingListCurr.do", "controller.booking.MemberBookingListCurrCommand");
		map.put("/memberBookingListPast.do", "controller.booking.MemberBookingListPastCommand");
		map.put("/memberBookingListCancel.do", "controller.booking.MemberBookingListCancelCommand");
		// 예약 끝====================================================
	}

	public static CommandFactory getInstance() {
		return instance;
	}

	public Command createCommand(String uri) {
		String commandClass = map.get(uri);
		if (commandClass == null) {
			return null;
		}
		try {
			Class obj = Class.forName(commandClass);
			Command command = (Command) obj.newInstance();
			return command;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
