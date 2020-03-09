package com.hashstring;

import java.util.List;

public class RHashtable implements RHashtableInt {

    private Record[] recordList;
    private int capacity;
    private int number;
    private int collision;

    RHashtable(int capacity){
        recordList = new Record[capacity];
        this.capacity = capacity;
        this.number = 0;
        this.collision = 0;
    }

    public boolean put(Record r){
        if (this.capacity == this.number){
            return false;
        }
        int hashCode = r.hashCode() & Integer.MAX_VALUE;
        int pos = hashCode % this.capacity;
        while(true){
            if (this.recordList[pos] == null){
                this.recordList[pos] = r;
                this.number += 1;
                return true;
            } else {
                // linear probing, notice don't over index
                this.collision += 1;
                pos += 1;
                pos %= this.capacity;
            }
        }
    }

    public int getCollisions(){
        return this.collision;
    }

    public float getLoad(){
        return (float)this.number / this.capacity;
    }
}
