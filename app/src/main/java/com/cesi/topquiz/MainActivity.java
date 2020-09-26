package com.cesi.topquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cesi.model.User;

import org.xml.sax.ext.Locator2;


public class MainActivity extends AppCompatActivity {
    private Button mPlaybutton;
    private EditText mEditText;
    private User user;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private SharedPreferences preferences;
    private static final String FIRSTNAME = "firstname";
    private static final String SCORE = "score" ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
//            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
//        }
        if (requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            user.setScore(score);
            Log.i("main","Le score est " + score);
            TextView text = findViewById(R.id.textView);

            text.setText(getText(R.string.txtScors) + " Score de " + score);
            // le lieu du preference du Score
            preferences.edit().putInt(SCORE,user.getScore()).apply();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlaybutton = findViewById(R.id.idButton);
        mEditText = findViewById(R.id.idPrenom);
        mPlaybutton.setEnabled(false);
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.i("========Page1=============","onTextchanged");
            }

//activer le boutton si il y a u ntexte dans le champ
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             //   Log.i("========Page1=============","onTextchanged, start :" + start + "chartSecence " + s.toString() );

                if(s.toString().length() != 0){
                    mPlaybutton.setEnabled(true);
                }else{
                    mPlaybutton.setEnabled(false);
                }
//                ou encor
//                mPlaybutton.setEnabled(s.toString().length() != 0);
//                pour activer le boutton on ferat le texte sur le s
            }


            @Override
            public void afterTextChanged(Editable s) {
              //  Log.i("========Page1=============","AfterTextchanged");
            }
        });
        // on garde le score et le prenom du joueur pour lui rappeller au debut
        //regarder les preference firstname et scroe
        preferences = getPreferences(MODE_PRIVATE);
        String firstname = preferences.getString(FIRSTNAME,null);
        if(firstname != null){
            mEditText.setText(firstname);
            int score = preferences.getInt(SCORE,-1);
            if(score >= 0 ){
                TextView  text = findViewById(R.id.textView);
                text.setText("De reoutr " + firstname + " ! Dernier Score : " + score);
            }
        }
    }

    public void onPlay(View view){
        user = new User( mEditText.getText().toString());
        preferences.edit().putString(FIRSTNAME, user.getFirstName()).apply();

        Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
        startActivity(gameActivity);

        startActivityForResult(gameActivity,GAME_ACTIVITY_REQUEST_CODE);
    }

//    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//    SharedPreferences.Editor editor = preferences.edit();
//    editor.putString("firstname", mUser.getFirstName());
//    editor.apply();
//    String firstname = getPreferences(MODE_PRIVATE).getString("firstname", null);

}