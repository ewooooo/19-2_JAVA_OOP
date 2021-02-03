package movie;

import java.util.Scanner;
import java.util.Random;
import mgr.Manageable;
import Main.Main;

public class show implements Manageable {
	Movie movie;
	public String Cinema;
	public String Date;
	public String time;
	public int totalchair;
	public int reserve;
	public int chair[];
	public static int count =0;
	public int showNo;
	

	public void read(Scanner scan) {
		count++;
		showNo = count;
		int n=scan.nextInt();
		if(n==0)
			Cinema="메가박스";
		else if(n==1)
			Cinema="롯데시네마";
		else if (n==2)
			Cinema="cgv";
		Date=scan.next();
		n=scan.nextInt();
		movie=Main.movieMgr.find(n-1);
		totalchair=scan.nextInt();
		chair=new int[totalchair];
		time=scan.next();
		for(int i=0;i<totalchair;i++)
			chair[i]=0;
		randomreserve();

	}
	public Movie getMovie() {
		return movie;
	}
	public void print() {
		System.out.printf("%s %s %s %s 총좌석:%d, 잔여석:%d\n", Cinema, movie.name, Date, time,
				totalchair, totalchair-reserve);
	}



	public boolean matches(String kwd) {
		if (movie.name.contains(kwd))
				return true;
		if (kwd.equals(Date))
				return true;
		if (kwd.equals(Cinema))
				return true;
		
			return false;
	}
	

	
	public void randomreserve() {
		Random random=new Random();
		reserve=random.nextInt(50);
		int n;
		for(int i=0;i<reserve;i++) {
			while(true) {
			n=random.nextInt(totalchair);
			if(chair[n]==1)
				continue;
			chair[n]=1;
			break;
			}
		}
		
	}
	
	public boolean addR(int chairnum) {
		if(chair[chairnum-1]==1)
			return false;
		chair[chairnum-1]=1;
		reserve+=1;
		return true;
	}
	
	public boolean removeR(int chairnum) {
		if(chair[chairnum-1]==0)
			return false;
		chair[chairnum-1]=0;
		reserve--;
		return true;
	}
	public boolean matches(int N) {
		if(this.movie.matches(N))
			return true;
		return false;
	}
	
}
