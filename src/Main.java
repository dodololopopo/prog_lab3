import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Collection<T extends Cloneable> implements Cloneable{
    public List<T> items; // Список для хранения элементов коллекции

    // Конструктор
    public Collection() {
        items = new ArrayList<>();
    }

    // Добавление элемента в коллекцию
    public void add(T item) {
        items.add(item);
    }

    // Вывод всех элементов коллекции
    public void output() {
        for (T item : items) {
            System.out.println(item.toString());
        }
    }

    @Override
    public Collection<T> clone() throws CloneNotSupportedException {
        // Создаем новый экземпляр Collection
        Collection<T> coll = new Collection<>();
        coll.items = new ArrayList<>();

        for (T item : this.items) {
            // Явное приведение типа с использованием интерфейса Cloneable
            T clonedItem = cloneItem(item);
            coll.items.add(clonedItem);
        }

        return coll;
    }

    @SuppressWarnings("unchecked")
    private T cloneItem(T item) {
        try {
            return (T) item.getClass().getMethod("clone").invoke(item);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Clone not supported for item: " + item, e);
        }
    }
}

// Интерфейс для объектов, которые имеют уникальный идентификатор
interface Identifiable {
    int getId();
}

class Author implements Cloneable, Identifiable{
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
    @Override
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Автор:\n");
        sb.append("  Имя - ").append(name).append("\n");
        sb.append("  Дата рождения - ").append(birthdate).append("\n");
        sb.append("  ID автора - ").append(id).append("\n");

        if (!bookIds.isEmpty()) {
            sb.append("  ID книг:\n");
            for (int bookId : bookIds) {
                sb.append("    - ").append(bookId).append("\n");
            }
        } else {
            sb.append("  Нет книг от данного автора\n");
        }

        return sb.toString();
    }
    @Override
    public Author clone() throws CloneNotSupportedException{
        return (Author) super.clone();
    }
}

//вспомогательный класс
class ClassHelper {
    // Метод для получения количества книг у автора
    public static int getBooksCount(Author author) {
        return author.getBookCount();
    }
}

abstract class Literature{
    abstract public void update_lib();
}
class Book extends Literature implements Cloneable, Identifiable{
    protected int id;                 // id книги
    protected boolean isAvailable;    // статус доступности книги
    protected int authorId;           // id автора
    protected String title;           // название книги
    protected String publishedYear;   // год публикации

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
    @Override
    public int getId() {
        return id;
    }

    public void update_lib(){
        System.out.println("== Библиотека обновлена ==");
        System.out.println("Причина: добавлена книга");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Книга:\n");
        sb.append("  Название - ").append(title).append("\n");
        sb.append("  Год публикации - ").append(publishedYear).append("\n");
        sb.append("  ID книги - ").append(id).append("\n");
        sb.append("  ID автора - ").append(authorId).append("\n");
        sb.append("  Статус - ").append(isAvailable ? "Доступна" : "Не доступна");
        return sb.toString();
    }

    @Override
    public Book clone() throws CloneNotSupportedException{
        return (Book) super.clone();
    }
}

class EBook extends Book implements Cloneable{
    private String file_format; // формат файла (например, PDF, EPUB)
    private double file_size;        // размер файла в мегабайтах

    public EBook(){
        new Book();
        file_size = 0;
        file_format = "-";
    }

    // Ввод данных для электронной книги
    public void input(int author_id) {
        Scanner scanner = new Scanner(System.in);
        super.input(author_id);
        System.out.print("Введите формат файла (например, PDF, EPUB): ");
        file_format = scanner.nextLine();
        System.out.print("Введите размер файла (в МБ): ");
        file_size = scanner.nextDouble();
    }

    //перегрузка функции вывода с методом базового класса и без
    public void output() {
        super.output(); // Вызов метода вывода из базового класса
        System.out.println("  Формат файла - " + file_format);
        System.out.println("  Размер файла - " + file_size +"МБ");
    }

