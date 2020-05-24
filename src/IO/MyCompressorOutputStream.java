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

//    @Override
//    public void write(byte[] b) {
//        if (b.length<12 || b==null)
//            try {
//                throw new IOException();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        Map<String,Integer> codes = new LinkedHashMap<>();
//        ArrayList<Pair<Integer,Integer>> array = new ArrayList<>();
//        int arrIndex=0;
//        array.add(arrIndex++,new Pair<>(0,0));
//        int k=12;
//        codes.put(String.valueOf(b[k]),arrIndex);
//        array.add(arrIndex++,new Pair((int)b[k++],0));
//
//        for (; k<b.length; k++){
//            int pointer=0;
//            String current = String.valueOf(b[k]);
//            while (codes.containsKey(current) && k<b.length-1){ // 0 00 01 010
//                pointer=codes.get(current);
//                k++;
//                current+=String.valueOf(b[k]);
//            }
//            if (codes.containsKey(current) && k==b.length-1) {
//                array.add(arrIndex, new Pair(2, codes.get(current)));
//                codes.put(current,arrIndex);
//            }
//            else{
//                array.add(arrIndex,new Pair((int)b[k],pointer));
//                codes.put(current,arrIndex++);
//            }
//        }
//
//        //Write compressed array//
//        for (int i=0; i< 12 ; i++) //maze dimensions + start/goal
//            write(b[i]);
//
//        int pointerSize= array.size()>256 ? (array.size()>65536 ? 3 : 2) : 1; // how many bytes to represent pointer
//        write(pointerSize+1); //pointer + val {0/1}
////        System.out.println("DEBUG : array size:" + array.size()+" unit size: "+(pointerSize+1)); //DEBUGGING
//        for (Pair<Integer, Integer> Pair : array) { //send array
//            write(Pair.getKey());
//            for (int j = pointerSize - 1; j >= 0; j--)
//                write((byte) (Pair.getValue() >> 8 * j));
//        }
////        out.close();
//        //Finish writing compressed array//
//    }

    @Override
    public void write(byte[] b) {
        if (b.length<12 || b==null)
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
                    cell=(byte)((cell<<1)|0);
            }
            write(cell);
        }
    }
}
