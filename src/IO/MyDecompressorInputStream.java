package IO;

import javafx.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }
    public int read(byte[] byteArray)throws IOException{
        //byte []newByteArray = new byte[12];
        //byte []compressedMaze = new byte[1000000000];
        //in.read(compressedMaze);
        ArrayList<Integer> array = new ArrayList<>();
        int next = in.read();
        while (next!=-1) {
            array.add(next);
            next=in.read();
        }

        LinkedList<Integer> unCompressMaze= new LinkedList<>();
        for(int i=0; i<12 ; i++)
            unCompressMaze.add(array.get(i));
        //int sizeOfPair =in.read();
        LinkedList<Pair<Integer,Integer>> dictionary = getDictionary(array,array.get(12));
        for(int i=1; i<dictionary.size(); i++){
            Stack<Integer> temp = new Stack<>();
            if(i != dictionary.size()-1)
               temp.add(dictionary.get(i).getKey());
            int p = dictionary.get(i).getValue();
            while (p>=1){

                temp.add(dictionary.get(p).getKey());
                p=dictionary.get(p).getValue();
            }
            while (!temp.isEmpty()) {
                if(temp.peek()==2){
                    temp.pop();
                }
                else {
                    unCompressMaze.add(temp.pop());
                }
            }
        }
        //byte []newByteMaze = new byte[byteArr.size()];
        for(int i=0; i<byteArray.length && i<unCompressMaze.size(); i++){
            int temp =unCompressMaze.get(i);
            byteArray[i]=(byte)temp;
        }
        //byteArray=newByteMaze;
        return 0;
    }

    private LinkedList<Pair<Integer,Integer>>getDictionary(ArrayList<Integer> array ,int sizeOfUnit)throws IOException{
        LinkedList<Pair<Integer,Integer>> dictionary = new LinkedList<>();
        int pos=13;
        while(pos<array.size()-1){
                int val=array.get(pos++);
                if(val==-1)
                    break;
                int p=array.get(pos++) & 0xFF;
                for(int i=sizeOfUnit-2; i>=1; i--)
                    p = (p << 8) | (array.get(pos++) & 0xFF);
                dictionary.add(new Pair<>(val,p));
        }
        return dictionary;
    }
}