    public void output(String str) {
        System.out.println("[ Книга ]");
        System.out.println("  Название - " + title);
        System.out.println("  Формат файла - " + file_format);
        System.out.println("  Размер файла - " + file_size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Книга:\n");
        sb.append("  Название - ").append(title).append("\n");
        sb.append("  Год публикации - ").append(publishedYear).append("\n");
        sb.append("  ID книги - ").append(id).append("\n");
        sb.append("  ID автора - ").append(authorId).append("\n");
        sb.append("  Статус - ").append(isAvailable ? "Доступна" : "Не доступна");
        sb.append("  Формат файла - ").append(file_format).append("\n");
        sb.append("  Размер файла - ").append(file_size).append("\n");
        return sb.toString();
    }

    @Override
    public EBook clone() throws CloneNotSupportedException{
        return (EBook) super.clone();
    }
};



class Reader implements Cloneable, Identifiable{
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
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Читатель:\n");
        sb.append("  Имя - ").append(name).append("\n");
        sb.append("  Почта - ").append(email).append("\n");
        sb.append("  ID читателя - ").append(id).append("\n");
        sb.append("  ID занятой книги - ");
        if (borrowedBookId == 0) {
            sb.append("Книг не занято").append("\n");
        } else {
            sb.append(borrowedBookId).append("\n");
        }
        return sb.toString();
    }
    @Override
    public Reader clone() throws CloneNotSupportedException{
        return (Reader) super.clone();
    }
}

class Order implements Cloneable, Identifiable {
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

    //выдача id запроса
    @Override
    public int getId() {
        return id;
    }
    // добавление даты возврата
    public void edit(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Запрос:\n");
        sb.append("  ID запроса - ").append(id).append("\n");
        sb.append("  ID читателя - ").append(readerId).append("\n");
        sb.append("  ID книги - ").append(bookId).append("\n");
        sb.append("  Дата запроса - ").append(orderDate).append("\n");
        sb.append("  Дата возврата - ").append(returnDate).append("\n");
        return sb.toString();
    }
    @Override
    public Order clone() throws CloneNotSupportedException{
        return (Order) super.clone();
    }
}

class Fine implements Cloneable, Identifiable {
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
            scanner.nextLine(); //чистка буфера

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

    //выдача id штрафа
    @Override
    public int getId() {
        return id;
    }

    // присвоение статуса "оплачен"
    public void edit() {
        isPaid = true;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Штраф:\n");
        sb.append("  ID штрафа - ").append(id).append("\n");
        sb.append("  ID читателя - ").append(readerId).append("\n");
        sb.append("  Объем штрафа - ").append(amount).append("\n");
        sb.append("  Статус - ").append(isPaid ? "Оплачен" : "Не оплачен").append("\n");
        sb.append("  Причина - ").append(reason).append("\n");
        return sb.toString();
    }
    @Override
    public Fine clone() throws CloneNotSupportedException{
        return (Fine) super.clone();
    }
}




public class Main{
    public static void main(String[] args) throws CloneNotSupportedException {
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
        System.out.println(lary);
        lary.increment();
        Author.show_amount_of_authors();

        //демонстрация с производным классом
        EBook Eb = new EBook();
        Eb.input(lary.getId());
        Eb.update_lib();
        lary.addBook(Eb.getId());
        Eb.output();
        Eb.output("shorter");

        //демонстрация клонирования
        EBook clonedEb = Eb.clone();
        clonedEb.setAvailability(false);
        System.out.println("Протитип:");
        System.out.println(Eb);
        System.out.println("Клон");
        System.out.println(clonedEb);

        //глубокое клонирование
        Collection<EBook> collectEbooks = new Collection<EBook>();
        collectEbooks.add(Eb);
        collectEbooks.add(clonedEb);
        Collection<EBook> clonedcollectEbooks = collectEbooks.clone();
        clonedcollectEbooks.items.get(0).input(lary.getId());
        System.out.println("При глубоком клонировании клонированные объекты не ссылаются на один и тот же объект:");
        collectEbooks.output();
        clonedcollectEbooks.output();


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
            System.out.println(book);
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
        System.out.println(lary2);
        lary2.increment();
        Author.show_amount_of_authors();

        Reader[] reader = new Reader[2];
        Fine[][] fine = new Fine[2][2];
        for (int i=0; i<2; i++){
            reader[i] = new Reader();
            try {
                reader[i].input();
            }
            catch (Exception e){
                System.out.println("We tried, we're sorry");
                System.exit(1);
            }
            for (int j=0; j<2; j++){
                fine[i][j] = new Fine();
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
            System.out.println(reader[i]);
            for (int j=0; j<2; j++){
                System.out.println(fine[i][j]);
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
        System.out.println(pery);

        // Пример редактирования заказа
        pery.edit("21.11.2121");
        System.out.println(pery);



        // Пример редактирования штрафа
        fine[0][0].edit();
        System.out.println(fine[0][0]);
    }
}