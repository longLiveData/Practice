public class Rectangle
{
    private int width;
    private int height;

    public Rectangle()
    {
        System.out.println("Creating a new rectangle.");
        this.width = Input.askInt("Enter width: ");
        this.height = Input.askInt("Enter height: ");
    }
    
    public void showArea (){
        int area = this.width * this.height;
        System.out.println("The rectangle's area is " + area);
    }
    
    public void show() {
        System.out.println("The rectangle has dimensions "+ this.width + "x" + this.height);
    }
    
    public static void showNumberOfSides() {
        System.out.println("Rectangles have 4 sides");
    }
    
    
}
