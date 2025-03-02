import java.util.ArrayList;
import java.util.Scanner;

class Author {
    private int id;                     // id автора
    private ArrayList<Integer> bookIds; // id книг
    private String name;                // имя автора
    private String birthdate;           // дата рождения

    public Author() {
        bookIds = new ArrayList<>();
    }

    // ввод автора
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[  Автор  ]");
        System.out.print("Введите имя: ");
        name = scanner.nextLine();

        System.out.print("Введите дату рождения дд.мм.гггг: ");
        birthdate = scanner.nextLine();

        System.out.print("Введите id автора: ");
        id = scanner.nextInt();
        scanner.nextLine();
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

        System.out.println("[  Книга  ]");
        System.out.print("Введите название: ");
        title = scanner.nextLine();

        System.out.print("Введите год публикации: ");
        publishedYear = scanner.nextLine();

        System.out.print("Введите id книги: ");
        id = scanner.nextInt();
        scanner.nextLine();
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
        System.out.println("[  Читатель  ]");
        System.out.print("Введите имя: ");
        name = scanner.nextLine();

        System.out.print("Введите email: ");
        email = scanner.nextLine();

        System.out.print("Введите id читателя: ");
        id = scanner.nextInt();
        scanner.nextLine();
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
        System.out.println("[  Запрос  ]");
        System.out.print("Введите id запроса: ");
        id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Дата запроса дд.мм.гггг: ");
        orderDate = scanner.nextLine();
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


