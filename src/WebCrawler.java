import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Manan on 21-05-2015.
 */
public class WebCrawler {

    protected static final String STARTING_URL_DEFAULT="http://yclist.com";
    protected static final int MAX_LINKS_DEFAULT=10000;
    private String _startingUrl;
    private int _maxLinks;
    private int _currentLinkIndex;
    private static ArrayList<String> _linkRepository = new ArrayList<String>();
    private static ArrayList<String> _badLinkRepository = new ArrayList<String>();
    private static ArrayList<String> _linksTraversed = new ArrayList<String>();

    public void startCrawling(String startingUrl,String maxLinksString) {
        validateStartingURL(startingUrl);
        validateMaxLinks(maxLinksString);
        _linkRepository.add(_startingUrl);
        while (_linkRepository.size()<_maxLinks){
            if(_linkRepository.size()==_currentLinkIndex){
                System.out.println("Repository of Links Exhausted");
                break;
            }
            crawler(_linkRepository.get(_currentLinkIndex));
        }
        System.out.println("Total links added to repository :" +_linkRepository.size());
        System.out.println("Total links which couldn't be parsed :" +_badLinkRepository.size());
        System.out.println("Total links parsed :"+_linksTraversed.size());
    }

    //This function is called for every link it goes through
    private void crawler(String URL) {
        try{
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.select("a");
            for (Element element : elements){
                if(!_linkRepository.contains(element.attr("abs:href"))){
                    if(_linkRepository.size()>=_maxLinks) break;
                    _linkRepository.add(element.attr("abs:href"));
                    if(element.text().contains("career") || element.text().contains("job") || element.text().contains("jobs") || element.text().contains("careers") || element.text().contains("about") || element.text().contains("team")){
                        System.out.println(element.attr("abs:href"));
                    }
                    //System.out.println("Link " + element.attr("abs:href") + " added to the repository ");
                }
            }
            _linksTraversed.add(URL);
            //System.out.println("Link " + URL + " parsed");
            
        }
        catch (Exception e){
            //System.out.println("Link " +URL+ " couldn't be parsed !");
            _badLinkRepository.add(URL);
        }
        finally {
            _currentLinkIndex++;
        }
    }

    //Validating the value of startingURL
    private void validateStartingURL(String startingUrl){
        try{
            Jsoup.connect(startingUrl).get();
            _startingUrl=startingUrl;
        }
        catch (MalformedURLException e){
            _startingUrl=STARTING_URL_DEFAULT;
            System.out.println("Starting URL passed is a malformed URL , so using the default URL "+STARTING_URL_DEFAULT);
        }
        catch (IOException e){
            _startingUrl=STARTING_URL_DEFAULT;
            System.out.println("The connection to the URL cannot be established to the starting URL , so using the default URL "+STARTING_URL_DEFAULT);
        }
        catch (Exception e){
            _startingUrl=STARTING_URL_DEFAULT;
            System.out.println("The connection to the URL cannot be established to the starting URL , so using the default URL "+STARTING_URL_DEFAULT);
        }
    }

    // Validating the value of maxLinks
    private void validateMaxLinks(String maxLinksString){
        try{
            _maxLinks=Integer.parseInt(maxLinksString);
        }
        catch (NumberFormatException numberFormatException){
            _maxLinks=MAX_LINKS_DEFAULT;
            System.out.println("Invalid Value of maximum number of links , taking the default value " +MAX_LINKS_DEFAULT);
        }
        catch (Exception e){
            _maxLinks=MAX_LINKS_DEFAULT;
            System.out.println("Invalid Value of maximum number of links , taking the default value " +MAX_LINKS_DEFAULT);
        }
        if(_maxLinks<=0){
            _maxLinks=MAX_LINKS_DEFAULT;
            System.out.println("Invalid Value of maximum number of links , taking the default value " +MAX_LINKS_DEFAULT );
        }
    }
}