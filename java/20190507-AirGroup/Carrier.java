import java.util.Scanner;
import java.io.IOException;

public class Carrier
{
    private Scanner kb; 
    private Aircraft plane1;
    private Aircraft plane2;

    private final int ADD_AIRCRAFT = 1;
    private final int DISPLAY = 2;
    private final int ADD_AIRCREW = 3;
    private final int LAUNCH = 4;
    private final int LAND = 5;
    private final int LOAD_FILE = 6;
    private final int EXIT = 7;

    public static void main( String [ ] args ) throws IOException
    {
         Carrier c = new Carrier( );
         c.run( );
    }

    public Carrier( )
    {
         kb = new Scanner( System.in );
         plane1 = null;
         plane2 = null;
    }

    private void run( ) throws IOException
    {
         int choice = -1;
         while( choice != EXIT )
         {
              displayMenu( );
              System.out.print( "\n\tEnter choice >> " );
              choice = kb.nextInt( );
              // clear out the newline left after the int read
              kb.nextLine( );
              process( choice);
         }
    }

    private void displayMenu( )
    {
         System.out.println( "\n\tCarrier Ops Menu\n" );
         System.out.println( "\t" + ADD_AIRCRAFT + ". Add an aircraft");
         System.out.println( "\t" + DISPLAY + ". Display all");
         System.out.println( "\t" + ADD_AIRCREW + ". Add an aircrew");
         System.out.println( "\t" + LAUNCH + ". Launch an aircraft");
         System.out.println( "\t" + LAND + ". Recover an aircraft" );
         System.out.println( "\t" + LOAD_FILE + ". Load from file" );
         System.out.println( "\t" + EXIT + ". Close Carrier Ops" );
    }

    private void process( int choice ) throws IOException
    {
         switch( choice )
         {
              case ADD_AIRCRAFT :
                   addAircraft( );
                break;

              case DISPLAY :
                   display( );
                break;

              case ADD_AIRCREW:
                   addAircrew( );
                break;

              case LAUNCH :
                   launch( );
                break;

              case LAND :
                   land( );
                break;
               
              case LOAD_FILE :
                   loadFromFile( );
                break;

              case EXIT :
                   // just trap this choice so that it does not show
                   // as an error
                break;

              default:
                   System.out.println( choice + " is not a valid choice" );
                break;
         }
    }

    // always add to plane1 first, if both aircraft are available
    // this applies to reading from the file too.
    private void addAircraft( )
    {
        if (plane1 == null) {
            String tailCode = this.getTailCodeByInput();
            plane1 = new Aircraft(tailCode);
        } else if (plane2 == null) {
            String tailCode = this.getTailCodeByInput();
            if (plane1.getTailCode().equals(tailCode)){
                System.out.println("That tail code is already assigned\nTail codes must be unique");
                return;
            }
            plane2 = new Aircraft(tailCode);
        } else {
            System.out.println("Cannot add another aircraft, the Carrier is full");
        }
    }
    
    private String getTailCodeByInput(){
        System.out.print( "\nEnter tail code >> " );
        return kb.nextLine();
    }

    private void display( )
    {
        if (plane1 == null && plane2 == null){
            System.out.println("There are no aircraft assigned yet, nothing to display");
            return;
        }
        if (plane1 != null) {
            System.out.println(plane1.toString());
        }
        if (plane2 != null) {
            System.out.println(plane2.toString());
        }
    }
 
    // Adding an aircrew does NOT have to be plane1 first and plane2
    // second. You can add an aircrew to plane2 first, provided that
    // plane2 exists and does not already have an aircrew, of course

    // aircrew call signs have to be unique

