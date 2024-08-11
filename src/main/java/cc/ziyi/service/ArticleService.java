package cc.ziyi.service;

import cc.ziyi.pojo.Article;
import cc.ziyi.pojo.PageBean;

public interface ArticleService {

    // new article add
    void add(Article article);

    // Conditional paging list query
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // delete article
    void deleteById(Integer id);

    // get details of article
    Article findById(Integer id);

    // update article
    void update(Article article);
}
