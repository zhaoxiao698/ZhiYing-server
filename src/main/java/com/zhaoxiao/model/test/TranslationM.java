package com.zhaoxiao.model.test;

public class TranslationM extends QuestionM{
    private String answer;

    public TranslationM() {
    }

    public TranslationM(int id, String info, String answer) {
        super(id, info, 0);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
