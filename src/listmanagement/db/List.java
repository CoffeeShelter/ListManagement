package listmanagement.db;

import java.sql.Timestamp;

public class List {
	private Timestamp date;	// 날짜,시간
	private String add;		// 주소
	private String pNumber;	// 핸드폰 번호
	private String etc;		// 비고
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getpNumber() {
		return pNumber;
	}
	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	
}
