package UI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class MovieHelperLogin extends JFrame{
	static JFrame  f;
	JPanel panel;
    JLabel user_label, password_label;
    JTextField userName_input;
    JPasswordField password_input;
    JButton login, createAccount;
    
    
    public static void main(String[] args) 
    {
        new MovieHelperLogin();
    }
	
    
    public MovieHelperLogin()
    {
    	f = new JFrame("경기대 영화 예매 도우미 로그인");
    	
    	// 아이디
        user_label = new JLabel("ID");
        userName_input = new JTextField();
        
        // 비밀번호
        password_label = new JLabel("Password :");
        password_input = new JPasswordField();

        // 로그인&회원가입 버튼
        login = new JButton("로그인");
        createAccount = new JButton("회원가입");
        
        // 기본 틀
        panel = new JPanel(new GridLayout(3, 1));
        

        panel.add(user_label);
        panel.add(userName_input);
        
        panel.add(password_label);
        panel.add(password_input);
        
        // 버튼 이벤트 호출 연동
        panel.add(createAccount);
        panel.add(login);
        
        createAccount.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	f.setVisible(false); // 창을 보이지만 않게함
                	//f.dispose(); // 창 자체를 포기화 및 종료 *!
                CreateAccount ca = new CreateAccount();
            }
        });
        
        login.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	String userName = userName_input.getText();
            	char getPassword[] = password_input.getPassword();
                String password = String.valueOf(getPassword);
                if (userName.trim().equals("admin") && password.trim().equals("admin")) {
                	f.dispose();
                	// (!)로그인 정보 추가하기
 //               	MyFrame.repaint();
                	
                
                } else {
                	 JOptionPane.showMessageDialog(login,"잘못된 아이디 또는 비밀번호입니다.","로그인 실패",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        f.add(panel);
        f.setSize(400, 100);
        f.setVisible(true);
        
    }
}
