package movie;

import java.util.ArrayList;
import java.util.Scanner;

import mgr.Manageable;

public class Movie implements Manageable {
	public int rank;
	String name;
	double percent;
	int time;
	public double evlaue;
	public String releaseDate;
	public String director;
	public ArrayList<String> genre = new ArrayList<>();
	public ArrayList<String> actor = new ArrayList<>();

	public void read(Scanner scan) {
		rank = scan.nextInt();
		name = scan.nextLine();
		percent = scan.nextDouble();
		time = scan.nextInt();
		releaseDate = scan.next();
		director = scan.next();
		int no = scan.nextInt();
		for (int i = 0; i < no; i++) {
			String gen = scan.next();
			genre.add(gen);
		}
		no = scan.nextInt();
		for (int i = 0; i < no; i++) {
			String act = scan.next();
			actor.add(act);
		}
		evlaue = scan.nextDouble();
	}
	public String getName() {
		return name;
	}
	public void print() {
		System.out.printf("%d위 %s 예매율:%.2f%% 개봉일:%s 상영시간:%d 평점:%.2f 장르: ", rank, name, percent, releaseDate, time,
				evlaue);
		for (String g : genre)
			System.out.print(g + " ");
		System.out.printf("\n감독:%s 배우:", director);
		for (String a : actor)
			System.out.print(a + " ");
		System.out.println();
	}

	public boolean matches(String kwd) {
		if (rank == Integer.parseInt(kwd) && kwd.length() == 1)
			return true;
		else if (name.contains(kwd))
			return true;
		else if (director.contains(kwd))
			return true;
		else {
			for (String g : genre) {
				if (g.contains(kwd))
					return true;
			}
			for (String a : actor) {
				if (a.contains(kwd))
					return true;
			}
		}
		return false;
	}

	public boolean matches(int N) {
		if (this.evlaue >= N)
			return true;
		return false;
	}

}
