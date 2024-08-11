package cc.ziyi.controller;

import cc.ziyi.pojo.Category;
import cc.ziyi.pojo.Result;
import cc.ziyi.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping  // path:/category;  method:POST; param: application/json
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping  // path:/category;  method: GET;  no param
    public Result<List<Category>> list(){  // class Result<T>
        List<Category> cs = categoryService.list();
        return Result.success(cs);   // response List<Category>
    }

    @GetMapping("/detail")  // path:/category/detail;  method:GET;  request sample: id=6 (queryString)
    public Result<Category> detail(Integer id){
        Category c = categoryService.findById(id);
        return Result.success(c);    // response Category-object
    }

    @PutMapping  // path:/category;  method: PUT;  param: application/json
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping // path:/category; method: DELETE;  request sample: id=6 (queryString)
    public Result delete(Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }

}
