/**   
'********************************************************************************'
'~ CPT23 Assignment 2: Implement Short Course Management System -  Driver class ~'
'********************************************************************************'
' Student: Trent Jackson                                                15/09/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
*/

import java.io.*;
import java.util.*;

public class Short_Course_Management
{
   private static Student [] enrolments = new Student[100];
   private static Scanner console = new Scanner(System.in);

   public static void main (String [] args) throws IOException
   {
      System.out.println("Reading student info from flat-file database ...");  
      readStudentDetailsFromFile();
      int selection = 0;

      do
      {
         System.out.println();
         System.out.println("======================================");
         System.out.println("--- Short Course Management System ---");
         System.out.println("--------------------------------------");
         System.out.println("1. Add a new student");
         System.out.println();
         System.out.println("2. Withdraw a student");
         System.out.println();
         System.out.println("3. Display student list for a course");
         System.out.println();
         System.out.println("4. Display the course figures");
         System.out.println();
         System.out.println("5. Quit ...");

         try
         {
            System.out.println();
            System.out.print("Enter an option: ");
            selection = console.nextInt();
         }

         catch (Exception e)
         {
            System.out.println("Error! Invalid Selection - " +
                                  "please try again...");
            selection = 0;
            String clearBuff = console.next();
         }

         switch(selection)
         {
            case 1:
               addStudent();
               break;

            case 2:
               withdrawStudent();
               break;

            case 3:
               displayStudentList();
               break;

            case 4:
               displayCourseFigures();
               break;

            case 5:
               writeStudentDetailsToFile();
               break;

            default:
               if (selection != 0)
               {
                  System.out.println("Error!  Invalid Selection - " +
                                     "please try again...");
                  System.out.println();
               }
         }
      } while (selection!= 5);      
   }  

   public static void addStudent()
   {
      System.out.println();
      System.out.println("=====================");
      System.out.println("+ Enrol New Student +");
      System.out.println("=====================");
      System.out.println();

      /** 
      // (Prompt user for student's name) 
      */

      String studentName;
      String clrBuff = console.nextLine();
      System.out.print("Student's name: ");
      studentName = console.nextLine();

      /** 
      // (Prompt user for student's address) 
      */

      String studentAddress;
      System.out.print("Address: ");
      studentAddress = console.nextLine();

      int studentAge = 0;
      boolean invalidEntry = false;

      /** 
      // (Prompt user for student's age) 
      // Validates input - has error handling 
      // for non integer entry ...
      */

      do
      {
         try
         {
            invalidEntry = false; 
            System.out.print("Age: ");
            studentAge = console.nextInt();
         }

         catch (Exception e)
         {
            System.out.println("Error! Invalid Entry - " +
                                   "please try again...");
            invalidEntry = true;
            studentAge = 0;
            String clearBuff = console.next();
         }
      } while (invalidEntry == true);

      int courseID = 0;
      courseID = getCourseID();

      /** 
      // (Fetch num of students enrolled) 
      */
      int studentsEnrolled = getNumStudents(courseID);

      Student obj = null;
      boolean isClassFull = false;
      boolean rejectEnrol = false;
      int studentNum = 0;
      double ageDiscount = 0;
      double furtherDiscount = 0;
      String strBuff = null;

      /** 
      // Check for student already existing (reject enrollment)
      // See if the class is full (reject)
      // Check if student is eligible for a discount
      // $100 off if over 50, 20% off if in another course ... 
      */

      switch(courseID)
      {
         case 1: // Italian Cooking //
            
            // Create instance of student (this instance is just for reference)
            obj = new ItalianCooking(studentName, studentAddress, studentAge, 0, 0, 0);

            // Does student already exist in this course?
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof ItalianCooking)
               { 
                  strBuff = enrolments[i].getName();
                  if (strBuff.equalsIgnoreCase(studentName))
                  {
                     System.out.println("Student already exists" +
                                  " - enrollment rejected!");
                     rejectEnrol = true;
                     break; 
                  }
               }
            }   
          
