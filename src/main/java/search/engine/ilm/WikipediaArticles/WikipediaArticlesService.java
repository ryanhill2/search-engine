package search.engine.ilm.WikipediaArticles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WikipediaArticlesService {
    @Autowired WikipediaArticlesRepository wikipediaArticlesRepository;
    public List<WikipediaArticles> findWikipediaArticles() {
        return wikipediaArticlesRepository.findAll();
    }

    public String createWikipediaArticle(WikipediaArticles wikipediaArticles) {
        wikipediaArticlesRepository.save(wikipediaArticles);
        return "Created Wikipedia Article";
    }
    public List<List>  findWikipediaArticlesByName(String name) {
        List<Map<String, String>> ContentAndTitle = wikipediaArticlesRepository.findWikipediaArticlesByName(name);
       Map<String, Integer> wordInArticleCount = new HashMap<>();
       // count the amount of times the word appears in each contentAndTitle and add it to the map
        for (Map<String, String> contentAndTitle : ContentAndTitle) {
            String content = contentAndTitle.get("content");
            // find how maqny times the word appears in the content
            int count = 0;
            int index = 0;
            while (index != -1) {
                index = content.indexOf(name, index);
                if (index != -1) {
                    count++;
                    index += name.length();
                }
            }
            // add name and count to the map
            wordInArticleCount.put(contentAndTitle.get("title"), count);
        }

        // create method to sort wordInArticleCount by value
        List<List>  sortedWordInArticleCount = sortByIntegerValue(wordInArticleCount);
        return sortedWordInArticleCount;
    }

    private List<List>  sortByIntegerValue(Map<String, Integer> wordInArticleCount) {
        Map<String, Integer>  sortByIntegerValue  = wordInArticleCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        // get all the keys from sortByIntegerValue
        List<String> title = new ArrayList<>(sortByIntegerValue.keySet());
        // get all the values from sortByIntegerValue
        List<Integer> contentOccurrence = new ArrayList<>(sortByIntegerValue.values());
        // add list of titles and list of contentOccurrence to a arraylist
        List<List> titleAndContentOccurrence = new ArrayList<>();
        titleAndContentOccurrence.add(title);
        titleAndContentOccurrence.add(contentOccurrence);
        return titleAndContentOccurrence;
    }
}

