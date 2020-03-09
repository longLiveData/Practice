package com.hashstring;

import java.io.FileReader;
import java.io.BufferedReader;

public class RProcess{

    private static RHashtable table;
    private static int tSize;

    public static void main(String [] args){
        if(args.length != 2){ // check sufficient arguments
          System.err.println("Usage: java RProcess <filename> <int tablesize>");
          return;
        }

        try{
          tSize = Integer.parseInt(args[1]);  // make sure second argument is an int
        }catch(Exception e){
          System.err.println("Invalid tablesize argument!");
          tSize=0;
        }

        if(tSize<1){
          System.err.println("Table size argument must be integer bigger than 0!");
          return;
        }

        table = new RHashtable(tSize); // create table
        String line;  // a line of input from data file
        String [] field;  // input line parsed into fields
        int count = 0;  // count input records
        float ld=0;  // for computing load factor as a percentage

        try{
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            if(! br.ready()){  //   make sure data file is ready for input
              System.err.println("Cannot read from input file!");
              return;
            }

            line = br.readLine();  // ignore first line of data file (just field names)

            while((line = br.readLine()) != null){
                count++;
                field = line.split(","); // comma-spearated fields
                Record rec = new Record(field[0],field[1],field[2]); // create new Record
                if(! table.put(rec)){ // make sure it got into the table
                    System.err.println("Line: "+count+"\tFailed to store new record.  Aborting!");
                    System.err.println("HASHCODE["+rec.hashCode()+"]: " +field[0] + " " + field[1] + " " + field[2]);
                    break;
                }
                ld = (float)((int)(table.getLoad()*1000))/10; // load as a truncated percentage
                System.out.println("Load: "+ld+"\tCollisions: "+table.getCollisions());
            }
            br.close();
        }catch(Exception e){
          System.err.println("Error processing file: " + e);
        }
        // System.err.println("ALL DONE");
    }
}
