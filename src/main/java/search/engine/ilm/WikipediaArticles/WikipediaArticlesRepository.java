package search.engine.ilm.WikipediaArticles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface WikipediaArticlesRepository extends JpaRepository<WikipediaArticles, Long> {
    @Query(value = "select title, content from wikipedia_articles where content like %:name%" , nativeQuery = true )
    List<Map<String, String>> findWikipediaArticlesByName(@Param("name") String name);
}
