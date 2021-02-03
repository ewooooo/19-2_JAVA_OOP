package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import UI.MyFrame;
import movie.*;
import mgr.Manager;

import mgr.Factory;

public class Main {
	Scanner scan = new Scanner(System.in);
	public static Manager<Movie> movieMgr = new Manager<>();
	public static Manager<show> shMgr = new Manager<show>();
	Manager<user> userMgr = new Manager<user>();
	
	
	user loginuser;
	show searchedshow;
	Movie searchedmovie;
	public static void main(String[] args) {
		Main main = new Main();
	
		main.mymain();
		main.getui();
		
	}


	void mymain() {
		movieMgr.readAll("input_movie.txt", new MoFactory());
		movieMgr.printAll();
		shMgr.readAll("input_show.txt", new shFactory());
		shMgr.printAll();
		userMgr.readAll("input_user.txt", new usFactory());
		userMgr.printAll();
	
		//login(scan);
		//searching();
	}

	void getui() {
		MyFrame mf = new MyFrame(this);
		mf.setVisible(true);
	}
////////////////////////////////////////////////////////////////////
	void searching() {

	}

	void login(Scanner scan) {
		String id;
		String password;
		user u;
		System.out.printf("아이디를 입력하시오>>");
		id = scan.next();
		System.out.printf("비밀번호를 입력하시오>>");
		password = scan.next();

		u = userMgr.find(id);
		if (u.passwordlogin(password))
			loginuser = u;
		System.out.printf("환영합니다. %s님\n", loginuser.getName());
	}

	void updateDB() {
		FileWriter fout = null;
		try {
			fout = new FileWriter("input_user.txt");
			for(user u: userMgr.stList) {
				String buf = u.upDateString();
				fout.write(buf, 0, buf.length());
				fout.write("\r\n", 0, 2);
			}
			fout.close();
		}catch(IOException e){
			System.out.println("입출력오류");
		}
	}
	public boolean islogin(){
		if(loginuser==null)
			return true;
		return false;
	}
	
	public ArrayList<Movie> getMovie(){
		
		return movieMgr.stList;
	}
	public ArrayList<show> getShow(String C, Movie selm){
		ArrayList<show> tmp= new ArrayList<>();
		for(show s : shMgr.searchAll(C))
			if(s.getMovie()==selm)
					tmp.add(s);
		return 	tmp;
	}
/////////////////////////////////////////////////////////////////////
	class shFactory implements Factory<show> {
		public show create() {
			return new show();
		}
	}

	class MoFactory implements Factory<Movie> {
		@Override
		public Movie create() {
			return new Movie();
		}
	}

	class usFactory implements Factory<user> {
		@Override
		public user create() {
			return new user();
		}
	}
}
