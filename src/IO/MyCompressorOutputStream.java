package IO;

import javafx.util.Pair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }
    @Override
    public void write(int b) {
        try {
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void write(byte[] byteMaze){
        int rowSize = ((byteMaze[0] & 0xFF) <<8) | (byteMaze[1] & 0xFF);
        int colSize = ((byteMaze[2] & 0xFF) <<8) | (byteMaze[3] & 0xFF);
        if (rowSize*colSize>= 170*170) {
            write(1);
            writeBig(byteMaze);
        }
        else {
            write(0);
            writeSmall(byteMaze);
        }
    }

    public void writeBig (byte[] b) {
        if (b.length<12)
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }

        Map<String,Integer> codes = new LinkedHashMap<>();
        ArrayList<Pair<Integer,Integer>> array = new ArrayList<>();
        int arrIndex=0;
        array.add(arrIndex++,new Pair<>(0,0));
        int k=12;
        codes.put(String.valueOf(b[k]),arrIndex);
        array.add(arrIndex++,new Pair((int)b[k++],0));

        for (; k<b.length; k++){
            int pointer=0;
            String current = String.valueOf(b[k]);
            while (codes.containsKey(current) && k<b.length-1){
                pointer=codes.get(current);
                k++;
                current+=String.valueOf(b[k]);
            }
            if (codes.containsKey(current) && k==b.length-1) {
                array.add(arrIndex, new Pair(2, codes.get(current)));
                codes.put(current,arrIndex);
            }
            else{
                array.add(arrIndex,new Pair((int)b[k],pointer));
                codes.put(current,arrIndex++);
            }
        }

        //Write compressed array//
        for (int i=0; i< 12 ; i++) //maze dimensions + start/goal
            write(b[i]);
        int pointerSize= array.size()>128 ? (array.size()>32768 ? 3 : 2) : 1; // how many bytes to represent the pointer
        write(pointerSize);
//        System.out.println("DEBUG : array size:" + array.size()+" unit size: "+(pointerSize+1)); //DEBUGGING
        for (Pair<Integer, Integer> Pair : array) { //send array
            byte cell = (byte) (Pair.getKey()<<7);
            cell = (byte) (cell | (Pair.getValue()>>(8*(pointerSize-1))));
            write(cell);
            for (int j = pointerSize - 2; j >= 0; j--) {
                cell=(byte) (Pair.getValue() >> 8 * j);
                write(cell);
            }
        }
        write(array.get(array.size()-1).getKey()); //last cell key (0/1/2)
        //Finish writing compressed array//
    }



    public void writeSmall(byte[] b) {
        if (b.length<12)
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        //Write compressed array//
        int i=0;
        for (; i< 12 ; i++) //maze dimensions + start/goal
            write(b[i]);
        while (i<b.length){
            byte cell=b[i++];
            for (int j =0 ; j<7; j++){
                if (i<b.length)
                    cell=(byte)((cell<<1)|b[i++]);
                else
                    cell=(byte)((cell << 1));
            }
            write(cell);
        }
    }
}
