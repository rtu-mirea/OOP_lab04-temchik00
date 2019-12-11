package Task2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        try {
            boolean c = true;
            Scanner in = new Scanner(System.in);
            ArrayList<Sportsman> t1 = new ArrayList<Sportsman>();
            ArrayList t2 = new ArrayList<Sportsman>();
            FileHandler fi = new FileHandler();
            while (c) {
                System.out.println("1.Добавить спортсмена\n2.Записать массив спортсменов\n" +
                        "3.Прочитать из файла 2го пункта и получить массив спортсменов с заданным ростом" +
                        "\n4.Поменять местами спортсменов\n5.Выход");
                int a = Integer.parseInt(in.next());
                switch (a) {
                    case 1:
                        Sportsman t = new Sportsman();
                        t1.add(t);
                        break;
                    case 2:
                        if (t1.isEmpty()) {
                            System.out.println("Массив пуст");
                            break;
                        }
                        fi.write(t1);
                        break;
                    case 3:
                        if (t1.isEmpty()) {
                            System.out.println("Массив пуст");
                            break;
                        }
                        System.out.println("Введите рост");
                        int n = in.nextInt();
                        t2 = fi.read(n);
                        System.out.println(t2.toString());
                        break;
                    case 4:
                        if (t2.isEmpty()) {
                            System.out.println("Массив пуст");
                            break;
                        }
                        fi.raf(t2);

                        break;
                    case 5:
                        c = false;
                        break;

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
