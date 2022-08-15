import java.util.Random;
import java.util.Arrays;

public class Course {

    public class Trial {
        private int endurance;

        // два конструктора для решения поставленных перед классом задач
        public Trial() {
            // конструктор случайного препятсвия,
            // со значениями кондиций от 0 -до 20
            Random random = new Random();
            this.endurance = random.nextInt(21);
        }

        public Trial(int endurance) {
            // конструктор «заказного» в плане сложности препятсвия
            this.endurance = endurance;
        }

        // геттер - для того, чтобы потом получать значения при прохождении
        public int getEndurance() {
            return endurance;
        }

        // вывод в строку - для теста создания, ну и так: пригодится…
        @Override
        public String toString() {
            return "Препятствие (" + endurance +")";
        }
    }

    /*
    для унификации процесса сделаем любую полосу из 10 препятсвий
     */
    private Trial[] trialArray = new Trial[10];

    public Course() {
        // конструктор случайной полосы
        for (int i = 0; i < 10; i += 1) {
            trialArray[i] = new Trial();
        }
    }

    public Course(int[]array) {
        // конструктор «заказной» полосы.
        // полосу передаем как двумерный массив, в котором строка - параметры
        // полосы, кроме того,
        // будем считать, что массив всегда передается «правильным» и не станем
        // обрабатывать исключений.
        for (int i = 0; i < 10; i += 1) {
            trialArray[i] = new Trial(array[i]);
        }
    }

    public void printCourse(){
        System.out.println("Полоса : "+Arrays.toString(this.trialArray));
    }

    // простейшие тесты работоспособности полученного класса.
    public static void main(String[] args) {
        // тест создания случайной полосы препятствий
        Course course = new Course();
        System.out.println(Arrays.toString(course.trialArray));

        // тест создания заказной полосы препятствий
        int array[] = {17,10,7,4,20,18,11,13,14,18};
        Course course1 = new Course(array);
        System.out.println(Arrays.toString(course1.trialArray));

        /* вывод тестов :
        [Испытание (10), Испытание (14), Испытание (17), Испытание (1), Испытание (13), Испытание (12), Испытание (4), Испытание (5), Испытание (8), Испытание (14)]
        [Испытание (17), Испытание (10), Испытание (7), Испытание (4), Испытание (20), Испытание (18), Испытание (11), Испытание (13), Испытание (14), Испытание (18)]
        */

    }

    public void doIt(Team team){
        // обработка прохождения командой полосы
        Team.Warrior warrior;
        Trial trial;
        for (int i = 0; i < 4; i += 1) { // цикл по участникам
            warrior = team.getWarrior(i);
            for (int j = 0; j<10; j+=1){ // цикл по препятствиям
                trial = this.trialArray[j];
                if (warrior.getEndurance()>=trial.getEndurance()) {
                    // он прошел препятсвие…
                    if (j==9) {
                        warrior.setResult(Team.Results.IS_WIN);
                    } else {
                        warrior.setResult(Team.Results.RUNING);
                    }
                    // вычесть выносливость
                    warrior.decreaseEndurance(trial.getEndurance());
                } else {
                    // он не прошел препятсвие…
                    warrior.setResult(Team.Results.IS_FAILED);
                    break;
                }

            }
        }
    }

}
