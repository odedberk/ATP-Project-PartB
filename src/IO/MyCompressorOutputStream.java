package IO;

import javafx.util.Pair;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        if (b.length<12 || b==null)
            throw new IOException();

        Map<String,Integer> codes = new HashMap();
        ArrayList<Pair<Integer,Integer>> array = new ArrayList<>();
        int arrIndex=0;
        int k=12;
        codes.put(String.valueOf(b[k++]),arrIndex);
        int temp=b[k];
        array.add(arrIndex++,new Pair(temp,-2));

        for (; k<b.length; k++){
            int pointer=-2;
            String current = String.valueOf(b[k]);
            while (codes.containsKey(current) && k<b.length-1){ // 0 00 01 010
                pointer=codes.get(current);
                k++;
                current+=String.valueOf(b[k]);
            }
            if (codes.containsKey(current) && k==b.length-1) {
                array.add(arrIndex, new Pair(2, codes.get(current)));
                codes.put(current,arrIndex);
            }
            else{
                temp=b[k];
                array.add(arrIndex,new Pair(temp,pointer));
                codes.put(current,arrIndex++);
            }
        }

        //Write compressed array//
        for (int i=0; i< 12 ; i++) //maze dimensions + start/goal
            write(b[i]);

        int pointerSize= array.size()>256 ? (array.size()>65536 ? 3 : 2) : 1; // how many bytes to represent pointer
        write(pointerSize+1); //pointer + val {0/1}

        for (int i=0 ; i<array.size(); i++){//send array
            write(array.get(i).getKey());
            //write(1);
            for (int j=pointerSize-1 ; j>=0 ; j--)
                write((byte)(array.get(i).getValue() >> 8*j) );
        }
        out.flush();
        //Finish writing compressed array//
    }
}
