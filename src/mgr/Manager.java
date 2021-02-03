package mgr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager<T extends Manageable> {
	public ArrayList<T> stList = new ArrayList<>();
	
	public void readAll(String filename, Factory<T> fac) {
		T st = null;
		Scanner fs = openFile(filename);
		while (fs.hasNext()) {
			st = fac.create();
			st.read(fs);
			if (stList.contains(st))
				continue;
			stList.add(st);
		}

		fs.close();
	}

	public void printAll() {
		for (T st : stList)
			st.print();
	}

	public Scanner openFile(String filename) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		scan.nextLine();
		return scan;
	}



	public T find(String kwd) {
		for (T a : stList)
			if (a.matches(kwd))
				return a;
		return null;
	}
	public T find(int n) {
		return stList.get(n);
	}

	public ArrayList<T> searchAll(String kwd) {
		ArrayList<T> arr = new ArrayList<T>();
		for (T a : stList)
			if (a.matches(kwd))
				arr.add(a);
		return arr;
	}
	public ArrayList<T> searchAll(int N) {
		ArrayList<T> arr = new ArrayList<T>();
		for (T a : stList)
			if (a.matches(N))
				arr.add(a);
		return arr;
	}
}
