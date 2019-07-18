import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

class ViewFrame extends JFrame
{
Container c;
JLabel lbl;
TextArea ta;
JButton btnBack,btnCount;
JPanel p1,p2,p3;

ViewFrame()
{
c=getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
lbl=new JLabel("VIEW STUDENTS");
lbl.setForeground(Color.RED);
lbl.setFont(new Font("Arial",Font.BOLD,30));
ta=new TextArea(8,40);
ta.setEditable(false);
btnBack= new JButton("Back");
btnCount= new JButton("Count");
p1=new JPanel();
p1.setPreferredSize(new Dimension(500,50));
p2=new JPanel();
p2.setPreferredSize(new Dimension(500,170));
p3=new JPanel();
p3.setPreferredSize(new Dimension(500,50));
p2.setLayout(null);
p3.setLayout(null);
ta.setBounds(30,10,420,150);
btnBack.setBounds(90,10,100,50);
btnCount.setBounds(300,10,100,50);
ta.setFont(new Font("Arial",Font.BOLD,15));
btnBack.setFont(new Font("Arial",Font.BOLD,18));
btnBack.setBackground(Color.WHITE);
btnCount.setFont(new Font("Arial",Font.BOLD,18));
btnCount.setBackground(Color.WHITE);


p1.add(lbl);
p2.add(ta);
p3.add(btnBack);
p3.add(btnCount);

c.add(p1);
c.add(p2);
c.add(p3);
p1.setBackground(Color.LIGHT_GRAY);
p2.setBackground(Color.LIGHT_GRAY);
p3.setBackground(Color.LIGHT_GRAY);

String data =DbHandler.viewStudent();
ta.setText(data);

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
MainFrame a=new MainFrame();
dispose();
}
});

btnCount.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
JOptionPane.showMessageDialog(new JDialog(),"Total count of students = " + DbHandler.getCount());
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

btnCount.addKeyListener(new KeyListener(){
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER){
JOptionPane.showMessageDialog(new JDialog(),"Total count of students = " + DbHandler.getCount());
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

setTitle("View Student");
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
