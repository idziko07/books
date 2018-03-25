import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu[] menu = Menu.values();

        int menuNamber = 1;
        do{
            System.out.print("\n");
            menuNamber = 1;
            for (Menu menu1 : menu) {
                System.out.println(menuNamber + ". " + menu1);
                menuNamber++;
            }
            System.out.println("Co chcesz zrobić: ");
            menuNamber = input.nextInt();
            if (menuNamber == 1){
                LoadScannerBook.loadLibrarySave();
            }else if (menuNamber == 2){
                LoadScannerBook.LoadLibraryRead();
            }else if (menuNamber ==3){
                LoadScannerBook.loadLibraryUpdate();
            }else if(menuNamber == 4){
                LoadScannerBook.loadLibraryDelete();
            }else if(menuNamber == 5){
                menuNamber = 5;
            }
        }while (menuNamber != 5);

    }
}