    private void addAircrew( )
    {
        if(plane1 == null && plane2 == null){
            System.out.println("No Aircraft, add aircraft before trying to add aircrew");
            return;
        }
        if(plane1!=null && plane2!=null && plane1.getCrew()!=null && plane2.getCrew()!=null){
            System.out.println("All Aircraft already have air crew");
            return;
        }
        String tailCode = this.getTailCodeByInput();
        Aircraft plane = null;
        if (plane1 != null){
            if(plane1.getTailCode().toLowerCase().equals(tailCode.toLowerCase())){
                plane  = plane1;
            }
        }
        if (plane2 != null){
            if(plane2.getTailCode().toLowerCase().equals(tailCode.toLowerCase())){
                plane  = plane2;
            }
        }
        if (plane == null){
            System.out.print("No Aircraft, add aircraft before trying to add aircrew");
            return;
        } else {
            if (plane.existCrew()){
                System.out.print("\nThis aircraft already has an aircrew");
                return;
            }
        }
        String callSign = this.getCallSignByInput();
        if (plane1 != null && plane1.existCrew()){
            if (plane1.getCrew().getCallSign().toLowerCase().equals(callSign.toLowerCase())){
                System.out.print("This call sign has already been assigned, call signs must be unique");
                return;
            }
        }
        if (plane2 != null && plane2.existCrew()){
            if (plane2.getCrew().getCallSign().toLowerCase().equals(callSign.toLowerCase())){
                System.out.print("This call sign has already been assigned, call signs must be unique");
                return;
            }
        }
        
        String name = this.getNameByInput();
        plane.addAircrew(name, callSign);
    }
    
    
    
    private String getCallSignByInput(){
        System.out.print( "\nEnter aircrew call sign >> " );
        return kb.nextLine();
    }
    
    private String getNameByInput(){
        System.out.print( "\nEnter aircrew name >> " );
        return kb.nextLine();
    }
    
    private String getNameByInput(String s){
        return s;
    }
    
    private String getAirCrewStatus(){
        System.out.print( "Enter required aircrew status >> " );
        return kb.nextLine();
    }
    
    private String getFileName(){
        System.out.print( "Enter file name >> " );
        return kb.nextLine();
    }

    private void launch( )
    {
        if (plane1 == null && plane2 == null){
            System.out.println("No aircraft assigned to the Carrier yet, add some aircraft");
            return;
        }
        String tailCode = this.getTailCodeByInput();
        Aircraft plane = null;
        if (plane1 != null){
            if(plane1.getTailCode().toLowerCase().equals(tailCode.toLowerCase())){
                plane  = plane1;
            }
        }
        if (plane2 != null){
            if(plane2.getTailCode().toLowerCase().equals(tailCode.toLowerCase())){
                plane  = plane2;
            }
        }
        if (plane != null){
            if (!plane.existCrew()){
                System.out.print("This aircraft does not have an aircrew, cannot launch");
                return;
            } else {
                if(plane.getStatus().equals("airborne")){
                    System.out.print("This aircraft is already airborne");
                    return;
                } else {
                    String aircrewStatus = this.getAirCrewStatus();
                    if (plane.getCrew().prepared(aircrewStatus)) {
                        plane.start();
                    } else {
                        System.out.print("The air crew of this aircraft do not have the required qualifications");
                    }
                }
            }
        }
    }

   // as always check that there is at least one aircraft
   // this really should be a method !!!
    private void land( )
    {
        if (plane1 == null && plane2 == null){
            System.out.println("There are no aircraft assigned yet, add some aircraft");
            return;
        }
        String tailCode = this.getTailCodeByInput();
        Aircraft plane = null;
        if (plane1 != null){
            if(plane1.getTailCode().toLowerCase().equals(tailCode.toLowerCase())){
                plane  = plane1;
            }
        }
        if (plane2 != null){
            if(plane2.getTailCode().toLowerCase().equals(tailCode.toLowerCase())){
                plane  = plane2;
            }
        }
        if (plane != null){
            if (!plane.existCrew()){
                System.out.print("This aircraft does not have an aircrew, cannot launch");
            } else {
                if(plane.getStatus().equals("on deck")){
                    System.out.print("This aircraft is already airborne");
                } else {
                    plane.end();
                }
            }
        } else {
            System.out.println("No aircraft with that tail code was found");
        }
    }

    private void loadFromFile( ) throws IOException
    {
        if (plane1 != null && plane2 != null){
            System.out.println("The Carrier is full, cannot add any more aircraft");
        }
    }
}



