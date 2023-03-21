import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookScraper {

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

            String bookUrl = booklink.attr("href");
//            System.out.println(bookUrl.toString().trim()); // veikia, outputtina knygu linkus visus

            Document bookDoc = Jsoup.connect(bookUrl).get();

//            System.out.println(bookDoc.toString()); // veikia outputtina kiekvienos knygos linko vidu

            String title = bookDoc.select("h1 span[itemprop=name]").text().trim();

//            System.out.println(title.toString()); // veikia, outputins visu knygu pavadinimus puslayje

            if (title.equalsIgnoreCase(targetTitle)) {
//            if(false) {
                Elements descriptionKeys = bookDoc.select(".Description-key-2Yw");
                Elements descriptionValues = bookDoc.select(".Description-value-2Yw");

                Element bookCode = bookDoc.select(".about-product li").get(4);

                System.out.println(bookCode); // check 6

                Map<String, String> bookDetails = new HashMap<>();

                bookDetails.put("title", title);

                for (int i = 0; i < descriptionKeys.size(); i++) {
                    String key = descriptionKeys.get(i).text().trim();
                    String value = descriptionValues.get(i).text().trim();
                    bookDetails.put(key, value);
                }

                return bookDetails;
            }
//            break;
        }

        return null;
    }
}