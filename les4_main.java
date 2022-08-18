import java.util.*;

class PhoneRecord{
    // класс записи в телефонную книгу (одна позиция)
    String surname;
    String phoneNumber;

    public PhoneRecord(String surname, String phoneNumber) {
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return surname +" :  " + phoneNumber;
    }
}

class PhoneBook<T extends PhoneRecord> extends ArrayList {
    // Класс телефонной книги, хранящий записи и наследованный от ArrayList - он по определению сразу коллекция

    public PhoneBook(){
        super();
    }

    public void add(String surname, String phoneNumber){
        this.add(new PhoneRecord(surname, phoneNumber));
    }

    public ArrayList get(String surname){ // возвращается список совпадений
        ArrayList<T> res = new ArrayList<>();
        for (int i=0; i<this.size(); i++){
            T record = (T) this.get(i);
            if (surname.equals(record.surname)){
                res.add(record);
            }
        }
        return res;
    }

}



public class Main {

    public static void main(String[] args) {
        // Задание 1
        String[] words = {"один", "раз", "два", "три", "раз", "два", "раз", "два", "раз", "два", "три",
                "раз", "два", "три", "четыре", "раз", "два","три","четыре","пять"};
            // 20 слов, 15 уникальных
        System.out.println("Иcходный массив : "+Arrays.toString(words)+" всего элементов —"+words.length);
        HashSet set = new HashSet(Arrays.asList(words));
        System.out.println("Список уникальных слов : " + set+" всего элементов —"+set.size());
        HashMap<String, Integer> frequency = new HashMap<>();
        for (String i:words){
            frequency.put(i, Collections.frequency(Arrays.asList(words),i));
        }
        System.out.println("Подсчет вхождений : "+frequency+"\n");

        // Задание 2
        PhoneBook pb = new PhoneBook();
        pb.add("Иванов","84262260011");
        pb.add("Петров","84262220011");
        pb.add("Иванов","84262260022");
        pb.add("Сидоров","84262260023");
        System.out.println("Телефонная книга : "+pb);                       // вся телефонная книга
        System.out.println("Совпадения для «Иванов» : "+pb.get("Иванов"));  // найденные Ивановы

    }

}
