package UI;
import javax.swing.*;

import UI.MovieHelperLogin;

import java.awt.*;
import java.awt.event.*;

public class CreateAccount extends JFrame{
	JFrame  f;
	JPanel panel;
    JLabel name_label, user_label, password_label, birth_label;
    JTextField name_input, userName_input, birth_input;
    JPasswordField password_input;
    JButton create, cancel;
    
      
    public CreateAccount()
    {
    	f = new JFrame("경기대 영화 예매 도우미 회원가입");
    	
    	// 이름
        name_label = new JLabel("이름");
        name_input = new JTextField();
    	
    	// 아이디
        user_label = new JLabel("ID");
        userName_input = new JTextField();
        
        // 비밀번호
        password_label = new JLabel("Password :");
        password_input = new JPasswordField();

        // 생일
        birth_label = new JLabel("생일");
        birth_input = new JTextField();
        
        // 회원가입 & 취소 버튼
        cancel = new JButton("취소");
        create = new JButton("확인");
        
        //기본 틀
        panel = new JPanel(new GridLayout(5, 1));
                
        panel.add(user_label);
        panel.add(userName_input);
        
        panel.add(password_label);
        panel.add(password_input);
                
        panel.add(name_label);
        panel.add(name_input);
        
        panel.add(birth_label);
        panel.add(birth_input);
        
        
        // 버튼 이벤트 호출 연동
        panel.add(cancel);
        panel.add(create);
        
        cancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	f.dispose();
            	MovieHelperLogin.f.setVisible(true);
            }
        });
        
        create.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	String name = name_input.getText();
            	String userName = userName_input.getText();
            	char getPassword[] = password_input.getPassword();
                String password = String.valueOf(getPassword);
                String birth = birth_input.getText();
                
                
                if (!name.equals("") && !userName.equals("") && !password.equals("") && !birth.equals("")) {
                	f.dispose();
                    // (!) 회원 정보 추가하기 + 로그인 정보 추가하기
                } else {
                	JOptionPane.showMessageDialog(create,"모든 정보를 입력해 주세요","회원가입 실패",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        f.add(panel);
        f.setSize(400, 150);
        f.setVisible(true);
        
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
}
