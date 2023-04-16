package com.zhaoxiao.model.word;

import java.util.List;

public class Word {
    /**
     * wordRank : 457
     * headWord : encourage
     * content : {"word":{"wordHead":"encourage","wordId":"CET4luan_1_457","content":{"sentence":{"sentences":[{"sContent":"I want to thank everyone who has encouraged and supported me.","sCn":"我要感谢每一位鼓励和支持过我的人。"}],"desc":"例句"},"realExamSentence":{"sentences":[{"sContent":"...Office managers knock down walls to encourage team building...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2017.6","type":"阅读理解"}},{"sContent":"...Many European governments have abandoned policies that used to encourage people to retire early...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2015.6","type":"阅读理解"}},{"sContent":"...My mission is to encourage green hands and those lacking time or money to feed themselves...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2015.12","type":"阅读理解"}},{"sContent":"...joining a programme that Bill and Melinda Gates and Warren Buffett started in June to encourage other wealthy people to give...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2013.6","type":"阅读理解"}},{"sContent":"...We encourage students to go on to college whether they are prepared or not...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2013.12","type":"阅读理解"}},{"sContent":"...Teachers could encourage boys to enjoy reading and writing with \"boy-focused\" approaches such as themes and characters that appeal to them...","sourceInfo":{"level":"CET4","year":"2011.12","type":"阅读理解"}}],"desc":"真题例句"},"usphone":"ɪn'kɝrɪdʒ","ukspeech":"encourage&type=1","star":0,"usspeech":"encourage&type=2","picture":"http://ydschool-online.nos.netease.com/CET4luan_1_457_encourage_1523505242368000458_encourage_JY.png","syno":{"synos":[{"pos":"vt","tran":"鼓励，怂恿；激励；支持","hwds":[{"w":"heart"},{"w":"stimulate"},{"w":"power"},{"w":"hand"},{"w":"second"}]}],"desc":"同近"},"ukphone":"ɪn'kʌrɪdʒ","phrase":{"phrases":[{"pContent":"encourage investment","pCn":"鼓励投资"}],"desc":"短语"},"phone":"in'kʌridʒ","speech":"encourage","remMethod":{"val":"en(使\u2026) ＋ courage(精神) → 使有精神 → 鼓励","desc":"记忆"},"relWord":{"desc":"同根","rels":[{"pos":"adj","words":[{"hwd":"encouraging","tran":"令人鼓舞的；鼓励的，奖励的"},{"hwd":"encouraged","tran":"受到鼓舞的"}]},{"pos":"adv","words":[{"hwd":"encouragingly","tran":"鼓励地；勉励人地"}]},{"pos":"n","words":[{"hwd":"encouragement","tran":"鼓励"}]},{"pos":"v","words":[{"hwd":"encouraging","tran":"鼓励，支持（encourage的ing形式）"}]}]},"trans":[{"tranCn":"鼓励","descOther":"英释","pos":"v","descCn":"中释","tranOther":"to give someone the courage or confidence to do something"}]}}}
     * bookId : CET4luan_1
     */

    private int wordRank;
    private String headWord;
    private ContentBeanX content;
    private String bookId;

    public int getWordRank() {
        return wordRank;
    }

    public void setWordRank(int wordRank) {
        this.wordRank = wordRank;
    }

    public String getHeadWord() {
        return headWord;
    }

    public void setHeadWord(String headWord) {
        this.headWord = headWord;
    }

    public ContentBeanX getContent() {
        return content;
    }

