
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FsaReaderWriter implements FsaIo {
   
    @Override
    public void read(Reader r, Fsa f) throws IOException, FsaFormatException {
        
        int data = r.read();         
        String fileContent = "";
        while(data != -1){             
            fileContent += (char)data;             
            data = r.read();         
        }
                
        String[] lines = fileContent.split("\n");
        
        for (String line: lines) {
            String[] seqs = line.split("\\s++");
            
            if (seqs[0].equals("final")) {
                State test = new FsaImpl.MyState(seqs[1], 0, 0);
                State s = f.findState(seqs[1]);
                if (s != null) {
                    s.setFinal(true);
                }
            }
            if (seqs[0].equals("initial")) {
                State test = new FsaImpl.MyState(seqs[1], 0, 0);
                State s = f.findState(seqs[1]);
                if (s != null) {
                    s.setInitial(true);
                }
            }
            if (seqs[0].equals("state")) {
                if (seqs.length == 4){
                    f.newState(seqs[1], Integer.parseInt(clear(seqs[2])), Integer.parseInt(clear(seqs[3])));
                }
            }
            if (seqs[0].equals("transition")) {
                if (f.findState(seqs[1]) != null && f.findState(seqs[3]) != null) {
                    f.newTransition(f.findState(seqs[1]), f.findState(seqs[3]), seqs[2]);
                }
            }
        }
        
        r.close();
    }
    
    public String clear(String num) {
        String res = "";
        for (int i=0; i<num.length(); i++) {
            char c = num.charAt(i);
            if ('0' <= c && c <= '9') {
                res += c;
            }
        }
        return res;
    }

    @Override
    public void write(Writer w, Fsa f) throws IOException {
        String res = f.toString();
        res = res.replace("STATE", "state");
        res = res.replace("TRANSITION", "transition");
        res = res.replace("FINAL", "final");
        res = res.replace("INITIAL", "initial");
        res = res.replace("(", " ");
        res = res.replace(")", " ");
        res = res.replace(",", " ");
        String[] temp = res.split("\n");
        String line = "";
        String temp1 = "";
        for (String s: temp) {
            if(s.charAt(0) == 's'){
                line = s.substring(0, s.length() - 3) + "\n";
            } else {
                line = s + "\n";
            }
            temp1 += line;
        }
        w.write(temp1);
        w.close();
    }
    
    public static void main(String[] args) throws IOException, FsaFormatException {
        
        FsaReaderWriter fr = new FsaReaderWriter();
        Fsa fi = new FsaImpl();
        
        Reader r = new FileReader(args[0]);
        fr.read(r, fi);
        
        Writer w = new FileWriter(args[1]);
        fr.write(w, fi);
        
    }
}
