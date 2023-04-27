package com.zhaoxiao.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zhaoxiao.entity.word.Book;
import com.zhaoxiao.entity.word.LearnedNum;
import com.zhaoxiao.entity.word.Proficiency;
import com.zhaoxiao.entity.word.WordRecord;
import com.zhaoxiao.mapper.WordMapper;
import com.zhaoxiao.model.word.BookMore;
import com.zhaoxiao.model.word.Word;
import com.zhaoxiao.model.word.WordSimple;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WordService {
    @Autowired
    private WordMapper wordMapper;

    private static List<WordSimple> wordSimpleList;
    private static String bookId;

    @SneakyThrows
    @Deprecated
    public List<Word> getWordList() {
        File file = ResourceUtils.getFile("src/main/resources/word/CET4luan_1_update.json");
        String json = FileUtils.readFileToString(file, "UTF-8");
        return JSON.parseArray(json, Word.class);
    }

    public List<WordSimple> getWordSimpleList(String bookIdS){
//        List<WordSimple> wordSimpleList = new ArrayList<>();
        if (wordSimpleList != null && wordSimpleList.size()!=0 && bookIdS.equals(bookId)){
            return wordSimpleList;
        }
        bookId = bookIdS;
        wordSimpleList = new ArrayList<>();
        String path = getClass().getClassLoader().getResource("word/"+bookIdS+"_update.json").getPath();
        String s = readJsonFile(path);
        JSONArray words = JSON.parseArray(s);
        for (Object wordObject : words) {
            JSONObject wordJSON = (JSONObject) wordObject;
            String bookId = wordJSON.getString("bookId");
            JSONObject content = wordJSON.getJSONObject("content");
            JSONObject word = content.getJSONObject("word");
            String wordHead = word.getString("wordHead");
            String wordId = word.getString("wordId");

            JSONObject content2 = word.getJSONObject("content");

            String picture = content2.getString("picture");
            String ukphone = content2.getString("ukphone");
            String ukspeech = content2.getString("ukspeech");
            String usphone = content2.getString("usphone");
            String usspeech = content2.getString("usspeech");

            JSONObject remMethodJSON = content2.getJSONObject("remMethod");
            String remMethod = "";
            if (remMethodJSON != null) {
                remMethod = remMethodJSON.getString("val");
            }

            JSONObject sentence = content2.getJSONObject("sentence");
            List<WordSimple.Sentence> sentences = new ArrayList<>();
            if (sentence != null){
                JSONArray sentencesJSON = sentence.getJSONArray("sentences");
                sentences = sentencesJSON.toJavaList(WordSimple.Sentence.class);
            }

            JSONObject syno = content2.getJSONObject("syno");
            List<WordSimple.Syno> synos = new ArrayList<>();
            if (syno != null) {
                JSONArray synosJSON = syno.getJSONArray("synos");
                synos = synosJSON.toJavaList(WordSimple.Syno.class);
            }

            JSONObject phrase = content2.getJSONObject("phrase");
            List<WordSimple.Phrase> phrases = new ArrayList<>();
            if (phrase != null){
                JSONArray phrasesJSON = phrase.getJSONArray("phrases");
                phrases = phrasesJSON.toJavaList(WordSimple.Phrase.class);
            }

            JSONObject relWord = content2.getJSONObject("relWord");
            List<WordSimple.RelWord> rels = new ArrayList<>();
            if (relWord != null) {
                JSONArray relsJSON = relWord.getJSONArray("rels");
                rels = relsJSON.toJavaList(WordSimple.RelWord.class);
            }

            JSONArray transJSON = content2.getJSONArray("trans");
            List<WordSimple.Tran> trans = transJSON.toJavaList(WordSimple.Tran.class);

            WordSimple wordSimple = new WordSimple(wordHead,wordId,bookId,picture,remMethod,ukphone,ukspeech,usphone,usspeech,
                    sentences,synos,phrases,rels,trans);
            wordSimpleList.add(wordSimple);
        }

//        JSONObject jobj = JSON.parseObject(s);
//        JSONArray features = jobj.getJSONArray("features");//构建JSONArray数组
//        for (int i = 0; i < features.size(); i++) {
//            JSONObject key = (JSONObject) features.get(i);
//            JSONObject geometry = key.getJSONObject("geometry");
//            JSONArray coordinates = geometry.getJSONArray("coordinates");
//            BigDecimal jingdu = coordinates.getBigDecimal(0);
//            BigDecimal weidu = coordinates.getBigDecimal(1);
//            JSONObject properties = key.getJSONObject("properties");
//            String name = (String) properties.getString("NAME");
//            System.out.println(name);
//        }

        return wordSimpleList;
    }

    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<WordSimple> getReviewList(String account, int num, String bookId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = sdf.format(new Date());
        String startTime = currentTime+" 00:00:00";
        String endTime = currentTime+" 23:59:59";
        List<String> reviewWordIdList = wordMapper.getReviewWordIdList(account,startTime,endTime,bookId);
        List<WordSimple> all = getWordSimpleList(bookId);
        List<WordSimple> reviewList = new ArrayList<>();
//        for (String s : reviewWordIdList) {
//            for (WordSimple wordSimple : all) {
//                if (wordSimple.getWordId().equals(s)){
//                    reviewList.add(wordSimple);
//                }
//            }
//        }
        for (WordSimple wordSimple : all) {
            if (reviewWordIdList.contains(wordSimple.getWordId())){
                reviewList.add(wordSimple);
                if (reviewList.size()>=num) break;
            }
        }
        //设置收藏
        for (WordSimple wordSimple : reviewList) {
            wordSimple.setCollect(wordMapper.getCollect(account, wordSimple.getWordId()) != null);
        }
        return reviewList;
    }

    public List<WordSimple> getLearnList(String account, int num, String bookId) {
        List<String> learnedWordIdList = wordMapper.getLearnedWordIdList(account,bookId);
        List<WordSimple> all = getWordSimpleList(bookId);
        List<WordSimple> learnList = new ArrayList<>();
        for (WordSimple wordSimple : all) {
            if (!learnedWordIdList.contains(wordSimple.getWordId())){
                learnList.add(wordSimple);
                if (learnList.size()>=num) break;
            }
        }
        //设置收藏
        for (WordSimple wordSimple : learnList) {
            wordSimple.setCollect(wordMapper.getCollect(account, wordSimple.getWordId()) != null);
        }
        return learnList;
    }

    public Map<String, Integer> getNeedNum(String account, String bookId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = sdf.format(new Date());
        String startTime = currentTime+" 00:00:00";
        String endTime = currentTime+" 23:59:59";
        List<String> reviewWordIdList = wordMapper.getReviewWordIdList(account,startTime,endTime,bookId);
        int reviewNum = reviewWordIdList.size();

        List<String> learnedWordIdList = wordMapper.getLearnedWordIdList(account,bookId);
        int totalNum = 0;
        if (wordMapper.getTotalNum(bookId)!=null){
            totalNum = wordMapper.getTotalNum(bookId);
        }
//        int learnNum = getWordSimpleList(bookId).size() - learnedWordIdList.size();
        int learnNum = totalNum - learnedWordIdList.size();

        Map<String, Integer> map = new HashMap<>();
        map.put("reviewNum", reviewNum);
        map.put("learnNum", learnNum);
        return map;
    }

    public Map<String,Integer> addWordRecord(String account, String wordId, int type, String bookId) {
        Map<String,Integer> map = new HashMap<>();

        Proficiency proficiencyEntity = wordMapper.getWordRecord(account,wordId);
        int proficiency;
        if (proficiencyEntity==null){
//            proficiency = Math.max(type, 0);
            proficiency = Math.max(type + 1, 1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);
            map.put("proficiency",proficiency);
            map.put("nextDayNum",1);
            proficiencyEntity = new Proficiency(account,wordId,proficiency,calendar.getTime(), bookId);
            wordMapper.addProficiency(proficiencyEntity);

            WordRecord wordRecord = new WordRecord();
            wordRecord.setUserAccount(account);
            wordRecord.setWordId(wordId);
            wordRecord.setType(0);
            wordRecord.setBookId(bookId);
            wordMapper.addWordRecord(wordRecord);
        } else {
            proficiency = proficiencyEntity.getProficiency();
            proficiency += type;
            if (proficiency < 1) {
                proficiency = 1;
            } else if (proficiency > 7) {
                proficiency = 7;
            }
            map.put("proficiency",proficiency);
            proficiencyEntity.setProficiency(proficiency);

//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(wordRecord.getNextTime());
//            calendar.add(Calendar.DATE, 1);

//            proficiencyEntity.setNextTime(getNextTime(proficiency,proficiencyEntity.getNextTime()));
            //应该在今天的基础上加
            proficiencyEntity.setNextTime(getNextTime(proficiency,new Date(),map));

            wordMapper.setProficiency(proficiencyEntity);

            WordRecord wordRecord = new WordRecord();
            wordRecord.setUserAccount(account);
            wordRecord.setWordId(wordId);
            wordRecord.setType(1);
            wordRecord.setBookId(bookId);
            wordMapper.addWordRecord(wordRecord);
        }
        return map;
    }

    private Date getNextTime(int proficiency, Date previous, Map<String,Integer> map){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(previous);
        switch (proficiency){
            case 1:
            case 2:
                calendar.add(Calendar.DATE, 1);
                map.put("nextDayNum",1);
                return calendar.getTime();
            case 3:
                calendar.add(Calendar.DATE, 2);
                map.put("nextDayNum",2);
                return calendar.getTime();
            case 4:
                calendar.add(Calendar.DATE, 4);
                map.put("nextDayNum",4);
                return calendar.getTime();
            case 5:
                calendar.add(Calendar.DATE, 7);
                map.put("nextDayNum",7);
                return calendar.getTime();
            case 6:
                calendar.add(Calendar.DATE, 15);
                map.put("nextDayNum",15);
                return calendar.getTime();
            case 7:
                return new Date();
        }
        return new Date();
    }

    public Book getBook(String bookId) {
        Book book = wordMapper.getBook(bookId);
//        Map<String, Integer> needNum = getNeedNum(account, bookId);
//        book.setReviewNum(needNum.get("reviewNum"));
//        book.setLearnNum(needNum.get("learnNum"));
        return book;
    }

    public List<Book> getBookList(String type) {
        String typeS = "";
        if (!type.equals("全部")){
            typeS = "where type='"+type+"'";
        }
        return wordMapper.getBookList(typeS);
    }

    public LearnedNum getLearnedNum(String account, String day) {
        return wordMapper.getLearnedNum(account,day);
    }

    public BookMore getBookMore(String account, String bookId) {
        Book book = getBook(bookId);

        Map<String, Integer> needNum = getNeedNum(account, bookId);
        int reviewNum = needNum.get("reviewNum");
        int learnNum = needNum.get("learnNum");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = sdf.format(new Date());
        LearnedNum learnedNum = getLearnedNum(account,currentTime);

        return new BookMore(book,reviewNum,learnNum,learnedNum);
    }

    public Date getNewDate() {
        Calendar calendar = Calendar.getInstance();
        Date dateNow = new Date();
        calendar.setTime(dateNow);
        calendar.add(Calendar.DATE, 1);
        Date dateNext = calendar.getTime();
        return dateNext;
    }

    public List<WordSimple> getWordSimplePageInfo(String account, String bookId) {
        List<String> learnedWordIdList = wordMapper.getWordSimplePageInfo(bookId);
        List<WordSimple> all = getWordSimpleList(bookId);
        List<WordSimple> pageInfo = new ArrayList<>();
        for (WordSimple wordSimple : all) {
            if (learnedWordIdList.contains(wordSimple.getWordId())){
                pageInfo.add(wordSimple);
            }
        }
        //设置收藏
        for (WordSimple wordSimple : pageInfo) {
            wordSimple.setCollect(wordMapper.getCollect(account, wordSimple.getWordId()) != null);
        }
        return pageInfo;
    }

    public List<WordSimple> getCollectionList(String account,String bookId) {
        List<String> learnedWordIdList = wordMapper.getCollectionList(account,bookId);
        List<WordSimple> all = getWordSimpleList(bookId);
        List<WordSimple> pageInfo = new ArrayList<>();
        for (WordSimple wordSimple : all) {
            if (learnedWordIdList.contains(wordSimple.getWordId())){
                pageInfo.add(wordSimple);
            }
        }
        //设置收藏
        for (WordSimple wordSimple : pageInfo) {
            wordSimple.setCollect(wordMapper.getCollect(account, wordSimple.getWordId()) != null);
        }
        return pageInfo;
    }

    public boolean collect(String account, String wordId,String bookId, boolean collect) {
        if(collect){
            if(wordMapper.getCollect(account,wordId)==null) {
                return wordMapper.addCollect(account, wordId, bookId);
            } else {
                return true;
            }
        } else {
            if(wordMapper.getCollect(account,wordId)!=null) {
                return wordMapper.removeCollect(account, wordId);
            } else {
                return true;
            }
        }
    }

    public List<WordSimple> getHistoryList(String account,String bookId) {
        List<WordSimple> learnedWordIdList = wordMapper.getHistoryList(account,bookId);
        List<WordSimple> all = getWordSimpleList(bookId);
        List<WordSimple> pageInfo = new ArrayList<>();
        for (WordSimple wordSimple : learnedWordIdList) {
            for (WordSimple simple : all) {
                if (wordSimple.getWordId().equals(simple.getWordId())){
//                    Date historyTime = wordSimple.getHistoryTime();
//                    wordSimple = simple;
//                    wordSimple.setHistoryTime(historyTime);

//                    wordSimple.setWordHead(wordSimple.getWordHead());
//                    wordSimple.setBookId(wordSimple.getBookId());
//                    wordSimple.setPicture(wordSimple.getPicture());

                    simple.setHistoryTime(wordSimple.getHistoryTime());
                    pageInfo.add(simple);
                }
            }
        }
//        for (WordSimple wordSimple : all) {
//            if (learnedWordIdList.contains(wordSimple.getWordId())){
//                learnedWordIdList.add(wordSimple);
//            }
//        }
        //设置收藏
        for (WordSimple wordSimple : pageInfo) {
            wordSimple.setCollect(wordMapper.getCollect(account, wordSimple.getWordId()) != null);
//            wordSimple.setHistoryTime(wordMapper.getHistoryTime(account, wordSimple.getWordId()));
        }
        return pageInfo;
    }
}
