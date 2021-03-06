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
				map.put("/admin/adminLogout.do", "controller.admin.AdminLogoutCommand");
				// 회원 관리 폼 요청
				map.put("/admin/manageMemberForm.do", "controller.admin.ManageMemberFormCommand");
				// 회원리스트 요청
				map.put("/admin/manageMember.do", "controller.admin.ManageMemberCommand");
				// 회원 수 카운트 요청
				map.put("/admin/manageMemberCount.do", "controller.admin.ManageMemberCountCommand");
				
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
				map.put("/auth/reLoginForm.do", "controller.member.ReLoginFormCommand");
				// 재로그인 요청
				map.put("/auth/reLogin.do", "controller.member.ReLoginCommand");
				// 회원정보 수정 요청
				map.put("/auth/modifyMember.do", "controller.member.ModifyMemberCommand");
				// 회원가입 폼 요청
				map.put("/joinForm.do", "controller.member.JoinFormCommand");
				// 회원가입 요청
				map.put("/join.do", "controller.member.JoinCommand");
				// 회원 탈퇴 요청
				map.put("/auth/withdraw.do", "controller.member.WithdrawCommand");
				// 회원 로그아웃 요청
				map.put("/auth/memberLogout.do", "controller.member.MemberLogoutCommand");
				// 회원 끝====================================================

		// 영화 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		map.put("/user/movie/listMovie.do", "controller.movie.ListMovieCommand");
		map.put("/user/movie/findMovie.do", "controller.movie.FindMovieCommand");
		map.put("/user/movie/detailMovie.do", "controller.movie.DetailMovieCommand");
		//map.put("/user/movie/removeMovie.do", "controller.movie.RemoveMovieCommand");
		map.put("/user/movie/listAllMovie.do", "controller.movie.ListAllMovieCommand");
		map.put("/user/movie/listAllMovieCount.do", "controller.movie.ListAllMovieCountCommand");
		map.put("/user/movie/listMovieCount.do", "controller.movie.ListMovieCountCommand");
		map.put("/user/movie/findMovieCount.do", "controller.movie.FindMovieCountCommand");
		map.put("/user/movie/listStateMovie.do", "controller.movie.ListStateMovieCommand");
		
		// 관리자
		map.put("/admin/movie/listMovie.do", "controller.movie.AdminListMovieCommand");
		map.put("/admin/movie/findMovie.do", "controller.movie.AdminFindMovieCommand");
		map.put("/admin/movie/detailMovie.do", "controller.movie.AdminDetailMovieCommand");
		map.put("/admin/movie/removeMovie.do", "controller.movie.RemoveMovieCommand");
		map.put("/admin/movie/listAllMovie.do", "controller.movie.AdminListAllMovieCommand");
		map.put("/admin/movie/modifyMovieForm.do", "controller.movie.ModifyMovieFormCommand");
		map.put("/admin/movie/removeMoviePhoto.do", "controller.movie.RemoveMoviePhotoCommand");

		// 영화 끝====================================================

		// 극장 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		map.put("/admin/theater/adminTheaterForm.do", "controller.theater.AdminTheaterFormCommand");
		map.put("/admin/theater/listScheduleTurnForm.do", "controller.theater.ListScheduleTurnCommand");
		map.put("/admin/theater/addTurnForm.do", "controller.theater.AddTurnFormCommand");
		map.put("/admin/theater/addScheduleForm.do", "controller.theater.AddScheduleFormCommand");
		map.put("/admin/theater/addSchedule.do", "controller.theater.AddScheduleTurnCommand");

		map.put("/admin/theater/adminTheaterView.do", "controller.theater.AdminTheaterSearchAjaxCommand");
		// 극장 끝====================================================

		// 예약 시작!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		map.put("/admin/adminBookingByMember.do", "controller.booking.AdminBookingByMemberCommand");
		map.put("/admin/adminBookingByTheater.do", "controller.booking.AdminBookingByTheaterCommand");
		map.put("/adminBookingAjax.do", "controller.booking.AdminBookingAjaxCommand");
		map.put("/adminBookingAjax2.do", "controller.booking.AdminBookingAjax2Command");
		map.put("/adminBookingAjax3.do", "controller.booking.AdminBookingAjax3Command");
		map.put("/adminBookingAjax4.do", "controller.booking.AdminBookingAjax4Command");
		map.put("/adminBookingCountAjax.do", "controller.booking.AdminBookingCountAjaxCommand");
		map.put("/adminBookingCountAjax2.do", "controller.booking.AdminBookingCountAjax2Command");
		map.put("/memberBooking.do", "controller.booking.MemberBookingCommand");
		map.put("/movieByTheaterAndDateAjax.do", "controller.theater.MovieByTheaterAndDateAjaxCommand");
		map.put("/theaterByMovieAndDateAjax.do", "controller.theater.TheaterByMovieAndDateAjaxCommand");
		map.put("/dateByMovieAndTheaterAjax.do", "controller.theater.DateByMovieAndTheaterAjaxCommand");
		map.put("/auth/memberBookingSelectSeat.do", "controller.booking.MemberBookingSelectSeatCommand");
		map.put("/auth/memberBookingPayForm.do", "controller.booking.MemberBookingPayFormCommand");
		map.put("/auth/memberExecuteBooking.do", "controller.booking.MemberExecuteBookingCommand");
		map.put("/auth/memberBookingListCurr.do", "controller.booking.MemberBookingListCurrCommand");
		map.put("/auth/memberBookingListPast.do", "controller.booking.MemberBookingListPastCommand");
		map.put("/auth/memberBookingListCancel.do", "controller.booking.MemberBookingListCancelCommand");
		map.put("/memberBookingIsRefundableAjax.do", "controller.booking.MemberBookingIsRefundableAjaxCommand");
		map.put("/memberBookingRefundAjax.do", "controller.booking.MemberBookingRefundAjaxCommand");
		// 예약 끝====================================================

		map.put("/getTurnListAjax.do", "controller.theater.GetTurnListAjaxCommand");
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
