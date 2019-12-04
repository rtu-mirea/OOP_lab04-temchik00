package com.company;

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
            byte[] bbb = t.toString().getBytes("UTF-8");
            dataOut.write(bbb);
            dataOut.write("\n".getBytes("UTF-8"));
        }

        File f = new File(name);
        if (f.exists()){
            System.out.println("exists");
        }else{
            System.out.println("don't exist");
        }

        dataOut.close();
    }

    public ArrayList read(String company) throws Exception{
        DataInputStream dataIn = new DataInputStream(new FileInputStream(name));
        ArrayList<Sportsman> b = new ArrayList<Sportsman>();
        while (dataIn.available() > 0){

            String[] k = dataIn.readLine().split(" ");
            if (k[8].equals(company)) {
                String n = k[0];
                String c = k[1];
                String ci = k[2];
                String hn = k[3];
                int s = Integer.parseInt(k[4]);
                int d = Integer.parseInt(k[5]);
                int a = Integer.parseInt(k[6]);
                Double cos = Double.parseDouble(k[7]);
                String com = k[8];
                Sportsman t = new Sportsman(n, c, ci, hn, s, d, a, cos, com);

                b.add(t);
            }
        }
        dataIn.close();
        return b;
    }

    public void raf(ArrayList<Sportsman> b, int num) throws Exception{
        name +="1.txt";
        DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(name));
        int i1 = 0, i2 = b.size() - 1;
        while(i1 < i2){
            if(b.get(i1).sportType.equals(b.get(i2).sportType)){
                Sportsman tmp = b.get(i1);
                b.set(i1, b.get(i2));
                b.set(i2, tmp);
                break;
            }
            i1++;
        }
        for(Sportsman t : b){
            byte[] bbb = t.toString().getBytes("UTF-8");
            dataOut.write(bbb);
            dataOut.write("\r\n".getBytes("UTF-8"));
        }
        dataOut.close();

        RandomAccessFile raf = new RandomAccessFile(name, "rw");
        raf.seek(0);
        String sss = "";
        for (int i = 0; i<=num; i++){
            sss = raf.readLine();
        }
        Sportsman t = new Sportsman(sss);
        t.add10();
        byte[] bbb = t.toString().getBytes("UTF-8");

        raf.seek(0);
        for (int i = 0; i<num; i++){
            raf.readLine();
        }
        raf.write(bbb);
        raf.close();
    }
}
