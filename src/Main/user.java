package Main;

import java.util.ArrayList;
import java.util.Scanner;
import mgr.Manageable;
import movie.show;
import movie.Movie;

public class user implements Manageable{
	private String id;
	private String name;
	private String birthday;
	private String password;
	private ArrayList<Integer> showNo = new ArrayList<>();
	private ArrayList<show> myMovieTime = new ArrayList<>();
	
	
	//파일 리드
	public void read(Scanner scan) {
		id= scan.next();
		name=scan.next();
		birthday=scan.next();
		password=scan.next();
		int buf = scan.nextInt();
		while(buf !=0) {
			showNo.add(buf);
			myMovieTime.add(Main.shMgr.find(buf-1));
			buf = scan.nextInt();
		}	
	}
	public String getID() {	return id;	}
	public String getName() {	return name;}
	//회원가입
	public void newlogin(String id,String name,String birthday,String password) {
		this.id=id;
		this.name = name;
		this.birthday =birthday;
		this.password = password;
	}
	
	//디버깅 print
	public void print() {
		System.out.printf("ID:%s 비밀번호:%s 이름:%s 생일:%s 찜 목록:",id,password,name,birthday);
		for(show s:myMovieTime) {
			System.out.print(s.getMovie().getName() +" ");
		}
		System.out.println();
	}
	
	public boolean matches(String kwd) {
		return id.equals(kwd);
	}
	public boolean matches(int N) {
		return false;
	}
	//password 확인
	public boolean passwordlogin(String kwd) {
		return password.equals(kwd);
	}
	
	//파일 업데이트 함수 실행
	public void addMyMovie(show myShow) {
		
		myMovieTime.add(myShow);
	}
	public void delMyMovie(show myShow) {
		
		myMovieTime.remove(myShow);
	}
	
	public String upDateString() {
		String outData = id+" "+name+" "+birthday+" "+password+" ";
		for(int i:showNo) {
			outData = outData + Integer.toString(i) + " ";
		}
		outData = outData + "0";
		return  outData;
	}
	
}
