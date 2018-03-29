/**   
'********************************************************************************'
'~ CPT23 Assignment 3 Short Course Management System  -        About GUI class  ~'
'********************************************************************************'
' Student: Trent Jackson                                                10/10/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class About_GUI extends JFrame implements ActionListener, WindowListener 
{

   JButton OK = new JButton("OK");

   public About_GUI(String title)
   {

      super(title);
      JLabel aboutTxt = new JLabel();
      aboutTxt.setText("<html><body> Short Course Management System" +
                       "<br> v0.1, 10/10/07, CPT23 Assignment 3" + 
                       "<br> by Trent Jackson ...  </body></html>");


      JPanel aboutPanel = new JPanel();
      aboutPanel.setLayout(null);

      OK.setBounds(178, 62, 58, 22);
      aboutTxt.setBounds(10, 0, 220, 70);

      aboutPanel.add(OK);
      aboutPanel.add(aboutTxt);
      OK.addActionListener(this);

      this.getContentPane().add (aboutPanel);
      this.pack();
      this.setSize(250, 125);
      this.setBackground(Color.gray);
      this.setVisible(true);
      this.setResizable(false);
      this.addWindowListener(this);

   }

   public void actionPerformed(ActionEvent e) throws NumberFormatException
   {
      String cmd = e.getActionCommand();

      if (cmd.equals("OK"))
      {
         Short_Course_Management.b3.setEnabled(true);
         this.dispose();
      } 
   }

   public void windowClosing(WindowEvent e)
   {
      Short_Course_Management.b3.setEnabled(true);
      this.dispose();
   }


  public void windowOpened(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
} 

