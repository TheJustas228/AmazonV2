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
}