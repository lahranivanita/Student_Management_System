import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

class DeleteFrame extends JFrame
{
Container c;

JLabel lblRno,lbl;
JTextField txtRno;
JButton btnSave,btnBack;
JPanel p1,p2,p3;

DeleteFrame(){
c=getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
Color c1=new Color(255,255,153);
Color c2=new Color(51,204,255);

lbl=new JLabel("DELETE A STUDENT");
lbl.setForeground(Color.RED);
lbl.setFont(new Font("Arial",Font.BOLD,30));

lblRno=new JLabel("Roll No :");
txtRno=new JTextField(5);
btnSave=new JButton("Save");
btnBack= new JButton("Back");

p1=new JPanel();
p1.setPreferredSize(new Dimension(500,50));
p2=new JPanel();
p2.setPreferredSize(new Dimension(500,30));
p3=new JPanel();
p3.setPreferredSize(new Dimension(500,50));
p3.setLayout(null);

btnSave.setBounds(90,10,100,50);
btnBack.setBounds(300,10,100,50);
btnSave.setFont(new Font("Arial",Font.BOLD,18));
btnBack.setFont(new Font("Arial",Font.BOLD,18));
lblRno.setFont(new Font("Arial",Font.BOLD,25));
txtRno.setFont(new Font("Arial",Font.PLAIN,25));
btnSave.setBackground(c2);
btnBack.setBackground(c2);

p1.add(lbl);
p2.add(lblRno);
p2.add(txtRno);
p3.add(btnSave);
p3.add(btnBack);

c.add(p1);
c.add(p2);
c.add(p3);
p1.setBackground(c1);
p2.setBackground(c1);
p3.setBackground(c1);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
int rno=0;
boolean var=true;
try{
rno=Integer.parseInt(txtRno.getText().toString());}
catch(NumberFormatException e){
JOptionPane.showMessageDialog(c,"Invalid Number");
var=false;
txtRno.setText("");
txtRno.requestFocus();
}
if(var==true && rno < 0)
{
JOptionPane.showMessageDialog(c,"Invalid Number!");
var=false;
txtRno.setText("");
txtRno.requestFocus();
}
if(var==true && txtRno.getText().toString().equals(""))
{
JOptionPane.showMessageDialog(c,"Roll Number cannot be empty!");
var=false;
txtRno.setText("");
txtRno.requestFocus();
}
if(var){
txtRno.setText("");
txtRno.requestFocus();
DbHandler.deleteStudent(rno);}
}
});

btnSave.addKeyListener(new KeyListener(){
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER)
{
int rno=0;
boolean var=true;
try{
rno=Integer.parseInt(txtRno.getText().toString());}
catch(NumberFormatException e){
JOptionPane.showMessageDialog(c,"Invalid Number");
var=false;
txtRno.setText("");
txtRno.requestFocus();
}
if(var==true && rno < 0)
{
JOptionPane.showMessageDialog(c,"Invalid Number!");
var=false;
txtRno.setText("");
txtRno.requestFocus();
}
if(var==true && txtRno.getText().toString().equals(""))
{
JOptionPane.showMessageDialog(c,"Roll Number cannot be empty!");
var=false;
txtRno.setText("");
txtRno.requestFocus();
}
if(var){
txtRno.setText("");
txtRno.requestFocus();
DbHandler.deleteStudent(rno);}
}
}
public void keyTyped(KeyEvent ke){}
public void keyReleased(KeyEvent ke){}
});

btnBack.addKeyListener(new KeyListener(){
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER){
MainFrame a=new MainFrame();
dispose();
}
}
public void keyTyped(KeyEvent ke){}
public void keyReleased(KeyEvent ke){}
});

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
MainFrame a=new MainFrame();
dispose();
}
});

this.addWindowListener(new WindowListener(){
public void windowActivated(WindowEvent we){}
public void windowDeactivated(WindowEvent we){}
public void windowIconified(WindowEvent we){}
public void windowDeiconified(WindowEvent we){}
public void windowClosed(WindowEvent we){}
public void windowOpened(WindowEvent we){}

public void windowClosing(WindowEvent we){
play();
int i=JOptionPane.showConfirmDialog(c,"Do you really want to exit ?","Exit ?",JOptionPane.YES_NO_CANCEL_OPTION);
if(i==JOptionPane.YES_OPTION)
	System.exit(1);
}
});

setTitle("Delete Student");
setSize(500,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
setVisible(true);
setResizable(false);

}
public static void play(){

try{
 AudioInputStream ais = AudioSystem.getAudioInputStream(new File("G:/JAVA Kamalsir/DBMS GUI/Tone.wav"));
 Clip test=AudioSystem.getClip();
 test.open(ais);
 test.start();
 
while(!test.isRunning())
	Thread.sleep(10);
while(test.isRunning())
	Thread.sleep(10);
 test.close();
}
catch(Exception e)
{e.printStackTrace();}
}

}