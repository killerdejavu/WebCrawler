import java.util.Scanner;

/**
 * Created by Manan on 21-05-2015.
 */

public class WebCrawlerTester {

    public static void main(String args[]) {

        String URL,maxLinks;
        Scanner scannerObject = new Scanner(System.in);

        System.out.print("Enter the starting URL from where you want to start crawling - ");
        URL=scannerObject.next();
        System.out.print("Enter the value of maximum URLs you want to crawl - ");
        maxLinks=scannerObject.next();

        WebCrawler crawlerObject = new WebCrawler();
        crawlerObject.startCrawling(URL,maxLinks);

    }
}
