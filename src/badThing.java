import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class badThing {

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

            if (title.equalsIgnoreCase(targetTitle)) {
//            if(false) {
                Elements descriptionKeys = bookDoc.select(".Description-key-2Yw");
                Elements descriptionValues = bookDoc.select(".Description-value-2Yw");

                Elements bookCode1 = bookDoc.select(".about-product li").get(4).select("span[itemprop=isbn gtin13 sku]");
                String bookCode = bookCode1.text().trim();
                String bookName1 = bookDoc.select(".book-title h1 span[itemprop=name]").text().trim();

                System.out.println(bookCode); // Prints out selected book ISBN, the code.
                System.out.println(bookName1);
//                Map<String, String> bookDetails = new HashMap<>();
//
//                bookDetails.put("title", title);
//
//                for (int i = 0; i < descriptionKeys.size(); i++) {
//                    String key = descriptionKeys.get(i).text().trim();
//                    String value = descriptionValues.get(i).text().trim();
//                    bookDetails.put(key, value);
//                }
//
//                return bookDetails;
            }
        }

        return null;
    }
}