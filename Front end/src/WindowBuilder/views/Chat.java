package WindowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class Chat extends JFrame {

private JPanel contentPane;
private JTextField textField;
//test cimmit 

/**
* Launch the application.
*/
/*
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
Chat frame = new Chat();
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}
*/

/**
* Create the frame.
*/
public Chat() {
initComponents();
}
private void initComponents() {
setTitle("Chat");
//setIconImage(Toolkit.getDefaultToolkit().getImage(Chat.class.getResource("/WindowBuilder/resources/chat.png")));
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 450, 300);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
textField = new JTextField();
textField.setColumns(10);
JTextArea textArea = new JTextArea();
textArea.setLineWrap(true);
JButton btnSend = new JButton("Send");
btnSend.addActionListener(new ActionListener(){

@Override
public void actionPerformed(ActionEvent arg0) {
if(!(textField.getText().equals(""))){
String message = textField.getText();
textArea.append("\t\t\t--> " + "You: " + "\n");
textArea.append(message + "\n");
textField.setText("");
}
}
}//end actionlistener method
);//end action listener
textField = new JTextField();
textField.setColumns(10);
GroupLayout gl_contentPane = new GroupLayout(contentPane);
gl_contentPane.setHorizontalGroup(
gl_contentPane.createParallelGroup(Alignment.LEADING)
.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
.addContainerGap(62, Short.MAX_VALUE)
.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
.addComponent(textArea, Alignment.TRAILING)
.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
.addComponent(textField, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
.addPreferredGap(ComponentPlacement.RELATED)
.addComponent(btnSend)))
.addContainerGap())
);
gl_contentPane.setVerticalGroup(
gl_contentPane.createParallelGroup(Alignment.LEADING)
.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
.addContainerGap(32, Short.MAX_VALUE)
.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
.addGap(18)
.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
.addComponent(btnSend)
.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
.addGap(19))
);
contentPane.setLayout(gl_contentPane);
}

}
