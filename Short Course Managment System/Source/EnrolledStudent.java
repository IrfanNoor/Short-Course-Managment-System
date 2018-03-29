/**   
'********************************************************************************'
'~ CPT23 Assignment 3 Short Course Management System  -  Enrolled Student class ~'
'********************************************************************************'
' Student: Trent Jackson                                                15/09/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
*/

public abstract class EnrolledStudent
{
   private String name;
   private String address;
   private int age;

   public EnrolledStudent (String name, String address, int age)
   {
      this.name = name;
      this.address = address;
      this.age = age;
   }

   public String getName()
   {
      return name;   
   } 

   public String getAddress()
   {
      return address;   
   } 

   public int getAge()
   {
      return age;   
   } 

}