            if (!rejectEnrol)
            { 
               isClassFull = ((ItalianCooking) obj).isClassFull(studentsEnrolled);
               if (isClassFull == true)
                  System.out.println("The class is full" +
                                     " - enrollment rejected!");
               else // - Enrol! - //
               { 
                  if (studentAge >= 50)
                  { 
                     ageDiscount = 100;
                     System.out.println("Pension discount applied ...");
                  }
                  if (IsStudentInOtherCourse(studentName) == true)
                  {
                     furtherDiscount = 20;
                     System.out.println("Existing student 20% discount applied ...");
                  } 

                  // Create final instance of student, this time offically store it in data array
                  obj = new ItalianCooking(studentName, studentAddress, studentAge, 0, ageDiscount, furtherDiscount);
                  studentNum = findEmptyPos();
                  enrolments[studentNum] = obj;
                  System.out.println("Student successfully " + 
                                     "enrolled! ...");
               }
            }
            break;

         case 2: // Seafood Cooking //
            
            // Create instance of student 
            obj = new SeafoodCooking(studentName, studentAddress, studentAge, 0, 0, 0);
          
            // Does student already exist in this course?
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof SeafoodCooking)
               { 
                  strBuff = enrolments[i].getName();
                  if (strBuff.equalsIgnoreCase(studentName))
                  {
                     System.out.println("Student already exists" +
                                  " - enrollment rejected!");
                     rejectEnrol = true;
                     break; 
                  }
               }
            }   
          
            if (!rejectEnrol)
            { 
               isClassFull = ((SeafoodCooking) obj).isClassFull(studentsEnrolled);
               if (isClassFull == true)
                  System.out.println("The class is full" +
                                     " - enrollment rejected!");
               else // - Enrol! - //
               { 
                  if (studentAge >= 50)
                  { 
                     ageDiscount = 100;
                     System.out.println("Pension discount applied ...");
                  }
                  if (IsStudentInOtherCourse(studentName) == true)
                  {
                     furtherDiscount = 20;
                     System.out.println("Existing student 20% discount applied ...");
                  } 

                  // Create final instance of student, this time offically store it in data array
                  obj = new SeafoodCooking(studentName, studentAddress, studentAge, 0, ageDiscount, furtherDiscount);
                  studentNum = findEmptyPos();
                  enrolments[studentNum] = obj;
                  System.out.println("Student successfully " + 
                                     "enrolled! ...");
               }
            }
            break;

         case 3: // Business writing //
            
            // Create instance of student 
            obj = new BusinessWriting(studentName, studentAddress, studentAge, 0, 0, 0);
          
            // Does student already exist in this course?
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof BusinessWriting)
               { 
                  strBuff = enrolments[i].getName();
                  if (strBuff.equalsIgnoreCase(studentName))
                  {
                     System.out.println("Student already exists" +
                                  " - enrollment rejected!");
                     rejectEnrol = true;
                     break; 
                  }
               }
            }   

            if (!rejectEnrol)
            { 
               isClassFull = ((BusinessWriting) obj).isClassFull(studentsEnrolled);
               if (isClassFull == true)
                  System.out.println("The class is full" +
                                     " - enrollment rejected!");
               else // - Enrol! - //
               { 
                  if (studentAge >= 50)
                  { 
                     ageDiscount = 100;
                     System.out.println("Pension discount applied ...");
                  }
                  if (IsStudentInOtherCourse(studentName) == true)
                  {
                     furtherDiscount = 20;
                     System.out.println("Existing student 20% discount applied ...");
                  } 

                  // Create final instance of student, this time offically store it in data array
                  obj = new BusinessWriting(studentName, studentAddress, studentAge, 0, ageDiscount, furtherDiscount);
                  studentNum = findEmptyPos();
                  enrolments[studentNum] = obj;
                  System.out.println("Student successfully " + 
                                     "enrolled! ...");
               }
            }
            break;

         case 4: // Creative writing //
            
            // Create instance of student 
            obj = new CreativeWriting(studentName, studentAddress, studentAge, 0, 0, 0);
          
            // Does student already exist in this course?
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof CreativeWriting)
               { 
                  strBuff = enrolments[i].getName();
                  if (strBuff.equalsIgnoreCase(studentName))
                  {
                     System.out.println("Student already exists" +
                                  " - enrollment rejected!");
                     rejectEnrol = true;
                     break; 
                  }
               }
            }   
          
            if (!rejectEnrol)
            { 
               isClassFull = ((CreativeWriting) obj).isClassFull(studentsEnrolled);
               if (isClassFull == true)
                  System.out.println("The class is full" +
                                     " - enrollment rejected!");
               else // - Enrol! - //
               { 
                  if (studentAge >= 50)
                  { 
                     ageDiscount = 100;
                     System.out.println("Pension discount applied ...");
                  }
                  if (IsStudentInOtherCourse(studentName) == true)
                  {
                     furtherDiscount = 20;
                     System.out.println("Existing student 20% discount applied ...");
                  } 

                  // Create final instance of student, this time offically store it in data array
                  obj = new CreativeWriting(studentName, studentAddress, studentAge, 0, ageDiscount, furtherDiscount);
                  studentNum = findEmptyPos();
                  enrolments[studentNum] = obj;
                  System.out.println("Student successfully " + 
                                     "enrolled! ...");
               }
            }
            break;

         case 5: // Sewing //
            
            // Create instance of student 
            obj = new Sewing(studentName, studentAddress, studentAge, 0, 0, 0);
          
            // Does student already exist in this course?
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof Sewing)
               { 
                  strBuff = enrolments[i].getName();
                  if (strBuff.equalsIgnoreCase(studentName))
                  {
                     System.out.println("Student already exists" +
                                  " - enrollment rejected!");
                     rejectEnrol = true;
                     break; 
                  }
               }
            }   
          
            if (!rejectEnrol)
            { 
               isClassFull = ((Sewing) obj).isClassFull(studentsEnrolled);
               if (isClassFull == true)
                  System.out.println("The class is full" +
                                     " - enrollment rejected!");
               else // - Enrol! - //
               { 
                  if (studentAge >= 50)
                  { 
                     ageDiscount = 100;
                     System.out.println("Pension discount applied ...");
                  }
                  if (IsStudentInOtherCourse(studentName) == true)
                  {
                     furtherDiscount = 20;
                     System.out.println("Existing student 20% discount applied ...");
                  } 

                  // Create final instance of student, this time offically store it in data array
                  obj = new Sewing(studentName, studentAddress, studentAge, 0, ageDiscount, furtherDiscount);
                  studentNum = findEmptyPos();
                  enrolments[studentNum] = obj;
                  System.out.println("Student successfully " + 
                                     "enrolled! ...");
               }
            }
            break;
      }
   }

   public static int findEmptyPos(){   
   /** 
   // 1. Find first empty spot in array
   // 2. Return index of to be used
   */

      int j = 0;
      for (int i = 0; i < enrolments.length; i++)
      {
        if (enrolments[i] == null)
        {
           j = i;
           break;
        }
      }
      return j;      
   }
  
   public static int getNumStudents(int classToCheck){
   /** 
   // (Find how many students there are in a given course) 
   */

      int studentCount = 0;

      switch(classToCheck)
      {
         case 1: // Italian Cooking //
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof ItalianCooking)
                  studentCount ++;
            }
            break;

         case 2: // Seafood Cooking //
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof SeafoodCooking)
                  studentCount ++;
            }
            break;

         case 3: // Business writing //
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof BusinessWriting)
                  studentCount ++;
            }
            break;

         case 4: // Creative writing //
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof CreativeWriting)
                  studentCount ++;
            }
            break;

         case 5: // Sewing //
            for (int i = 0; i < enrolments.length; i++)
            {  
               if(enrolments[i] instanceof Sewing)
                  studentCount ++;
            }
            break;
      }
      return studentCount;
   }

   public static void displayStudentList(){
   /** 
   // (Show list of students for any given course)
   // 1. Name
   // 2. Address
   // 3. Age
   */

      System.out.println();
      System.out.println("=====================");
      System.out.println("+   Student List    +");
      System.out.println("=====================");

      int courseID = 0;
      String strBuff = null;
      int intBuff = 0;
      courseID = getCourseID();

      if (courseID == 1)
      {
         System.out.println("==============================================");
         System.out.println("== Students Enrolled in Italian Cooking ... ==");
         System.out.println("==============================================");
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof ItalianCooking)
            {
               strBuff = enrolments[i].getName();
               System.out.print(strBuff + ", ");

               strBuff = enrolments[i].getAddress();
               System.out.print(strBuff + ", ");

               intBuff = enrolments[i].getAge();
               System.out.print(intBuff);
               System.out.println();
            } 
         }
      }
      else if (courseID == 2)
      {
         System.out.println("==============================================");
         System.out.println("== Students Enrolled in Seafood Cooking ... ==");
         System.out.println("==============================================");
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof SeafoodCooking)
            {
               strBuff = enrolments[i].getName();
               System.out.print(strBuff + ", ");

               strBuff = enrolments[i].getAddress();
               System.out.print(strBuff + ", ");

               intBuff = enrolments[i].getAge();
               System.out.print(intBuff);
               System.out.println();
            } 
         }
      }
      else if (courseID == 3)
      {
         System.out.println("===============================================");
         System.out.println("== Students Enrolled in Business writing ... ==");
         System.out.println("===============================================");
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof BusinessWriting)
            {
               strBuff = enrolments[i].getName();
               System.out.print(strBuff + ", ");

               strBuff = enrolments[i].getAddress();
               System.out.print(strBuff + ", ");

               intBuff = enrolments[i].getAge();
               System.out.print(intBuff);
               System.out.println();
            } 
         }
      }
      else if (courseID == 4)
      {
         System.out.println("===============================================");
         System.out.println("== Students Enrolled in Creative writing ... ==");
         System.out.println("===============================================");
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof CreativeWriting)
            {
               strBuff = enrolments[i].getName();
               System.out.print(strBuff + ", ");

               strBuff = enrolments[i].getAddress();
               System.out.print(strBuff + ", ");

               intBuff = enrolments[i].getAge();
               System.out.print(intBuff);
               System.out.println();
            } 
         }
      }
      else if (courseID == 5)
      {
         System.out.println("=====================================");
         System.out.println("== Students Enrolled in Sewing ... ==");
         System.out.println("=====================================");
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof Sewing)
            {
               strBuff = enrolments[i].getName();
               System.out.print(strBuff + ", ");

               strBuff = enrolments[i].getAddress();
               System.out.print(strBuff + ", ");

               intBuff = enrolments[i].getAge();
               System.out.print(intBuff);
               System.out.println();
            } 
         }
      }
      System.out.println();               
   }

   public static boolean IsStudentInOtherCourse(String name){
   /** 
   // (Try to locate student in another course)
   // Used to determine if student gets a 20% discount
   */

      String strBuff = null;
      boolean studentExists = false;

      for (int i = 0; i < enrolments.length; i++)
      {
         if (enrolments[i] != null)
         {
            strBuff = enrolments[i].getName();
            if (name.equalsIgnoreCase(strBuff))
            {
               studentExists = true;
               break;
            } 
         }
      }
      return studentExists;
   }

   public static int getCourseID(){
   /** 
   // (Implemted many time throughout for obtaining Course ID from user)
   // 1. Show course ledgend - ID's
   // 2. Prompt user for selection
   // 3. Return this val
   */

      System.out.println();
      System.out.println("<- Which course? ->");
      System.out.println();
      System.out.println("Italian Cooking  001");
      System.out.println("Seafood Cooking  002");
      System.out.println("Business Writing 003");
      System.out.println("Creative Writing 004");
      System.out.println("Sewing ...       005");
      System.out.println();

      /** 
      // (Prompt user for course ID) 
      // Validates input - has comprehensive error handling 
      // for non integer entry & non existent course ...
      */

      boolean invalidEntry;
      int selID = 0;

      do
      {
         try
         {
            invalidEntry = false;
            System.out.print("Enter ID: ");
            selID = console.nextInt();

            if (selID > 5)
            {
               invalidEntry = true;
               System.out.println("Error! Invalid Entry - " +
                                  "please try again...");
            }
          }
            
          catch (Exception e)
          {
             System.out.println("Error! Invalid Entry - " +
                                "please try again...");
             invalidEntry = true;
             selID = 0;
             String clearBuff = console.next();
          }
      } while (invalidEntry == true);
      return selID;
   }
   
   public static void displayCourseFigures(){
   /** 
   // (Course figures for all courses) 
   // 1. Income
   // 2. Cost
   // 3. Profit
   // 4. Num students in class
   */

      System.out.println();
      System.out.println("=====================");
      System.out.println("+   Course Figures  +");
      System.out.println("=====================");
      System.out.println();

      int studentCount = 0;
      double studentDebt = 0;
      double income = 0;
      double cost = 0;
      int index = 0;
      boolean studentExists = false;
   
      // Italian cooking //
      studentCount = getNumStudents(1);
      System.out.print("Italian Cooking: Students " + studentCount);
      
      for (int i = 0; i < enrolments.length; i++)
      {
         if (enrolments[i] instanceof ItalianCooking)
         {
            studentDebt = ((ItalianCooking) enrolments[i]).getDebt(); 
            income += studentDebt;
            index = i;
            studentExists = true; 
         }
      } 
      System.out.print(", Income: $" + income);

      if (studentExists)
        cost = ((ItalianCooking) enrolments[index]).calcCost(studentCount);
      
      System.out.print(", Cost: $" + cost);
      System.out.print(", Profit: $" + (income - cost));
      System.out.println();

      // Seafood cooking //
      studentCount = getNumStudents(2);
      System.out.print("Seafood Cooking: Students " + studentCount);
      
      studentDebt = 0;
      income = 0;
      cost = 0;
      index = 0;
      studentExists = false;

      for (int i = 0; i < enrolments.length; i++)
      {
         if (enrolments[i] instanceof SeafoodCooking)
         {
            studentDebt = ((SeafoodCooking) enrolments[i]).getDebt(); 
            income += studentDebt;
            index = i;
            studentExists = true; 
         }
      } 
      System.out.print(", Income: $" + income);

      if (studentExists)
        cost = ((SeafoodCooking) enrolments[index]).calcCost(studentCount);

      System.out.print(", Cost: $" + cost);
      System.out.print(", Profit: $" + (income - cost));
      System.out.println();

      // Business writing //
      studentCount = getNumStudents(3);
      System.out.print("Business Writing: Students " + studentCount);
      
      studentDebt = 0;
      income = 0;
      cost = 0;
      index = 0;
      studentExists = false;

      for (int i = 0; i < enrolments.length; i++)
      {
         if (enrolments[i] instanceof BusinessWriting)
         {
            studentDebt = ((BusinessWriting) enrolments[i]).getDebt(); 
            income += studentDebt;
            index = i;
            studentExists = true; 
         }
      } 
      System.out.print(", Income: $" + income);

      if (studentExists)
        cost = ((BusinessWriting) enrolments[index]).calcCost(studentCount);
  
      System.out.print(", Cost: $" + cost);
      System.out.print(", Profit: $" + (income - cost));
      System.out.println();

      // Creative Writing //
      studentCount = getNumStudents(4);
      System.out.print("Creative Writing: Students " + studentCount);
      
      studentDebt = 0;
      income = 0;
      cost = 0;
      index = 0;
      studentExists = false;

      for (int i = 0; i < enrolments.length; i++)
      {
         if (enrolments[i] instanceof CreativeWriting)
         {
            studentDebt = ((CreativeWriting) enrolments[i]).getDebt(); 
            income += studentDebt;
            index = i;
            studentExists = true; 
         }
      } 
      System.out.print(", Income: $" + income);

      if (studentExists)
        cost = ((CreativeWriting) enrolments[index]).calcCost(studentCount);
  
      System.out.print(", Cost: $" + cost);
      System.out.print(", Profit: $" + (income - cost));
      System.out.println();

      // Sewing //
      studentCount = getNumStudents(5);
      System.out.print("Sewing: Students " + studentCount);
      
      studentDebt = 0;
      income = 0;
      cost = 0;
      index = 0;
      studentExists = false;

      for (int i = 0; i < enrolments.length; i++)
      {
         if (enrolments[i] instanceof Sewing)
         {
            studentDebt = ((Sewing) enrolments[i]).getDebt(); 
            income += studentDebt;
            index = i;
            studentExists = true; 
         }
      } 
      System.out.print(", Income: $" + income);

      if (studentExists)
        cost = ((Sewing) enrolments[index]).calcCost(studentCount);
  
      System.out.print(", Cost: $" + cost);
      System.out.print(", Profit: $" + (income - cost));
      System.out.println();
   }

   public static void withdrawStudent(){
   /** 
   // (Widthdraw student from course)
   // 1. Request name
   // 2. Course ID
   // 3. Find student in array
   // 4. Null pos in array
   */

      System.out.println();
      System.out.println("=====================");
      System.out.println("+ Widthdraw Student +");
      System.out.println("=====================");
      System.out.println();

      String name = null;
      String clrBuff = console.nextLine();
      System.out.print("Student's name: ");
      name = console.nextLine();

      int courseID = getCourseID();
      String strBuff = null;

      if (courseID == 1)
      {
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof ItalianCooking)
            {
               strBuff = enrolments[i].getName();
               if (name.equalsIgnoreCase(strBuff))
               {
                  enrolments[i] = null;
                  System.out.print("Student successfully withdrawn ...");
                  break;
               }
            }  
         }
      }
      else if (courseID == 2)
      {
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof SeafoodCooking)
            {
               strBuff = enrolments[i].getName();
               if (name.equalsIgnoreCase(strBuff))
               {
                  enrolments[i] = null;
                  System.out.print("Student successfully withdrawn ...");
                  break;
               }
            }  
         }
      }
      else if (courseID == 3)
      {
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof BusinessWriting)
            {
               strBuff = enrolments[i].getName();
               if (name.equalsIgnoreCase(strBuff))
               {
                  enrolments[i] = null;
                  System.out.print("Student successfully withdrawn ...");
                  break;
               }
            }  
         }
      }
      else if (courseID == 4)
      {
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof CreativeWriting)
            {
               strBuff = enrolments[i].getName();
               if (name.equalsIgnoreCase(strBuff))
               {
                  enrolments[i] = null;
                  System.out.print("Student successfully withdrawn ...");
                  break;
               }
            }  
         }
      }
      else if (courseID == 5)
      {
         for (int i = 0; i < enrolments.length; i++)
         {
            if (enrolments[i] instanceof Sewing)
            {
               strBuff = enrolments[i].getName();
               if (name.equalsIgnoreCase(strBuff))
               {
                  enrolments[i] = null;
                  System.out.print("Student successfully withdrawn ...");
                  break;
               }
            }  
         }
      }
      else
         System.out.print("Error: Can't find student in course!");
   }      

   public static void writeStudentDetailsToFile() throws IOException{

      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("studentlist.txt")));

      String name = null;
      String address = null;
      String course = null;
      int age = 0;
      double debt = 0;

      for (int i = 0; i < enrolments.length; i++)
      {
         if (enrolments[i] != null)
         {
            if (enrolments[i] instanceof ItalianCooking)
            {
               course = "ItalianCooking";
               debt = ((ItalianCooking) enrolments[i]).getDebt();
            }
            else if (enrolments[i] instanceof SeafoodCooking)
            {
               course = "SeafoodCooking";
               debt = ((SeafoodCooking) enrolments[i]).getDebt();
            }
            else if (enrolments[i] instanceof BusinessWriting)
            {
               course = "BusinessWriting";
               debt = ((BusinessWriting) enrolments[i]).getDebt();
            } 
            else if (enrolments[i] instanceof CreativeWriting)
            {
               course = "CreativeWriting";
               debt = ((CreativeWriting) enrolments[i]).getDebt();
            } 
            else if (enrolments[i] instanceof Sewing)
            {
               course = "Sewing";
               debt = ((Sewing) enrolments[i]).getDebt();
            } 
   
            pw.println(course);
            name = enrolments[i].getName();
            pw.println(name);
   
            address = enrolments[i].getAddress();
            pw.println(address);

            age = enrolments[i].getAge();
            pw.println(age);
      
            pw.println(debt);
         }
      }  
      pw.close();   
   } 

   public static void readStudentDetailsFromFile() throws IOException
   {
      String course = null;
      String name = null;
      String address = null;
      int age = 0;
      double debt = 0;
      
      Student obj = null;
      int index = 0;

      try
      {
         BufferedReader br = new BufferedReader(new FileReader("studentlist.txt"));

         while ((course = br.readLine()) !=null) 
         {
            /** Grab next 4 lines (this is student info for the course)
            //  1. name
            //  2. address
            //  3. age
            //  4. amount owing (for this course only)
            */ 
            
            //for (int i = 0; i < 4; i ++)
            {
               name = br.readLine();
               address = br.readLine();
               age = Integer.parseInt(br.readLine());
               debt = Double.parseDouble(br.readLine());
            }

            // Create obj instance & save to array
            if (course.equals("ItalianCooking"))
               obj = new ItalianCooking(name, address, age, debt, 0, 0);
            else if (course.equals("SeafoodCooking"))
               obj = new SeafoodCooking(name, address, age, debt, 0, 0);
            else if (course.equals("BusinessWriting"))
               obj = new BusinessWriting(name, address, age, debt, 0, 0);
            else if (course.equals("CreativeWriting"))
               obj = new CreativeWriting(name, address, age, debt, 0, 0);
            else if (course.equals("Sewing"))
               obj = new Sewing(name, address, age, debt, 0, 0);

            enrolments[index] = obj;
            index ++; 
         }
      }

      catch (FileNotFoundException e)
      {
         System.out.println("Error! studentlist.txt file was not found!");
      }
      
   }    
}

/** THE END !!! - phew :) */


