package bean;

public class account {

	String id;
	String fintype;
	String amount;
	String date;
	String note;
	String clasx;
	public String getId() {
		return id;
	}
	public account(String id, String fintype, String amount, String date, String note, String clasx) {
		super();
		this.id = id;
		this.fintype = fintype;
		this.amount = amount;
		this.date = date;
		this.note = note;
		this.clasx = clasx;
	}
	public account() {
		// TODO Auto-generated constructor stub
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClasx() {
		return clasx;
	}
	public void setClasx(String clasx) {
		this.clasx = clasx;
	}
	public String getFintype() {
		return fintype;
	}
	public void setFintype(String fintype) {
		this.fintype = fintype;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
