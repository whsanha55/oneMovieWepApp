package domain.movie;

import java.util.ArrayList;
import java.util.List;

import model.movie.ActorPhotoDAO;

public class ActorVO {
	int actorNo;
	String actorName;
	String characterName;
	int actorPhotoNo;
	int movieNo;
	int roleNo;
	private List<ActorPhotoVO> actorPhotos = new ArrayList<ActorPhotoVO>();
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

	public List<ActorPhotoVO> getActorPhotos() {
		return actorPhotos;
	}

	public void setActorPhotos(List<ActorPhotoVO> actorPhotos) {
		this.actorPhotos = actorPhotos;
	}

	public void addActorPhoto(ActorPhotoVO actorPhoto) {
		actorPhotos.add(actorPhoto);
	}

	@Override
	public String toString() {
		return "ActorVO [actorNo=" + actorNo + ", actorName=" + actorName + ", characterName=" + characterName
				+ ", actorPhotoNo=" + actorPhotoNo + ", movieNo=" + movieNo + ", roleNo=" + roleNo + ", actorPhotos="
				+ actorPhotos + ", role=" + role + "]";
	}
	
	
}
