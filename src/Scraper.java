import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Scraper {

    public static void main(String[] args) {
        String url = "https://www.knygos.lt/lt/knygos/zanras/publicistika/";
        String targetTitle = "Baimės metai";

        try {
            Map<String, String> bookDetails = scrapeBook(url, targetTitle);

            if (bookDetails != null) {
                System.out.println(bookDetails);
            } else {
                System.out.println("Book not found.");
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static Map<String, String> scrapeBook(String url, String targetTitle) throws IOException {
        Document doc = Jsoup.connect(url).get();

//        System.out.print(doc.toString());

        Elements books = doc.select(".products-holder .product");

        for (Element book : books) {

            Element booklink = book.select("a").first();

//            String bookUrl = booklink.absUrl("href");
            String bookUrl = booklink.attr("href");
//            System.out.println(bookUrl.toString().trim()); // veikia, spausdina url visu knygu puslapyje esanciu

            Document bookDoc = Jsoup.connect(bookUrl).get();

//            System.out.println(bookDoc.toString()); // veikia, spausdina kiekvienos knygos visa html

            String title = bookDoc.select("h1 span[itemprop=name]").text().trim();

//            System.out.println(title.toString()); // veikia spausdina pavadinimus knygu
            Elements books1 = doc.select(".about-product li");

            for (Element bookCheck : books1) {

                String bookCheck1 = bookCheck.select("strong").text().trim();
                String bookName1 = bookDoc.select(".book-title h1 span[itemprop=name]").text().trim();
                System.out.println(bookName1);
                System.out.println(bookCheck1);

            if(bookCheck1.equals("ISBN:") || bookCheck1.equals("EAN-13:")) {
               String bookCode = bookCheck.select("span[itemprop=isbn gtin13 sku]").text().trim();
                System.out.println(bookCode); // Prints out selected book ISBN, the code.
                System.out.println(bookName1);
            }
//                String bookCode1 = bookDoc.select(".about-product li").get(5).select("span[itemprop=isbn gtin13 sku]").text().trim();
//                System.out.println(bookCode1); // Prints out selected book ISBN, the code.
//                System.out.println(bookName1); //veikia isspausdina ieskomos knygos padavinima
//            } else {
//                String bookCode1 = bookDoc.select(".about-product li").get(4).select("span[itemprop=isbn gtin13 sku]").text().trim();
//                System.out.println(bookCode1); // Prints out selected book ISBN, the code.
//                System.out.println(bookName1); //veikia isspausdina ieskomos knygos padavinima
//            }
            }
        }
        return null;
    }

}