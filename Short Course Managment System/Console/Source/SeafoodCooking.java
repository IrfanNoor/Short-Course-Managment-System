/**   
'********************************************************************************'
'~ CPT23 Assignment 2 Short Course Management System  -  Seafood Cooking class  ~'
'********************************************************************************'
' Student: Trent Jackson                                                15/09/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
*/

public class SeafoodCooking extends Student
{
   private static final double STUDENT_FEE = 700;
   private static final double BASE_RUN_COST = 1000;
   private static final double PER_STUDENT_COST = 50;
   private static final int MAX_ENROLMENTS = 10;
   
   private double debt;

   public SeafoodCooking(String name, String address, int age, double debt, double ageDiscount, double futherDiscount)
   {
      super(name, address, age);

      futherDiscount = (STUDENT_FEE / 100) * futherDiscount;

      if (debt == 0) // User creating new instance   
         this.debt = STUDENT_FEE - (ageDiscount + futherDiscount);
      else           // Creating new instance from flat file database (we already know the debt)
         this.debt = debt;
   }

   public double getDebt()
   {
      return debt;
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
