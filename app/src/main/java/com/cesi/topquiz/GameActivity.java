package com.cesi.topquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cesi.model.Question;
import com.cesi.model.QuestionBank;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    private QuestionBank questionBank;
    private TextView textView;
    private Button  [] buttonsTab =  new Button[4];
    private Question currentQuestion ;
    private int score = 0;
    private int NumberOfQuestions;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public final GameActivity context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        questionBank = generateQuestions();
        textView = findViewById(R.id.TextView);
        buttonsTab[0] = findViewById(R.id.button1);
        buttonsTab[1] = findViewById(R.id.button2);
        buttonsTab[2] = findViewById(R.id.button3);
        buttonsTab[3] = findViewById(R.id.button4);

        NumberOfQuestions = 4;
        displayQuestion();
    }

    private QuestionBank generateQuestions(){
    Question question1 = new Question("Who is the creator of Android ?",
            Arrays.asList("Andy Rubin",
                     "Steve Wozniak",
                     "Jake Wharton",
                     "Paul Smith"),
            0,"Andy Rubin co-created the Android operating system, \n" +
            " before supporting its development at Google");
    Question question2 = new Question("When did the first  man land on the moon ?",
            Arrays.asList("1958",
                    "1962",
                    "1967",
                    "1969"),
            3,"1969 is the good answer\n" +
            "July 21, 1969 Neil Armstrong walked on the moon");
    Question question3 = new Question("What is the house number of The Simpsons ?",
             Arrays.asList("42",
                    "101",
                    "666",
                    "742"),
            3,"742 Evergreen Terrace is the street address most frequently attributed to the Simpson family home, \n" +
            " in the animated sitcom The Simpsons and in the feature film The Simpsons Movie.");
        Question question4 = new Question("In what year did Béhanzin king of Dahomey die ?",
                Arrays.asList(" 1906",
                        "1857",
                        "1806",
                        "1789"),
                0,"Béhanzin born in 1845 and died in 1906 is a king of Abomey. During his reign \n" +
                ", the kingdom of Dahomey is defeated to form the colony of Dahomey. ");
        return new QuestionBank( Arrays.asList(question1,
                        question2,
                        question3,
                        question4));
    }
//pour afficher les question
    private void displayQuestion(){
       currentQuestion =  questionBank.getQuestion();
        textView.setText(currentQuestion.getQuestion());
        for (int i = 0 ; i < buttonsTab.length; i++){
            buttonsTab[i].setText(currentQuestion.getChoisiListe().get(i));
        }

    }
//Pour le clique sur les bouton
    // on va definie des tague pour chaque boutton
    // on recuper les tage des bouutton pui on les conpare a la bonne reponse attendu
    public void onAnswer(View view){
        Log.i( "====game Activity", "Tag" + view.getTag());
        int rep = Integer.parseInt((String)view.getTag());

        if(rep == currentQuestion.getAnswerIndex()){
            Toast.makeText(this, "Correct !", Toast.LENGTH_SHORT).show();
            score++;
        }else {
            Toast.makeText(this, "Incorrect ! \n " + currentQuestion.getInfoRep().toString(), Toast.LENGTH_SHORT).show();
        }
        //--NumberOfQuestions == 0
        //displayQuestion();
//        if(--NumberOfQuestions == 0){
//            endGame();
//        }
//        else{
//            currentQuestion = questionBank.getQuestion();
//            displayQuestion();
//        }

        // ----------------- reorganisation du code -----------------------

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // If this is the last question, ends the game.
                // Else, display the next question.
                if(questionBank.remainingQuestion() > 0){
                    displayQuestion();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Parfait !")
                            .setMessage("Votre score est " + score)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent();
                                    intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            })
                            .create()
                            .show();
                }
                //

            }
        }, 2000); // LENGTH_SHORT is usually 2 second long

        //on met en place un gestio nqui sexute un code pendant un delait



    }

   private void endGame(){
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Parfait !")
               .setMessage("Votre score est " + score)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Intent intent = new Intent();
                       intent.putExtra(BUNDLE_EXTRA_SCORE, score);
                       setResult(RESULT_OK, intent);
                       finish();
                   }
               })
               .create()
               .show();
   }

    public void isExiste (){

    }
}