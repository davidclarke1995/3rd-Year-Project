package WindowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class FirstWindowBuilderGui extends JFrame {

	private JPanel contentPaneMain;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					FirstWindowBuilderGui frame = new FirstWindowBuilderGui();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirstWindowBuilderGui() {
		
		initComponents();
		createEvents();
	}
	
///////////////////////////////////////////////////////////////////////////
//This method contains all of the code for creating and initialising components
/////////////////////////////////////////////////
private void initComponents() {
	int number = 1;
	//setIconImage(Toolkit.getDefaultToolkit().getImage(FirstWindowBuilderGui.class.getResource("/WindowBuilder/resources/chat.png")));
	setTitle("MyMessengerGui");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPaneMain = new JPanel();
	contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPaneMain);
	
	JLabel lblUsername = new JLabel("UserName:");
	
	textField = new JTextField();
	textField.setColumns(10);
	
	JLabel lblPassword = new JLabel("Password:");
	
	passwordField = new JPasswordField();
	passwordField.setColumns(10);
	
	JLabel lblNewLabel = new JLabel();
	lblNewLabel.setText("Enter your details");
	
	JButton btnLogin = new JButton("Login");
	btnLogin.addActionListener(new ActionListener() {
		//@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0) {
			///////////////////////
			String userName = textField.getText();
			String password = passwordField.getText();
			if(userName.equals("GaryConnelly") && password.equals("gary")){
				Chat frame = new Chat();
				frame.setVisible(true);
			}
			else{
				//txtrHello.setText("Invalid username or password");
				lblNewLabel.setText("Invalid username or password");
			}
			
		}
	});
	
	
	
	
	
	
	
	GroupLayout gl_contentPaneMain = new GroupLayout(contentPaneMain);
	gl_contentPaneMain.setHorizontalGroup(
		gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPaneMain.createSequentialGroup()
				.addGap(108)
				.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING, false)
					.addGroup(gl_contentPaneMain.createSequentialGroup()
						.addComponent(lblPassword)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(passwordField))
					.addGroup(gl_contentPaneMain.createSequentialGroup()
						.addComponent(lblUsername)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(173, Short.MAX_VALUE))
			.addGroup(gl_contentPaneMain.createSequentialGroup()
				.addGap(82)
				.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
				.addGap(91))
			.addGroup(gl_contentPaneMain.createSequentialGroup()
				.addGap(184)
				.addComponent(lblNewLabel)
				.addContainerGap(194, Short.MAX_VALUE))
	);
	gl_contentPaneMain.setVerticalGroup(
		gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPaneMain.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblUsername)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
					.addComponent(lblPassword)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addComponent(btnLogin)
				.addGap(18)
				.addComponent(lblNewLabel)
				.addContainerGap(121, Short.MAX_VALUE))
	);
	contentPaneMain.setLayout(gl_contentPaneMain);
	
	
	
	
	

}


	///////////////////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	/////////////////////////////////////////////////
	private void createEvents() {
		
		
	}
}
