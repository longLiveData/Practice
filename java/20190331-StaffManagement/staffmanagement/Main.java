package staffmanagement;

import static staffmanagement.ui.MainWindow.createMainWindow;
import static staffmanagement.ui.MainWindow.st;

public class Main {
    
    public static void main(String[] args){
        st = new Staff();
        String[] em1 = {"Tony", "male", "17455", "1984-03-31", "2014-06-26", "0044161", "No.1 Coventry Road"};
        String[] em2 = {"Jeffney", "female", "18325", "1983-04-14", "2012-07-04", "0044278", "No.2 Coventry Road"};
        String[] em3 = {"Tom", "male", "18776", "1987-09-23", "2016-12-23", "0044641", "No.3 Coventry Road"};
        st.add(em1);
        st.add(em2);
        st.add(em3);
        createMainWindow();
    }
    
}
