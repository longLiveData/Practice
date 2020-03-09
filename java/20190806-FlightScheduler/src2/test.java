
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xidia
 */
public class test {
    public static void main(String[] args){
        ArrayList<ArrayList<Object>> a = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> o = new ArrayList<Object>();
        o.add("a1");
        o.add("a2");
        a.add(o);
        a.add(o);
        
        String name = a.get(0).get(0).toString();
        System.out.println(name);
    }
}
