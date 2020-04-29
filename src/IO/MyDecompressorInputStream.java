package IO;

import javafx.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    @Override
    public int read() throws IOException {
        return in.read();
    }
    public int read(byte[] byteArray)throws IOException{
        //byte []newByteArray = new byte[12];
        byte []compressedMaze = new byte[0];
        in.read(compressedMaze);
        LinkedList<Integer> byteArr= new LinkedList<>();
        for(int i=0; i<12 ; i++){
                int temp = compressedMaze[i];
                byteArr.add(temp);
        }
        int sizeOfPair =compressedMaze[12];
        LinkedList<Pair<Integer,Integer>> dictionary = getDictionary(compressedMaze, sizeOfPair);
        byteArr.add(dictionary.get(0).getKey());
        for(int i=1; i<dictionary.size(); i++){
            Stack<Integer> temp = new Stack<>();
            if(i != dictionary.size()-1)
               temp.add(dictionary.get(i).getKey());
            int p = dictionary.get(i).getValue();
            while (p>=0){
                temp.add(dictionary.get(p).getKey());
                p=dictionary.get(p).getValue();
            }
            while (!temp.isEmpty())
                byteArr.add(temp.pop());
        }
        byte []newByteMaze = new byte[byteArr.size()];
        for(int i=0; i<newByteMaze.length; i++){
            int temp =byteArr.get(i);
            newByteMaze[i]=(byte)temp;
        }
        byteArray=newByteMaze;
        return 0;
    }

    private LinkedList<Pair<Integer,Integer>>getDictionary(byte []arr ,int sizeOfUnit){
        LinkedList<Pair<Integer,Integer>> dictionary = new LinkedList<>();
        int pos=13;
        while(pos<arr.length){
            int val=arr[pos++];
            int p=arr[pos++] & 0xFF;
            for(int i=1; i<sizeOfUnit-1; i++){
                p = ((arr[pos++] & 0xFF) <<(8*i)) | p;
            }
            dictionary.add(new Pair<>(val,p));
            }
        return dictionary;
    }
}
