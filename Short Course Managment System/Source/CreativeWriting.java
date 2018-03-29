/**   
'********************************************************************************'
'~ CPT23 Assignment 3 Short Course Management System  -  Creative Writing class ~'
'********************************************************************************'
' Student: Trent Jackson                                                15/09/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
*/

public class CreativeWriting extends EnrolledStudent
{
   private static final double STUDENT_FEE = 200;
   private static final double BASE_RUN_COST = 800;
   private static final double PER_STUDENT_COST = 0;
   private static final int MAX_ENROLMENTS = 15; 

   private int season;
   boolean isFirstCourse;

   public CreativeWriting(String name, String address, int age, int season, boolean isFirstCourse)
   {
      super(name, address, age);
      this.season = season;
      this.isFirstCourse = isFirstCourse;
  }

   public boolean firstCourse()
   {
      return isFirstCourse;
   }

   public int getSeason()
   {
      return season;
   }

   public double getStudentFee()
   {
      return STUDENT_FEE;
   }

   public double calcCost(int numStudentsEnrolled)
   {
      double cost;
      cost = (numStudentsEnrolled * PER_STUDENT_COST) + BASE_RUN_COST;
      return cost;
   }

   public boolean isClassFull(int numStudentsEnrolled)
   {
      if (numStudentsEnrolled == MAX_ENROLMENTS)
         return true;  
      else
         return false;
   }
}
