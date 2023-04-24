package com.zhaoxiao.service.admin;

import com.zhaoxiao.entity.test.*;
import com.zhaoxiao.mapper.admin.AdminTestMapper;
import com.zhaoxiao.mapper.admin.AdminTestMapperNew;
import com.zhaoxiao.model.test.*;
import com.zhaoxiao.util.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AdminTestServiceNew {
    @Autowired
    private AdminTestMapperNew adminTestMapperNew;

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.accessPath}")
    private String accessPath;

    public List<QuestionNew> getQuestionList(int questionBankId, int type) {
        List<QuestionNew> questionNewList = null;
        int i = 1;
        String typeS = "and type=" + type;
        switch (type){
            case 1:
            case 2:
            case 3:
            case 4:

                questionNewList = adminTestMapperNew.getListeningList(questionBankId, typeS);

                for (QuestionNew questionNew : questionNewList) {
                    List<SubQuestionNew> subQuestionNewList = adminTestMapperNew.getListeningQuestionList(questionNew.getId());
                    for (SubQuestionNew subQuestionNew : subQuestionNewList) {
                        subQuestionNew.setOrder(i++);
                    }
                    questionNew.setSubQuestionNewList(subQuestionNewList);
                    questionNew.setSubQuestionNum(subQuestionNewList.size());
                }
                break;
            case 5:
                questionNewList = adminTestMapperNew.getBankedList(questionBankId);

                for (QuestionNew questionNew : questionNewList) {
                    List<SubQuestionNew> subQuestionNewList = adminTestMapperNew.getBankedQuestionList(questionNew.getId());
                    for (SubQuestionNew bankedQuestion : subQuestionNewList) {
                        bankedQuestion.setOrder(i++);
                    }
                    questionNew.setWordList(questionNew.getWord().split(","));
                    questionNew.setSubQuestionNewList(subQuestionNewList);
                    questionNew.setSubQuestionNum(subQuestionNewList.size());
                }
                break;
            case 6:
                questionNewList = adminTestMapperNew.getMatchList(questionBankId);

                for (QuestionNew questionNew : questionNewList) {
                    List<SubQuestionNew> subQuestionNewList = adminTestMapperNew.getMatchQuestionList(questionNew.getId());
                    for (SubQuestionNew subQuestionNew : subQuestionNewList) {
                        subQuestionNew.setOrder(i++);
                    }
                    questionNew.setSubQuestionNewList(subQuestionNewList);
                    questionNew.setSubQuestionNum(subQuestionNewList.size());
                }
                break;
            case 7:
                questionNewList = adminTestMapperNew.getCarefulList(questionBankId);

                for (QuestionNew questionNew : questionNewList) {
                    List<SubQuestionNew> subQuestionNewList = adminTestMapperNew.getCarefulQuestionList(questionNew.getId());
                    for (SubQuestionNew subQuestionNew : subQuestionNewList) {
                        subQuestionNew.setOrder(i++);
                    }
                    questionNew.setSubQuestionNewList(subQuestionNewList);
                    questionNew.setSubQuestionNum(subQuestionNewList.size());
                }
                break;
            case 8:
            case 9:
            case 10:
                questionNewList = adminTestMapperNew.getNewList(questionBankId, typeS);

                for (QuestionNew questionNew : questionNewList) {
                    List<SubQuestionNew> subQuestionNewList = adminTestMapperNew.getNewQuestionList(questionNew.getId());
                    for (SubQuestionNew subQuestionNew : subQuestionNewList) {
                        subQuestionNew.setOrder(i++);
                    }
                    questionNew.setSubQuestionNewList(subQuestionNewList);
                    questionNew.setSubQuestionNum(subQuestionNewList.size());
                }
                break;
            case 11:
            case 12:
                questionNewList = adminTestMapperNew.getTranslationList(questionBankId);

                for (QuestionNew questionNew : questionNewList) {
                    questionNew.setOrder(i++);
                }
                break;
            case 13:
            case 14:
                questionNewList = adminTestMapperNew.getWritingList(questionBankId, typeS);

                for (QuestionNew questionNew : questionNewList) {
                    questionNew.setOrder(i++);
                }
                break;
            case 15:
                questionNewList = adminTestMapperNew.getClozeList(questionBankId);

                for (QuestionNew questionNew : questionNewList) {
                    List<SubQuestionNew> subQuestionNewList = adminTestMapperNew.getClozeQuestionList(questionNew.getId());
                    for (SubQuestionNew subQuestionNew : subQuestionNewList) {
                        subQuestionNew.setOrder(i++);
                    }
                    questionNew.setSubQuestionNewList(subQuestionNewList);
                    questionNew.setSubQuestionNum(subQuestionNewList.size());
                }
                break;
        }
        return questionNewList;
    }

    public List<TestFtype> getTestFtypeList(int questionBankId) {
        List<TestFtype> ftypes = new ArrayList<>();
        TestFtype ftype1,ftype2,ftype3,ftype4,ftype5;
        List<TestStype> stypes1,stypes2,stypes3,stypes4,stypes5;
        int num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,num13;

        switch (questionBankId) {
            case 1:
                stypes1 = new ArrayList<>();

                num1 = adminTestMapperNew.getListeningCount(1, questionBankId);
                stypes1.add(new TestStype(1, "短篇新闻", num1, 1000, 400));

                num2 = adminTestMapperNew.getListeningCount(2, questionBankId);
                stypes1.add(new TestStype(2, "长对话", num2, 500, 400));

                num3 = adminTestMapperNew.getListeningCount(3, questionBankId);
                stypes1.add(new TestStype(3, "听力篇章", num3, 500, 400));

                ftype1 = new TestFtype(1, "听力", stypes1, false, num1+num2+num3, 500, 400);


                stypes2 = new ArrayList<>();

                num5 = adminTestMapperNew.getBankedCount(questionBankId);
                stypes2.add(new TestStype(5, "选词填空", num5, 1000, 400));

                num6 = adminTestMapperNew.getMatchCount(questionBankId);
                stypes2.add(new TestStype(6, "匹配", num6, 500, 400));

                num7 = adminTestMapperNew.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num5+num6+num7, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = adminTestMapperNew.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(11, "汉译英", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = adminTestMapperNew.getWritingCount(0,questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, 1000, 400));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, 500, 400);


                ftypes.add(ftype1);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;

            case 2:
                stypes1 = new ArrayList<>();
                num2 = adminTestMapperNew.getListeningCount(2, questionBankId);
                stypes1.add(new TestStype(2, "长对话", num2, 500, 400));

                num3 = adminTestMapperNew.getListeningCount(3, questionBankId);
                stypes1.add(new TestStype(3, "听力篇章", num3, 500, 400));

                num4 = adminTestMapperNew.getListeningCount(4, questionBankId);
                stypes1.add(new TestStype(4, "讲话/报道/讲座", num4, 1000, 400));


                ftype1 = new TestFtype(1, "听力", stypes1, false, num2+num3+num4, 500, 400);


                stypes2 = new ArrayList<>();

                num5 = adminTestMapperNew.getBankedCount(questionBankId);
                stypes2.add(new TestStype(5, "选词填空", num5, 1000, 400));

                num6 = adminTestMapperNew.getMatchCount(questionBankId);
                stypes2.add(new TestStype(6, "匹配", num6, 500, 400));

                num7 = adminTestMapperNew.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num5+num6+num7, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = adminTestMapperNew.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(11, "汉译英", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = adminTestMapperNew.getWritingCount(0,questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, 1000, 400));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, 500, 400);


                ftypes.add(ftype1);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;

            case 3:
            case 4:
                stypes5 = new ArrayList<>();

                num10 = adminTestMapperNew.getClozeCount(questionBankId);
                stypes5.add(new TestStype(15,"完形填空",num10,500,400));

                ftype5 = new TestFtype(5, "完形填空", stypes5, false, num10, 500, 400);


                stypes2 = new ArrayList<>();

                num7 = adminTestMapperNew.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                num11 = adminTestMapperNew.getNewCount(1,questionBankId);
                stypes2.add(new TestStype(8, "新题型-七选五", num11, 1000, 400));

                num12 = adminTestMapperNew.getNewCount(2,questionBankId);
                stypes2.add(new TestStype(9, "新题型-排序", num12, 1000, 400));

                num13 = adminTestMapperNew.getNewCount(3,questionBankId);
                stypes2.add(new TestStype(10, "新题型-小标题", num13, 1000, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num7+num11+num12+num13, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = adminTestMapperNew.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(12, "英译汉", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = adminTestMapperNew.getWritingCount(1,questionBankId);
                stypes4.add(new TestStype(14, "应用文", num9, 1000, 400));

                num9 = adminTestMapperNew.getWritingCount(2,questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, 1000, 400));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, 500, 400);


                ftypes.add(ftype5);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;


            default:break;
        }
