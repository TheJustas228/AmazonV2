import java.util.Collection;
import java.util.Properties;

public class Properties2 {
    private String name;
    private int barcode;
    private int pageCount;
    private String publisher;

    Properties2(String name, int barcode, int pageCount, String publisher) {
        this.name = name;
        this.barcode = barcode;
        this.pageCount = pageCount;
        this.publisher = publisher;
    }

    public int getBarcode() {
        return barcode;
    }
    public int getPageCount() {
        return pageCount;
    }
    public String getName() {
        return name;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
//    public static void sortPageCount(ArrayList<Properties2> lol){
//        boolean swap = true; //sorts the collection by page count
//        while (swap) {
//            swap = false;
//            for (int i = 0; i < lol.size() - 1; i++) {
//
//                if (lol.get(i).getPageCount() > lol.get(i + 1).getPageCount()) {
//                    swap = true;
//                    Properties2 temp = lol.get(i);
//                    lol.set(i, lol.get(i+1));
//                    lol.set(i+1, temp);
//                }
//            }
//
//        }
//    }
}