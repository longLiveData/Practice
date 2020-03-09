public class Token {
    
    private String type; // EOF, string_literal,
    private String lexeme;
    
    Token(String type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }
    
    public String getType(){
        return this.type;
    }
    
    @Override
    public String toString(){
        return this.lexeme;
    }
    
}
