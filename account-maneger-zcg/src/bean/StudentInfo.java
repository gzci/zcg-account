package bean;
 
public class StudentInfo {
 
		private int id;			   //ѧ��
		private String name;       //����
		private int age;           //����
		private String gender;     //�Ա�
		private String major;      //רҵ
		private String pwd;
		public StudentInfo(int id, String name, int age, String gender, String major, String pwd) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.major = major;
			this.pwd = pwd;
		}
		
		public StudentInfo() {
			super();
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getMajor() {
			return major;
		}
		public void setMajor(String major) {
			this.major = major;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
}
