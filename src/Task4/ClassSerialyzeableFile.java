package Task4;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class ClassSerialyzeableFile implements Serializable{

    String file = "";
    LinkedList<User> users = new LinkedList<User>();

    ClassSerialyzeableFile(String f){
        if (new File(f).exists()){
            file = f;
        }else{
            System.out.println("Файла не существует");
        }
    }

    public void record(User us) throws Exception{
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(us);
        oos.close();
    }

    public User read() throws Exception{
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream oin = new ObjectInputStream(in);
        User us = new User();
        us = (User)oin.readObject();
        return us;
    }

    public void collection () throws Exception{
        System.out.println("Сколько объектов заполнить");
        Scanner is = new Scanner(System.in);
        int n = is.nextInt();
        for (int i = 0; i<n; i++){
            System.out.println("Введите имя логин и пароль " + i + "го объекта");
            User u = new User();
            u.vvodsconsoli();
            users.add(u);
        }
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(users);
    }

    public void readfile() throws Exception{
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream oin = new ObjectInputStream(in);

        users = (LinkedList)oin.readObject();

    }

    public LinkedList<User> gerArray(){
        return users;
    }

}