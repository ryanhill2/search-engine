package search.engine.ilm.WikipediaArticles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/WikipediaArticles")
public class WikipediaArticlesController {
    @Autowired WikipediaArticlesService wikipediaArticlesService;

    @CrossOrigin
    @GetMapping()
    public List<WikipediaArticles> findWikipediaArticles() {
        return wikipediaArticlesService.findWikipediaArticles();
    }

    @CrossOrigin
    @GetMapping(value = "/{name}")
    public List<List>  findWikipediaArticlesByName(@PathVariable String name) {
        return wikipediaArticlesService.findWikipediaArticlesByName(name);
    }

    @PostMapping()
    public String createWikipediaArticle(@RequestBody WikipediaArticles wikipediaArticles) {
        return wikipediaArticlesService.createWikipediaArticle(wikipediaArticles);
    }
}
