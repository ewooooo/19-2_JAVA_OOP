package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPage extends JFrame {
	JFrame f;
	JLabel[] zzim, del;
	int max = 3; // SAMPLE (!) 찜 최대 개수
	String[] movie, date, time;
	int[] remSeat, allSeat;


	MyPage() 
	{ 
		f = new JFrame("경기대 영화 찜목록"); 
		
		ImageIcon icon = new ImageIcon("background.png");

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setLayout(null);

	
		
		
		zzim = new JLabel[max];
		del = new JLabel[max];
		movie = new String[max];
		date = new String[max];
		time = new String[max];
		remSeat = new int[max];
		allSeat = new int[max];
		
		
		// SAMPLE (!) 찜한 영화 정보 (영화 이름, 날짜, 시간, 남은 잔여석, 총 잔여석)
		movie[0] = "겨울왕국2";	movie[1] = "스파이더맨";	movie[2] = "블랙 머니";
		date[0] = "12/1";	date[1] = "12/2";	date[2] = "12/3";
		time[0] = "11:30";	time[1] = "12:15";	time[2] = "13:00";
		remSeat[0] = 70;	remSeat[1] = 85;    remSeat[2] = 37;
		allSeat[0] = 230;	allSeat[1] = 170;	allSeat[2] = 200;

		for (int i = 0; i < max; i++) {
			String info = movie[i] + " / " + date[i] + " / " + time[i] + "  (남은 좌석 : " + remSeat[i] + "/" + allSeat[i] + ")";
			
			zzim[i] = new JLabel("");
			del[i] = new JLabel("삭제");

			zzim[i].setBounds(30,30+(i*50),300,40);
			zzim[i].setText(info);
			zzim[i].setForeground(Color.WHITE);
			del[i].setBounds(400,35+(i*50),70,30);
			del[i].setForeground(Color.WHITE);
			panel.add(zzim[i]);
			panel.add(del[i]);

		}

		for (int i = 0; i < max; i++) {
			int a = i;
			del[i].addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					zzim[a].setVisible(false);
					del[a].setVisible(false);
					// (!) 해당 영화 정보 삭제
					for (int i2 = a; i2 < max-1; i2++) {
						int y = zzim[i2+1].getY();
						zzim[i2+1].setBounds(30,y-50,300,40);
						del[i2+1].setBounds(400,y-50,70,30);
					}			
				}
			});
		}



		f.add(panel);
		f.setSize(500, 100+(50*max)); 
		f.setVisible(true);  

	} 
}
