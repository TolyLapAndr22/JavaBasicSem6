package hw_6_1;

/*

Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы.
Реализовать в java. Создать множество ноутбуков. Написать метод, который будет запрашивать у пользователя
критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно
хранить в Map. Например: “Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации
можно также в Map.
Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
Работу сдать как обычно ссылкой на гит репозиторий
Частые ошибки:
1. Заставляете пользователя вводить все существующие критерии фильтрации
2. Невозможно использовать более одного критерия фильтрации одновременно
3. При выборке выводятся ноутбуки, которые удовлетворяют только одному фильтру,
а не всем введенным пользователем
4. Работа выполнена только для каких то конкретных ноутбуков, и если поменять характеристики ноутбуков
или добавить еще ноутбук, то программа начинает работать некорректно
 */
import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int hdd;
    String os;
    String color;

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }


}

public class hw_6 {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        Set<Laptop> filtLaptops;
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Dell", 32, 128, "Linux", "Gray"));
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Silver"));
        laptops.add(new Laptop("Apple", 16, 1024, "MacOS", "Red"));
        laptops.add(new Laptop("Lenovo", 32, 1024, "Windows", "Gray"));
        filtLaptops=laptops;

        //Map<String, Object> filters = new HashMap<>();
        Map<Integer, String> menu = new HashMap<>();
        menu.put(1," ОЗУ");
        menu.put(2," Объем ЖД");
        menu.put(3," Операционная система");
        menu.put(4," Цвет");
        menu.put(5," Поиск");

        prMenu(menu);

        Scanner scanner = new Scanner(System.in);


        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 5) {
                break;
            }
            switch (choice) {
                case 1:
                    if (!menu.containsKey(1)) {
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        break;
                    }
                    System.out.println("Минимальный объем ОЗУ?");

                    filtLaptops = filterLapRam(filtLaptops, scanner.nextInt());
                    menu.remove(1);
                    prMenu(menu);
                    break;
                case 2:
                    if (!menu.containsKey(2)) {
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        break;
                    }
                    System.out.println("Минимальный объем ЖД?");

                    filtLaptops = filterLapHdd(filtLaptops, scanner.nextInt());
                    menu.remove(2);
                    prMenu(menu);
                    break;
                case 3:
                    if (!menu.containsKey(3)) {
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        break;
                    }
                    System.out.println("Операционная система?");

                    filtLaptops = filterLapOs(filtLaptops, scanner.next());
                    menu.remove(3);
                    prMenu(menu);
                    break;
                case 4:
                    if (!menu.containsKey(4)) {
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        break;
                    }
                    System.out.println("Цвет?");

                    filtLaptops = filterLapColor(filtLaptops, scanner.next());
                    menu.remove(4);
                    prMenu(menu);
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        System.out.println("ноутбуки у нас есть:");
        if (filtLaptops.isEmpty()) System.out.printf("их уже продали");
        for (Laptop laptop : filtLaptops) {
            System.out.println(laptop);
        }
    }
    static void  prMenu(Map<Integer,String> menu){
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        for (Map.Entry str:menu.entrySet()) {
            System.out.println(str);
        }
    }
    static Set<Laptop> filterLapRam(Set<Laptop> laptop, int ram){
        Set<Laptop> set=new HashSet<Laptop>();
        for (Laptop lap: laptop){
            if(lap.ram >= ram){
                set.add(lap);
            }
        }
        return set;
    }
    static Set<Laptop> filterLapHdd(Set<Laptop> laptop, int hdd){
        Set<Laptop> set=new HashSet<Laptop>();
        for (Laptop lap: laptop){
            if(lap.hdd >= hdd){
                set.add(lap);
            }
        }
        return set;
    }
    static Set<Laptop> filterLapOs(Set<Laptop> laptop, String Os){
        Set<Laptop> set=new HashSet<Laptop>();
        for (Laptop lap: laptop){
            if(lap.os.equals(Os)){
                set.add(lap);
            }
        }
        return set;
    }
    static Set<Laptop> filterLapColor(Set<Laptop> laptop, String Color) {
        Set<Laptop> set = new HashSet<Laptop>();
        for (Laptop lap : laptop) {
            if (lap.color.equals(Color)) {
                set.add(lap);
            }
        }
        return set;
    }

}

