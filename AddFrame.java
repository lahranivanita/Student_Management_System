import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

class AddFrame extends JFrame
{
Container c;

JLabel lblRno,lblName,lbl;
JTextField txtRno,txtName;
JButton btnSave,btnBack;
JPanel p1,p2,p3,p4;

AddFrame(){
c=getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));

lbl=new JLabel("ADD A STUDENT");
lbl.setForeground(Color.RED);
lbl.setFont(new Font("Arial",Font.BOLD,30));

lblRno=new JLabel("Roll No :");
lblName=new JLabel("Name :");
txtRno=new JTextField(5);
txtName=new JTextField(15);
btnSave=new JButton("Save");
btnBack= new JButton("Back");

p1=new JPanel();
p1.setPreferredSize(new Dimension(500,60));
p2=new JPanel();
p2.setPreferredSize(new Dimension(500,5));
p3=new JPanel();
p3.setPreferredSize(new Dimension(500,8));
p4=new JPanel();
p4.setLayout(null);
p4.setPreferredSize(new Dimension(500,100));

lblRno.setFont(new Font("Arial",Font.BOLD,25));
txtRno.setFont(new Font("Arial",Font.PLAIN,25));
lblName.setFont(new Font("Arial",Font.BOLD,25));
txtName.setFont(new Font("Arial",Font.PLAIN,25));

btnSave.setBounds(90,40,100,50);
btnBack.setBounds(300,40,100,50);
btnSave.setFont(new Font("Arial",Font.BOLD,18));
btnBack.setFont(new Font("Arial",Font.BOLD,18));
btnBack.setBackground(Color.PINK);
btnSave.setBackground(Color.PINK);

p1.add(lbl);
p2.add(lblRno);
p2.add(txtRno);
p3.add(lblName);
p3.add(txtName);
p4.add(btnSave);
p4.add(btnBack);

c.add(p1);
c.add(p2);
c.add(p3);
c.add(p4);
p1.setBackground(Color.CYAN);
p2.setBackground(Color.CYAN);
p3.setBackground(Color.CYAN);
p4.setBackground(Color.CYAN);


btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
int rno=0;
String name=null;
boolean var=true;
try{

 rno=Integer.parseInt(txtRno.getText().toString());}
 catch(NumberFormatException e){
 JOptionPane.showMessageDialog(c,"Invalid Number");
 var=false;
 txtRno.setText("");
 txtRno.requestFocus();
}
if( var==true&&rno <= 0)
{
 JOptionPane.showMessageDialog(c,"Invalid Number!"); 
 var=false;
 txtRno.setText("");
 txtRno.requestFocus();
}
if(var==true&& txtRno.getText().toString().equals(""))
{
 JOptionPane.showMessageDialog(c,"Roll Number cannot be empty!");
 var=false;
 txtRno.setText("");
 txtRno.requestFocus();
}
name=txtName.getText().toString();
char[] a=name.toCharArray();
for(char n: a)
{
if (!Character.isLetter(n))
{
 JOptionPane.showMessageDialog(c,"Invalid Name!");
 var=false;
 txtName.setText("");
 txtName.requestFocus();
 break;
}
}
if(var==true && name.equals(""))
{
 JOptionPane.showMessageDialog(c,"Name cannot be empty!");
 var=false;
 txtName.setText("");
 txtName.requestFocus();
}
if(var)
{
txtRno.setText("");
txtName.setText("");
txtRno.requestFocus();
DbHandler.addStudent(rno,name);}
}
});

btnSave.addKeyListener(new KeyListener(){
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER)
{
int rno=0;
String name=null;
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
 var=false ;
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
name=txtName.getText().toString();
char[] a=name.toCharArray();
for(char n: a)
{
if (!Character.isLetter(n))
{
 JOptionPane.showMessageDialog(c,"Invalid Name!");
 var=false;
 txtName.setText("");
 txtName.requestFocus();
 break;
}
}
if(var==true && name.equals(""))
{
 JOptionPane.showMessageDialog(c,"Name cannot be empty!");
 var=false;
 txtName.setText("");
 txtName.requestFocus();
}
if(var)
{
txtRno.setText("");
txtName.setText("");
txtRno.requestFocus();
DbHandler.addStudent(rno,name);}
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

setTitle("Add Student");
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