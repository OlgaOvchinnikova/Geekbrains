import java.beans.PropertyEditorSupport;
import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;

public class Main {
    /*
    группа самописных исключений
     */
    static class MyArraySizeException extends Exception {
        public MyArraySizeException(){
            super("Ошибка размера массива");
        }
    }
    static class MyArrayDataException extends Exception {
        public MyArrayDataException(int i, int j){
            super("Ошибка данных в ячейке "+i+" "+j);
        }
    }

    /*
    методы из задания
     */
    public static void checkArraySize(String[][] array) throws MyArraySizeException {
        // проверка размерности массива
        boolean result = false;
        if (array.length!=4) {
            result = true;
        }
        for (String[] i: array) {
            if (i.length!=4) {
                result = true;
            }
        }
        if (result) {
            throw new MyArraySizeException();
        }
    }

    public static void arraySumm(String[][] array) throws MyArrayDataException {
        // сумма элементов массива
        int res = 0;
        Integer value;
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                try {
                    value = Integer.parseInt(array[i][j]);
                }
                catch (Exception e) {
                    System.out.println("Неконвертируемое значение - "+"\""+array[i][j]+"\" в ячейке : "+i+" "+j);
                    throw new MyArrayDataException(i,j);
                }
                res+=value;
            }
        }
        System.out.println("Сумма элементов массива = "+res);
    }

    /*
    точка входа
     */
    public static void main(String[] args) {
        String[][] good_array = {{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","15","16"}};
        String[][] good_array1 = {{"1","s","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","15","16"}};
        String[][] bad_array = {{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","15","16","17"}};
        // исключение «ошибка размера» на правильном массиве
        try {
            checkArraySize(good_array);
        }
        catch (MyArraySizeException e) {
            System.out.println(e);
        }
        // исключение «ошибка размера» на неправильном массиве
        try {
            checkArraySize(bad_array);
        }
        catch (MyArraySizeException e) {
            System.out.println(e);
                // clipboard : Main$MyArraySizeException: Ошибка размера массива
        }
        // подсчет суммы дял правильного массива
        try {
            arraySumm(good_array);
        }
        catch (MyArrayDataException e) {
            System.out.println(e);
                // clipboard : Сумма элементов массива = 136
        }
        // подсчет суммы для неправильного массива
        try {
            arraySumm(good_array1);
        }
        catch (MyArrayDataException e) {
            System.out.println(e);
                // clipboard : Неконвертируемое значение - "s" в ячейке : 0 1
                //             Main$MyArrayDataException: Ошибка данных в ячейке 0 1
        }

    }
}
