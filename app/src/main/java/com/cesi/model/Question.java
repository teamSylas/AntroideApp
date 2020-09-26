package com.cesi.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private  String question;
    private List <String> choisiListe ;
    private int answerIndex;
    private String infoRep;

    public String getInfoRep() {
        return infoRep;
    }

    public void setInfoRep(String infoRep) {
        this.infoRep = infoRep;
    }

    public Question(String question, List<String> choisiListe, int answerIndex, String infoRep) {
        this.question = question;
        this.choisiListe = choisiListe;
        this.answerIndex = answerIndex;
        this.infoRep = infoRep;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoisiListe() {
        return choisiListe;
    }

    public void setChoisiListe(List<String> choisiListe) {
        this.choisiListe = choisiListe;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }


}
