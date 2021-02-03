package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.SpringLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import Main.*;
import movie.*;

public class MyFrame extends JFrame {
	private JTextField searchField;
	

	public MyFrame(Main main) {
		ImageIcon icon = new ImageIcon("background.png");
		ImageIcon header = new ImageIcon("header.png");
		setTitle("경기대 영화 예매 도우미");
		getContentPane().setLayout(null);

		JPanel theMain = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		JPanel theMain2 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		JPanel topLogin = new JPanel();
		topLogin.setBackground(new Color(44, 44, 44));
		topLogin.setBounds(0, 0, 900, 38);
		getContentPane().add(topLogin);
		topLogin.setLayout(null);

		JLabel loginLabel = new JLabel("로그인");
		loginLabel.setBounds(808, 10, 60, 16);
		topLogin.add(loginLabel);
		loginLabel.setFont(new Font("돋움", Font.BOLD, 13));
		loginLabel.setForeground(new Color(255, 255, 255));
		loginLabel.setBackground(new Color(255, 255, 255));

		JLabel zzimLabel = new JLabel("찜목록");
		zzimLabel.setBounds(740, 10, 50, 16);
		topLogin.add(zzimLabel);
		zzimLabel.setFont(new Font("돋움", Font.BOLD, 13));
		zzimLabel.setForeground(new Color(255, 255, 255));
		zzimLabel.setBackground(new Color(255, 255, 255));
		zzimLabel.setVisible(false);

		JPanel topSearch = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(header.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		topSearch.setBackground(new Color(61, 61, 61));
		topSearch.setBounds(0, 38, 900, 48);
		getContentPane().add(topSearch);
		topSearch.setLayout(null);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "오늘");
		p.put("text.month", "달");
		p.put("text.year", "년");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setForeground(Color.WHITE);
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
		datePicker.getJFormattedTextField().setBackground(new Color(39, 39, 39));
		datePicker.getJFormattedTextField().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		datePicker.setBounds(525, 10, 143, 28);
		topSearch.add(datePicker);
		String date;
		
		
		searchField = new JTextField();
		searchField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				searchField.setText("");
			}
		});
		searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		searchField.setText("통합 검색");
		searchField.setForeground(Color.WHITE);
		searchField.setBackground(new Color(39, 39, 39));
		searchField.setHorizontalAlignment(SwingConstants.CENTER);
		searchField.setBounds(706, 10, 109, 27);
		topSearch.add(searchField);
		searchField.setColumns(10);

		JButton searchButton = new JButton("  ");
		searchButton.setBorder(null);
		searchButton.setBorderPainted(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setOpaque(false);
		searchButton.setBounds(832, 10, 27, 28);
		topSearch.add(searchButton);

		JSlider slider = new JSlider();

		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setValue(0);
		slider.setMaximum(10);
		slider.setBackground(new Color(39, 39, 39));
		slider.setBounds(373, 13, 116, 26);
		topSearch.add(slider);

		JLabel starValue = new JLabel("평점");
		starValue.setForeground(Color.WHITE);
		starValue.setBounds(346, 11, 57, 28);
		topSearch.add(starValue);

		theMain.setBorder(new EmptyBorder(20, 30, 20, 30));
		theMain.setBounds(0, 86, 884, 735);
		getContentPane().add(theMain);
		theMain.setLayout(null);

		theMain2.setBorder(new EmptyBorder(20, 30, 20, 30));
		theMain2.setBounds(0, 86, 884, 735);
		getContentPane().add(theMain2);
		theMain2.setLayout(null);
		theMain2.setVisible(false);

		ImageIcon[] img = new ImageIcon[100];
		JPanel[] movieList = new JPanel[100];
		JLabel[] imageLabel = new JLabel[100];
		JPanel[] movieButton = new JPanel[100];
		JLabel[] labelTime = new JLabel[100];
		JLabel[] labelReser = new JLabel[100];
		JLabel detailImage = new JLabel();
		JLabel[] timeList;
		JLabel dateCheck = new JLabel ();
		JLabel[] detailInfo = new JLabel[6];
		for (int i = 0; i < 6; i++)
			detailInfo[i] = new JLabel("");

		ArrayList<Movie> m=main.getMovie();
		int index=m.size();
		Movie selm=null;

		for (int i = 0; i < index; i++) {
		
			img[i] = new ImageIcon( m.get(i).rank + "_small.jpg"); // �̹��� ����
			movieList[i] = new JPanel();
			movieList[i].setLayout(null);
			movieList[i].setBounds(10 + (178 * (i % 5)), 15 + (230 * (i / 5)), 147, 212);
			theMain.add(movieList[i]);

			imageLabel[i] = new JLabel(img[i]);
			imageLabel[i].setHorizontalAlignment(SwingConstants.LEADING);
			imageLabel[i].setBounds(0, 0, 147, 190);
			movieList[i].add(imageLabel[i]);

			int a = i;
			selm=m.get(a);
			
			imageLabel[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					imageLabel[a].setToolTipText("영화이름"); // ���콺���� �ؽ�Ʈ
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					Movie mo=m.get(a);
					selm=mo;
					Image modImg = img[a].getImage();
					Image modedImg = modImg.getScaledInstance(240, 310, java.awt.Image.SCALE_SMOOTH);
					ImageIcon modedIcon = new ImageIcon(modedImg);
					detailImage.setIcon(modedIcon);
					theMain.setVisible(false);
					theMain2.setVisible(true);
					setSize(890, 600);
					String selDate = "선택한 날짜 : " + "11/5"; // SAMPLE (!) ������ ��¥�� ǥ���ϱ�
					dateCheck.setText(selDate);
					// SAMPLE (!) ������ ��ȭ�� ���� ǥ���ϱ�
					detailInfo[0].setText(mo.getName());
					detailInfo[1].setText("개봉일   " + mo.releaseDate);
					
					String tmp=mo.genre.get(0);
					for(int i=1;i<mo.genre.size();i++)
						tmp=tmp+(","+mo.genre.get(i));
					detailInfo[2].setText("장르   " + tmp);
					detailInfo[3].setText("감독   " + mo.director);
					tmp=mo.actor.get(0);
					for(int i=1;i<mo.actor.size();i++)
						tmp=tmp+(","+mo.actor.get(i));
					detailInfo[4].setText("출연   " +tmp);
					detailInfo[5].setText("평점  " + mo.evlaue + "/10");

				}
			});

			movieButton[i] = new JPanel();
			movieButton[i].setLayout(null);
			movieButton[i].setBackground(new Color(32, 32, 32));
			movieButton[i].setBounds(0, 189, 147, 23);
			movieList[i].add(movieButton[i]);

			labelTime[i] = new JLabel("상세정보");
			labelTime[i].setForeground(new Color(183, 183, 183));
			labelTime[i].setFont(new Font("굴림", Font.BOLD, 12));
			labelTime[i].setBounds(5, 2, 80, 20);
			movieButton[i].add(labelTime[i]);

			labelTime[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Desktop.getDesktop()
						.browse(new URL("https://movie.naver.com/movie/bi/mi/basic.nhn?code=189053").toURI()); // 세부정보링크
					} catch (Exception e1) {
					}
				}
			});

			labelReser[i] = new JLabel("예매");
			labelReser[i].setForeground(new Color(183, 183, 183));
			labelReser[i].setFont(new Font("굴림", Font.BOLD, 12));
			labelReser[i].setBounds(110, 2, 40, 20);
			movieButton[i].add(labelReser[i]);

			labelReser[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Desktop.getDesktop()
						.browse(new URL("http://ticket.movie.naver.com/Ticket/Reserve.aspx?m_id=M000075354&t_tab=1").toURI()); // 세부정보링크
					} catch (Exception e1) {
					}
				}
			});
		}

		//			theMain2.setBounds(0, 86, 884, 500);

		JLabel undo = new JLabel("뒤로가기");
		undo.setForeground(Color.WHITE);
		undo.setFont(new Font("굴림", Font.BOLD, 20));
		undo.setBounds(20, 10, 100, 30);

		detailImage.setBounds(270, 10, 240, 310);

		detailInfo[0].setBounds(20, 60, 200, 50);
		detailInfo[0].setForeground(Color.WHITE);
		detailInfo[0].setFont(new Font("굴림", Font.BOLD, 40));

		for (int i = 1; i<6; i++) {
			detailInfo[i].setBounds(20, 100+(i*30), 200, 35);
			detailInfo[i].setForeground(Color.WHITE);
			detailInfo[i].setFont(new Font("굴림", Font.BOLD, 15));
		}


		for (int i = 0; i<6; i++)
			theMain2.add(detailInfo[i]);

		theMain2.add(undo);
		theMain2.add(detailImage);

		ImageIcon[] radioIcon = new ImageIcon[3];
		radioIcon[0] = new ImageIcon("cgv.png");
		radioIcon[1] = new ImageIcon("lotte.png");
		radioIcon[2] = new ImageIcon("mega.png");
		JLabel radioImage[] = new JLabel[3];
		JRadioButton radio[] = new JRadioButton[3];
		ButtonGroup radioGroup = new ButtonGroup();

		radioImage[0] = new JLabel();
		radioImage[0].setIcon(radioIcon[0]);
		radioImage[0].setBounds(540, 30, 90, 90);
		theMain2.add(radioImage[0]);

		radio[0] = new JRadioButton();
		radio[0].setBounds(575, 130, 15, 15);
		radio[0].setOpaque(false);
		radioGroup.add(radio[0]);
		theMain2.add(radio[0]);
		radio[0].setSelected(true);

		radioImage[1] = new JLabel();
		radioImage[1].setIcon(radioIcon[1]);
		radioImage[1].setBounds(650, 30, 90, 90);
		theMain2.add(radioImage[1]);

		radio[1] = new JRadioButton();
		radio[1].setBounds(685, 130, 15, 15);
		radio[1].setOpaque(false);
		radioGroup.add(radio[1]);
		theMain2.add(radio[1]);
		
		
		radioImage[2] = new JLabel();
		radioImage[2].setIcon(radioIcon[2]);
		radioImage[2].setBounds(760, 30, 90, 90);
		theMain2.add(radioImage[2]);

		radio[2] = new JRadioButton();
		radio[2].setBounds(795, 130, 15, 15);
		radio[2].setOpaque(false);
		radioGroup.add(radio[2]);
		theMain2.add(radio[2]);
		String Cinema="cgv";
		
		dateCheck.setBounds(600, 160, 300, 30);
		dateCheck.setForeground(Color.WHITE);
		dateCheck.setFont(new Font("굴림", Font.BOLD, 20));
		theMain2.add(dateCheck);

		JLabel pleaseSelect = new JLabel("아래 시간대 중에서 선택하십시오.");
		pleaseSelect.setBounds(530, 180, 350, 30);
		pleaseSelect.setForeground(Color.WHITE);
		pleaseSelect.setFont(new Font("굴림", Font.BOLD, 20));
		theMain2.add(pleaseSelect);

		// SAMPLE (!) 해당 시간대, 남은 좌석, 전체 좌석
		ArrayList<show> sh= main.getShow(Cinema,selm);
		int index2= sh.size();
		System.out.print(index2);
		timeList= new JLabel[index2];

		for (int s = 0; s<index2 ; s++) {
			show psh=sh.get(s);
			int a = s;
			timeList[s] = new JLabel(psh.time + " (남은 좌석 : " + psh.reserve + "/" + psh.totalchair + ")" );
			timeList[s].setForeground(Color.WHITE);
			timeList[s].setFont(new Font("굴림", Font.BOLD, 15));
			timeList[s].setBounds(540, 220+(25*s), 200, 15);
			theMain2.add(timeList[s]);

			timeList[s].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (main.islogin()) {
						MovieHelperLogin mhl = new MovieHelperLogin();
						zzimLabel.setVisible(true);
						loginLabel.setText("로그아웃");
					}
					else {

						if (true) // (!) 해당 시간이 이미 예약 되어있는가?
						{
							if (psh.reserve != psh.totalchair) {
								JOptionPane.showMessageDialog(timeList[a],"찜이 성공적으로 되었습니다.","찜 완료",JOptionPane.INFORMATION_MESSAGE);
								if (radio[0].isSelected()) {
									//cgv							
									}
								else if (radio[1].isSelected()) {
									//롯시
								}
								else {
									//메가박스
								}
								// (!) 찜 목록에 추가하기 					    
							}
							else {
								JOptionPane.showMessageDialog(timeList[a],"이미 매진 되었습니다.","찜 실패",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else JOptionPane.showMessageDialog(timeList[a],"이미 찜한 시간대입니다.","찜 오류",JOptionPane.INFORMATION_MESSAGE);
					}
				}				
			});

		}


		undo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theMain.setVisible(true);
				theMain2.setVisible(false);
				theMain.repaint();
				setSize(890, 820);
			}
		});

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				starValue.setText(Integer.toString(slider.getValue())+"점");
			}
		});

		//아래 두개에만 기능줘도 되는것 같음
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				theMain.setVisible(true);
				theMain2.setVisible(false);
				theMain.repaint(); //리스트 새로고침
			}
		}); //검색버튼 눌렀을 경우 (모양은 바꿀예정)

		loginLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


				if(loginLabel.getText() == "로그인") {
					MovieHelperLogin mhl = new MovieHelperLogin();
					zzimLabel.setVisible(true);
					loginLabel.setText("로그아웃");
				}	
				else if(loginLabel.getText() == "로그아웃") {
					zzimLabel.setVisible(false);
					loginLabel.setText("로그인");
					// (!) 로그인 정보 초기화					
				}
			}
		}); //로그인 버튼 눌렀을 경우

		zzimLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyPage mp = new MyPage();				
			}
		});


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(890, 820);
		setResizable(false);
	}
}

