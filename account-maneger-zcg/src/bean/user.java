package bean;

public class user {
	private String id;			   //Ñ§ºÅ
	private String name;       //ÐÕÃû
	private String pwd;
	private String issuper;
	private String supercode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getIssuper() {
		return issuper;
	}
	public void setIssuper(String issuper) {
		this.issuper = issuper;
	}
	public String getSupercode() {
		return supercode;
	}
	public void setSupercode(String supercode) {
		this.supercode = supercode;
	}
	public user(String id, String name, String pwd, String issuper, String supercode) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.issuper = issuper;
		this.supercode = supercode;
	}
	public user() {
		super();
	}
	
	
	

}
