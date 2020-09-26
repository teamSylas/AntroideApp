package com.cesi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private  int lastIndex = 0 ;
    private List<Question> questionList; // = new ArrayList<Question>();

    public QuestionBank(List<Question> questionList) {
        Collections.shuffle(questionList);
        this.questionList = questionList;
    }

    public Question getQuestion (){
       Question q =  questionList.get(lastIndex);
       lastIndex++;
//       if(lastIndex >= questionList.size()){
//          lastIndex = 0;
//       }
       return q;
    }

//    public int getNBQuestion(){
//
//    }
//
    public int remainingQuestion (){
        return questionList.size() - lastIndex;
    }
}