//        ftypes.add(new TestFtype(""));
        return ftypes;
    }

    public boolean addQuestion1(QuestionNew questionNew) {
        switch (questionNew.getType()){
            case 1:
            case 2:
            case 3:
            case 4:
                return adminTestMapperNew.addListening(questionNew);
            case 5:
                return adminTestMapperNew.addBanked(questionNew);
            case 6:
                return adminTestMapperNew.addMatch(questionNew);
            case 7:
                return adminTestMapperNew.addCareful(questionNew);
            case 8:
            case 9:
            case 10:
                return adminTestMapperNew.addNew(questionNew);
            case 11:
            case 12:
                return adminTestMapperNew.addTranslation(questionNew);
            case 13:
            case 14:
                return adminTestMapperNew.addWriting(questionNew);
            case 15:
                return adminTestMapperNew.addCloze(questionNew);
        }
        return false;
    }

    public boolean addSubQuestion(SubQuestionNew subQuestionNew) {
        switch (subQuestionNew.getType()){
            case 1:
            case 2:
            case 3:
            case 4:
                return adminTestMapperNew.addListeningQuestion(subQuestionNew);
            case 5:
                return adminTestMapperNew.addBankedQuestion(subQuestionNew);
            case 6:
                return adminTestMapperNew.addMatchQuestion(subQuestionNew);
            case 7:
                return adminTestMapperNew.addCarefulQuestion(subQuestionNew);
            case 8:
            case 9:
            case 10:
                return adminTestMapperNew.addNewQuestion(subQuestionNew);
            case 11:
            case 12:
                return false;
            case 13:
            case 14:
                return false;
            case 15:
                return adminTestMapperNew.addClozeQuestion(subQuestionNew);
        }
        return false;
    }

    public boolean addQuestion(String info,
                               int questionBankId,
                               String word,
                               MultipartFile audioFile,
                               int type,
                               int num,
                               String A,
                               String B,
                               String C,
                               String D,
                               String E,
                               String F,
                               String G,
                               String answer,
                               MultipartFile imgFile) {
        String audio = "";
        String img = "";

        if (type==1||type==2||type==3||type==4){
            if (audioFile!=null&&!audioFile.isEmpty()) {
                String fileName = audioFile.getOriginalFilename();
                String extension = "";

                if (fileName != null) {
                    int lastDotIndex = fileName.lastIndexOf(".");
                    if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                        extension = fileName.substring(lastDotIndex + 1);
                    }
                }

                if ("mp3".equalsIgnoreCase(extension) || "wav".equalsIgnoreCase(extension)) {
                    audio = addFile(audioFile, "question/audio");
                } else {
                    return false;
                }
            } else{
                return false;
            }
        } else if (type==13){
            if (imgFile!=null&&!imgFile.isEmpty()) {
                try {
                    BufferedImage image = ImageIO.read(imgFile.getInputStream());
                    if (image != null) {
                        img = addFile(imgFile, "question/img");
                    } else {
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return false;
            }
        }

        QuestionNew questionNew = new QuestionNew();
        questionNew.setInfo(info);
        questionNew.setQuestionBankId(questionBankId);
        questionNew.setWord(word);
        questionNew.setAudio(audio);
        questionNew.setType(type);
        questionNew.setNum(num);
        questionNew.setA(A);
        questionNew.setB(B);
        questionNew.setC(C);
        questionNew.setD(D);
        questionNew.setE(E);
        questionNew.setF(F);
        questionNew.setG(G);
        questionNew.setAnswer(answer);
        questionNew.setImg(img);

        switch (type){
            case 1:
            case 2:
            case 3:
            case 4:
                return adminTestMapperNew.addListening(questionNew);
            case 5:
                return adminTestMapperNew.addBanked(questionNew);
            case 6:
                return adminTestMapperNew.addMatch(questionNew);
            case 7:
                return adminTestMapperNew.addCareful(questionNew);
            case 8:
            case 9:
            case 10:
                return adminTestMapperNew.addNew(questionNew);
            case 11:
            case 12:
                return adminTestMapperNew.addTranslation(questionNew);
            case 13:
            case 14:
                return adminTestMapperNew.addWriting(questionNew);
            case 15:
                return adminTestMapperNew.addCloze(questionNew);
        }
        return false;
    }

    private String addFile(MultipartFile imgFile, String folder) {
        String channelPath = uploadFolder + folder;

        File file = new File(channelPath);
        if(!file.exists()){
            MyFile.mkDirectory(channelPath);
        }
        String oldName = imgFile.getOriginalFilename();
        String newName;
        String img = "";
        if (oldName!=null&&!oldName.equals("")) {
            newName= UUID.randomUUID().toString().replace("-","")
                    +oldName.substring(oldName.lastIndexOf("."));
        } else {
            newName= UUID.randomUUID().toString().replace("-","");
        }
        try {
            imgFile.transferTo(new File(file, Objects.requireNonNull(newName)));
            img = accessPath + folder + "/" + newName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public boolean setQuestion(int id, String info, int questionBankId, String word, MultipartFile audioFile, int type, int num, String A, String B, String C, String D, String E, String F, String G, String answer, MultipartFile imgFile) {
        String audio = "";
        String img = "";

        if (type==1||type==2||type==3||type==4){
            if (audioFile!=null&&!audioFile.isEmpty()){
                String fileName = audioFile.getOriginalFilename();
                String extension = "";

                if (fileName != null) {
                    int lastDotIndex = fileName.lastIndexOf(".");
                    if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                        extension = fileName.substring(lastDotIndex + 1);
                    }
                }

                if ("mp3".equalsIgnoreCase(extension) || "wav".equalsIgnoreCase(extension)) {
                    String oldAudio = adminTestMapperNew.getListeningAudio(id);
                    if (oldAudio!=null){
                        removeImg(oldAudio);
                    }
                    audio = addFile(audioFile,"question/audio");
                } else {
                    return false;
                }
            }
        } else if (type==13){
            if (imgFile!=null&&!imgFile.isEmpty()){
                try {
                    BufferedImage image = ImageIO.read(imgFile.getInputStream());
                    if (image != null) {
                        String oldImg = adminTestMapperNew.getWritingImg(id);
                        if (oldImg!=null){
                            removeImg(oldImg);
                        }
                        img = addFile(imgFile,"question/img");
                    } else {
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        QuestionNew questionNew = new QuestionNew();
        questionNew.setId(id);
        questionNew.setInfo(info);
        questionNew.setQuestionBankId(questionBankId);
        questionNew.setWord(word);
        questionNew.setAudio(audio);
        questionNew.setType(type);
        questionNew.setNum(num);
        questionNew.setA(A);
        questionNew.setB(B);
        questionNew.setC(C);
        questionNew.setD(D);
        questionNew.setE(E);
        questionNew.setF(F);
        questionNew.setG(G);
        questionNew.setAnswer(answer);
        questionNew.setImg(img);

        switch (type){
            case 1:
            case 2:
            case 3:
            case 4:
                if (audio.equals("")){
                    return adminTestMapperNew.setListeningNoAudio(questionNew);
                }
                return adminTestMapperNew.setListening(questionNew);
            case 5:
                return adminTestMapperNew.setBanked(questionNew);
            case 6:
                return adminTestMapperNew.setMatch(questionNew);
            case 7:
                return adminTestMapperNew.setCareful(questionNew);
            case 8:
            case 9:
            case 10:
                return adminTestMapperNew.setNew(questionNew);
            case 11:
            case 12:
                return adminTestMapperNew.setTranslation(questionNew);
            case 13:
            case 14:
                if (img.equals("")){
                    return adminTestMapperNew.setWritingNoImg(questionNew);
                }
                return adminTestMapperNew.setWriting(questionNew);
            case 15:
                return adminTestMapperNew.setCloze(questionNew);
        }
        return false;
    }

    public boolean setSubQuestion(SubQuestionNew subQuestionNew) {
        switch (subQuestionNew.getType()){
            case 1:
            case 2:
            case 3:
            case 4:
                return adminTestMapperNew.setListeningQuestion(subQuestionNew);
            case 5:
                return adminTestMapperNew.setBankedQuestion(subQuestionNew);
            case 6:
                return adminTestMapperNew.setMatchQuestion(subQuestionNew);
            case 7:
                return adminTestMapperNew.setCarefulQuestion(subQuestionNew);
            case 8:
            case 9:
            case 10:
                return adminTestMapperNew.setNewQuestion(subQuestionNew);
            case 11:
            case 12:
                return false;
            case 13:
            case 14:
                return false;
            case 15:
                return adminTestMapperNew.setClozeQuestion(subQuestionNew);
        }
        return false;
    }

    private void removeImg(String oldImg) {
        String replacedPath = oldImg.replaceFirst(accessPath,uploadFolder);
        File fileToDelete = new File(replacedPath);
        if (fileToDelete.exists() && fileToDelete.isFile()) {
            if (fileToDelete.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("The specified file does not exist.");
        }
    }

    public boolean removeQuestion(int id,int type) {
        switch (type){
            case 1:
            case 2:
            case 3:
            case 4:
                String oldAudio = adminTestMapperNew.getListeningAudio(id);
                if (oldAudio!=null){
                    removeImg(oldAudio);
                }
                adminTestMapperNew.removeListeningSub(id);
                return adminTestMapperNew.removeListening(id);
            case 5:
                adminTestMapperNew.removeBankedSub(id);
                return adminTestMapperNew.removeBanked(id);
            case 6:
                adminTestMapperNew.removeMatchSub(id);
                return adminTestMapperNew.removeMatch(id);
            case 7:
                adminTestMapperNew.removeCarefulSub(id);
                return adminTestMapperNew.removeCareful(id);
            case 8:
            case 9:
            case 10:
                adminTestMapperNew.removeNewSub(id);
                return adminTestMapperNew.removeNew(id);
            case 11:
            case 12:
                return adminTestMapperNew.removeTranslation(id);
            case 13:
            case 14:
                String oldImg = adminTestMapperNew.getWritingImg(id);
                if (oldImg!=null){
                    removeImg(oldImg);
                }
                return adminTestMapperNew.removeWriting(id);
            case 15:
                adminTestMapperNew.removeClozeSub(id);
                return adminTestMapperNew.removeCloze(id);
        }
        return false;
    }

    public boolean removeSubQuestion(int id,int type) {
        switch (type){
            case 1:
            case 2:
            case 3:
            case 4:
                return adminTestMapperNew.removeListeningQuestion(id);
            case 5:
                return adminTestMapperNew.removeBankedQuestion(id);
            case 6:
                return adminTestMapperNew.removeMatchQuestion(id);
            case 7:
                return adminTestMapperNew.removeCarefulQuestion(id);
            case 8:
            case 9:
            case 10:
                return adminTestMapperNew.removeNewQuestion(id);
            case 11:
            case 12:
                return false;
            case 13:
            case 14:
                return false;
            case 15:
                return adminTestMapperNew.removeClozeQuestion(id);
        }
        return false;
    }
}
