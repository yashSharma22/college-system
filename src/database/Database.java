package database;



import java.sql.*;
import java.util.*;

import college_system.Admin;
import college_system.Fee;
import college_system.Student;
import college_system.Teacher;
public class Database {
	private static final int NULL = 0;
	private Connection con = null;
	private PreparedStatement st;
	private ResultSet rs,rt,rw;

	public Database() {
		try {
			if (con == null) {
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
				if (con != null) {
					System.out.println("connection established");
					checkDefaultDatabase();
				}
			} else {
				System.out.println("connection alredy done");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public void checkDefaultDatabase() {
		try {
			
			st = con.prepareStatement("show databases");
			rs = st.executeQuery();
			boolean dbpresent=false;
			while(rs.next()) {
				if(rs.getString(1).equals("college_system")) {
					dbpresent=true;
					break;
				}
			}
			if (dbpresent)
			{
				
				rs=con.prepareStatement("use college_system").executeQuery();//diret execute the query
				
			}
			else
			{
				
				int n=con.prepareStatement("create database college_system").executeUpdate();//diret execute the query
				System.out.println(n);
				rs=con.prepareStatement("use college_system").executeQuery();//diret execute the query
				
			}
			createDefaultTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> getDatabaseTables() {//db ki table ki arraylist return krege search krne k liye
		ArrayList<String> tables = new ArrayList();
	
		try {
			st = con.prepareStatement("show tables");
			rs = st.executeQuery();
			while(rs.next()) {
				tables.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}
	public void createDefaultTable() { //isme table create kr rhe h agr phle se nhi h
		Map<String,String> tables = new HashMap<String,String>();
		tables.put("admin","(a_id int NOT NULL, password varchar(20),name varchar(20) NOT NULL,PRIMARY KEY(a_id))");
		tables.put("department","(d_id int NOT NULL AUTO_INCREMENT,name varchar(20) NOT NULL ,PRIMARY KEY(d_id)) AUTO_INCREMENT=1001");
		tables.put("teacher","(t_id int NOT NULL AUTO_INCREMENT,name varchar(20) NOT NULL,email varchar(20) NOT NULL,password varchar(20) NOT NULL,mobileno varchar(10) NOT NULL,d_id int NOT NULL,PRIMARY KEY(t_id),FOREIGN KEY (d_id) REFERENCES department(d_id)) AUTO_INCREMENT=2001");
		tables.put("student","(s_id int NOT NULL AUTO_INCREMENT,name varchar(20) NOT NULL,password varchar(20) NOT NULL,email varchar(20) NOT NULL,mobileno varchar(10) NOT NULL,gender varchar(10) NOT NULL,DOB varchar(10) NOT NULL,tenthpercentage int(10) NOT NULL,twelfthpercentage int(10) NOT NULL,d_id int NOT NULL,PRIMARY KEY(s_id),FOREIGN KEY (d_id) REFERENCES department(d_id)) AUTO_INCREMENT=3001");
		tables.put("feeadmin","(f_id int NOT NULL, password varchar(20),name varchar(20) NOT NULL,PRIMARY KEY(f_id))");
		ArrayList<String> dbtables = getDatabaseTables();

		
		
		tables.forEach((tablename,tablefields)->{
			if(!dbtables.contains(tablename)) {
				createTables(tablename,tablefields);
			}
		}); 
	}
	public void createTables(String name,String fields) {
		try {
		 int n=con.prepareStatement("create table "+name + fields ).executeUpdate();
		 
		 System.out.println("table created");
		 if(name.equals("admin")) {
			 con.prepareStatement("insert into admin values(1234,'1234','RD')").executeUpdate();

			 System.out.println("admin id inserted");
		 }
		 if(name.equals("department")) {
			
			 con.prepareStatement("insert into department values(NULL,'CSE')").executeUpdate();
			 con.prepareStatement("insert into department values(NULL,'ME')").executeUpdate();
			 con.prepareStatement("insert into department values(NULL,'ECE')").executeUpdate();
			 con.prepareStatement("insert into department values(NULL,'IT')").executeUpdate();
			 con.prepareStatement("insert into department values(NULL,'CIVIL')").executeUpdate();
			 con.prepareStatement("insert into department values(NULL,'EE')").executeUpdate();
			 System.out.println("department id inserted");
		 }
		 if(name.equals("feeadmin")) {
			 con.prepareStatement("insert into feeadmin values(4321,'1234','RD')").executeUpdate();

			 System.out.println("feeadmin id inserted");
		 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean adminlogin(int aid, String pass) {
		try {
			st= con.prepareStatement("select * from admin where a_id= ? and password=?");
			st.setInt(1, aid);
			st.setString(2, pass);
			rs= st.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return false;
	}
	public Admin adminDetail() {
		Admin a = new Admin();
		 try {
			a.setAid(rs.getInt(1));
			a.setName(rs.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return a;
	}
	public Map<Integer,String> getDepartment(){
		Map<Integer,String> m = new HashMap<Integer, String>();
		try {
			st = con.prepareStatement("select * from department");
			rs = st.executeQuery();
			while(rs.next()){
				m.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	public boolean addTeacher(Teacher t) {
		try {
			st = con.prepareStatement("insert into teacher values(?,?,?,?,?,?)");
			st.setInt(1,NULL);
			st.setString(2, t.getName());
			st.setString(3, t.getEmail());
			st.setString(4, t.getPassword());
			st.setString(5, t.getMobileno());
			st.setInt(6, t.getDeptid());
			st.executeUpdate();
			System.out.println("teacher data entered");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return true;
		// TODO Auto-generated method stub
		
	}
	public boolean teacherLogin(int tid, String pass) {
		try {
			st=con.prepareStatement("select * from teacher where t_id=? and password=?");
			st.setInt(1, tid);
			st.setString(2, pass);
			rs= st.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Teacher teacherDetail() {
		Teacher te = new Teacher();
		try {
			te.setTid(rs.getInt(1));
			te.setName(rs.getString(2));
			te.setEmail(rs.getString(3));
			te.setMobileno(rs.getString(5));
			te.setDeptid(rs.getInt(6));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return te;
	}
	public boolean addStudent(Student stu) {
		// TODO Auto-generated method stub
		try {
			st = con.prepareStatement("insert into Student values(?,?,?,?,?,?,?,?,?,?)");
			st.setInt(1,NULL);
			st.setString(2,stu.getName());
			st.setString(3, stu.getPassword());
			st.setString(4, stu.getEmail());
			st.setString(5, stu.getMobno());
			st.setString(6,stu.getGender());
			st.setString(7, stu.getDob());
			st.setInt(8,stu.getTenth());
			st.setInt(9,stu.getTwelfth() );
			st.setInt(10,stu.getDeptid());
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean studentlogin(String email, String pass) {
		// TODO Auto-generated method stub
		try {
			st= con.prepareStatement("select * from student where email=? and password=?");
			st.setString(1, email);
			st.setString(2, pass);
			rs = st.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Student studentDetail() {
		// TODO Auto-generated method stub
		Student stu = new Student();
		try {
			stu.setSid(rs.getInt(1));
			stu.setName(rs.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stu;
	}
	public boolean feeadminlogin(int fid, String pass) {
		// TODO Auto-generated method stub
		try {
			st= con.prepareStatement("select * from feeadmin where f_id= ? and password=?");
			st.setInt(1, fid);
			st.setString(2, pass);
			rs= st.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return false;
		
	}
	public Fee feeadminDetail() {
		Fee fe = new Fee();
		 try {
			fe.setFid(rs.getInt(1));
			fe.setName(rs.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return fe;
	}
	
	
	
}
