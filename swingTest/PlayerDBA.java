package swingTest;
//db�� ����
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDBA {
	String url, user, pwd;
	
	//db����: �����ڰ� ����. url,user,pwd�� ������ ��� ���Ŷ� ���������� ����.
	public PlayerDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url="jdbc:oracle:thin:@localhost:1521:xe"; //1521:����Ŭ�� �� ��Ʈ ��ȣ
			user="scott";
			pwd="1234";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//�߰�
	public void playerInsert(Player p) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="INSERT INTO player "
					+ "VALUES (player_seq.nextval,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getBirth());
			ps.setDouble(3, p.getHeight());
			ps.setDouble(4, p.getWeight());
			ps.setString(5, p.getKind());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,ps);
		}
		
	}
	
	//�󼼺���
	public Player playerDetail(int num) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Player p=null;
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="SELECT*FROM player WHERE num="+num;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) { //rs�� �ִٸ� p�� �� ������ ��
				p=new Player(); //Player ��ü ������ְ� ���� ���� Ȱ��
				p.setNum(rs.getInt("num"));
				p.setName(rs.getString("name"));
				p.setBirth(rs.getString("birth"));
				p.setHeight(rs.getDouble("height"));
				p.setWeight(rs.getDouble("weight"));
				p.setKind(rs.getString("kind"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		} 
		return p;
	}
	
	//����
	public ArrayList<Player> playerView() { //arr���� PlayerSwing2�� ���������. ���ϰ��� ArrayList<Player>
		Connection con=null; //�����ϴ� ��ü���� ���߿� �ݵ�� �ݾ��ֱ� (�ݱ� �޼ҵ� ���� ���)
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Player> arr=new ArrayList<Player>(); //rs.next()�� ���� ���� �����ؾ��� ��=ArrayList
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="select * from player order by num desc";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Player p=new Player(); //Player ��ü ������ְ� ���� ���� Ȱ��
				p.setNum(rs.getInt("num"));
				p.setName(rs.getString("name"));
				p.setBirth(rs.getString("birth"));
				p.setHeight(rs.getDouble("height"));
				p.setWeight(rs.getDouble("weight"));
				p.setKind(rs.getString("kind"));
				arr.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //try, catch�� �����ϵ� �ݵ�� ����. �ݱ� �޼ҵ� ȣ��
			closeConnection(con,st,rs);
		}
		return arr;
	}
	
	//����
	public void playerUpdate(Player p) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			String sql="UPDATE player SET name=?, birth=?, "
					+ "height=?, weight=?,kind=? where num=?";
			con=DriverManager.getConnection(url,user,pwd);
			ps=con.prepareStatement(sql);
				ps.setString(1, p.getName());
				ps.setString(2, p.getBirth());
				ps.setDouble(3, p.getHeight());
				ps.setDouble(4, p.getWeight());
				ps.setString(5, p.getKind());
				ps.setInt(6, p.getNum());
				ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		} 
	}
	
	//����
	public void playerDelete(int num) {
		Connection con=null;
		Statement st=null;
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="DELETE FROM palyer WHERE num="+num;
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	//�˻�
	public ArrayList<Player> playerSearch(String key, String word) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Player>arr=new ArrayList<Player>(); //��ü����� �Ȱ����� sql���� �ٸ�
		try {
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			String sql="SELECT * FROM player WHERE "
					+key+" LIKE '%"+word+"%'";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Player p=new Player(); 
				p.setNum(rs.getInt("num"));
				p.setName(rs.getString("name"));
				p.setBirth(rs.getString("birth"));
				p.setHeight(rs.getDouble("height"));
				p.setWeight(rs.getDouble("weight"));
				p.setKind(rs.getString("kind"));
				arr.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	//�ݱ� (����)�޼ҵ�
	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(con!=null) con.close();
			if(ps!=null) ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}