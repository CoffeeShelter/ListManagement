package listmanagement.db;

import java.sql.Timestamp;

public class List {
	private Timestamp date;	// ��¥,�ð�
	private String add;		// �ּ�
	private String pNumber;	// �ڵ��� ��ȣ
	private String etc;		// ���
	
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
