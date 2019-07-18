import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
import java.time.*;


public class MainFrame extends JFrame
{
static boolean var=true;
Container c;
JButton btnAdd,btnView,btnDelete,btnUpdate;
JLabel jL1,jL2;

MainFrame()
{

this.var=var;
c=getContentPane();

btnAdd=new JButton("Add");
btnView=new JButton("View");
btnDelete=new JButton("Delete");
btnUpdate=new JButton("Update");
JLabel jL1 =new JLabel();
JLabel jL2 =new JLabel();


ImageIcon img=new ImageIcon("G:/JAVA Kamalsir/DBMS GUI/student.jpg");
JLabel i =new JLabel(img);

c.setLayout(null);


btnAdd.setBounds(250,50,300,40);
btnView.setBounds(250,100,300,40);
btnUpdate.setBounds(250,150,300,40);
btnDelete.setBounds(250,200,300,40);

btnAdd.setFont(new Font("Arial",Font.BOLD,18));
btnView.setFont(new Font("Arial",Font.BOLD,18));
btnUpdate.setFont(new Font("Arial",Font.BOLD,18));
btnDelete.setFont(new Font("Arial",Font.BOLD,18));
jL1.setForeground(Color.BLACK);
jL2.setForeground(Color.BLACK);
jL1.setFont(new Font("Arial",Font.BOLD,25));
jL2.setFont(new Font("Arial",Font.BOLD,25));

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);
i.setBounds(50,150,700,500);                                                               
c.add(i);
c.add(jL1);
c.add(jL2);
jL1.setBounds(275,330,700,500); 
jL2.setBounds(275,360,700,500);
c.setBackground(Color.YELLOW); 


Timer SimpleTimer = new Timer(1000, new ActionListener(){
    
    public void actionPerformed(ActionEvent e) {
        jL1.setText("DATE : " +LocalDate.now());
        jL2.setText("TIME :  " +LocalTime.now());
        
    }
});
SimpleTimer.start();


btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
AddFrame a=new AddFrame();
dispose();
}
});

btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
ViewFrame a=new ViewFrame();
dispose();
}
});

btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
UpdateFrame a=new UpdateFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
DeleteFrame a=new DeleteFrame();
dispose();
}
});

btnAdd.addKeyListener(new KeyListener(){
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER)
{
AddFrame a=new AddFrame();
dispose();
}
}
public void keyTyped(KeyEvent ke){}
public void keyReleased(KeyEvent ke){}
});

btnView.addKeyListener(new KeyListener(){
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER)
{
ViewFrame a=new ViewFrame();
dispose();
}
}
public void keyTyped(KeyEvent ke){}
public void keyReleased(KeyEvent ke){}
});

btnUpdate.addKeyListener(new KeyListener(){
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER)
{
UpdateFrame a=new UpdateFrame();
dispose();
}
}
public void keyTyped(KeyEvent ke){}
public void keyReleased(KeyEvent ke){}
});

btnDelete.addKeyListener(new KeyListener(){
public void keyPressed(KeyEvent ke){
if(ke.getKeyCode()==KeyEvent.VK_ENTER)
{
DeleteFrame a=new DeleteFrame();
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

public void windowOpened(WindowEvent we){
if(var){
var=false;
play();
JOptionPane.showMessageDialog(c,"Welcome to Student Management System.");
}
}

public void windowClosing(WindowEvent we){
playing();
int i=JOptionPane.showConfirmDialog(c,"Do you really want to exit ?","Exit ?",JOptionPane.YES_NO_CANCEL_OPTION);
if(i==JOptionPane.YES_OPTION)
	System.exit(1);
}
});

setTitle("Student Management System");
setSize(830,850);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
setVisible(true);
setResizable(false);

}

public static void play(){

try{
 AudioInputStream ais = AudioSystem.getAudioInputStream(new File("G:/JAVA Kamalsir/DBMS GUI/etman.wav"));
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

public static void playing(){
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
public static void main(String args[])
{

MainFrame m = new MainFrame();

}//end of main

}//end of MainFrameclass




class DbHandler
{

public static void addStudent(int rno,String name)
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","abc123");

String s="insert into studentgui values(?,?)";
PreparedStatement pst=con.prepareStatement(s);

pst.setInt(1,rno);
pst.setString(2,name);
int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(), r + " record(s) inserted");
con.close();

}//end of try

catch(SQLException e){
JOptionPane.showMessageDialog(new JDialog(),"insert issue :" +  e + "\n" + " 0 records inserted");
}//end of catch

}

public static String viewStudent()
{
StringBuffer sb = new StringBuffer();
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","abc123");

Statement st=con.createStatement();
String s="select * from studentgui";
ResultSet rs=st.executeQuery(s);

while(rs.next())
{
int rno = rs.getInt(1);
String name=rs.getString(2);
sb.append("Rno: " + rno + "  Name: " + name + "\n");
}//end of while 

con.close();
}//end of try

catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(), e);
}//end of catch
return sb.toString();

}

public static void updateStudent(int rno,String name)
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","abc123");

String s="update studentgui set name=? where rno=?";
PreparedStatement pst=con.prepareStatement(s);
pst.setString(1,name);
pst.setInt(2,rno);
int r=pst.executeUpdate();
if(r==0)
JOptionPane.showMessageDialog(new JDialog(), "This Number is not present in the table!" + "\n" + r + " record(s) updated");
else
JOptionPane.showMessageDialog(new JDialog(), r + " record(s) updated");
con.close();
}//end of try

catch(SQLException e){
JOptionPane.showMessageDialog(new JDialog(), e);
}//end of catch

}

public static void deleteStudent(int rno)
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","abc123");

String s="delete from studentgui where rno=?";
PreparedStatement pst=con.prepareStatement(s);

pst.setInt(1,rno);
int r=pst.executeUpdate();
if(r==0)
JOptionPane.showMessageDialog(new JDialog(), "This Number is not present in the table!" + "\n" + r + " record(s) deleted");
else
JOptionPane.showMessageDialog(new JDialog(), r + " record(s) deleted");

}//end of try

catch(SQLException e){
JOptionPane.showMessageDialog(new JDialog(), e);
}//end of catch

}

public static int getCount()
{
int count=0;
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","abc123");
Statement stmt=con.createStatement();
String s="select count(*) as total from studentgui";
ResultSet rs=stmt.executeQuery(s);			
while(rs.next())
count=rs.getInt("total");				
rs.close();
stmt.close();
con.close();

}//end of try

catch(SQLException e){
JOptionPane.showMessageDialog(new JDialog(), e);
}//end of catch

return count;
}


}





