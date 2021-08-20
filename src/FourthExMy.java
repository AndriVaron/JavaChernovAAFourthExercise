import java.util.Random;
import java.util.Scanner;

//К сожалению, не успеваю написать код сам, но точно сделаю это позже. Java надо постоянно тренировать, как раз
//Пока изучаем код без помощи доп. материалов (вебинар смотрел за день до изучения работы):
public class FourthExMy {
    public static int SIZE = 3;                         //Задаём размер поля
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';               //Указываем символы, которые будут отображаться на игровом поле
    public static final char DOT_O = 'O';
    public static char[][] map;                         //Создаём массив для поля
//    public static Scanner sc = new Scanner(System.in);  //Метод для запроса данных для ввода
    public static Random rand = new Random();           //Метод для создания рандома под ИИ
    public static void main(String[] args) {
        initMap();                                      //Вывод метода размера игрового поля
        printMap();                                     //Вывод метода,который отображает карту в консоле
        while (true) {                                  //Пока не заполниться поле, игра не закончится
            IITurn();                                //Вывод хода человека
            printMap();                                 //Показ измененной карты
            if (checkWin(DOT_X)) {
                System.out.println("Победил T-1000");  //Проверка победы ИИ
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");            //Проверка, если ничьи после хода человека
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {                      //Проверка победы ИИ 2
                System.out.println("Победил T-800");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");            //Проверка, если ничьи после хода ИИ
                break;
            }
            System.out.println("Игра закончена");       //Если что-то из этого сработало, то конец цикла
        }
    }
        public static void initMap() {
        map = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {                //Создаётся двумерный массив, где мы заполняем игровое поле точками
                for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
                }
            }
        }
        public static void printMap(){
            for (int i = 0; i <= SIZE; i++) {               //Метод по отображению созданного поля
                System.out.print(i + " ");                  //Где пустые служат для более понятного отображения поля
            }
            System.out.println();
            for (int i = 0; i < SIZE; i++) {                //Тут выводяться цифры для отображения краёв полей
                System.out.print((i + 1) + " ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");      //А тут как раз выводиться само поле
                }
                System.out.println();
            }
            System.out.println();
        }


        public static boolean isCellValid(int x, int y){
                if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false; //Проверка, свободна ли место под установку символа
                if (map[y][x] == DOT_EMPTY) return true;
                return false;
        }

        //Разберём логику победы
        public static boolean checkWin (char symb){
            int diagonal1, diagonal2, horizontal, vertical;   //Задаём параметры, с которыми будем проверять победу
            for (int i = 0; i < SIZE; i++) {
                horizontal = 0; vertical = 0;                 //Начинаем цикл с нулевых отметок
                for (int j = 0; j < SIZE; j++) {              //Цикл проверки по горизонтали и вертикали
                    if (map[i][j] == symb) {                  //Проверка какой символ стоит по горизонтале
                        horizontal++;
                    }
                    if (map[j][i] == symb) {                  //Проверка какой символ стоит по вертикале
                        vertical++;
                    }
                }
                if (horizontal == SIZE|| vertical == SIZE) {  //Если по горизонтале или по вертикале количество символов ровно нашему размеру полю - вернуть правду
                    return true;
                }
            }
            diagonal1 = 0; diagonal2 = 0;                     //Цикл проверки по диагоналям
            for (int i = 0; i < SIZE; i++) {
                if (map[i][i] == symb) {                      //Проверка какой символ стоит по диагонале сверху-вниз
                    diagonal1++;
                }
                if (map[i][SIZE - i - 1] == symb) {           //Проверка какой символ стоит по диагонале снизу-вверх
                    diagonal2++;
                }
            }
            if (diagonal1 == SIZE || diagonal2 == SIZE) {    //Если по диагонале 1 или 2 количество символов ровно нашему размеру полю - вернуть правду
                return true;
            }
            return false;                                   //Иначе, если ничего не правда, то продолжить цикл
        }
        
        public static boolean isMapFull(){
                for (int i = 0; i < SIZE; i++) {            //Проверяем заполненость карты, пока есть точки - продолжаем цикл
                    for (int j = 0; j < SIZE; j++) {
                        if (map[i][j] == DOT_EMPTY) return false;
                    }
                }
                return true;
        }
        //Работа T-800
        public static void aiTurn() {
            int x, y;
            do {
                x = rand.nextInt(SIZE);                 //Не очень умный ИИ, так как ставит нолики в свободные поля
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("T-800 походил в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = DOT_O;
        }
            //Работа T-1000
        public static void IITurn(){
//            int x, y;
//                do {
//                    System.out.println("Введите координаты в формате X Y");
//                    x = sc.nextInt() - 1;
//                    y = sc.nextInt() - 1;
//                } while (!isCellValid(x, y));
//                map[y][x] = DOT_X;
                int x, y;
                do {
                    x = rand.nextInt(SIZE);                      //Посмотрим как два робота будут друг с другом играть
                    y = rand.nextInt(SIZE);
                } while (!isCellValid(x, y));
                System.out.println("T-1000 походил в точку " + (x + 1) + " " + (y + 1));
                map[y][x] = DOT_X;
        }

}

