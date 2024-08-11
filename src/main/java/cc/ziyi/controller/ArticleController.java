package cc.ziyi.controller;


import cc.ziyi.pojo.Article;
import cc.ziyi.pojo.PageBean;
import cc.ziyi.pojo.Result;
import cc.ziyi.service.ArticleService;
import cc.ziyi.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // new article add
    @PostMapping   // path: /article; method: POST; param: application/json
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping  // path: /article; method:GET; request sample:pageNum=1&pageSize=3&categoryId=2&state=Draft
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb =  articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);  // PageBean contains member variables : "total" and "items"
    }

    @PutMapping  // path: /article; method:PUT; param: application/json
    public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping  // path:/article; method:DELETE; request sample: id=4
    public Result delete(Integer id){
        articleService.deleteById(id);
        return Result.success();
    }

    // get details of article
    @GetMapping("/detail")   // path:/article/detail; method:GET; request sample: id=4
    public Result<Article> detail(Integer id){
        Article a = articleService.findById(id);
        return Result.success(a);  // data-field in response is Article-object
    }

}
