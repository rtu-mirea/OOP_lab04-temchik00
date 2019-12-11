package Task2;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Sportsman {
    public String secondName;
    public int weight;
    public int height;
    public String sportType;
    public static final int maxLen = 15;
    public static final char fill = (char)30;

    public Sportsman(String secondName, int weight, int height, String sportType) throws IllegalArgumentException {
        if(weight < 1 || height < 1){
            throw new IllegalArgumentException("Wrong");
        }
        if(secondName.length() >= maxLen){
            throw new IllegalArgumentException("Длина фамилии превышает " + maxLen);
        }
        for(int i = 0; i < secondName.length(); ++i)
            if(secondName.charAt(i) == fill)
                throw new IllegalArgumentException("Использован недопустимый символ " + fill);
        this.secondName = secondName;
        for(int i = secondName.length(); i < maxLen; ++i){
            this.secondName += fill;
        }
        this.weight = weight;
        this.height = height;
        if(sportType.length() >= maxLen){
            throw new IllegalArgumentException("Длина названия спорта превышает " + maxLen);
        }
        for(int i = 0; i < sportType.length(); ++i)
            if(sportType.charAt(i) == fill)
                throw new IllegalArgumentException("Использован недопустимый символ " + fill);
        this.sportType = sportType;
        for(int i = sportType.length(); i < maxLen; ++i){
            this.sportType += fill;
        }
    }

    public Sportsman() throws IllegalArgumentException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите фамилию: ");
        String secondName = in.next();
        System.out.println("Введите вес: ");
        int weight = in.nextInt();
        System.out.println("Введите рост: ");
        int height = in.nextInt();
        System.out.println("Введите тип спорта: ");
        String sportType = in.next();
        if(weight < 1 || height < 1){
            throw new IllegalArgumentException("Wrong");
        }
        if(secondName.length() >= maxLen){
            throw new IllegalArgumentException("Длина фамилии превышает " + maxLen);
        }
        for(int i = 0; i < secondName.length(); ++i)
            if(secondName.charAt(i) == fill)
                throw new IllegalArgumentException("Использован недопустимый символ " + fill);
        this.secondName = secondName;
        for(int i = secondName.length(); i < maxLen; ++i){
            this.secondName += fill;
        }
        this.weight = weight;
        this.height = height;
        if(sportType.length() >= maxLen){
            throw new IllegalArgumentException("Длина названия спорта превышает " + maxLen);
        }
        for(int i = 0; i < sportType.length(); ++i)
            if(sportType.charAt(i) == fill)
                throw new IllegalArgumentException("Использован недопустимый символ " + fill);
        this.sportType = sportType;
        for(int i = sportType.length(); i < maxLen; ++i){
            this.sportType += fill;
        }
    }

    public Sportsman(byte info[]){

        byte[] tmpBytes = new byte[maxLen];
        for(int i = 0; i < maxLen; ++i){
            tmpBytes[i] = info[i];
        }
        secondName = new String(tmpBytes);
        tmpBytes = new byte[4];
        for(int i = 0; i < 4; ++i){
            tmpBytes[i] = info[i + maxLen + 1];
        }
        weight = ByteBuffer.wrap(tmpBytes).getInt();

        for(int i = 0; i < 4; ++i){
            tmpBytes[i] = info[i + maxLen + 4 + 2];
        }
        height = ByteBuffer.wrap(tmpBytes).getInt();
        tmpBytes = new byte[maxLen];
        for(int i = 0; i < maxLen; ++i){
            tmpBytes[i] = info[i + maxLen + 8 + 3];
        }
        sportType = new String(tmpBytes);
    }

    public String getSportType(){
        String ans = "";
        int i = 0;
        while(sportType.charAt(i) != (char)fill){
            ans += sportType.charAt(i);
            ++i;
        }
        return ans;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        String n = "", t = "";
        int i = 0;
        while (secondName.charAt(i) != fill){
            n += secondName.charAt(i);
            ++i;
        }
        i = 0;
        while (sportType.charAt(i) != fill){
            t += sportType.charAt(i);
            ++i;
        }
        return n + ' ' + weight + ' ' + height + ' ' + t;
    }

    public static int byteLen(){
        return maxLen * 2 + 8 + 4;
    }

    public byte[] toByteArray() throws UnsupportedEncodingException {
        byte tmp[] = secondName.getBytes("UTF-8");
        int endPos = tmp.length;
        byte ans[] = new byte[tmp.length * 2 + 8 + 4];
        for(int i = 0; i < tmp.length; ++i){
            ans[i] = tmp[i];
        }
        ans[endPos] = " ".getBytes("UTF-8")[0];
        endPos++;
        tmp = ByteBuffer.allocate(4).putInt(weight).array();
        for(int i = 0; i < tmp.length; ++i){
            ans[i + endPos] = tmp[i];
        }
        endPos += 4;
        ans[endPos] = " ".getBytes("UTF-8")[0];
        endPos++;
        tmp = ByteBuffer.allocate(4).putInt(height).array();
        for(int i = 0; i < tmp.length; ++i){
            ans[i + endPos] = tmp[i];
        }
        endPos += 4;
        ans[endPos] = " ".getBytes("UTF-8")[0];
        endPos++;
        tmp = sportType.getBytes("UTF-8");
        for(int i = 0; i < tmp.length; ++i){
            ans[i + endPos] = tmp[i];
        }
        endPos += tmp.length;
        ans[endPos] = "\n".getBytes("UTF-8")[0];
        return ans;
    }

    public Boolean compareTo(Sportsman obj) {
        return obj.sportType.equals(this.sportType);
    }


}
