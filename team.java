import java.util.Arrays;
import java.util.Random;

public class Team {

    enum Results {ON_START, RUNING, IS_WIN, IS_FAILED}
    protected class Warrior {
        private int endurance;
        private Results result;

        public Warrior(int endurance) {
            // заказной участник
            this.endurance = endurance;
            this.result = Results.ON_START;
        }

        public Warrior() {
            // случайный участник
            Random random = new Random();
            this.endurance = random.nextInt(150); // чтобы некоторые не прошли
            this.result = Results.ON_START;
        }

        // вывод параметров участника
        @Override
        public String toString() {
            return "Выносливость - " + endurance;
        }

        public void setResult(Results result) {
            this.result = result;
        }

        public int getEndurance() {
            return endurance;
        }

        public void decreaseEndurance(int i) {
            this.endurance =this.endurance-i;
        }

    }

    protected Warrior[] warriorArray = new Warrior[4];
    private String name;

    public Team() {
        // конструктор команды со случайными участниками
        this.name = "«Безымянная команда»";
        for (int i = 0; i < 4; i += 1) {
            this.warriorArray[i] = new Warrior();
        }
    }

    public Team(String name, int endurance) {
        // конструктор команды со случайными участниками
        // c именем команды и одинаковой выносливостью
        this.name = "«"+name+"»";
        for (int i = 0; i < 4; i += 1) {
            this.warriorArray[i] = new Warrior(endurance);
        }
    }

    public void printTeamInfo() {
        // вывод информации по всему подразделению
        System.out.println("Сводная информация по команде "+this.name+":");
        for (int i = 0; i < 4; i += 1) {
            System.out.println("   Участник №"+(i+1)+" ("+ warriorArray[i]+" "+ warriorArray[i].result+")");
        }
    }

    public void printTeamWinInfo() {
        // вывод информации по подебителям
        System.out.println("Информация по победителям:");
        for (int i = 0; i < 4; i += 1) {
            if (warriorArray[i].result==Results.IS_WIN) {
                System.out.println("   Участник №"+(i+1)+" "+ warriorArray[i]);
            }
        }
    }

    public Warrior getWarrior(int i){
        return this.warriorArray[i];
    }

    // тесты работоспособности полученного класса
    public static void main(String[] args) {
        // создание команды
        // случайная команда
        Team team = new Team();
        team.printTeamInfo();
        team.printTeamWinInfo();

        // заказная команда (спецсостав)
        Team team1 = new Team("Название команды", 1000); // эти всех победят
        team1.printTeamInfo();
        team1.printTeamWinInfo();

        /* вывод тестов :
        Сводная информация по команде
        Участник №1 (Выносливость - 115 ON_START)
        Участник №2 (Выносливость - 80 ON_START)
        Участник №3 (Выносливость - 65 ON_START)
        Участник №4 (Выносливость - 6 ON_START)
        Информация по победителям
        Сводная информация по команде
        Участник №1 (Выносливость - 1000 ON_START)
        Участник №2 (Выносливость - 1000 ON_START)
        Участник №3 (Выносливость - 1000 ON_START)
        Участник №4 (Выносливость - 1000 ON_START)
        Информация по победителям
        */

    }

}
