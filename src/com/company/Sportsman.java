package com.company;

import java.util.Scanner;

public class Sportsman {
    public String secondName;
    public String name;
    public String date;
    public int weight;
    public int height;
    public Boolean isMan;
    public String sportType;

    public Sportsman(String secondName, String name, String date, int weight, int height,
                     Boolean isMan, String sportType) throws IllegalArgumentException {
        if(weight < 1 || height < 1){
            throw new IllegalArgumentException("Wrong");
        }
        this.secondName = secondName;
        this.name = name;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.isMan = isMan;
        this.sportType = sportType;
    }

    public Sportsman() throws IllegalArgumentException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите фамилию: ");
        this.secondName = in.next();
        System.out.println("Введите имя: ");
        this.name = in.next();
        System.out.println("Введите дату рождения: ");
        this.date = in.next();
        System.out.println("Введите вес: ");
        this.weight = in.nextInt();
        System.out.println("Введите рост: ");
        this.height = in.nextInt();
        if(weight < 1 || height < 1){
            throw new IllegalArgumentException("Wrong");
        }

        System.out.println("Введите 0, если женский пол, другое - мужской: ");
        this.isMan = !in.next().equals(0);
        System.out.println("Введите тип спорта: ");
        this.sportType = in.next();
    }

    public String toString(){
        return secondName + " " + name + " " + date + " " + weight + " " + height + " " + isMan + " " + sportType;
    }

    public Boolean compareTo(Sportsman obj) {
        return obj.sportType.equals(this.sportType);
    }


}
