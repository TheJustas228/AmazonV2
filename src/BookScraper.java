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

            String bookNoAuthor = "";

            System.out.println(title); // veikia, outputins visu knygu pavadinimus puslayje
            for (int i = 0; i<8; i++) {
                String bookPublisher = bookDoc.select(".about-product li").get(i).select("span[itemprop=name]").text().trim();
                if (bookPublisher.length() > 1) {
                        System.out.println(bookPublisher);
                        bookNoAuthor =bookPublisher;
                        break;
                    }
                }
            for (int i = 0; i<8; i++) {
                //<a href="https://www.knygos.lt/lt/knygos/autorius/rytis-zemkauskas/">Rytis Zemkauskas</a>
                String bookAuthor = bookDoc.select(".about-product li").get(i).select("a[href]").text().trim();
                if (bookAuthor.length() > 1) {
                    if(bookNoAuthor.equals(bookAuthor)){
                        System.out.println("Nėra autoriaus");
                        break;
                    }else {
                        System.out.println(bookAuthor);
                        break;
                    }
                }
            }
            for (int i = 0; i<8; i++){
                String bookPages = bookDoc.select(".about-product li").get(i).select("span[itemprop=numberOfPages]").text().trim();
                if(bookPages.length() > 1) {
                    System.out.println(bookPages + " puslapiai");
                    break;
                }
            }
            for (int i = 0; i<8; i++){
                String bookCode = bookDoc.select(".about-product li").get(i).select("span[itemprop=isbn gtin13 sku]").text().trim();
                if(bookCode.length() > 5) {
                    System.out.println(bookCode);
                    break;
                }
            }
        }
        return null;
    }
}