import java.util.ArrayList;
import java.util.List;

abstract class Fruit{                   // все фрукты
    double weight_coeff;                // коэффициент пересчета массы
    int count;                          // количество (причина образования партии)

    public Fruit(int value){
        this.count = value;
    }

    public Double getWeight(){          // вес партии фруктов
        double result = this.count*this.weight_coeff;
        return result;
    };
}

class Apple extends Fruit{              // яблоки
    public Apple(int count){
        super(count);
        this.weight_coeff = 1;
    }

    @Override
    public String toString() {
        return "Партия яблок {" + count + " штук, массой "+getWeight()+ "f}";
    }
};

class Orange extends Fruit{            // апельсины
    public Orange(int count){
        super(count);
        this.weight_coeff = 1.5;
    }

    @Override
    public String toString() {
        return "Партия апельсинов {" + count + " штук, массой "+getWeight()+ "f}";
    }
};

class Box<T extends Fruit>{             // коробка
    // условимся что коробки безразмерны и в них ведется учет по партиям
    List<T> list = new ArrayList<>();
        // каждый элемент этого списка - партия фруктов одного вида.

    public Box() {
        // ничего не делаем - все уже сделано…
    }

    @Override
    public String toString() {
        return "Коробка {" + list + "}";
    }

    public void add(T value){
        this.list.add(value);
    }

    public Double getWeight(){             // масса коробки суммарно по всем партиям
        Double result = 0.0;
        for (T i: list) {
            result+=i.getWeight();
        }
        return result;
    }

    public boolean compare(Box box){       // сравнение коробок
        boolean result = false;
        Double selfWeight = this.getWeight();
        Double anotherWeight=box.getWeight();
        if (selfWeight.equals(anotherWeight)){ // Коробки по условию равны, когда равны их массы, без учета типа фруктов
            result= true;
        }
        return result;
    }

    public void emptyTo(Box box){          // метод пересыпания фруктов, аргумент — это коробка-приемник.
        // условимся, что пересыпаем непустые коробки
        for (T i:this.list) {
            box.add(i);                    // перенесем каждую партию в коробку-приемник
        }
        this.list.clear();                 // очистим коробку отправитель
    }

}

// точка входа
public class Task2v1 {

    public static Apple createApples(int value){
        Apple apples = new Apple(value);
        return apples;
    }

    public static Orange createOranges(int value){
        Orange oranges = new Orange(value);
        return oranges;
    }

    /*
    Тесты работоспособности созданныйх классов и методов
     */
    public static void main(String[] args) {
        // тест создания партий фруктов одного вида
        System.out.println("--- Тест создания партий фрутов --------------------");
        System.out.println(createApples(10));       // тест получения партии яблок
        System.out.println(createOranges(3));       // тест получения партии апельсинов

        System.out.println("--- Тест создания коробок --------------------------");
        // Создание коробок
        Box<Apple> appleBox = new Box<>();
        appleBox.add(createApples(10));
            // при необходимости проверки исключений каждый такой вызов нужно обернуть в конструкцию try…catch
        appleBox.add(createApples(6));
        appleBox.add(createApples(3));
        appleBox.add(createApples(8));
        System.out.println(appleBox);
        System.out.println("Общая масса коробки - "+appleBox.getWeight()+"f");
            // Коробка {[Партия яблок {10 штук, массой 10.0}, Партия яблок {6 штук, массой 6.0}, Партия яблок {3 штук, массой 3.0}, Партия яблок {8 штук, массой 8.0}]}
            // 27
        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(createOranges(10));
        orangeBox.add(createOranges(6));
        orangeBox.add(createOranges(11));
        System.out.println(orangeBox);
        System.out.println("Общая масса коробки - "+orangeBox.getWeight()+"f");
            // Коробка {[Партия апельсинов {10 штук, массой 15.0}, Партия апельсинов {6 штук, массой 9.0}, Партия апельсинов {11 штук, массой 16.5}]}
            // 40.5

        // Сравнение коробок
        System.out.println("--- Тест сравнения коробок -------------------------");
        if (orangeBox.compare(appleBox)) {          // две неравные коробки
            System.out.println("Коробки равны");    // clipboard : Коробки не равны
        } else {
            System.out.println("Коробки не равны");
        };
        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        appleBox1.add(createApples(3));
        orangeBox1.add(createOranges(2));
        // имеем две равные коробки с массой 3f каждая
        System.out.println(appleBox1);
        System.out.println(orangeBox1);
        if (orangeBox1.compare(appleBox1)) {         // две равные коробки
            System.out.println("Коробки равны");     // clipboard : Коробки равны
        } else {
            System.out.println("Коробки не равны");
        };

        // ссыпание коробок в одну
        System.out.println("--- Тест ссыпания коробок в одну -------------------");
        System.out.println(appleBox);
            //Коробка {[Партия яблок {10 штук, массой 10.0f}, Партия яблок {6 штук, массой 6.0f}, Партия яблок {3 штук, массой 3.0f}, Партия яблок {8 штук, массой 8.0f}]}
        System.out.println(appleBox1);
            //Коробка {[Партия яблок {3 штук, массой 3.0f}]}
        appleBox.emptyTo(appleBox1);
        System.out.println(appleBox);   // коробка отправитель должна остаться пуста
            //Коробка {[]}
        System.out.println(appleBox1);
            //Коробка {[Партия яблок {3 штук, массой 3.0f}, Партия яблок {10 штук, массой 10.0f}, Партия яблок {6 штук, массой 6.0f}, Партия яблок {3 штук, массой 3.0f}, Партия яблок {8 штук, массой 8.0f}]}
        orangeBox.emptyTo(appleBox1);
        System.out.println(appleBox1);
        System.out.println(orangeBox);
    }

}
