import java.util.ArrayList;
import java.util.Scanner;

class Author {
    private int id;                     // id автора
    private ArrayList<Integer> bookIds; // id книг
    private String name;                // имя автора
    private String birthdate;           // дата рождения

    public static int amount_of_authors = 0;

    public Author() {
        bookIds = new ArrayList<>();
    }

    // ввод автора
    public void input() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("[  Автор  ]");
            System.out.print("Введите имя: ");
            name = scanner.nextLine();

            System.out.print("Введите дату рождения дд.мм.гггг: ");
            birthdate = scanner.nextLine();

            System.out.print("Введите id автора: ");
            id = Integer.parseInt(scanner.nextLine());

            if (id < 0) {
                throw new IllegalArgumentException("ID не может быть отрицательным.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
            throw e; // выбрасываем исключение дальше
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // вывод автора
    public void output() {
        System.out.println("Автор:");
        System.out.println("  Имя - " + name);
        System.out.println("  Дата рождения - " + birthdate);
        System.out.println("  ID автора - " + id);
        if (!bookIds.isEmpty()) {
            System.out.println("  ID книг:");
            for (int bookId : bookIds) {
                System.out.println("    - " + bookId);
            }
        } else {
            System.out.println("  Нет книг от данного автора");
        }
        System.out.println();
    }

    // добавление книги
    public void addBook(int bookId) {
        bookIds.add(bookId);
    }

    // выдача id автора
    public int getId() {
        return id;
    }

    //при добавлении автора их общее число растет
    void increment() {
        amount_of_authors++;
    }

    //при удалении автора их общее число уменьшается
    void decrement() {
        amount_of_authors--;
    }

    //отображение общего числа авторов
    static void show_amount_of_authors() {
        System.out.println("  \n\nВсего авторов: " + amount_of_authors + "\n\n");
    }

    //метод для вспомогательного класса
    public int getBookCount() {
        return bookIds.size(); // Возвращаем количество книг
    }
}

//вспомогательный класс
class ClassHelper {
    // Метод для получения количества книг у автора
    public static int getBooksCount(Author author) {
        return author.getBookCount();
    }
}


class Book {
    private int id;                 // id книги
    private boolean isAvailable;    // статус доступности книги
    private int authorId;           // id автора
    private String title;           // название книги
    private String publishedYear;   // год публикации

    public Book() {
        isAvailable = true;
    }

    // ввод книги
    public void input(int authorId) {
        Scanner scanner = new Scanner(System.in);
        this.authorId = authorId;

        try {
            System.out.println("[  Книга  ]");
            System.out.print("Введите название: ");
            title = scanner.nextLine();

            System.out.print("Введите год публикации: ");
            publishedYear = scanner.nextLine();

            System.out.print("Введите id книги: ");
            id = Integer.parseInt(scanner.nextLine());

            if (id < 0) {
                throw new IllegalArgumentException("ID не может быть отрицательным.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
            throw e; // выбрасываем исключение дальше
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // вывод книги
    public void output() {
        System.out.println("Книга:");
        System.out.println("  Название - " + title);
        System.out.println("  Год публикации - " + publishedYear);
        System.out.println("  ID книги - " + id);
        System.out.println("  ID автора - " + authorId);
        System.out.println("  Статус - " + (isAvailable ? "Доступна" : "Не доступна"));
        System.out.println();
    }

    // управление доступностью книги
    public void setAvailability(boolean status) {
        isAvailable = status;
    }

    // выдача id книги
    public int getId() {
        return id;
    }
}

class Reader {
    private int id;                 // id читателя
    private int borrowedBookId;     // id занятой книги
    private String name;            // имя
    private String email;           // эл. почта

    public Reader() {
        borrowedBookId = 0;
    }

    // ввод читателя
    public void input() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("[  Читатель  ]");
            System.out.print("Введите имя: ");
            name = scanner.nextLine();

            System.out.print("Введите email: ");
            email = scanner.nextLine();

            System.out.print("Введите id читателя: ");
            id = Integer.parseInt(scanner.nextLine());

            if (id < 0) {
                throw new IllegalArgumentException("ID не может быть отрицательным.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
            throw e; // выбрасываем исключение дальше
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // вывод читателя
    public void output() {
        System.out.println("Читатель:");
        System.out.println("  Имя - " + name);
        System.out.println("  Почта - " + email);
        System.out.println("  ID читателя - " + id);
        System.out.print("  ID занятой книги - ");
        if (borrowedBookId == 0) {
            System.out.println("Книг не занято");
        } else {
            System.out.println(borrowedBookId);
        }
        System.out.println();
    }

    // выдача id читателя
    public int getId() {
        return id;
    }
}

class Order {
    private int id;             // id запроса
    private int readerId;       // id читателя
    private int bookId;         // id запрашиваемой книги
    private String orderDate;   // дата запроса
    private String returnDate;  // дата возврата

    public Order() {
        returnDate = "unreturned";
    }

    // ввод запроса
    public void input(int readerId, int bookId) {
        this.readerId = readerId;
        this.bookId = bookId;

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("[  Запрос  ]");
            System.out.print("Введите id запроса: ");
            id = Integer.parseInt(scanner.nextLine());

            if (id < 0) {
                throw new IllegalArgumentException("ID не может быть отрицательным.");
            }

            System.out.print("Дата запроса дд.мм.гггг: ");
            orderDate = scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
            throw e; // выбрасываем исключение дальше
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // вывод запроса
    public void output() {
        System.out.println("Запрос:");
        System.out.println("  ID запроса - " + id);
        System.out.println("  ID книги - " + bookId);
        System.out.println("  ID читателя - " + readerId);
        System.out.println("  Дата запроса - " + orderDate);
        System.out.println("  Дата возврата - " + returnDate);
        System.out.println();
    }

    // добавление даты возврата
    public void edit(String returnDate) {
        this.returnDate = returnDate;
    }
}

class Fine {
    private int id;             // id штрафа
    private int readerId;       // id читателя
    private int amount;         // размер штрафа
    private boolean isPaid;     // оплачен ли штраф
    private String reason;      // причина штрафа

    public Fine() {
        isPaid = false;
    }

    // ввод штрафа
    public void input(int readerId) {
        this.readerId = readerId;

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("[  Штраф  ]\nВведите объем штрафа: ");
            amount = scanner.nextInt();

            System.out.print("Введите id штрафа: ");
            id = Integer.parseInt(scanner.nextLine());

            if (id < 0) {
                throw new IllegalArgumentException("ID не может быть отрицательным.");
            }

            System.out.print("Причина штрафа: ");
            reason = scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
            throw e; // выбрасываем исключение дальше
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // вывод штрафа
    public void output() {
        System.out.println("Штраф:");
        System.out.println("  ID штрафа - " + id);
        System.out.println("  ID читателя - " + readerId);
        System.out.println("  Объем штрафа - " + amount + " $");
        System.out.println("  Статус - " + (isPaid ? "Оплачен" : "Не оплачен"));
        System.out.println("  Причина - " + reason);
        System.out.println();
    }

    // присвоение статуса "оплачен"
    public void edit() {
        isPaid = true;
    }
}




public class Main{
    public static void main(String[] args) {
        int howManyBooks;
        Scanner scanner = new Scanner(System.in);
        Author lary = new Author();
        try{
            lary.input();
        }
        catch (Exception e){
            System.out.println("We tried, we're sorry");
            System.exit(1);
        }
        lary.output();
        lary.increment();
        Author.show_amount_of_authors();

        System.out.print("Сколько добавить книг?: ");
        howManyBooks = scanner.nextInt();
        scanner.nextLine();

        //работа с массивом объектов
        Book[] gary = new Book[howManyBooks];
        for (int i = 0; i < howManyBooks; ++i) {
            System.out.print("N" + (i + 1) + ": ");
            gary[i] = new Book();
            try{
                gary[i].input(lary.getId());
            }
            catch (Exception e){
                System.out.println("We tried, we're sorry");
                System.exit(1);
            }
            lary.addBook(gary[i].getId()); // Добавление книги к автору
        }

        // Вывод данных всех книг
        for (Book book : gary) {
            book.output();
        }
        lary.output();

        // Показать количество книг у автора с использованием вспомогательного класса
        System.out.println("Количество книг у автора " + lary.getId() + ": " + ClassHelper.getBooksCount(lary));


        Author lary2 = new Author();
        try{
            lary2.input();
        }
        catch (Exception e){
            System.out.println("We tried, we're sorry");
            System.exit(1);
        }
        lary2.output();
        lary2.increment();
        Author.show_amount_of_authors();

        Reader[] reader = new Reader[2];
        Fine[][] fine = new Fine[2][2];
        for (int i=0; i<2; i++){
            try {
                reader[i].input();
            }
            catch (Exception e){
                System.out.println("We tried, we're sorry");
                System.exit(1);
            }
            for (int j=0; j<2; j++){
                try{
                    fine[i][j].input(reader[i].getId());
                }
                catch (Exception e){
                    System.out.println("We tried, we're sorry");
                    System.exit(1);
                }
            }
        }

        for (int i=0; i<2; i++){
            reader[i].output();
            for (int j=0; j<2; j++){
                fine[i][j].output();
            }
        }

        Order pery = new Order();
        try{
            pery.input(reader[0].getId(), gary[0].getId()); // Используем первую книгу для примера
        }
        catch (Exception e){
            System.out.println("We tried, we're sorry");
            System.exit(1);
        }
        pery.output();

        // Пример редактирования заказа
        pery.edit("21.11.2121");
        pery.output();



        // Пример редактирования штрафа
        fine[0][0].edit();
        fine[0][0].output();
    }
}
