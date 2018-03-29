/**   
'********************************************************************************'
'~ CPT23 Assignment 3 Short Course Management System  -    Main Menu GUI class  ~'
'********************************************************************************'
' Student: Trent Jackson                                                10/10/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
*/

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Short_Course_Management extends JFrame implements ActionListener, WindowListener{
    
   public static EnrolledStudent [] enrolments = new EnrolledStudent[500];
   public static EnrolledStudent [] students = new EnrolledStudent[500];

   public static Short_Course_Management mainGUI;
   public static         Enrolments_GUI enrolGUI;
   public static              About_GUI aboutGUI;
   public static      Financial_GUI financialGUI;

   public static JButton b1;
   public static JButton b2;
   public static JButton b3;
   public static JButton b4;

   JRadioButton rb1 = new JRadioButton        ("Season 1");
   JRadioButton rb2 = new JRadioButton        ("Season 2");
   JRadioButton rb3 = new JRadioButton        ("Season 3");
   JRadioButton rb4 = new JRadioButton        ("Season 4"); 
   JRadioButton rb5 = new JRadioButton ("Italian Cooking");
   JRadioButton rb6 = new JRadioButton ("Seafood Cooking");
   JRadioButton rb7 = new JRadioButton("Business Writing");
   JRadioButton rb8 = new JRadioButton("Creative Writing");
   JRadioButton rb9 = new JRadioButton      ("Sewing ...");

   public static void main(String[] args) throws IOException{

     mainGUI = new Short_Course_Management("Short Course Management System");
     readStudentDetailsFromFile();
     readEnrolmentDetailsFromFile();
   }

   public Short_Course_Management(String title){

      super(title);
    
      b1 = new JButton("Enrolment");
      b2 = new JButton("Course Info");
      b3 = new JButton("About");
      b4 = new JButton("Exit ...");

      // Main panel holds everything
      JPanel panel = new JPanel();
      panel.setLayout(null);

      // Season / period panel
      JPanel seasonPanel = new JPanel();
      seasonPanel.setLayout(new GridLayout(4,2));

      // Sel course panel
      JPanel coursePanel = new JPanel();
      coursePanel.setLayout(new GridLayout(4,2));

      JPanel blank = new JPanel();
      blank.setLayout(new GridLayout(4,2));

      // Titled borders
      Border loweredEtched;
      loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
      blank.      setBorder(BorderFactory.createTitledBorder(loweredEtched, ""));
      seasonPanel.setBorder(BorderFactory.createTitledBorder(loweredEtched, 
                                                                " Period "));
      coursePanel.setBorder(BorderFactory.createTitledBorder(loweredEtched, 
                                                  " Available Courses ..."));
      
      // Season selection via radio butts (1-4)
      seasonPanel.add(rb1);
      seasonPanel.add(rb2);
      seasonPanel.add(rb3);
      seasonPanel.add(rb4);

      ButtonGroup rbGroup1 = new ButtonGroup();
      rbGroup1.add(rb1);
      rbGroup1.add(rb2);
      rbGroup1.add(rb3);
      rbGroup1.add(rb4);

      // Course selection (1-5)
      coursePanel.add(rb5);
      coursePanel.add(rb6);
      coursePanel.add(rb7);
      coursePanel.add(rb8);
      coursePanel.add(rb9);

      ButtonGroup rbGroup2 = new ButtonGroup();
      rbGroup2.add(rb5);
      rbGroup2.add(rb6);
      rbGroup2.add(rb7);
      rbGroup2.add(rb8);
      rbGroup2.add(rb9);
      
      b1.addActionListener(this);     
      b2.addActionListener(this);     
      b3.addActionListener(this);     
      b4.addActionListener(this);     

      b1.         setBounds(365,8,145,22);
      b2.         setBounds(365,40,145,22);
      b3.         setBounds(365,100,70,22);
      b4.         setBounds(440,100,70,22);
      blank.      setBounds(365,70,145,5);
      seasonPanel.setBounds(5,0,90,125);
      coursePanel.setBounds(95,0,265,125);

      // Add controls to container
      panel.add(seasonPanel);
      panel.add(coursePanel);
      panel.add(blank);
      panel.add(b1);
      panel.add(b2);
      panel.add(b3);
      panel.add(b4);

      this.getContentPane().add (panel);
      this.pack();
      this.setSize(525, 163);
      this.setResizable(false);
      this.addWindowListener(this);
      this.setVisible(true);

      // Default selections
      rb1.setSelected(true);
      rb5.setSelected(true);

   }

   public void actionPerformed(ActionEvent e) throws NumberFormatException{

      String cmd = e.getActionCommand();
      int season = 0;

      if (rb1.isSelected() == true)
         season = 1;
      else if (rb2.isSelected() == true)
         season = 2;
      else if (rb3.isSelected() == true)
         season = 3;
      else if (rb4.isSelected() == true)
         season = 4;

      if (cmd.equals("Enrolment"))
      {
         b1.setEnabled(false);
         b2.setEnabled(false);
         b3.setEnabled(false);

         if (rb5.isSelected() == true)
            enrolGUI = new Enrolments_GUI("Student Enrolment", season, "Italian Cooking");
         else if (rb6.isSelected() == true)
            enrolGUI = new Enrolments_GUI("Student Enrolment", season, "Seafood Cooking");
         else if (rb7.isSelected() == true)
            enrolGUI = new Enrolments_GUI("Student Enrolment", season, "Business Writing");
         else if (rb8.isSelected() == true)
            enrolGUI = new Enrolments_GUI("Student Enrolment", season, "Creative Writing");
         else if (rb9.isSelected() == true)
            enrolGUI = new Enrolments_GUI("Student Enrolment", season, "Sewing");
      }
      else if (cmd.equals("Course Info"))
      {
         b1.setEnabled(false);
         b2.setEnabled(false);
         b3.setEnabled(false);

         if (rb5.isSelected() == true)
            financialGUI = new Financial_GUI("Course Figures", season, "Italian Cooking");
         else if (rb6.isSelected() == true)
            financialGUI = new Financial_GUI("Course Figures", season, "Seafood Cooking");
         else if (rb7.isSelected() == true)
            financialGUI = new Financial_GUI("Course Figures", season, "Business Writing");
         else if (rb8.isSelected() == true)
            financialGUI = new Financial_GUI("Course Figures", season, "Creative Writing");
         else if (rb9.isSelected() == true)
            financialGUI = new Financial_GUI("Course Figures", season, "Sewing");
      }
      else if (cmd.equals("About"))
      {
         b3.setEnabled(false);
         aboutGUI = new About_GUI("About ...");
      }
      else if (cmd.equals("Exit ..."))
      {
         try
         {
            writeStudentDetailsToFile();
            writeEnrolmentDetailsToFile();
            System.exit(0);
         }
         catch (Exception err)
         {
            System.out.println("Fatal IO error while terminating!");
         }
      }
   }

   public static void writeEnrolmentDetailsToFile() throws IOException{

      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("EnrolList.txt")));

      String courseID = null;
      String name = null;
      boolean firstCourse = false;
      int season = 0;      

      for (int i = 0; i < enrolments.length; i++)
      {
         if (Short_Course_Management.enrolments[i] != null)
         {
            if(enrolments[i] instanceof ItalianCooking)
            {
               season = ((ItalianCooking)enrolments[i]).getSeason();
               name = ((ItalianCooking)enrolments[i]).getName();
               firstCourse = ((ItalianCooking)enrolments[i]).firstCourse();
               courseID = "001";
            } 
            if(enrolments[i] instanceof SeafoodCooking)
            {
               season = ((SeafoodCooking)enrolments[i]).getSeason();
               name = ((SeafoodCooking)enrolments[i]).getName();
               firstCourse = ((SeafoodCooking)enrolments[i]).firstCourse();
               courseID = "002";
            } 
            if(enrolments[i] instanceof BusinessWriting)
            {
               season = ((BusinessWriting)enrolments[i]).getSeason();
               name = ((BusinessWriting)enrolments[i]).getName();
               firstCourse = ((BusinessWriting)enrolments[i]).firstCourse();
               courseID = "003";
            } 
            if(enrolments[i] instanceof CreativeWriting)
            {
               season = ((CreativeWriting)enrolments[i]).getSeason();
               name = ((CreativeWriting)enrolments[i]).getName();
               firstCourse = ((CreativeWriting)enrolments[i]).firstCourse();
               courseID = "004";
            } 
            if(enrolments[i] instanceof Sewing)
            {
               season = ((Sewing)enrolments[i]).getSeason();
               name = ((Sewing)enrolments[i]).getName();
               firstCourse = ((Sewing)enrolments[i]).firstCourse();
               courseID = "005";
            } 
            pw.println("S"+ season + "," + courseID + "," + name + "," + firstCourse);   
         }
      }
      pw.close();   
   } 

   public static void writeStudentDetailsToFile() throws IOException{

      PrintWriter pw = new PrintWriter(new BufferedWriter
                                (new FileWriter("StudentList.txt")));

      String name = null;
      String address = null;
      int age = 0;

      for (int i = 0; i < students.length; i++)
      {
         if (students[i] != null)
         {   
            name = students[i].getName();
            address = students[i].getAddress();
            age = students[i].getAge();

            pw.println(name + "," + address + "," + age);   
         }
      }  
      pw.close();   
   } 

   public static void readStudentDetailsFromFile() throws IOException{

      String name = "";
      String address = "";
      String line = "";
      char chr;
      String age ="";
      int index = 0;
      int pos = 0;

      EnrolledStudent obj = null;

      try
      {
         BufferedReader br = new BufferedReader(new FileReader("StudentList.txt"));

         while ((line = br.readLine()) !=null) 
         {
            /** Read in student info, name, address & age. 
            //  Grab one line at a time ...
            */ 
            
            // Find comma which denotes end of entry
            for (int i = 0; i < line.length(); i++)
            {
               chr = line.charAt(i);

               if (chr != ',')
               {   
                  switch(pos)
                  {
                     case 0: 
                        name += chr;
                        break;
                       
                     case 1: 
                        address += chr;
                        break;

                     case 2: 
                        age += chr;
                        break;
                  }
               } 
               else
                  pos++;
            }
               // Create instance of student
               int studentAge = Integer.parseInt(age);
               obj = new Student(name, address, studentAge);
               students[index] = obj;

               index ++;
               name = "";
               address = "";
               age = "";
               pos = 0;   
         }
      }

      catch (FileNotFoundException err)
      {
         System.out.println("Error! studentlist.txt file was not found!");
      }
      
   }    

   public static void readEnrolmentDetailsFromFile() throws IOException{

      String name = "";
      String enrolledName = "";
      String address = "";
      String courseID= "";
      String season = "";
      String line = "";
      String firstCourse = "";

      char chr;
      int index = 0;
      int pos = 0;
      int age = 0;
      int intSeason = 0;
      boolean isFirstCourse = false;
      EnrolledStudent obj = null;

      try
      {
         BufferedReader br = new BufferedReader(new FileReader("EnrolList.txt"));

         while ((line = br.readLine()) !=null) 
         {
            /** < File Format >
            //     1. Season no
            //     2. Course ID
            //     3. Name
            //     4. True / false flag denoting student's first enrolment
            //     (this is used to determine discount eligibility)
            */ 
            
            // Find comma which denotes end of entry
            for (int i = 0; i < line.length(); i++)
            {
               chr = line.charAt(i);

               if (chr != ',')
               {   
                  switch(pos)
                  {
                     case 0: 
                        season += chr;
                        break;
                       
                     case 1: 
                        courseID += chr;
                        break;

                     case 2: 
                        enrolledName += chr;
                        break;

                     case 3: 
                        firstCourse += chr;
                        break;
                  }
               } 
               else
                  pos++;
            }
            
            String temp = "";
            temp += season.charAt(1);
            intSeason = Integer.parseInt(temp);

            // Find student obj, we need their address & age
            for (int i = 0; i < students.length; i++)
            {
               if (students[i] != null)
               {
                  name = students[i].getName();

                  if (name.equals(enrolledName))
                  {  
                     age = students[i].getAge();
                     address = students[i].getAddress();
                     break;
                  }
               }
            }
            
            isFirstCourse = Boolean.valueOf (firstCourse);

            // Create instances
            if (courseID.equals("001"))
            {
               obj = new ItalianCooking(enrolledName, address, age, intSeason, isFirstCourse);
               enrolments[index] = obj;
            }
            if (courseID.equals("002"))
            {
               obj = new SeafoodCooking(enrolledName, address, age, intSeason, isFirstCourse);
               enrolments[index] = obj;
            }
            if (courseID.equals("003"))
            {
               obj = new BusinessWriting(enrolledName, address, age, intSeason, isFirstCourse);
               enrolments[index] = obj;
            }
            if (courseID.equals("004"))
            {
               obj = new CreativeWriting(enrolledName, address, age, intSeason, isFirstCourse);
               enrolments[index] = obj;
            }
            if (courseID.equals("005"))
            {
               obj = new Sewing(enrolledName, address, age, intSeason, isFirstCourse);
               enrolments[index] = obj; 
            }

            index ++;
            enrolledName = "";
            firstCourse = "";
            season = "";
            courseID = "";
            pos = 0;   
         }
      }

      catch (FileNotFoundException err)
      {
         System.out.println("Error! EnrolList.txt file was not found!");
      }
      
   }    

   public void windowClosing(WindowEvent e){

      try
      {
         writeStudentDetailsToFile();
         writeEnrolmentDetailsToFile();
         System.exit(0);
      }
      catch (Exception err)
      {
         System.out.println("Fatal IO error while terminating!");
      }
   }

  public void windowOpened(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
} 


