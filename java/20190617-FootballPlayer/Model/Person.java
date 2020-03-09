package Model;

public class Person {
    
       protected String name;

       protected Height height;

       protected int weight;

       protected String hometown;

       protected String highSchool;
       
       public Person(){
           
           this.height = new Height(0, 0);
       
       };

       public Person(String name, Height height, int weight, String hometown, String highSchool)
       {
           
             this.name = name;

             this.height = height;

             this.weight = weight;

             this.hometown = hometown;

             this.highSchool = highSchool;

       }
       
       public void setWeight(int weight){
           
           this.weight = weight;
           
       }
       
       public void setName(String name){
           
           this.name = name;
           
       }
       
       public void setHometown(String hometown){
           
           this.hometown = hometown;
           
       }
       
       public void setHeight(Height height){
           
           this.height = height;
           
       }
       
       
       public void setHighSchool(String highSchool) {
           
           this.highSchool = highSchool;
           
       }
       
       public String getHeight() {
           return this.height.toString();
       }

       public String toString() {
             return(name+","+height+","+weight+","+hometown+","+highSchool);
       }

}