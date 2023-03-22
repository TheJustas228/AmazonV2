import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Map;

public class BookScraper {

    public static void main(String[] args) {
        String url = "https://www.knygos.lt/lt/knygos/zanras/publicistika/";

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

//        System.out.print(doc.toString());

        Elements books = doc.select(".products-holder .product");

        for (Element book : books) {

            Element bookLink = book.select("a").first();

            String bookUrl = bookLink.attr("href");
            System.out.println(bookUrl.trim()); // veikia, outputtina knygu linkus visus

            Document bookDoc = Jsoup.connect(bookUrl).get();

//            System.out.println(bookDoc.toString()); // veikia outputtina kiekvienos knygos linko vidu

            String title = bookDoc.select("h1 span[itemprop=name]").text().trim();

            System.out.println(title); // veikia, outputins visu knygu pavadinimus puslayje

            String bookCode = bookDoc.select(".about-product li").get(4).select("span[itemprop=isbn gtin13 sku]").text().trim();

            System.out.println(bookCode); // check 6

        }
        return null;
    }
}