package domain.movie;

public class ActorPhotoVO {
	private int actorPhotoNo ;
	private String actorOriginalFileName;
	private String actorSystemFileName;
	private String photoUrl;
	private int actorNo;
	
	public ActorPhotoVO() {
		super();
	}

	public int getActorPhotoNo() {
		return actorPhotoNo;
	}

	public void setActorPhotoNo(int actorPhotoNo) {
		this.actorPhotoNo = actorPhotoNo;
	}

	public String getActorOriginalFileName() {
		return actorOriginalFileName;
	}

	public void setActorOriginalFileName(String actorOriginalFileName) {
		this.actorOriginalFileName = actorOriginalFileName;
	}

	public String getActorSystemFileName() {
		return actorSystemFileName;
	}

	public void setActorSystemFileName(String actorSystemFileName) {
		this.actorSystemFileName = actorSystemFileName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getActorNo() {
		return actorNo;
	}

	public void setActorNo(int actorNo) {
		this.actorNo = actorNo;
	}
	
	@Override
	public String toString() {
		return "ActorPhotoVO [actorPhotoNo=" + actorPhotoNo + ", actorOriginalFileName=" + actorOriginalFileName
				+ ", actorSystemFileName=" + actorSystemFileName + ", photoUrl=" + photoUrl + ", actorNo=" + actorNo
				+ "]";
	}

}
