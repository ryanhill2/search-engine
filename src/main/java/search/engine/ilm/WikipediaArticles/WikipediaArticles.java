package search.engine.ilm.WikipediaArticles;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class WikipediaArticles {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Getter @Setter
    public String title;
    @Getter @Setter
    @Column(columnDefinition="text")
    public String content;
}
