package domain;

public class PagingVO {
	private int postPerPage; // ���������� ������ �Խñ� ��
	private int pageBlock; // ���������� ������ ������ ��ϼ�
	private int totalPost; // �� �Խñ� ��
	private int totalPage; // �� ������ ��
	private int currentPage; // ���� ������ ��ȣ
	private int startPage; // ���� ������ ��ȣ�� ���� ������ �׷��� ���� ������ ��ȣ
	private int endPage; // ���� ������ ��ȣ�� ���� ������ �׷��� ������ ������ ��ȣ
	private int prevPage; // ����
	private int nextPage; // ����
	private int startRow;
	private int endRow;
	private int num; // ���� ��������ȣ�� ���� �Խñ��� ���� ��ȣ

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
