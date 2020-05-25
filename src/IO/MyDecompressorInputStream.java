package IO;

import javafx.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() {
        try {
            return in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read mhatod to read byte array that rprasante maze
     * @param byteArray
     * @return
     */
    public int read(byte[] byteArray){

        ArrayList<Integer> array = new ArrayList<>();
        int next = 0;
        try {
            next = in.read();

        while (next!=-1) { // read received array
            array.add(next);
            next=in.read();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0; i<12 ; i++) { // get dimensions
            int temp = array.get(i);
            byteArray[i] =(byte)temp;
        }
        LinkedList<Pair<Integer,Integer>> dictionary = getDictionary(array,array.get(12));
        ArrayList<String> deCompress = new ArrayList<>();
        deCompress.add("");
        for(int i=1; i<dictionary.size(); i++) {
            String temp = "";
            if (i != dictionary.size() - 1)
                temp = "" + dictionary.get(i).getKey();
            else {
                if (dictionary.get(i).getKey() != 2)
                    temp = "" + dictionary.get(i).getKey();
            }
            if (dictionary.get(i).getValue() >= 1)
                temp = deCompress.get(dictionary.get(i).getValue()) + temp;
            deCompress.add(i, temp);
        }
        int insert=12;
        for (String temp : deCompress){
            for(char c : temp.toCharArray()) {
                if(c=='1')
                    byteArray[insert++]=1;
                else
                    byteArray[insert++]=0;
            }
        }

        return 0;
    }

    private LinkedList<Pair<Integer,Integer>>getDictionary(ArrayList<Integer> array ,int sizeOfUnit){
        LinkedList<Pair<Integer,Integer>> dictionary = new LinkedList<>();
        int pos=13+sizeOfUnit;
        dictionary.add(new Pair<>(0,0));
        while(pos<array.size()-2){
                int val=(array.get(pos)>>7)&1;
                if(pos+sizeOfUnit+1==array.size())
                    if(array.get(pos+sizeOfUnit)==2)
                        val=2;
                int p=(array.get(pos++)&127) & 0xFF;
                for(int i=sizeOfUnit-2; i>=0; i--)
                    p = (p <<(8)) | (array.get(pos++) & 0xFF);
                dictionary.add(new Pair<>(val,p));
        }
        return dictionary;
    }
//
//    public int read(byte[] byteArray) {
//
//        ArrayList<Byte> array = new ArrayList<>();
//        int next = 0;
//        try {
//            next = in.read();
//            while (next != -1) { // read received array
//                array.add((byte) next);
//                next = in.read();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int i = 0;
//        for (; i < 12; i++) { // get dimensions
//            int temp = array.get(i);
//            byteArray[i] = (byte) temp;
//        }
//        int rowSize = ((array.get(0)& 0xFF) <<8) | (array.get(1) & 0xFF);
//        int colSize = ((array.get(2) & 0xFF) <<8) | (array.get(3) & 0xFF);
//        int size = rowSize*colSize;
//        int k=0;
//        while(k<size && i<array.size()){
//            byte cell=array.get(i++);
//            for (int j =7 ; j>=0 && k<size; j--,k++){
//                byteArray[12+k]= (byte) (1&cell>>j);
//            }
//        }
//        return 0;
//    }
}


