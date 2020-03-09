package Model;

import java.util.ArrayList;

public class FootballPlayer extends Person implements TableMember{

       private int number;

       private String position;
       
       public FootballPlayer(){
           super(" ", new Height(0, 0), 0, " ", " ");
           
           this.number = 0;
           
           this.position = " ";
           
       };
       
        public FootballPlayer(int number,String name, String position, int feet,int inches, int weight, String hometown, String highSchool) {
        
            super(name, new Height(feet, inches), weight, hometown, highSchool);

            this.number = number;

            this.position = position;
            
       }
       
       public void setNumber(int number){
           
           this.number = number;
           
       }
       
       public void setPosition(String position){
           
           this.position = position;
           
       }
       
       public String toString(){
           
            return(name + "," + position);
       }
       
       
       @Override
       
       public String getAttribute(int n) {

             switch(n)
             {

                    case 0 : return number + "";

                    case 1 : return position + "";

                    case 2 : return name + "";                  

                    case 3 : return this.getHeight() + "";

                    case 4 : return weight + "";
                  
                    case 5 : return hometown + "";
                            
                    case 6 : return highSchool + "";
                         
             }

             return null;
       }

       @Override
       
       public ArrayList<String> getAttributes() {

             ArrayList<String> attributes = new ArrayList<>();

             for(int n=0; n<7; n++){
              
                 attributes.add(getAttribute(n));
                 
             }

             return attributes;
             
       }

       @Override
       
       public String getAttributeName(int n) {

             switch(n)

             {

                    case 0 : return "number";

                    case 1 : return "position";

                    case 2 : return "name";

                    case 3 : return "height";

                    case 4 : return "weight";

                    case 5 : return "hometown";

                    case 6 : return "highSchool";

             }

             return null;

       }

       @Override
       
       public ArrayList<String> getAttributeNames() {

             ArrayList<String> attributesNames = new ArrayList<>();

             for(int n=0;n<7;n++)

                    attributesNames.add(getAttributeName(n));

             return attributesNames;

       }
       
       

}