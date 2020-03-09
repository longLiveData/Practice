package com.hashstring;

import org.junit.Before;
import org.junit.Test;

public class RHashtableTest {

    private int capacity = 5;
    private RHashtable table = new RHashtable(capacity);

    @Before
    public void setup(){
        Record r = new Record("name", "phone", "email");
        // add records until one position left
        for (int i = 0; i < this.capacity - 1; i++){
            table.put(r);
        }
    }

    @Test
    public void normalPut(){
        // you can still add one
        Record r = new Record("name", "phone", "email");
        System.out.println(table.put(r));
    }

    @Test
    public void faultPut(){
        Record r = new Record("name", "phone", "email");
        table.put(r);
        // when you want to add more, false
        System.out.println(table.put(r));
    }

}
