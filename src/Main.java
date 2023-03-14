import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Properties2> Books = new ArrayList<Properties2>();
        Properties2 Book1 = new Properties2("C00l b00k", 123456789, 299, "Alma littera");
        Properties2 Book2 = new Properties2("C11l b11k", 567891223, 333, "Alma littera");
        Properties2 Book3 = new Properties2("C22l b22k", 689540348, 312, "Alma littera");
        Properties2 Book4 = new Properties2("C33l b33k", 785912501, 777, "Alma littera");
        Properties2 Book5 = new Properties2("C44l b44k", 758129804, 666, "Alma littera");

        Books.add(Book1);
        Books.add(Book2);
        Books.add(Book3);
        Books.add(Book4);
        Books.add(Book5);
//        for(int i = 0; i < 5; i++){  //Print out all books in system.
//            System.out.print(Books.get(i).getName() + "; ");
//            System.out.print(Books.get(i).getBarcode() + "; ");
//            System.out.print(Books.get(i).getPageCount() + "; ");
//            System.out.print(Books.get(i).getPublisher() + "; ");
//            System.out.println("");
//        }
        int z = 1;
        while(z!=0) {
            System.out.println("What book do you want to buy?");
            System.out.println("Do you want to search by barcode or name?(If you want to exit enter exit)");
            Scanner choice = new Scanner(System.in);
            String Choice = choice.nextLine();
            if (Choice.equalsIgnoreCase("barcode")) {
                System.out.println("Enter barcode of the book (9 character code):");
                Scanner barcode = new Scanner(System.in);
                int barcode1 = barcode.nextInt();
                Iterator<Properties2> lol = Books.iterator();
                while(lol.hasNext()){
                    Properties2 item = lol.next();
                    if(barcode1 == item.getBarcode()){
                        System.out.println(item.getName() + "; ");
                        System.out.println(item.getBarcode() + "; ");
                        System.out.println(item.getPageCount() + "; ");
                        System.out.println(item.getPublisher() + "; ");
                        System.out.println("");
                        break;
                    }
                }
            } else if (Choice.equalsIgnoreCase("name")) {
                System.out.println("Enter name of the book:");
                Scanner name = new Scanner(System.in);
                String Name = name.nextLine();
                Iterator<Properties2> lol = Books.iterator();
                while (lol.hasNext()) {
                    Properties2 item = lol.next();
                    if (Name.equalsIgnoreCase(item.getName())){
                        System.out.println(item.getName() + "; ");
                        System.out.println(item.getBarcode() + "; ");
                        System.out.println(item.getPageCount() + "; ");
                        System.out.println(item.getPublisher() + "; ");
                        System.out.println("");
                    }
                }
            }else if (Choice.equalsIgnoreCase("exit")) {
                z=0;
            }else{
                System.out.println("You searched something else, try again...(Try writing either name or barcode or exit)");
            }
        }
    }
}