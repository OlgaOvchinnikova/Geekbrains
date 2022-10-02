import java.util.Arrays;

class arrayBox<T>{
    private T[] obj;

    public arrayBox(T... obj) {
        this.obj = obj;
    }

    public void excahge(int i, int j){
        T value = obj[i];
        obj[i] = obj[j];
        obj[j] = value;
    }

    @Override
    public String toString() {
        return "Содержимое массива {"+ Arrays.toString(obj) +"}";
    }
}

public class Task1 {
    public static void main(String[] args) {
        arrayBox<Integer> myBox1 = new arrayBox<>(10,9,8,7,6,5,4,3,2,1,0);
        System.out.println(myBox1);// clipboard : Содержимое массива {[10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}
        myBox1.excahge(0,10);
        System.out.println(myBox1);// clipboard : Содержимое массива {[0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10]}

        arrayBox<String> myBox2 = new arrayBox<>("10","9","8","7","6","слово","4","3","2","1","0");
        System.out.println(myBox2);// clipboard : Содержимое массива {[10, 9, 8, 7, 6, слово, 4, 3, 2, 1, 0]}
        myBox2.excahge(0,10);
        System.out.println(myBox2);// clipboard : Содержимое массива {[0, 9, 8, 7, 6, слово, 4, 3, 2, 1, 10]}
    }

    /*
    Общий вывод:
        Содержимое массива {[10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}
        Содержимое массива {[0, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10]}
        Содержимое массива {[10, 9, 8, 7, 6, слово, 4, 3, 2, 1, 0]}
        Содержимое массива {[0, 9, 8, 7, 6, слово, 4, 3, 2, 1, 10]}
     */


}
