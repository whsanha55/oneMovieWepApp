package domain.movie;

public class ActorVO {
	private int actorNo;
	private String actorName;
	private String characterName;
	private int actorPhotoNo;
	private int movieNo;
	private int roleNo;
	private ActorPhotoVO actorPhoto;
	private RoleVO role = new RoleVO();

	public ActorVO() {
		super();
	}

	public int getActorNo() {
		return actorNo;
	}

	public void setActorNo(int actorNo) {
		this.actorNo = actorNo;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getActorPhotoNo() {
		return actorPhotoNo;
	}

	public void setActorPhotoNo(int actorPhotoNo) {
		this.actorPhotoNo = actorPhotoNo;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public int getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}

	public RoleVO getRole() {
		return role;
	}

	public void setRole(RoleVO role) {
		this.role = role;
	}

	public ActorPhotoVO getActorPhoto() {
		return actorPhoto;
	}

	public void setActorPhoto(ActorPhotoVO actorPhoto) {
		this.actorPhoto = actorPhoto;
	}

	@Override
	public String toString() {
		return "ActorVO [actorNo=" + actorNo + ", actorName=" + actorName + ", characterName=" + characterName
				+ ", actorPhotoNo=" + actorPhotoNo + ", movieNo=" + movieNo + ", roleNo=" + roleNo + ", actorPhoto="
				+ actorPhoto + ", role=" + role + "]";
	}	
	

}
