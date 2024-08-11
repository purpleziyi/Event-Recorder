package cc.ziyi.mapper;

import cc.ziyi.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper 
public interface ArticleMapper {
    // new article add
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    // help-method of list(): query the total number of records
    @Select("select count(*) from article " +
            "where create_user=#{userId} and category_id = #{categoryId} and state = #{state}")
    long count(Integer userId, Integer categoryId, String state);

    // set dynamic sql in resource-package mapper
    List<Article> list(Integer userId, Integer categoryId, String state, int startIndex, Integer pageSize);

    //delete
    @Delete("delete from article where id=#{id}")
    void deleteById(Integer id);

    // article details
    @Select("select * from article where id = #{id}")
    Article findById(Integer id);

    // update article
    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId}, update_time=#{updateTime} where id=#{id}")
    void update(Article article);
}
