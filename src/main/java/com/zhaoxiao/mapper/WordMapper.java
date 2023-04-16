package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.word.Book;
import com.zhaoxiao.entity.word.LearnedNum;
import com.zhaoxiao.entity.word.Proficiency;
import com.zhaoxiao.entity.word.WordRecord;
import com.zhaoxiao.model.word.BookMore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WordMapper {
    //select * from proficiency where nextTime <= '2023-04-14 23:59:59';
    //select * from proficiency where nextTime between '2023-04-14 00:00:00' and '2023-04-14 23:59:59';
    @Select("select wordId from proficiency where userAccount=#{account} and nextTime <= #{endTime} and proficiency<7 and bookId=#{bookId} order by nextTime")
    List<String> getReviewWordIdList(String account, String startTime, String endTime, String bookId);

    @Select("select wordId from proficiency where userAccount=#{account} and bookId=#{bookId}")
    List<String> getLearnedWordIdList(String account, String bookId);

    @Select("select * from proficiency where userAccount=#{account} and wordId=#{wordId}")
    Proficiency getWordRecord(String account, String wordId);

    @Insert("insert into proficiency(userAccount,wordId,proficiency,nextTime,bookId) values(#{userAccount},#{wordId},#{proficiency},#{nextTime},#{bookId})")
    void addProficiency(Proficiency wordRecord);

    @Update("update proficiency set proficiency=#{proficiency},nextTime=#{nextTime} where userAccount=#{userAccount} and wordId=#{wordId}")
    void setProficiency(Proficiency wordRecord);

    @Insert("insert into wordRecord(userAccount,wordId,type,bookId) values(#{userAccount},#{wordId},#{type},#{bookId})")
    void addWordRecord(WordRecord wordRecord);

    @Select("select num from book where id=#{bookId}")
    Integer getTotalNum(String bookId);

    @Select("select * from book where id=#{bookId}")
    Book getBook(String bookId);

    @Select("select * from book ${type}")
    List<Book> getBookList(String type);

//    @Select("select count(*) from wordRecord where addTime=#{time}")
    @Select("SELECT DATE_FORMAT(addTime,'%Y-%m-%d') day ,count(*) num from wordRecord " +
            "where userAccount=#{account} group by day having day=#{day}")
    LearnedNum getLearnedNum(String account, String day);

    @Select("select wordId from word where bookId=#{bookId}")
    List<String> getWordSimplePageInfo(String bookId);

    @Select("select wordId from wordCollection where userAccount=#{account} and bookId=#{bookId}")
    List<String> getCollectionList(String account,String bookId);

    @Select("select wordId from wordCollection where userAccount=#{account} and wordId=#{wordId}")
    String getCollect(String account, String wordId);

    @Insert("insert into wordCollection(userAccount,wordId,bookId) values(#{account},#{wordId},#{bookId})")
    boolean addCollect(String account, String wordId,String bookId);

    @Delete("delete from wordCollection where userAccount=#{account} and wordId=#{wordId}")
    boolean removeCollect(String account, String wordId);
}
