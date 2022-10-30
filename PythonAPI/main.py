import json
import requests


def get_content_of_articles_given_wiki_name(wiki_name):
    response = requests.get("https://en.wikipedia.org/w/rest.php/v1/page/" + wiki_name)
    return response.content.decode("utf-8")


def get_articles(REQUIRED_ARTICLES):
    for i in range(REQUIRED_ARTICLES):
        articles_with_title_and_content = {}
        response = requests.get("https://en.wikipedia.org/wiki/Special:Random")
        # remove the "https://en.wikipedia.org/wiki/" part
        wiki_name = (response.url[30:])
        wiki_content = get_content_of_articles_given_wiki_name(wiki_name)
        # add wiki_name and wiki_content to the dictionary
        articles_with_title_and_content['title'] = wiki_name
        articles_with_title_and_content['content'] = wiki_content
        print(wiki_name + " Added")

        json_data = json.dumps(articles_with_title_and_content)

        print(json_data)
        url = 'http://localhost:8081/WikipediaArticles/'
        headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
        x = requests.post(url, data=json_data, headers=headers)
        print(x.text)


get_articles(200)

