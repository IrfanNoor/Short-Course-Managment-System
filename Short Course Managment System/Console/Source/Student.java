/**   
'********************************************************************************'
'~ CPT23 Assignment 2 Short Course Management System  -  Student Abstract class ~'
'********************************************************************************'
' Student: Trent Jackson                                                15/09/07 ' 
'     OUA: 105464                                                                '
'********************************************************************************'
*/

public abstract class Student
{
   private String name;
   private String address;
   private int age;

   public Student (String name, String address, int age)
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
