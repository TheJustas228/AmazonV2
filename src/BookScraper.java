import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class BookScraper {
    public static int bookCodeLast;
    public static int bookPagesLast;
    public static String authorLast;
    public static String publisherLast;
    public static String bookNameLast;
    public static String bookUrlLast;

    public static void main(String[] args) {
        String url = "https://www.knygos.lt/lt/knygos/zanras/grozine-literatura/";

        try {
            Map<String, String> bookDetails = scrapeBook(url);

            if (bookDetails != null) {
                System.out.println(bookDetails);
            } else {
                System.out.println("Book not found.");
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static Map<String, String> scrapeBook(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        ArrayList<Properties2> booksCollection = new ArrayList<Properties2>();


        Elements books = doc.select(".products-holder .product");

        for (Element book : books) {

            Element bookLink = book.select("a").first();

            String bookUrl = bookLink.attr("href");
            //System.out.println(bookUrl.trim()); // veikia, outputtina knygu linkus visus

            bookUrlLast = bookUrl.trim();

            Document bookDoc = Jsoup.connect(bookUrl).get();
//            System.out.println(bookDoc.toString()); // veikia outputtina kiekvienos knygos linko vidu

            String title = bookDoc.select("h1 span[itemprop=name]").text().trim();

            bookNameLast = title;

            String bookNoAuthor = "";

           // System.out.println(title); // veikia, outputins visu knygu pavadinimus puslayje
            for (int i = 0; i < 8; i++) {
                String bookPublisher = bookDoc.select(".about-product li").get(i).select("span[itemprop=name]").text().trim();
                if (bookPublisher.length() > 1) {
                    // System.out.println(bookPublisher);
                    bookNoAuthor = bookPublisher;
                    publisherLast = bookPublisher;
                    break;
                }
            }
            for (int i = 0; i < 8; i++) {
                String bookAuthor = bookDoc.select(".about-product li").get(i).select("a[href]").text().trim();
                if (bookAuthor.length() > 1) {
                    if (bookNoAuthor.equals(bookAuthor)) {
                        // System.out.println("Nėra autoriaus");
                        authorLast = ("Nėra autoriaus");
                        break;
                    } else {
                        // System.out.println(bookAuthor);
                        authorLast = bookAuthor;
                        break;
                    }
                }
            }
            for (int i = 0; i < 8; i++) {
                String bookPages = bookDoc.select(".about-product li").get(i).select("span[itemprop=numberOfPages]").text().trim();
                if (bookPages.length() > 1) {
                    //System.out.println(bookPages + " puslapiai");
                    int convertedBP = Integer.parseInt(bookPages);
                    bookPagesLast = convertedBP;
                    break;
                }
            }
            for (int i = 0; i < 8; i++) {
                String bookCode = bookDoc.select(".about-product li").get(i).select("span[itemprop=isbn gtin13 sku]").text().trim();
                if (bookCode.length() > 5) {
                    //System.out.println(bookCode);
                    try {
                        int convertedBC = Integer.parseInt(bookCode);
                        bookCodeLast = convertedBC;
                    } catch (NumberFormatException e) {
                        bookCodeLast = -1;
                    }
                    break;
                }
            }
            booksCollection.add(new Properties2(bookNameLast, bookCodeLast, bookPagesLast, publisherLast));
        }
//        boolean swap = true; //sorts the collection by page count
//        while (swap) {
//            swap = false;
//            for (int i = 0; i < booksCollection.size() - 1; i++) {
//
//                if (booksCollection.get(i).getPageCount() > booksCollection.get(i + 1).getPageCount()) {
//                    swap = true;
//                    Properties2 temp = booksCollection.get(i);
//                    booksCollection.set(i, booksCollection.get(i+1));
//                    booksCollection.set(i+1, temp);
//                }
//            }
//
//        }
        boolean swap = true; //sorts the collection by name
        while (swap) {
            swap = false;
            for (int i = 0; i < booksCollection.size() - 1; i++) {

                if (booksCollection.get(i).getName().compareTo(booksCollection.get(i+1).getName()) >0) {
                    swap = true;
                    Properties2 temp = booksCollection.get(i);
                    booksCollection.set(i, booksCollection.get(i+1));
                    booksCollection.set(i+1, temp);
                }
            }
        }

        for (int i = 0; i < booksCollection.size() - 1; i++) {
            System.out.println(booksCollection.get(i).getName());
            System.out.println(booksCollection.get(i).getPageCount());
        }
        return null;
    }
}
