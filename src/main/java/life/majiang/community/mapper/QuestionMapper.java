package life.majiang.community.mapper;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("Select * from question limit #{offset},#{size}")
    List<Question> List(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("Select count(1) from question")
    Integer count();

    @Select("Select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> ListByUserId(@Param(value = "userId") Integer userId,@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("Select count(1) from question where creator = #{userId} ")
    Integer countByUserId(@Param(value = "userId") Integer id);

    @Select("Select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);
}
