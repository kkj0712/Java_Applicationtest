package swingTest;
//db와 연결
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDBA {
	String url, user, pwd;
	
	//db연결: 생성자가 해줌. url,user,pwd는 앞으로 계속 쓸거라서 전역변수로 빼줌.
	public PlayerDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url="jdbc:oracle:thin:@localhost:1521:xe"; //1521:오라클이 깔린 포트 번호
			user="scott";
			pwd="1234";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//추가
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
	
	//상세보기
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
			if(rs.next()) { //rs가 있다면 p에 다 담으면 됨
				p=new Player(); //Player 객체 만들어주고 게터 세터 활용
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
	
	//보기
	public ArrayList<Player> playerView() { //arr값을 PlayerSwing2에 돌려줘야함. 리턴값은 ArrayList<Player>
		Connection con=null; //연결하는 객체들은 나중에 반드시 닫아주기 (닫기 메소드 따로 사용)
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Player> arr=new ArrayList<Player>(); //rs.next()가 도는 동안 저장해야할 곳=ArrayList
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="select * from player order by num desc";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Player p=new Player(); //Player 객체 만들어주고 게터 세터 활용
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
		} finally { //try, catch문 수행하든 반드시 실행. 닫기 메소드 호출
			closeConnection(con,st,rs);
		}
		return arr;
	}
	
	//수정
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
	
	//삭제
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
	
	//검색
	public ArrayList<Player> playerSearch(String key, String word) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Player>arr=new ArrayList<Player>(); //전체보기와 똑같은데 sql문만 다름
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
	
	//닫기 (종료)메소드
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