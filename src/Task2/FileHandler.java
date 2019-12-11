package Task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private String name = "";
    public void write(ArrayList<Sportsman> b) throws Exception{
        System.out.println("Enter file name");
        Scanner in = new Scanner(System.in);
        name = in.next();

        DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(name));

        for(Sportsman t : b){
            byte[] bbb = t.toByteArray();
            dataOut.write(bbb);
        }

        File f = new File(name);
        if (f.exists()){
            System.out.println("exists");
        }else{
            System.out.println("don't exist");
        }

        dataOut.close();
    }

    public ArrayList read(int height) throws Exception{
        DataInputStream dataIn = new DataInputStream(new FileInputStream(name));
        ArrayList<Sportsman> b = new ArrayList<Sportsman>();
        byte[] obj = new byte[Sportsman.byteLen()];
        while (dataIn.available() > 0){
            dataIn.read(obj);
            Sportsman t = new Sportsman(obj);
            if(t.getHeight() == height){
                b.add(t);
            }
        }
        dataIn.close();
        return b;
    }

    public void raf(ArrayList<Sportsman> b) throws Exception{

        name +="1";
        DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(name));

        for(Sportsman t : b){
            byte[] bbb = t.toByteArray();
            dataOut.write(bbb);
        }
        dataOut.close();

        RandomAccessFile raf = new RandomAccessFile(name, "rw");
        long lastPointer = raf.length() - Sportsman.byteLen();
        raf.seek(lastPointer);
        byte[] obj = new byte[Sportsman.byteLen()];
        raf.read(obj);
        Sportsman last = new Sportsman(obj);
        raf.seek(0);
        int readedBytes = raf.read(obj);
        while(readedBytes != -1){
            Sportsman t = new Sportsman(obj);
            if(t.compareTo(last) && raf.getFilePointer() <= lastPointer) {
                byte[] bbb = t.toByteArray();
                long pointer = raf.getFilePointer() - Sportsman.byteLen();
                raf.seek(pointer);
                raf.write(last.toByteArray());
                raf.seek(lastPointer);
                raf.write(bbb);
                break;
            }
            readedBytes = raf.read(obj);
        }
        raf.close();
    }
}
