/**   
'********************************************************************************'
'~ CPT23 Assignment 3 Short Course Management System  -    Enrolments GUI class ~'
'********************************************************************************'
' Student: Trent Jackson                                                10/10/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class Enrolments_GUI extends JFrame implements ActionListener, WindowListener{

   private JTabbedPane tabbedPane;
   private String course;
   private int season;

   JPanel tab1Panel     = new JPanel();
   JPanel tab2Panel     = new JPanel();
   JTextField nameEntry = new JTextField();
   JTextField ageEntry  = new JTextField();
   JTextField addEntry  = new JTextField();

   DefaultListModel studentName      = new DefaultListModel();
   DefaultListModel studentAge       = new DefaultListModel();
   DefaultListModel studentAddress   = new DefaultListModel();
   DefaultListModel isFirstCourse    = new DefaultListModel();
   DefaultListModel enrolledStudents = new DefaultListModel();

   JList studentNamelist1    = new JList(studentName);
   JList studentNamelist2    = new JList(studentName);
   JList enrolledStudentlist = new JList(enrolledStudents);

   JScrollPane scrollstudentNamelist1     = new JScrollPane(studentNamelist1);
   JScrollPane scrollstudentNamelist2     = new JScrollPane(studentNamelist2);
   JScrollPane scrollEnrolledStudentslist = new JScrollPane(enrolledStudentlist);
    
   public Enrolments_GUI(String title, int season, String course){

      super(title);
      this.course = course;
      this.season = season;

      JPanel topPanel = new JPanel();
      topPanel.setLayout(new BorderLayout());
      getContentPane().add(topPanel);

      // Create the tab pages
      createPage1();
      createPage2();

      studentNamelist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      studentNamelist2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      enrolledStudentlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // Create a tabbed pane
      tabbedPane = new JTabbedPane();
      tabbedPane.addTab( "Students ...", tab1Panel);
      tabbedPane.addTab( "Course Enrolments", tab2Panel);
      topPanel.add(tabbedPane, BorderLayout.CENTER);

      this.pack();
      this.setSize(525, 400);
      this.setBackground(Color.gray);
      this.setVisible(true);
      this.setResizable(false);
      this.addWindowListener(this);
      
      loadStudentList();
      loadEnrolledList();
   }

   public void createPage1(){ 

   /** 
   // Layout various components on the first tab of GUI (pg1)
   // 1. Lisbox for displaying student names
   // 2. Name, address & age entry fields
   //
   // 3. Buttons:
   //            a. add student
   //            b. remove
   //            c. cancel
   //            d. OK (save changes & close) 
   // 4. Apply etched borders throughout
   **/

      JPanel newStudentPanel  = new JPanel();
      JPanel studentNamePanel = new JPanel();
      JLabel nameEntryLabel   = new JLabel("Name:");    
      JButton b1              = new JButton("Add Student");
      JButton b2              = new JButton("Remove >");
      JButton b3              = new JButton("Cancel");
      JButton b4              = new JButton("OK");
      JLabel addEntryLabel    = new JLabel("Address:");
      JLabel ageEntryLabel    = new JLabel("Age:");
      JPanel blank            = new JPanel();

      tab1Panel.       setLayout(null);  
      newStudentPanel. setLayout(null);
      studentNamePanel.setLayout(null);
      blank.setLayout(new GridLayout(4,2));

      Border loweredEtched;
      loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
      tab1Panel.       setBorder(BorderFactory.createTitledBorder(loweredEtched, ""));
      newStudentPanel. setBorder(BorderFactory.createTitledBorder(loweredEtched, "Add New Student into System ..."));         
      studentNamePanel.setBorder(BorderFactory.createTitledBorder(loweredEtched, "Students"));
      blank.setBorder(BorderFactory.createTitledBorder(loweredEtched, ""));

      newStudentPanel. add(nameEntryLabel);
      studentNamePanel.add(scrollstudentNamelist1);
      newStudentPanel. add(nameEntry);
      newStudentPanel. add(ageEntryLabel);
      newStudentPanel. add(ageEntry);
      newStudentPanel. add(addEntry);
      newStudentPanel. add(addEntryLabel);
      newStudentPanel. add(b1);
      newStudentPanel. add(b2);
      newStudentPanel. add(b4);
      newStudentPanel. add(b3);
      newStudentPanel. add(blank);
      tab1Panel.       add(newStudentPanel);
      tab1Panel.       add(studentNamePanel);
      b1.addActionListener(this);     
      b2.addActionListener(this);     
      b3.addActionListener(this);     
      b4.addActionListener(this); 

      scrollstudentNamelist1.setBounds(10,20,155,275);
      newStudentPanel.       setBounds(5,30,330,305);
      studentNamePanel.      setBounds(335,30,175,305);
      nameEntryLabel.        setBounds(50,30,50,20);
      nameEntry.             setBounds(92,32,140,20);        
      ageEntryLabel.         setBounds(245,30,50,20);
      ageEntry.              setBounds(275,32,42,20);
      addEntryLabel.         setBounds(35,60,75,20);
      addEntry.              setBounds(92,62,225,20);
      b1.     setBounds(172,110,145,22);
      b2.     setBounds(172,195,145,22);
      b3.     setBounds(172,272,80,22);
      b4.     setBounds(260,272,58,22);
      blank.  setBounds(10,140,310,5);

   }
 
   public void createPage2(){

   /** 
   // Layout various components on the second tab of GUI (pg2) 
   // 1. Listbox for displaying student names
   // 2. Listbox to display enrolment names
   // 3. Buttons:
   //            a. add student to course
   //            b. remove
   //            c. cancel
   //            d. OK (save changes & close) 
   // 4. Apply etched borders throughout
   // 5. Label telling user the course name & season
   **/

      JPanel studentNamePanel = new JPanel();
      JPanel enrolledStudentPanel = new JPanel();
      JLabel l1  = new JLabel("Season "+ this.season + ": "+ this.course);
      JButton b1 = new JButton("Add to course >");
      JButton b2 = new JButton("<html><center>"+"Remove from"+"<br>"+"course ..."+
                                                               "</center></html>");
      JButton b3 = new JButton("Cancel");
      JButton b4 = new JButton("OK");

      tab2Panel.           setLayout(null);  
      studentNamePanel.    setLayout(null);
      enrolledStudentPanel.setLayout(null);
      Border loweredEtched;
      loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
      tab2Panel.           setBorder(BorderFactory.createTitledBorder(loweredEtched, ""));
      studentNamePanel.    setBorder(BorderFactory.createTitledBorder(loweredEtched, 
                                                               "All Students in System"));
      enrolledStudentPanel.setBorder(BorderFactory.createTitledBorder(loweredEtched, 
                                                                    "Enrolled Students"));

      studentNamePanel.    add(scrollstudentNamelist2);
      enrolledStudentPanel.add(scrollEnrolledStudentslist);
      tab2Panel.           add(studentNamePanel);
      tab2Panel.           add(enrolledStudentPanel);
      studentNamePanel.    add(b1);
      studentNamePanel.    add(b2);
      studentNamePanel.    add(b3);
      tab2Panel.           add(l1);
      studentNamePanel.    add(b4);
      b1.addActionListener(this);     
      b2.addActionListener(this);     
      b3.addActionListener(this);     
      b4.addActionListener(this); 

      scrollstudentNamelist2.    setBounds(10,20,155,275);
      scrollEnrolledStudentslist.setBounds(10,20,155,275);
      studentNamePanel.          setBounds(5,30,330,305);
      enrolledStudentPanel.      setBounds(335,30,175,305);
      l1.setBounds(180,10,250,20);
      b1.setBounds(172,20,145,22);
      b2.setBounds(172,50,145,40);
      b3.setBounds(172,272,80,22);
      b4.setBounds(260,272,58,22);
      l1.setForeground(Color.red);
      b2.setActionCommand("Remove from course");
   }

   public void actionPerformed(ActionEvent e) throws NumberFormatException{

      boolean invalidEntry = false;
      String address = null;
      String name = null;
      String strAge = null;
      int age = 0; 

      String cmd = e.getActionCommand();
      EnrolledStudent obj = null;

      if (cmd.equals("Add Student"))
      {
         // Add student's details to list 
         name = nameEntry.getText();
         strAge = ageEntry.getText();
         address = addEntry.getText();

         // Bit of error checking (check for blank fields)
         if (name.length() == 0)
            JOptionPane.showMessageDialog(null,"Must enter their name", 
                     "Details incomplete!", JOptionPane.ERROR_MESSAGE);
         else if (strAge.length() == 0)   
            JOptionPane.showMessageDialog(null,"Must enter their age", 
                     "Details incomplete!", JOptionPane.ERROR_MESSAGE);
         else if (address.length() == 0)   
            JOptionPane.showMessageDialog(null,"Must enter their address", 
                     "Details incomplete!", JOptionPane.ERROR_MESSAGE);

         else // One last test, check for valid age entry
         { 
            try
            {
               int test = Integer.parseInt(strAge);
            }
            catch (Exception err)
            {  
               JOptionPane.showMessageDialog(null,"Age must be numerical", 
                             "Details wrong!", JOptionPane.ERROR_MESSAGE);
               invalidEntry = true; 
            }

         // Two digts max for age entry
            if (strAge.length() > 2)
            {
               JOptionPane.showMessageDialog(null,"Age has too many digits", 
                              "Details wrong!", JOptionPane.ERROR_MESSAGE);
               invalidEntry = true; 
            }

         // Add student's details to list (only names shown in list box)
            if (!invalidEntry)
            {
               studentName.addElement(name);
               studentAge.addElement(strAge);
               studentAddress.addElement(address);

         // Reset fields
               nameEntry.setText(null);
               addEntry.setText(null);
               ageEntry.setText(null);
            } 
         } 
      }

      else if (cmd.equals("Remove >"))
      {
         int index = studentNamelist1.getSelectedIndex();
           
         if (index >= 0) // User make selection from list???
         {

         /** Check to see if this student is enrolled in a course 
         // (reject if so, user must remove them from the course first)
         **/

            name = studentName.getElementAt(index).toString();
            String courseStudent = null;  
            boolean studentExistsInCourse = false;

            for (int i = 0; i < Short_Course_Management.enrolments.length; i++)
            {
               if(Short_Course_Management.enrolments[i] != null)
               {
                  courseStudent = (Short_Course_Management.enrolments[i]).getName();

                  if (courseStudent.equals(name))
                  {
                     studentExistsInCourse = true;
                     break;
                  }
               }
            }
            if (! studentExistsInCourse)
            {
               studentName.remove(index);
               studentAge.remove(index);
               studentAddress.remove(index);
            }
            else
               JOptionPane.showMessageDialog(null,
               "Student is enrolled in a course must un-enroll first!", 
               "Can't remove", JOptionPane.ERROR_MESSAGE);
         }
         else
            JOptionPane.showMessageDialog(null,"No selection made ...", "Can't remove", 
                                                            JOptionPane.ERROR_MESSAGE);
      }

      else if (cmd.equals("OK"))
      {
         createStudentInstances();
         createEnrollmentInstances();

         // Re-enable buttons on main menu interface
         Short_Course_Management.b1.setEnabled(true);
         Short_Course_Management.b2.setEnabled(true);
         Short_Course_Management.b3.setEnabled(true);
         this.dispose();
      }

      else if (cmd.equals("Add to course >"))
      {
         String courseStudent = null;
         boolean abortEnrol = false;
         int index = studentNamelist2.getSelectedIndex();

         if (index >= 0) // User selected a student from the list?
         {
            String newStudent = studentNamelist2.getSelectedValue().toString();

         // Check to see if student already exists
            int size = enrolledStudents.getSize();
            for (int i = 0; i < size; i++)
            {
               courseStudent = enrolledStudents.getElementAt(i).toString();
               if (courseStudent.equals(newStudent))
               {
                  JOptionPane.showMessageDialog(null,"Student already exists!", "Can't enrol", 
                                                                   JOptionPane.ERROR_MESSAGE);
                  abortEnrol = true;
                  break;
               }
            }

         // Is the class full?
            int studentCount = enrolledStudents.getSize();
            boolean full = isClassFull(studentCount);
             
            if (full)
            { 
               JOptionPane.showMessageDialog(null,"The class is full sorry", "Can't enrol", 
                                                                JOptionPane.ERROR_MESSAGE);
               abortEnrol = true;
            }
            
            if (! abortEnrol)
            {     
               enrolledStudents.addElement(newStudent);

         /** Check to see if this is the student's first enrolment in a course
         //  We need to know this to be able to determine is the student is 
         //  entitled to a discount....
         **/   
               boolean firstCourse = isStudentsFirstCourse(newStudent);
               isFirstCourse.addElement(firstCourse);
            }
         }

         // User hasn't made a selction (no student to add), prompt them about it
            else
               JOptionPane.showMessageDialog(null,"No student selected ...", "Can't enrol", 
                                                                JOptionPane.ERROR_MESSAGE);
      }

      else if (cmd.equals("Remove from course"))
      {
         int index = enrolledStudentlist.getSelectedIndex();

         if (index >= 0)
         {
            enrolledStudents.remove(index);
            isFirstCourse.remove(index);
         }
         else
            JOptionPane.showMessageDialog(null,"No selection made ...", "Can't remove", 
                                                            JOptionPane.ERROR_MESSAGE);
      }

      else if (cmd.equals("Cancel"))
      {
         // Re-enable buttons on main menu interface
         Short_Course_Management.b1.setEnabled(true);
         Short_Course_Management.b2.setEnabled(true);
         Short_Course_Management.b3.setEnabled(true);
         this.dispose();
      }
   }

   public void createEnrollmentInstances(){

      String address = null;
      String name = null;
      String strAge = null;
      int age = 0; 
      int season = 0;
      
      EnrolledStudent obj = null;
      int size = enrolledStudents.getSize();

      // Null array, needs to be resorted (user may have removed some entries from list)
      for (int i = 0; i < Short_Course_Management.enrolments.length; i++)
      {
         if (course.equals("Italian Cooking"))
         {
            if(Short_Course_Management.enrolments[i] instanceof ItalianCooking)
            {
               season = ((ItalianCooking)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                     Short_Course_Management.enrolments[i] = null;
            }
         }
         if (course.equals("Seafood Cooking"))
         {
            if(Short_Course_Management.enrolments[i] instanceof SeafoodCooking)
            {
               season = ((SeafoodCooking)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                     Short_Course_Management.enrolments[i] = null;
            }
         }
         if (course.equals("Business Writing"))
         {
            if(Short_Course_Management.enrolments[i] instanceof BusinessWriting)
            {
               season = ((BusinessWriting)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                     Short_Course_Management.enrolments[i] = null;
            }
         }
         if (course.equals("Creative Writing"))
         {
            if(Short_Course_Management.enrolments[i] instanceof CreativeWriting)
            {
               season = ((CreativeWriting)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                     Short_Course_Management.enrolments[i] = null;
            }
         }
         if (course.equals("Sewing"))
         {
            if(Short_Course_Management.enrolments[i] instanceof Sewing)
            {
               season = ((Sewing)Short_Course_Management.enrolments[i]).getSeason();
                  if (season == this.season)
                     Short_Course_Management.enrolments[i] = null;
            }
         }
      }
 
      // Scan-through data lists and gather info needed for creating an obj instance
      for (int i = 0; i < size; i++)
      {
         String enrolledName = enrolledStudents.getElementAt(i).toString();
         String firstCourse = isFirstCourse.getElementAt(i).toString();

      // Find student obj, we need their address & age
         for (int j = 0; j < Short_Course_Management.students.length; j++)
         {
            if (Short_Course_Management.students[j] != null)
            {
               name = Short_Course_Management.students[j].getName();

               if (name.equals(enrolledName))
               {  
                  age = Short_Course_Management.students[j].getAge();
                  address = Short_Course_Management.students[j].getAddress();
                  break;
               }
            }
         }

      /** Confirm that student is still enrolled in multiple courses   
      //  If they aren't, reset first course flag disallowing discount
      **/
         boolean fCourse = Boolean.valueOf (firstCourse);

         if (! fCourse)
         {  
            boolean multipleEnroll = isStudentsFirstCourse(enrolledName);
            fCourse = multipleEnroll;
         }

      // Create instances
         if (course.equals("Italian Cooking"))
            obj = new ItalianCooking(enrolledName, address, age, this.season, fCourse);
         if (course.equals("Seafood Cooking"))
            obj = new SeafoodCooking(enrolledName, address, age, this.season, fCourse);
         if (course.equals("Business Writing"))
            obj = new BusinessWriting(enrolledName, address, age, this.season, fCourse);
         if (course.equals("Creative Writing"))
            obj = new CreativeWriting(enrolledName, address, age, this.season, fCourse);
         if (course.equals("Sewing"))
            obj = new Sewing(enrolledName, address, age, this.season, fCourse);

      // Find empty pos in array to store obj
         for (int j = 0; j < Short_Course_Management.students.length; j++)
         { 
            if (Short_Course_Management.enrolments[j] == null)
            {
                Short_Course_Management.enrolments[j] = obj; 
                break;
            }
         }
      }
   }

   public boolean isStudentsFirstCourse(String chkName){

      String name = null;      
      boolean exists = false;

      for (int i = 0; i < Short_Course_Management.enrolments.length; i++)
      {
         if (Short_Course_Management.enrolments[i] != null)
         {
            name = Short_Course_Management.enrolments[i].getName();
            if (chkName.equals(name))
            { 
               exists = true;
               break;
            }
         }   
      }
      return !exists;
   }

   public void createStudentInstances(){

      String address = null;
      String name = null;
      String strAge = null;
      int age = 0; 

      EnrolledStudent obj = null;
      int size = studentName.getSize();
      int index = studentNamelist1.getSelectedIndex();

      // Null array, needs to be resorted (user may have removed some entries from list)
      for (int i = 0; i < Short_Course_Management.students.length; i++)
         Short_Course_Management.students[i] = null;

      // Scan-through data lists and gather info needed for creating an obj instance
      for (int i = 0; i < size; i++)
      {
         name = studentName.getElementAt(i).toString();
         address = studentAddress.getElementAt(i).toString();
         strAge = studentAge.getElementAt(i).toString();
         age = Integer.parseInt(strAge);

      // Create instances
         obj = new Student(name, address, age);
         Short_Course_Management.students[i] = obj;
      }
   }

   public void loadEnrolledList(){

   /** 
   // Loop-through instances and extract info to display 
   // (names only shown, but other info held)
   **/

      String name = null;
      String address = null;
      boolean firstCourse = false;
      int season = 0;
      int age = 0;

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
                     name = (Short_Course_Management.enrolments[i]).getName();
                     enrolledStudents.addElement(name);
                     firstCourse = ((ItalianCooking)Short_Course_Management.enrolments[i]).firstCourse();
                     isFirstCourse.addElement(firstCourse);
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
                     name = (Short_Course_Management.enrolments[i]).getName();
                     enrolledStudents.addElement(name);
                     firstCourse = ((SeafoodCooking)Short_Course_Management.enrolments[i]).firstCourse();
                     isFirstCourse.addElement(firstCourse);
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
                     name = (Short_Course_Management.enrolments[i]).getName();
                     enrolledStudents.addElement(name);
                     firstCourse = ((BusinessWriting)Short_Course_Management.enrolments[i]).firstCourse();
                     isFirstCourse.addElement(firstCourse);
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
                     name = (Short_Course_Management.enrolments[i]).getName();
                     enrolledStudents.addElement(name);
                     firstCourse = ((CreativeWriting)Short_Course_Management.enrolments[i]).firstCourse();
                     isFirstCourse.addElement(firstCourse);
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
                     name = (Short_Course_Management.enrolments[i]).getName();
                     enrolledStudents.addElement(name);
                     firstCourse = ((Sewing)Short_Course_Management.enrolments[i]).firstCourse();
                     isFirstCourse.addElement(firstCourse);
                  }
               }
            }
         }
      }
   }

   public boolean isClassFull(int studentsEnrolled){

      EnrolledStudent temp = null;
      boolean full = false; 

      if (this.course.equals("Italian Cooking"))
      {
         temp = new ItalianCooking("", "", 0, 0, false);
         full = ((ItalianCooking) temp).isClassFull(studentsEnrolled);  
      }
      else if (this.course.equals("Seafood Cooking"))
      {
         temp = new SeafoodCooking("", "", 0, 0, false);
         full = ((SeafoodCooking) temp).isClassFull(studentsEnrolled);  
      }
      else if (this.course.equals("Business Writing"))
      {
         temp = new BusinessWriting("", "", 0, 0, false);
         full = ((BusinessWriting) temp).isClassFull(studentsEnrolled);  
      }
      else if (this.course.equals("Creative Writing"))
      {
         temp = new CreativeWriting("", "", 0, 0, false);
         full = ((CreativeWriting) temp).isClassFull(studentsEnrolled);  
      }
      else if (this.course.equals("Sewing"))
      {
         temp = new Sewing("", "", 0, 0, false);
         full = ((Sewing) temp).isClassFull(studentsEnrolled);  
      }
      return full;
   }

   public void loadStudentList(){

   /** 
   // Loop-through instances and extract info to display ... 
   // Names only shown in list, but other info such as address is held
   **/

      String name = null;
      String address = null;
      int age = 0;

      for (int i = 0; i < Short_Course_Management.students.length; i++)
      {
         if (Short_Course_Management.students[i] != null)
         {
            name = Short_Course_Management.students[i].getName();
            age = Short_Course_Management.students[i].getAge();
            address = Short_Course_Management.students[i].getAddress();
            
            studentName.addElement(name);
            studentAge.addElement(age);
            studentAddress.addElement(address);
         }
      }
   }

   public void windowClosing(WindowEvent e)
   {
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

