/**   
'********************************************************************************'
'~ CPT23 Assignment 3 Short Course Management System  -     Financial GUI class ~'
'********************************************************************************'
' Student: Trent Jackson                                                10/10/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Financial_GUI extends JFrame implements ActionListener, WindowListener{

   private String course;
   private int    season;

   DefaultListModel studentName       = new DefaultListModel();
   JList studentNamelist1             = new JList(studentName);
   JScrollPane scrollstudentNamelist1 = new JScrollPane(studentNamelist1);
    
   JLabel showTotalStudents           = new JLabel();
   JLabel showIncome                  = new JLabel();
   JLabel showCost                    = new JLabel();
   JLabel showProfit                  = new JLabel();

   public Financial_GUI(String title, int season, String course){

      super(title);
      this.course = course;
      this.season = season;

      JPanel topPanel         = new JPanel();
      JPanel figuresPanel     = new JPanel();
      JPanel studentNamePanel = new JPanel();
      JButton b1              = new JButton("OK");
      JLabel showCourseName   = new JLabel("Season "+ this.season + ": "+ this.course);

      JLabel L1               = new JLabel("Total Students:");
      JLabel L2               = new JLabel("Income:");
      JLabel L3               = new JLabel("Cost:");
      JLabel L4               = new JLabel("Profit:");

      topPanel.        setLayout(null);  
      figuresPanel.    setLayout(null);
      studentNamePanel.setLayout(null);
      Border loweredEtched;
      loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
      topPanel.        setBorder(BorderFactory.createTitledBorder(loweredEtched, ""));
      figuresPanel.    setBorder(BorderFactory.createTitledBorder(loweredEtched, 
                                                            "Course Info ..."));   
      studentNamePanel.setBorder(BorderFactory.createTitledBorder(loweredEtched,
                                 "Student Debt (this course)"));

      getContentPane().add(topPanel);
      studentNamePanel.add(scrollstudentNamelist1);   
      topPanel.add(showCourseName);

      figuresPanel.add(b1);
      figuresPanel.add(L1);
      figuresPanel.add(L2);
      figuresPanel.add(L3);
      figuresPanel.add(L4);
      figuresPanel.add(showTotalStudents);
      figuresPanel.add(showIncome);
      figuresPanel.add(showCost);
      figuresPanel.add(showProfit);
      topPanel.    add(studentNamePanel);
      topPanel.    add(figuresPanel);
      b1.addActionListener(this);     
      
      L1.setBounds(50, 20, 150, 25);      
      L2.setBounds(90, 50, 150, 25);      
      L3.setBounds(107, 80, 150, 25);      
      L4.setBounds(101, 110, 150, 25);      
      b1.setBounds(260,272,58,22);

      showTotalStudents.     setBounds(140, 20, 150, 25);      
      showIncome.            setBounds(140, 50, 150, 25);      
      showCost.              setBounds(140, 80, 150, 25);      
      showProfit.            setBounds(140, 110, 150, 25);      
      scrollstudentNamelist1.setBounds(10,20,155,275);
      figuresPanel.          setBounds(5,30,330,305);
      studentNamePanel.      setBounds(335,30,175,305);
      showCourseName.        setForeground(Color.red);
      showCourseName.        setBounds(180,10,250,20);

      studentNamelist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      this.pack();
      this.setSize(525, 375);
      this.setBackground(Color.gray);
      this.setVisible(true);
      this.setResizable(false);
      this.addWindowListener(this);
      
      calcFigures();
   }
 
   public void calcFigures(){

   /** 
   // Loop-through instances and extract & calc info to display
   // 1. Total num of students enrolled ...
   // 2. Student debt (this course, this season only)
   // 3. The centre's cost
   // 4. Income ...
   // 5. Profit
   // 
   // Discount policies are enforced throughout
   // 1. 20% off if student is in two or more courses
   // 2. $100 off if student is 50 or over
   // 3. Seafood cooking & business writing courses receive 
   // $200 subsidy if there's more than 10 enrolments
   **/

      String name = null;
      String address = null;
      int season = 0;
      int studentCount = 0;
      int age = 0;
      int indexRef = 0;
      double debt = 0;
      double discount = 0;
      double fee = 0;
      double income = 0;
      double cost = 0;
      double profit = 0;
      double subsidy = 0;

      for (int i = 0; i < Short_Course_Management.enrolments.length; i++)
      {
         if (Short_Course_Management.enrolments[i] != null)
         {
            if (this.course.equals("Italian Cooking"))
            {
               if(Short_Course_Management.enrolments[i] instanceof ItalianCooking)
               {
                  season = ((ItalianCooking)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                  { 
                     studentCount ++;
                     indexRef = i; // Index of known to exist instance needed later for accessors 
                     name = Short_Course_Management.enrolments[i].getName();
                     fee = ((ItalianCooking)Short_Course_Management.enrolments[i]).getStudentFee();

                     boolean giveDiscount = !
                     ((ItalianCooking)Short_Course_Management.enrolments[i]).firstCourse();

                     // Confirm eligibility (must still be in two or more courses)
                     if (giveDiscount)
                     {  
                        boolean multipleEnroll = isStudentInMultipleCourses(name);
                        if (multipleEnroll)
                           discount = (fee / 100) * 20;
                     }                
                       
                     age = Short_Course_Management.enrolments[i].getAge();
                     if (age >= 50)
                       discount += 100;

                     debt = (fee - discount);
                     studentName.addElement(name);
                     studentName.addElement("$" + debt);

                     income += debt;
                     discount = 0;
                  }
               }
            }
            if (this.course.equals("Seafood Cooking"))
            {
               if(Short_Course_Management.enrolments[i] instanceof SeafoodCooking)
               {
                  season = ((SeafoodCooking)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                  { 
                     studentCount ++;
                     if (studentCount >= 10)
                        subsidy = 200;

                     indexRef = i;
                     name = Short_Course_Management.enrolments[i].getName();
                     fee = ((SeafoodCooking)Short_Course_Management.enrolments[i]).getStudentFee();

                     boolean giveDiscount = !
                     ((SeafoodCooking)Short_Course_Management.enrolments[i]).firstCourse();

                     if (giveDiscount)
                     {  
                        boolean multipleEnroll = isStudentInMultipleCourses(name);
                        if (multipleEnroll)
                           discount = (fee / 100) * 20;
                     }                
      
                     age = Short_Course_Management.enrolments[i].getAge();
                     if (age >= 50)
                       discount += 100;

                     debt = (fee - discount);
                     studentName.addElement(name);
                     studentName.addElement("$" + debt);

                     income += debt;
                     discount = 0;
                  }
               }
            }
            if (this.course.equals("Business Writing"))
            {
               if(Short_Course_Management.enrolments[i] instanceof BusinessWriting)
               {
                  season = ((BusinessWriting)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                  { 
                     studentCount ++;
                     if (studentCount >= 10)
                        subsidy = 200;

                     indexRef = i;
                     name = Short_Course_Management.enrolments[i].getName();
                     fee = ((BusinessWriting)Short_Course_Management.enrolments[i]).getStudentFee();

                     boolean giveDiscount = !
                     ((BusinessWriting)Short_Course_Management.enrolments[i]).firstCourse();

                     if (giveDiscount)
                     {  
                        boolean multipleEnroll = isStudentInMultipleCourses(name);
                        if (multipleEnroll)
                           discount = (fee / 100) * 20;
                     }                
                       
                     age = Short_Course_Management.enrolments[i].getAge();
                     if (age >= 50)
                       discount += 100;

                     debt = (fee - discount);
                     studentName.addElement(name);
                     studentName.addElement("$" + debt);

                     income += debt;
                     discount = 0;
                  }
               }
            }
            if (this.course.equals("Creative Writing"))
            {
               if(Short_Course_Management.enrolments[i] instanceof CreativeWriting)
               {
                  season = ((CreativeWriting)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                  { 
                     studentCount ++;
                     indexRef = i;
                     name = Short_Course_Management.enrolments[i].getName();
                     fee = ((CreativeWriting)Short_Course_Management.enrolments[i]).getStudentFee();

                     boolean giveDiscount = !
                     ((CreativeWriting)Short_Course_Management.enrolments[i]).firstCourse();

                     if (giveDiscount)
                     {  
                        boolean multipleEnroll = isStudentInMultipleCourses(name);
                        if (multipleEnroll)
                           discount = (fee / 100) * 20;
                     }                
                       
                     age = Short_Course_Management.enrolments[i].getAge();
                     if (age >= 50)
                       discount += 100;

                     debt = (fee - discount);
                     studentName.addElement(name);
                     studentName.addElement("$" + debt);

                     income += debt;
                     discount = 0;
                  }
               }
            }
            if (this.course.equals("Sewing"))
            {
               if(Short_Course_Management.enrolments[i] instanceof Sewing)
               {
                  season = ((Sewing)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                  { 
                     indexRef = i;
                     studentCount ++;
                     name = Short_Course_Management.enrolments[i].getName();
                     fee = ((Sewing)Short_Course_Management.enrolments[i]).getStudentFee();

                     boolean giveDiscount = !
                     ((Sewing)Short_Course_Management.enrolments[i]).firstCourse();

                     if (giveDiscount)
                     {  
                        boolean multipleEnroll = isStudentInMultipleCourses(name);
                        if (multipleEnroll)
                           discount = (fee / 100) * 20;
                     }                
                       
                     age = Short_Course_Management.enrolments[i].getAge();
                     if (age >= 50)
                       discount += 100;

                     debt = (fee - discount);
                     studentName.addElement(name);
                     studentName.addElement("$" + debt);

                     income += debt;
                     discount = 0;
                  }
               }
            }
         }
      }

      if (studentCount > 0)
      {
      // Get the running cost of course ...
         if (this.course.equals("Italian Cooking"))
            cost = ((ItalianCooking)Short_Course_Management.enrolments[indexRef]).
                                                           calcCost(studentCount);
         else if (this.course.equals("Seafood Cooking"))
            cost = ((SeafoodCooking)Short_Course_Management.enrolments[indexRef]).
                                                           calcCost(studentCount);
         else if (this.course.equals("Business Writing"))
            cost = ((BusinessWriting)Short_Course_Management.enrolments[indexRef]).
                                                           calcCost(studentCount);
         else if (this.course.equals("Creative Writing"))
            cost = ((CreativeWriting)Short_Course_Management.enrolments[indexRef]).
                                                           calcCost(studentCount);
         else if (this.course.equals("Sewing"))
            cost = ((Sewing)Short_Course_Management.enrolments[indexRef]).
                                                           calcCost(studentCount);

         income += subsidy;
         profit = (income - cost);
      }
      showTotalStudents.setText("" + studentCount);      
      showIncome.setText("$" + income);      
      showCost.setText("$" + cost);      
      showProfit.setText("$" + profit);      

   }

   public boolean isStudentInMultipleCourses(String chkName){

      String name = null;      
      int occurrences = 0;

      for (int i = 0; i < Short_Course_Management.enrolments.length; i++)
      {
         if (Short_Course_Management.enrolments[i] != null)
         {
            name = Short_Course_Management.enrolments[i].getName();
            if (chkName.equals(name)) 
               occurrences ++;
               if (occurrences > 1)
                  break;
         }   
      }
      if (occurrences > 1)
         return true;
      else return false;
   }
   
   public void actionPerformed(ActionEvent e) throws NumberFormatException{

      String cmd = e.getActionCommand();

      if (cmd.equals("OK"))
      {
         // Re-enable buttons on main menu interface
         Short_Course_Management.b1.setEnabled(true);
         Short_Course_Management.b2.setEnabled(true);
         Short_Course_Management.b3.setEnabled(true);
         this.dispose();
      }
   }

   public void windowClosing(WindowEvent e){

         Short_Course_Management.b1.setEnabled(true);
         Short_Course_Management.b2.setEnabled(true);
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

