package database;



import java.sql.*;
import java.util.*;
import java.util.Date;

import college_system.Admin;
import college_system.Exam;
import college_system.Fee;
import college_system.Question;
import college_system.Student;
import college_system.Teacher;
public class Database {
	private static final int NULL = 0;
	private Connection con = null;
	private PreparedStatement st;
	private ResultSet rs;

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
		tables.put("student","(s_id int NOT NULL AUTO_INCREMENT,name varchar(20) NOT NULL,password varchar(20) NOT NULL,email varchar(20) NOT NULL,mobileno varchar(10) NOT NULL,gender varchar(10) NOT NULL,DOB varchar(10) NOT NULL,tenthpercentage int(10) NOT NULL,twelfthpercentage int(10) NOT NULL,d_id int NOT NULL,totalfee int,submitedfee int,PRIMARY KEY(s_id),FOREIGN KEY (d_id) REFERENCES department(d_id)) AUTO_INCREMENT=3001");
		tables.put("feeadmin","(f_id int NOT NULL, password varchar(20),name varchar(20) NOT NULL,PRIMARY KEY(f_id))");
		tables.put("exam", "(e_id int NOT NULL AUTO_INCREMENT,examName varchar(10) NOt NULL,d_id int NOT NULL,date DATE NOT NULL,time TIME NOT NULL,noOfQues int NOT NULL,pmarks int NOT NULL,nmarks int NOT NULL,duration_time int NOT NULL,PRIMARY KEY(e_id),FOREIGN KEY (d_id) REFERENCES department(d_id)) AUTO_INCREMENT=4001");
		tables.put("result","(s_id int NOT NULL,noOfCorrectQues int NOT NULL,noOfIncorrectQues int NOT NULL,e_id int NOT NULL,FOREIGN KEY(e_id) REFERENCES exam(e_id))");
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
	public Map<Integer,String> getExamIdName(){
		Map<Integer,String> m = new HashMap<Integer, String>();
		try {
			st = con.prepareStatement("select * from exam");
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
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
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
			st = con.prepareStatement("insert into Student values(?,?,?,?,?,?,?,?,?,?,?,?)");
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
			st.setInt(11, 100000);
			st.setInt(12, 0);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public int studentlogin(String email, String pass) {
		// TODO Auto-generated method stub
		try {
			st= con.prepareStatement("select * from student where email=? and password=?");
			st.setString(1, email);
			st.setString(2, pass);
			rs = st.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public Student studentDetail(int sid) {
		// TODO Auto-generated method stub
		
		Student stu = new Student();
		try {
			st= con.prepareStatement("select * from student where s_id=?");
			st.setInt(1, sid);
			rs=st.executeQuery();
			rs.next();
			stu.setSid(rs.getInt(1));
			stu.setName(rs.getString(2));
			stu.setEmail(rs.getString(4));
			stu.setMobno(rs.getString(5));
			stu.setGender(rs.getString(6));
			stu.setDob(rs.getString(7));
			stu.setTenth(rs.getInt(8));
			stu.setTwelfth(rs.getInt(9));
			stu.setDeptid(rs.getInt(10));
			stu.setTotalfees(rs.getInt(11));
			stu.setSubmitedfees(rs.getInt(12));

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
	public boolean addExam(Exam ex) {
		try {
			st = con.prepareStatement("insert into exam values(?,?,?,?,?,?,?,?,?)");
			st.setInt(1, NULL);
			st.setString(2, ex.getExamName());
			st.setInt(3, ex.getDeptid());
			st.setDate(4, ex.getDate());
			st.setTime(5, ex.getTime());
			st.setInt(6, ex.getNoOfQues());
			st.setInt(7,ex.getPmarks());
			st.setInt(8, ex.getNmarks());
			st.setInt(9, ex.getDtime());
			st.executeUpdate();
			System.out.println("exam data entered");
			st= con.prepareStatement("select max(e_id) from exam");
			rs=st.executeQuery();
			rs.next();
			createTables("exques"+rs.getInt(1),"(id int AUTO_INCREMENT NOT NULL,ques varchar(1000),op1 varchar(100),op2 varchar(100),op3 varchar(100),op4 varchar(100),coption int,PRIMARY KEY(id))");
			System.out.println("exam question table created");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addQues(int exid,Question q) {
		try {
			st= con.prepareStatement("insert into exques"+exid+" values(?,?,?,?,?,?,?)");
			st.setInt(1,NULL);
			st.setString(2,q.getQues());
			st.setString(3, q.getOp1());
			st.setString(4, q.getOp2());
			st.setString(5, q.getOp3());
			st.setString(6, q.getOp4());
			st.setInt(7, q.getCoption());
			
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public ArrayList<Exam> getOnlineExamLinks(int did){
		ArrayList<Exam> lx = new ArrayList<Exam>();
		Date dt = null;
		Time tt = null;
		java.sql.Date ds = null;
		try {
			 dt = new Date();
			 ds= new java.sql.Date(dt.getTime());
			tt = new Time(dt.getTime());
			st = con.prepareStatement("select * from exam where date=? and time<=? and d_id=?");
			st.setDate(1, ds);
			st.setTime(2, tt);
			st.setInt(3, did);
			System.out.println(did);
			rs = st.executeQuery();
			while(rs.next()) {
				Exam ex = new Exam();
				ex.setExamName(rs.getString(2));
				ex.setDtime(rs.getInt(9));
				ex.setNoOfQues(rs.getInt(6));
			ex.setExamid(rs.getInt(1));
			lx.add(ex);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lx;
	}
	public Exam getOnlineExamDetails(int eid) {
		Exam e = new Exam();
		try {
			
			st=con.prepareStatement("select * from exam where e_id=?");
			st.setInt(1, eid);
			rs=st.executeQuery();
			rs.next();
			e.setExamid(rs.getInt(1));
			e.setExamName(rs.getString(2));
			e.setDeptid(rs.getInt(3));
			e.setDate(rs.getDate(4));
			e.setTime(rs.getTime(5));
			e.setNoOfQues(rs.getInt(6));
			e.setPmarks(rs.getInt(7));
			e.setNmarks(rs.getInt(8));
			e.setDtime(rs.getInt(9));
		} catch (SQLException l) {
			// TODO Auto-generated catch block
			l.printStackTrace();
		}
		return e;
		
	}
	//it gets all the question from database
	public ArrayList<Question> getAllQuestion(int eid) {
		ArrayList<Question> aq = new ArrayList<Question>();
		try {
			rs=con.prepareStatement("select * from exques"+eid).executeQuery();
			while(rs.next()) {
				Question q= new Question();
				q.setQid(rs.getInt(1));
				q.setQues(rs.getString(2));
				q.setCoption(rs.getInt(7));
				q.setOp1(rs.getString(3));
				q.setOp2(rs.getString(4));
				q.setOp3(rs.getString(5));
				q.setOp4(rs.getString(6));
				aq.add(q);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return aq;

	}
	//it fetch the question from database for online exam and return a arraylist
	public ArrayList<Question> getExamQuestion(Exam e){
		ArrayList<Question> dbques = getAllQuestion(e.getExamid());
		ArrayList<Question> examques = new ArrayList<Question>();
		for(int i=0;i<e.getNoOfQues();i++) {
			int rno = (int) (dbques.size()*Math.random());
			examques.add(dbques.get(rno));
			dbques.remove(rno);
			
		}
		return examques;
	}
	public boolean saveResult(int s_id,int e_id,int crrans,int incrrans) {
		try {
			st=con.prepareStatement("insert into result values(?,?,?,?)");
			st.setInt(1, s_id);
			st.setInt(2,crrans);
			st.setInt(3, incrrans);
			st.setInt(4,e_id);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public ResultSet selectExam() {
		try {
			st = con.prepareStatement("select * from exam");
			rs=st.executeQuery();
			//return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public boolean updateExam(Exam ex) {
		try {
			st = con.prepareStatement("update exam set examName=?,d_id=?,date=?,time=?,noOfQues=?,pmarks=?,nmarks=?,duration_time=? where e_id=? ");
			st.setString(1, ex.getExamName());
			st.setInt(2, ex.getDeptid());
			st.setDate(3, ex.getDate());
			st.setTime(4, ex.getTime());
			st.setInt(5, ex.getNoOfQues());
			st.setInt(6,ex.getPmarks());
			st.setInt(7, ex.getNmarks());
			st.setInt(8, ex.getDtime());
			st.setInt(9, ex.getExamid());

			st.executeUpdate();
			
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateQues(int examId, Question q) {
		try {
			st= con.prepareStatement("update exques"+examId+" set ques=?,op1=?,op2=?,op3=?,op4=?,coption=? where id=? ");
		//	st.setInt(1,NULL);
			st.setString(1,q.getQues());
			st.setString(2, q.getOp1());
			st.setString(3, q.getOp2());
			st.setString(4, q.getOp3());
			st.setString(5, q.getOp4());
			st.setInt(6, q.getCoption());
			st.setInt(7, q.getQid());
			
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteQuestio(int examid, int qid) {
		try {
			st= con.prepareStatement("delete from exques"+examid+" where id="+qid);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean feesubmit(int amount, int sid) {
		try {
			st= con.prepareStatement("update student set submitedfee=? where s_id=?");
			st.setInt(1, amount);
			st.setInt(2, sid);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
