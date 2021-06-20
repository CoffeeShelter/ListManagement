package listmanagement.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;

	public Vector<List> getList() {
		Vector<List> lists = new Vector<>();
		String SQL = "select * from list";

		try {
			pstmt = DBManagement.getConn().prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				List list = new List();
				list.setDate(rs.getTimestamp("_date"));
				list.setAdd(rs.getString("_add"));
				list.setpNumber(rs.getString("pNumber"));
				list.setEtc(rs.getString("etc"));
				lists.add(list);
			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int insertList(List list) {
		String SQL = "insert into List values (?, ?, ?, ?)";
		try {
			pstmt = DBManagement.getConn().prepareStatement(SQL);

			pstmt.setTimestamp(1, list.getDate());
			pstmt.setString(2, list.getAdd());
			pstmt.setString(3, list.getpNumber());
			pstmt.setString(4, list.getEtc());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}
}
