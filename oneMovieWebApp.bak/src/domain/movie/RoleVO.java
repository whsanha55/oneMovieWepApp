package domain.movie;

public class RoleVO {
	
	int roleNo;
	String roleName;
	
	public RoleVO() {
		super();
	}

	public int getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RoleVO [roleNo=" + roleNo + ", roleName=" + roleName + "]";
	}
	
	
	

}