    public void setContent(ContentBeanX content) {
        this.content = content;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public static class ContentBeanX {
        /**
         * word : {"wordHead":"encourage","wordId":"CET4luan_1_457","content":{"sentence":{"sentences":[{"sContent":"I want to thank everyone who has encouraged and supported me.","sCn":"我要感谢每一位鼓励和支持过我的人。"}],"desc":"例句"},"realExamSentence":{"sentences":[{"sContent":"...Office managers knock down walls to encourage team building...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2017.6","type":"阅读理解"}},{"sContent":"...Many European governments have abandoned policies that used to encourage people to retire early...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2015.6","type":"阅读理解"}},{"sContent":"...My mission is to encourage green hands and those lacking time or money to feed themselves...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2015.12","type":"阅读理解"}},{"sContent":"...joining a programme that Bill and Melinda Gates and Warren Buffett started in June to encourage other wealthy people to give...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2013.6","type":"阅读理解"}},{"sContent":"...We encourage students to go on to college whether they are prepared or not...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2013.12","type":"阅读理解"}},{"sContent":"...Teachers could encourage boys to enjoy reading and writing with \"boy-focused\" approaches such as themes and characters that appeal to them...","sourceInfo":{"level":"CET4","year":"2011.12","type":"阅读理解"}}],"desc":"真题例句"},"usphone":"ɪn'kɝrɪdʒ","ukspeech":"encourage&type=1","star":0,"usspeech":"encourage&type=2","picture":"http://ydschool-online.nos.netease.com/CET4luan_1_457_encourage_1523505242368000458_encourage_JY.png","syno":{"synos":[{"pos":"vt","tran":"鼓励，怂恿；激励；支持","hwds":[{"w":"heart"},{"w":"stimulate"},{"w":"power"},{"w":"hand"},{"w":"second"}]}],"desc":"同近"},"ukphone":"ɪn'kʌrɪdʒ","phrase":{"phrases":[{"pContent":"encourage investment","pCn":"鼓励投资"}],"desc":"短语"},"phone":"in'kʌridʒ","speech":"encourage","remMethod":{"val":"en(使\u2026) ＋ courage(精神) → 使有精神 → 鼓励","desc":"记忆"},"relWord":{"desc":"同根","rels":[{"pos":"adj","words":[{"hwd":"encouraging","tran":"令人鼓舞的；鼓励的，奖励的"},{"hwd":"encouraged","tran":"受到鼓舞的"}]},{"pos":"adv","words":[{"hwd":"encouragingly","tran":"鼓励地；勉励人地"}]},{"pos":"n","words":[{"hwd":"encouragement","tran":"鼓励"}]},{"pos":"v","words":[{"hwd":"encouraging","tran":"鼓励，支持（encourage的ing形式）"}]}]},"trans":[{"tranCn":"鼓励","descOther":"英释","pos":"v","descCn":"中释","tranOther":"to give someone the courage or confidence to do something"}]}}
         */

        private WordBean word;

        public WordBean getWord() {
            return word;
        }

        public void setWord(WordBean word) {
            this.word = word;
        }

        public static class WordBean {
            /**
             * wordHead : encourage
             * wordId : CET4luan_1_457
             * content : {"sentence":{"sentences":[{"sContent":"I want to thank everyone who has encouraged and supported me.","sCn":"我要感谢每一位鼓励和支持过我的人。"}],"desc":"例句"},"realExamSentence":{"sentences":[{"sContent":"...Office managers knock down walls to encourage team building...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2017.6","type":"阅读理解"}},{"sContent":"...Many European governments have abandoned policies that used to encourage people to retire early...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2015.6","type":"阅读理解"}},{"sContent":"...My mission is to encourage green hands and those lacking time or money to feed themselves...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2015.12","type":"阅读理解"}},{"sContent":"...joining a programme that Bill and Melinda Gates and Warren Buffett started in June to encourage other wealthy people to give...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2013.6","type":"阅读理解"}},{"sContent":"...We encourage students to go on to college whether they are prepared or not...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2013.12","type":"阅读理解"}},{"sContent":"...Teachers could encourage boys to enjoy reading and writing with \"boy-focused\" approaches such as themes and characters that appeal to them...","sourceInfo":{"level":"CET4","year":"2011.12","type":"阅读理解"}}],"desc":"真题例句"},"usphone":"ɪn'kɝrɪdʒ","ukspeech":"encourage&type=1","star":0,"usspeech":"encourage&type=2","picture":"http://ydschool-online.nos.netease.com/CET4luan_1_457_encourage_1523505242368000458_encourage_JY.png","syno":{"synos":[{"pos":"vt","tran":"鼓励，怂恿；激励；支持","hwds":[{"w":"heart"},{"w":"stimulate"},{"w":"power"},{"w":"hand"},{"w":"second"}]}],"desc":"同近"},"ukphone":"ɪn'kʌrɪdʒ","phrase":{"phrases":[{"pContent":"encourage investment","pCn":"鼓励投资"}],"desc":"短语"},"phone":"in'kʌridʒ","speech":"encourage","remMethod":{"val":"en(使\u2026) ＋ courage(精神) → 使有精神 → 鼓励","desc":"记忆"},"relWord":{"desc":"同根","rels":[{"pos":"adj","words":[{"hwd":"encouraging","tran":"令人鼓舞的；鼓励的，奖励的"},{"hwd":"encouraged","tran":"受到鼓舞的"}]},{"pos":"adv","words":[{"hwd":"encouragingly","tran":"鼓励地；勉励人地"}]},{"pos":"n","words":[{"hwd":"encouragement","tran":"鼓励"}]},{"pos":"v","words":[{"hwd":"encouraging","tran":"鼓励，支持（encourage的ing形式）"}]}]},"trans":[{"tranCn":"鼓励","descOther":"英释","pos":"v","descCn":"中释","tranOther":"to give someone the courage or confidence to do something"}]}
             */

            private String wordHead;
            private String wordId;
            private ContentBean content;

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

            public ContentBean getContent() {
                return content;
            }

            public void setContent(ContentBean content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * sentence : {"sentences":[{"sContent":"I want to thank everyone who has encouraged and supported me.","sCn":"我要感谢每一位鼓励和支持过我的人。"}],"desc":"例句"}
                 * realExamSentence : {"sentences":[{"sContent":"...Office managers knock down walls to encourage team building...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2017.6","type":"阅读理解"}},{"sContent":"...Many European governments have abandoned policies that used to encourage people to retire early...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2015.6","type":"阅读理解"}},{"sContent":"...My mission is to encourage green hands and those lacking time or money to feed themselves...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2015.12","type":"阅读理解"}},{"sContent":"...joining a programme that Bill and Melinda Gates and Warren Buffett started in June to encourage other wealthy people to give...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2013.6","type":"阅读理解"}},{"sContent":"...We encourage students to go on to college whether they are prepared or not...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2013.12","type":"阅读理解"}},{"sContent":"...Teachers could encourage boys to enjoy reading and writing with \"boy-focused\" approaches such as themes and characters that appeal to them...","sourceInfo":{"level":"CET4","year":"2011.12","type":"阅读理解"}}],"desc":"真题例句"}
                 * usphone : ɪn'kɝrɪdʒ
                 * ukspeech : encourage&type=1
                 * star : 0
                 * usspeech : encourage&type=2
                 * picture : http://ydschool-online.nos.netease.com/CET4luan_1_457_encourage_1523505242368000458_encourage_JY.png
                 * syno : {"synos":[{"pos":"vt","tran":"鼓励，怂恿；激励；支持","hwds":[{"w":"heart"},{"w":"stimulate"},{"w":"power"},{"w":"hand"},{"w":"second"}]}],"desc":"同近"}
                 * ukphone : ɪn'kʌrɪdʒ
                 * phrase : {"phrases":[{"pContent":"encourage investment","pCn":"鼓励投资"}],"desc":"短语"}
                 * phone : in'kʌridʒ
                 * speech : encourage
                 * remMethod : {"val":"en(使\u2026) ＋ courage(精神) → 使有精神 → 鼓励","desc":"记忆"}
                 * relWord : {"desc":"同根","rels":[{"pos":"adj","words":[{"hwd":"encouraging","tran":"令人鼓舞的；鼓励的，奖励的"},{"hwd":"encouraged","tran":"受到鼓舞的"}]},{"pos":"adv","words":[{"hwd":"encouragingly","tran":"鼓励地；勉励人地"}]},{"pos":"n","words":[{"hwd":"encouragement","tran":"鼓励"}]},{"pos":"v","words":[{"hwd":"encouraging","tran":"鼓励，支持（encourage的ing形式）"}]}]}
                 * trans : [{"tranCn":"鼓励","descOther":"英释","pos":"v","descCn":"中释","tranOther":"to give someone the courage or confidence to do something"}]
                 */

                private SentenceBean sentence;
                private RealExamSentenceBean realExamSentence;
                private String usphone;
                private String ukspeech;
                private int star;
                private String usspeech;
                private String picture;
                private SynoBean syno;
                private String ukphone;
                private PhraseBean phrase;
                private String phone;
                private String speech;
                private RemMethodBean remMethod;
                private RelWordBean relWord;
                private List<TransBean> trans;

                public SentenceBean getSentence() {
                    return sentence;
                }

                public void setSentence(SentenceBean sentence) {
                    this.sentence = sentence;
                }

                public RealExamSentenceBean getRealExamSentence() {
                    return realExamSentence;
                }

                public void setRealExamSentence(RealExamSentenceBean realExamSentence) {
                    this.realExamSentence = realExamSentence;
                }

                public String getUsphone() {
                    return usphone;
                }

                public void setUsphone(String usphone) {
                    this.usphone = usphone;
                }

                public String getUkspeech() {
                    return ukspeech;
                }

                public void setUkspeech(String ukspeech) {
                    this.ukspeech = ukspeech;
                }

                public int getStar() {
                    return star;
                }

                public void setStar(int star) {
                    this.star = star;
                }

                public String getUsspeech() {
                    return usspeech;
                }

                public void setUsspeech(String usspeech) {
                    this.usspeech = usspeech;
                }

                public String getPicture() {
                    return picture;
                }

                public void setPicture(String picture) {
                    this.picture = picture;
                }

                public SynoBean getSyno() {
                    return syno;
                }

                public void setSyno(SynoBean syno) {
                    this.syno = syno;
                }

                public String getUkphone() {
                    return ukphone;
                }

                public void setUkphone(String ukphone) {
                    this.ukphone = ukphone;
                }

                public PhraseBean getPhrase() {
                    return phrase;
                }

                public void setPhrase(PhraseBean phrase) {
                    this.phrase = phrase;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getSpeech() {
                    return speech;
                }

                public void setSpeech(String speech) {
                    this.speech = speech;
                }

                public RemMethodBean getRemMethod() {
                    return remMethod;
                }

                public void setRemMethod(RemMethodBean remMethod) {
                    this.remMethod = remMethod;
                }

                public RelWordBean getRelWord() {
                    return relWord;
                }

                public void setRelWord(RelWordBean relWord) {
                    this.relWord = relWord;
                }

                public List<TransBean> getTrans() {
                    return trans;
                }

                public void setTrans(List<TransBean> trans) {
                    this.trans = trans;
                }

                public static class SentenceBean {
                    /**
                     * sentences : [{"sContent":"I want to thank everyone who has encouraged and supported me.","sCn":"我要感谢每一位鼓励和支持过我的人。"}]
                     * desc : 例句
                     */

                    private String desc;
                    private List<SentencesBean> sentences;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<SentencesBean> getSentences() {
                        return sentences;
                    }

                    public void setSentences(List<SentencesBean> sentences) {
                        this.sentences = sentences;
                    }

                    public static class SentencesBean {
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
                }

                public static class RealExamSentenceBean {
                    /**
                     * sentences : [{"sContent":"...Office managers knock down walls to encourage team building...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2017.6","type":"阅读理解"}},{"sContent":"...Many European governments have abandoned policies that used to encourage people to retire early...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2015.6","type":"阅读理解"}},{"sContent":"...My mission is to encourage green hands and those lacking time or money to feed themselves...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2015.12","type":"阅读理解"}},{"sContent":"...joining a programme that Bill and Melinda Gates and Warren Buffett started in June to encourage other wealthy people to give...","sourceInfo":{"paper":"第二套","level":"CET4","year":"2013.6","type":"阅读理解"}},{"sContent":"...We encourage students to go on to college whether they are prepared or not...","sourceInfo":{"paper":"第一套","level":"CET4","year":"2013.12","type":"阅读理解"}},{"sContent":"...Teachers could encourage boys to enjoy reading and writing with \"boy-focused\" approaches such as themes and characters that appeal to them...","sourceInfo":{"level":"CET4","year":"2011.12","type":"阅读理解"}}]
                     * desc : 真题例句
                     */

                    private String desc;
                    private List<SentencesBeanX> sentences;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<SentencesBeanX> getSentences() {
                        return sentences;
                    }

                    public void setSentences(List<SentencesBeanX> sentences) {
                        this.sentences = sentences;
                    }

                    public static class SentencesBeanX {
                        /**
                         * sContent : ...Office managers knock down walls to encourage team building...
                         * sourceInfo : {"paper":"第三套","level":"CET4","year":"2017.6","type":"阅读理解"}
                         */

                        private String sContent;
                        private SourceInfoBean sourceInfo;

                        public String getSContent() {
                            return sContent;
                        }

                        public void setSContent(String sContent) {
                            this.sContent = sContent;
                        }

                        public SourceInfoBean getSourceInfo() {
                            return sourceInfo;
                        }

                        public void setSourceInfo(SourceInfoBean sourceInfo) {
                            this.sourceInfo = sourceInfo;
                        }

                        public static class SourceInfoBean {
                            /**
                             * paper : 第三套
                             * level : CET4
                             * year : 2017.6
                             * type : 阅读理解
                             */

                            private String paper;
                            private String level;
                            private String year;
                            private String type;

                            public String getPaper() {
                                return paper;
                            }

                            public void setPaper(String paper) {
                                this.paper = paper;
                            }

                            public String getLevel() {
                                return level;
                            }

                            public void setLevel(String level) {
                                this.level = level;
                            }

                            public String getYear() {
                                return year;
                            }

                            public void setYear(String year) {
                                this.year = year;
                            }

                            public String getType() {
                                return type;
                            }

                            public void setType(String type) {
                                this.type = type;
                            }
                        }
                    }
                }

                public static class SynoBean {
                    /**
                     * synos : [{"pos":"vt","tran":"鼓励，怂恿；激励；支持","hwds":[{"w":"heart"},{"w":"stimulate"},{"w":"power"},{"w":"hand"},{"w":"second"}]}]
                     * desc : 同近
                     */

                    private String desc;
                    private List<SynosBean> synos;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<SynosBean> getSynos() {
                        return synos;
                    }

                    public void setSynos(List<SynosBean> synos) {
                        this.synos = synos;
                    }

                    public static class SynosBean {
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
                }

                public static class PhraseBean {
                    /**
                     * phrases : [{"pContent":"encourage investment","pCn":"鼓励投资"}]
                     * desc : 短语
                     */

                    private String desc;
                    private List<PhrasesBean> phrases;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<PhrasesBean> getPhrases() {
                        return phrases;
                    }

                    public void setPhrases(List<PhrasesBean> phrases) {
                        this.phrases = phrases;
                    }

                    public static class PhrasesBean {
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
                }

                public static class RemMethodBean {
                    /**
                     * val : en(使…) ＋ courage(精神) → 使有精神 → 鼓励
                     * desc : 记忆
                     */

                    private String val;
                    private String desc;

                    public String getVal() {
                        return val;
                    }

                    public void setVal(String val) {
                        this.val = val;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class RelWordBean {
                    /**
                     * desc : 同根
                     * rels : [{"pos":"adj","words":[{"hwd":"encouraging","tran":"令人鼓舞的；鼓励的，奖励的"},{"hwd":"encouraged","tran":"受到鼓舞的"}]},{"pos":"adv","words":[{"hwd":"encouragingly","tran":"鼓励地；勉励人地"}]},{"pos":"n","words":[{"hwd":"encouragement","tran":"鼓励"}]},{"pos":"v","words":[{"hwd":"encouraging","tran":"鼓励，支持（encourage的ing形式）"}]}]
                     */

                    private String desc;
                    private List<RelsBean> rels;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<RelsBean> getRels() {
                        return rels;
                    }

                    public void setRels(List<RelsBean> rels) {
                        this.rels = rels;
                    }

                    public static class RelsBean {
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
                }

                public static class TransBean {
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
        }
    }
}
