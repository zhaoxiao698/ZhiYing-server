package com.zhaoxiao.model.test;

import java.util.List;

public class Paper {
    private List<ListeningM> listeningList;
    private List<BankedM> bankedList;
    private List<MatchM> matchList;
    private List<CarefulM> carefulList;
    private List<TranslationM> translationList;
    private List<WritingM> writingList;
    private List<ClozeM> clozeList;
    private List<NewM> newList;

    public Paper() {
    }

    public Paper(List<ListeningM> listeningList, List<BankedM> bankedList, List<MatchM> matchList, List<CarefulM> carefulList, List<TranslationM> translationList, List<WritingM> writingList, List<ClozeM> clozeList, List<NewM> newList) {
        this.listeningList = listeningList;
        this.bankedList = bankedList;
        this.matchList = matchList;
        this.carefulList = carefulList;
        this.translationList = translationList;
        this.writingList = writingList;
        this.clozeList = clozeList;
        this.newList = newList;
    }

    public List<ListeningM> getListeningList() {
        return listeningList;
    }

    public void setListeningList(List<ListeningM> listeningList) {
        this.listeningList = listeningList;
    }

    public List<BankedM> getBankedList() {
        return bankedList;
    }

    public void setBankedList(List<BankedM> bankedList) {
        this.bankedList = bankedList;
    }

    public List<MatchM> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<MatchM> matchList) {
        this.matchList = matchList;
    }

    public List<CarefulM> getCarefulList() {
        return carefulList;
    }

    public void setCarefulList(List<CarefulM> carefulList) {
        this.carefulList = carefulList;
    }

    public List<TranslationM> getTranslationList() {
        return translationList;
    }

    public void setTranslationList(List<TranslationM> translationList) {
        this.translationList = translationList;
    }

    public List<WritingM> getWritingList() {
        return writingList;
    }

    public void setWritingList(List<WritingM> writingList) {
        this.writingList = writingList;
    }

    public List<ClozeM> getClozeList() {
        return clozeList;
    }

    public void setClozeList(List<ClozeM> clozeList) {
        this.clozeList = clozeList;
    }

    public List<NewM> getNewList() {
        return newList;
    }

    public void setNewList(List<NewM> newList) {
        this.newList = newList;
    }
}
