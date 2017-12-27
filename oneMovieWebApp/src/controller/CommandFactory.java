package controller;

import java.util.HashMap;

public class CommandFactory {
	// �̱��� ����
	private static CommandFactory instance = new CommandFactory();
	private HashMap<String, String> map = new HashMap<String, String>();

	private CommandFactory() {
		// ȸ�� ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// ������ �α��� ��û
		map.put("/adminLogin.do", "controller.admin.AdminLoginCommand");
		// ������ �α׾ƿ� ��û
		map.put("/adminLogout.do", "controller.admin.AdminLogoutCommand");
		// ȸ�� ���� �� ��û
		map.put("/manageMemberForm.do", "controller.admin.ManageMemberFormCommand");
		// ȸ������Ʈ ��û
		map.put("/manageMember.do", "controller.admin.ManageMemberCommand");

		// ȸ�� �α��� �� ��û
		map.put("/memberLoginForm.do", "controller.member.MemberLoginFormCommand");
		// ȸ�� �α��� ��û
		map.put("/memberLogin.do", "controller.member.MemberLoginCommand");
		// ���̵� ã�� �� ��û
		map.put("/findIdForm.do", "controller.member.FindIdFormCommand");
		// ���̵� ã�� ��û
		map.put("/findId.do", "controller.member.FindIdCommand");
		// ��й�ȣ ã�� �� ��û
		map.put("/findPwdForm.do", "controller.member.FindPwdFormCommand");
		// ��й�ȣ ã�� ��û
		map.put("/findPwd.do", "controller.member.FindPwdCommand");
		// ���̵� �̸��� �ߺ�Ȯ�� ��û
		map.put("/duplicate.do", "controller.member.DuplicateCommand");
		// ��α��� �� ��û(ȸ������ ������)
		map.put("/reLoginFrom.do", "controller.member.ReLoginFormCommand");
		// ��α��� ��û
		map.put("/reLogin.do", "controller.member.ReLoginCommand");
		// ȸ������ ���� ��û
		map.put("/modifyMember.do", "controller.member.ModifyMemberCommand");
		// ȸ������ �� ��û
		map.put("/joinForm.do", "controller.member.JoinFormCommand");
		// ȸ������ ��û
		map.put("/join.do", "controller.member.JoinCommand");
		// ȸ�� Ż�� ��û
		map.put("/withdraw.do", "controller.member.WithdrawCommand");
		// ȸ�� �α׾ƿ� ��û
		map.put("/memberLogout.do", "controller.member.memberLogoutCommand");
		// ȸ�� ��====================================================

		// ��ȭ ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		map.put("/user/movie/listMovie.do", "controller.movie.ListMovieCommand");
		map.put("/user/movie/findMovie.do", "controller.movie.FindMovieCommand");
		map.put("/user/movie/detailMovie.do", "controller.movie.DetailMovieCommand");
		map.put("/user/movie/removeMovie.do", "controller.movie.RemoveMovieCommand");
		map.put("/user/movie/listAllMovie.do", "controller.movie.ListAllMovieCommand");
		map.put("/user/movie/listAllMovieCount.do", "controller.movie.ListAllMovieCountCommand");
		map.put("/user/movie/findMovieCount.do", "controller.movie.FindMovieCountCommand");
		map.put("/user/movie/listStateMovie.do", "controller.movie.ListStateMovieCommand");
		
		// ������
		map.put("/admin/movie/listMovie.do", "controller.movie.AdminListMovieCommand");
		map.put("/admin/movie/findMovie.do", "controller.movie.AdminFindMovieCommand");
		map.put("/admin/movie/detailMovie.do", "controller.movie.AdminDetailMovieCommand");
		map.put("/admin/movie/removeMovie.do", "controller.movie.AdminRemoveMovieCommand");
		map.put("/admin/movie/listAllMovie.do", "controller.movie.AdminListAllMovieCommand");
		map.put("/admin/movie/modifyMovieForm.do", "controller.movie.ModifyMovieFormCommand");

		// ��ȭ ��====================================================

		// ���� ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		map.put("/admin/theater/adminTheaterForm.do", "controller.theater.AdminTheaterFormCommand");
		map.put("/admin/theater/listScheduleTurnForm.do", "controller.theater.ListScheduleTurnCommand");
		map.put("/admin/theater/addTurnForm.do", "controller.theater.AddTurnFormCommand");
		map.put("/admin/theater/addScheduleForm.do", "controller.theater.AddScheduleFormCommand");
		map.put("/admin/theater/addSchedule.do", "controller.theater.AddScheduleTurnCommand");

		map.put("/admin/theater/adminTheaterView.do", "controller.theater.AdminTheaterSearchAjaxCommand");
		// ���� ��====================================================

		// ���� ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		map.put("/adminBookingByMember.do", "controller.booking.AdminBookingByMemberCommand");
		map.put("/adminBookingByTheater.do", "controller.booking.AdminBookingByTheaterCommand");
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
		map.put("/memberBookingIsRefundableAjax.do", "controller.booking.MemberBookingIsRefundableAjaxCommand");
		map.put("/memberBookingRefundAjax.do", "controller.booking.MemberBookingRefundAjaxCommand");
		// ���� ��====================================================

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
