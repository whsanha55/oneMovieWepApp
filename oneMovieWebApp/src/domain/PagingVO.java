package domain;

public class PagingVO {
	private int postPerPage; // 한페이지에 보여줄 게시글 수
	private int pageBlock; // 한페이지에 보여줄 페이지 목록수
	private int totalPost; // 총 게시글 수
	private int totalPage; // 총 페이지 수
	private int currentPage; // 현재 페이지 번호
	private int startPage; // 현재 페이지 번호가 속한 페이지 그룹의 시작 페이지 번호
	private int endPage; // 현재 페이지 번호가 속한 페이지 그룹의 마지막 페이지 번호
	private int prevPage; // 이전
	private int nextPage; // 다음
	private int startRow;
	private int endRow;
	private int num; // 현재 페이지번호가 속한 게시글의 시작 번호

	public PagingVO() {
		super();
	}

	public int getPostPerPage() {
		return postPerPage;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public int getTotalPost() {
		return totalPost;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getNum() {
		return num;
	}

	public void setPostPerPage(int postPerPage) {
		this.postPerPage = postPerPage;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setProperty() {
		// postperpage pageblock, totalpost, currentpage, totalpage
		this.totalPage = totalPost / postPerPage + (totalPost % postPerPage > 0 ? 1 : 0);
		int tempPage = currentPage / pageBlock + (currentPage % pageBlock > 0 ? 1 : 0);
		// this.startPage =
		this.startPage = (tempPage - 1) * pageBlock + 1;
		this.endPage = startPage + pageBlock - 1;
		if (endPage > totalPage) {
			this.endPage = totalPage;
		}
		this.prevPage = startPage - 1;
		this.nextPage = endPage + 1;
		this.startRow = (currentPage-1) * postPerPage + 1;
		this.endRow = currentPage * postPerPage;
		if(endRow > totalPost) {
			this.endRow = totalPost;
		}
		this.num = (currentPage-1) * postPerPage;
	}

	@Override
	public String toString() {
		return "PagingVO [postPerPage=" + postPerPage + ", pageBlock=" + pageBlock + ", totalPost=" + totalPost
				+ ", totalPage=" + totalPage + ", currentPage=" + currentPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + ", startRow="
				+ startRow + ", endRow=" + endRow + ", num=" + num + "]";
	}

	
}
