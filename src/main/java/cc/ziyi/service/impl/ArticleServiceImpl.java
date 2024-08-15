package cc.ziyi.service.impl;

import cc.ziyi.mapper.ArticleMapper;
import cc.ziyi.pojo.Article;
import cc.ziyi.pojo.PageBean;
import cc.ziyi.service.ArticleService;
import cc.ziyi.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        // complement attribute value
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id"); // get userId from ThreadLocal
        article.setCreateUser(userId);   // set creator

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // create PageBean-obj for encapsulate data
        PageBean<Article> pb = new PageBean<>();

        // start paging query  PageHelper
//        PageHelper.startPage(pageNum,pageSize);

        /** use pagination to optimize system */
        int startIndex = (pageNum - 1) * pageSize;  // the start position of the data of the current page in the DB

        // Call mapper to query the total number of records
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        long totalCount = articleMapper.count(userId, categoryId, state);

        // Call mapper to query the data of the current page
        List<Article> as = articleMapper.list(userId, categoryId, state, startIndex, pageSize);

        // make PageBean object properties
        pb.setTotal(totalCount);
        pb.setItems(as);
        return pb;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now()); // complement updateTime-attribute
        articleMapper.update(article);
    }

    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }


    // get details of article
    @Override
    public Article findById(Integer id) {
        Article a = articleMapper.findById(id);
        return a;
    }


}
