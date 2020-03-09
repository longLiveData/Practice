import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    
    public ArrayList<Token> tokenList = new ArrayList<>();
    private int index = 0;
    final private List<String> keywords = 
            Arrays.asList("integer", "real", "set", "input", "output", "if", "else", "repeat", "times", "while", "variable");

    public void ReadFile(String fileName){
        
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // read file line by line
            while ((str = bf.readLine()) != null) {
                ArrayList<Token> tokenLine = getTokenLine(str);
                for (Token token: tokenLine){
                    tokenList.add(token);
                }
            }
            tokenList.add(new Token("EOF", "EOF"));
            bf.close();
            fr.close();
        } catch (IOException e) {
        }
    }
    
    public ArrayList<Token> getTokenLine(String line){
        
        ArrayList<Token> tokenLine = new ArrayList<>();
        // process
        for (int index = 0; index < line.length(); index++){
            // white space: jump out
            if (line.charAt(index) == ' '){
            } 
            // comments: comes to end
            else if (line.charAt(index) == '!'){
                if (line.charAt(index+1) == '!'){
                    return tokenLine;
                }
            }
            // ": string
            else if (line.charAt(index) == '\"'){
                // find end
                int endIndex = index + 1;
                while (endIndex < line.length() && line.charAt(endIndex) != '\"'){
                    endIndex += 1;
                }
                // add it to tokenLine
                tokenLine.add(new Token("string_literal", line.substring(index+1, endIndex)));
                index = endIndex;
            }
            // process letter 
            else if (isLetter(line.charAt(index))){
                // find the end position
                int endIndex = index;
                while (endIndex < line.length() && isLetterOrDigit(line.charAt(endIndex))){
                    endIndex += 1;
                }
                // get type
                String type = "";
                String letex = line.substring(index, endIndex);
                if (this.keywords.contains(letex)) {
                    type = letex;
                } else {
                    type = "id";
                }
                // add it to tokenLine
                tokenLine.add(new Token(type, letex));   
                index = endIndex - 1;
            }
            // process digit
            else if (isDigit(line.charAt(index))){
                // find end
                int endIndex = index + 1;
                while (isDigit(line.charAt(endIndex)) || line.charAt(endIndex) == '.'){
                    endIndex += 1;
                }
                //add it to tokenLine
                tokenLine.add(new Token("number", line.substring(index, endIndex)));
                index = endIndex - 1;
            }
            // process symbols
            else if ("+-*/=><:,(–)".contains(String.valueOf(line.charAt(index)))) {
                tokenLine.add(new Token("symbol", String.valueOf(line.charAt(index))));
            }
        }
        return tokenLine;
    }
    
    public boolean goToEnd(){
        return this.index >= this.tokenList.size();
    }
    
    public Token GetToken() {
        Token returnToken = this.tokenList.get(this.index);
        this.index += 1;
        return returnToken;
    }
    
    public Token PeekToken() {
        return this.tokenList.get(this.index);
    }
    
    public boolean isLetter(char c){
        int i = (int)c;     
        if((i>=65&&i<=90)||(i>=97&&i<=122)){
            return true;
        }
        return false;
    }
    
    public boolean isDigit(char c) {
        return Character.isDigit(c);
    }
    
    public boolean isLetterOrDigit(char c){
        return this.isLetter(c) || this.isDigit(c);
    }
    
}
