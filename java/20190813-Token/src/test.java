public class test {
    
    public static void main(String[] args){
        
        Lexer l = new Lexer();
        
        // update file path to yours
        String fileName = "res/test1.txt";
        l.ReadFile(fileName);
        
        while (! l.goToEnd()) {
            System.out.println(l.GetToken().toString());
        }
    }
}