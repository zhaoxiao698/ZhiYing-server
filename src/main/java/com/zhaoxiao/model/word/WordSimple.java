package com.zhaoxiao.model.word;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WordSimple {
    private String wordHead;//单词
    private String wordId;//单词ID
    private String bookId;//单词书ID
    private String picture;//图片
    private String remMethod;//记忆
    private String ukphone;//英音音标
    private String ukspeech;//英音发音https请求参数
    private String usphone;//美音音标
    private String usspeech;//美音发音https请求参数
    private List<Sentence> sentences;//例句列表
    private List<Syno> synos;//近义词列表
    private List<Phrase> phrases;//短语列表
    private List<RelWord> rels;//同根词列表
    private List<Tran> trans;//翻译列表

    private int proficiency;//熟练度
    private int nextDayNum;//下次复习天数
    private boolean collect;//是否收藏

    public WordSimple() {
    }

    public WordSimple(String wordHead, String wordId, String bookId, String picture, String remMethod, String ukphone, String ukspeech, String usphone, String usspeech, List<Sentence> sentences, List<Syno> synos, List<Phrase> phrases, List<RelWord> rels, List<Tran> trans) {
        this.wordHead = wordHead;
        this.wordId = wordId;
        this.bookId = bookId;
        this.picture = picture;
        this.remMethod = remMethod;
        this.ukphone = ukphone;
        this.ukspeech = ukspeech;
        this.usphone = usphone;
        this.usspeech = usspeech;
        this.sentences = sentences;
        this.synos = synos;
        this.phrases = phrases;
        this.rels = rels;
        this.trans = trans;
    }

    public String getWordHead() {
        return wordHead;
    }

    public void setWordHead(String wordHead) {
        this.wordHead = wordHead;
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRemMethod() {
        return remMethod;
    }

    public void setRemMethod(String remMethod) {
        this.remMethod = remMethod;
    }

    public String getUkphone() {
        return ukphone;
    }

    public void setUkphone(String ukphone) {
        this.ukphone = ukphone;
    }

    public String getUkspeech() {
        return ukspeech;
    }

    public void setUkspeech(String ukspeech) {
        this.ukspeech = ukspeech;
    }

    public String getUsphone() {
        return usphone;
    }

    public void setUsphone(String usphone) {
        this.usphone = usphone;
    }

    public String getUsspeech() {
        return usspeech;
    }

    public void setUsspeech(String usspeech) {
        this.usspeech = usspeech;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Syno> getSynos() {
        return synos;
    }

    public void setSynos(List<Syno> synos) {
        this.synos = synos;
    }

    public List<Phrase> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<Phrase> phrases) {
        this.phrases = phrases;
    }

    public List<RelWord> getRels() {
        return rels;
    }

    public void setRels(List<RelWord> rels) {
        this.rels = rels;
    }

    public List<Tran> getTrans() {
        return trans;
    }

    public void setTrans(List<Tran> trans) {
        this.trans = trans;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public int getNextDayNum() {
        return nextDayNum;
    }

    public void setNextDayNum(int nextDayNum) {
        this.nextDayNum = nextDayNum;
    }

    public boolean getCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    //例句
    public static class Sentence {
        /**
         * sContent : I want to thank everyone who has encouraged and supported me.
         * sCn : 我要感谢每一位鼓励和支持过我的人。
         */

        private String sContent;
        private String sCn;

        public String getSContent() {
            return sContent;
        }

        public void setSContent(String sContent) {
            this.sContent = sContent;
        }

        public String getSCn() {
            return sCn;
        }

        public void setSCn(String sCn) {
            this.sCn = sCn;
        }
    }

    //近义词
    public static class Syno {
        /**
         * pos : vt
         * tran : 鼓励，怂恿；激励；支持
         * hwds : [{"w":"heart"},{"w":"stimulate"},{"w":"power"},{"w":"hand"},{"w":"second"}]
         */

        private String pos;
        private String tran;
        private List<HwdsBean> hwds;

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getTran() {
            return tran;
        }

        public void setTran(String tran) {
            this.tran = tran;
        }

        public List<HwdsBean> getHwds() {
            return hwds;
        }

        public void setHwds(List<HwdsBean> hwds) {
            this.hwds = hwds;
        }

        public static class HwdsBean {
            /**
             * w : heart
             */

            private String w;

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }
        }
    }

    //短语
    public static class Phrase {
        /**
         * pContent : encourage investment
         * pCn : 鼓励投资
         */

        private String pContent;
        private String pCn;

        public String getPContent() {
            return pContent;
        }

        public void setPContent(String pContent) {
            this.pContent = pContent;
        }

        public String getPCn() {
            return pCn;
        }

        public void setPCn(String pCn) {
            this.pCn = pCn;
        }
    }

    //同根词
    public static class RelWord {
        /**
         * pos : adj
         * words : [{"hwd":"encouraging","tran":"令人鼓舞的；鼓励的，奖励的"},{"hwd":"encouraged","tran":"受到鼓舞的"}]
         */

        private String pos;
        private List<WordsBean> words;

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public List<WordsBean> getWords() {
            return words;
        }

        public void setWords(List<WordsBean> words) {
            this.words = words;
        }

        public static class WordsBean {
            /**
             * hwd : encouraging
             * tran : 令人鼓舞的；鼓励的，奖励的
             */

            private String hwd;
            private String tran;

            public String getHwd() {
                return hwd;
            }

            public void setHwd(String hwd) {
                this.hwd = hwd;
            }

            public String getTran() {
                return tran;
            }

            public void setTran(String tran) {
                this.tran = tran;
            }
        }
    }

    //翻译
    public static class Tran {
        /**
         * tranCn : 鼓励
         * descOther : 英释
         * pos : v
         * descCn : 中释
         * tranOther : to give someone the courage or confidence to do something
         */

        private String tranCn;
        private String descOther;
        private String pos;
        private String descCn;
        private String tranOther;

        public String getTranCn() {
            return tranCn;
        }

        public void setTranCn(String tranCn) {
            this.tranCn = tranCn;
        }

        public String getDescOther() {
            return descOther;
        }

        public void setDescOther(String descOther) {
            this.descOther = descOther;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getDescCn() {
            return descCn;
        }

        public void setDescCn(String descCn) {
            this.descCn = descCn;
        }

        public String getTranOther() {
            return tranOther;
        }

        public void setTranOther(String tranOther) {
            this.tranOther = tranOther;
        }
    }
}

