package com.hashstring;

public class Record {

    private String name;
    private String phone;
    private String email;

    Record(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int hashCode(){
        // first, hashcode() function of java
//         return this.name.hashCode();

        String key = this.name;
        int arraySize = 11113;
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            int letterValue = key.charAt(i) - 96;
            hashCode = ((hashCode << 4) + letterValue) % arraySize;
        }
        return hashCode;
    }
}
