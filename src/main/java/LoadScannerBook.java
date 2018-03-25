import java.util.Scanner;

public class LoadScannerBook {
    private static Scanner input = new Scanner(System.in);

    public static void loadLibrarySave(){

        System.out.println("Podaj nazwe ksiazki: ");
        String title = input.nextLine();

        System.out.println("Podaj autora ksiazki: ");
        String author = input.nextLine();

        System.out.println("Rok wydania ksiazki: ");
        int year = input.nextInt();

        System.out.println("Nr isbn: ");
        long isbn = input.nextLong();
        input.nextLine();

      Books book = new Books(title,author,year,isbn);
      BooksDao booksDao = new BooksDao();
      booksDao.librarySave(book);
      booksDao.close();
    }

    public static void LoadLibraryRead(){
        System.out.println("Podaj nr isbn ktróry chcesz wyświetlić:");
        long isbn = input.nextLong();
        input.nextLine();
        BooksDao booksDao = new BooksDao();
        Books books = booksDao.libraryRead(isbn);
        System.out.println(books);
        booksDao.close();
    }

    public static void loadLibraryUpdate(){
        System.out.println("Jaki nr isbn zaaktualizować");
        long isbn = input.nextLong();
        input.nextLine();
        BooksDao booksDao = new BooksDao();
        Books books = booksDao.libraryRead(isbn);

        System.out.println("Podaj tytuł ksiazki: ");
        books.setTitle(input.nextLine());

        System.out.println("Podaj authora ksiazki: ");
        books.setAuthor(input.nextLine());

        System.out.println("Rok wydania: ");
        books.setYear(input.nextInt());
        input.nextLine();
        booksDao.libraryUpdate(books);
        booksDao.close();
    }

    public static void loadLibraryDelete(){
        System.out.println("Podaj id ktore usunać:");
        long id = input.nextInt();
        input.nextLine();
        BooksDao booksDao = new BooksDao();
        booksDao.libraryDelete(id);
        booksDao.close();
    }
}
