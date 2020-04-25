package com.edufree.harare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddNoteActivity extends AppCompatActivity {

    private static final String ADD_NOTE_KEY="com.edufree.harare_add_note";
    private EditText mAdd;
    private String chosenTour=null;

    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mTopToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);


        Intent mData=getIntent();
        chosenTour=mData.getStringExtra(ADD_NOTE_KEY);
        getSupportActionBar().setTitle(chosenTour);

        mAdd=(EditText)findViewById(R.id.addNote_id);
        mAdd.requestFocus();
        //show keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.save_id){
            String user_new_note=mAdd.getText().toString().trim();
            if(!user_new_note.isEmpty()){
                Toast.makeText(getApplicationContext(), user_new_note, Toast.LENGTH_SHORT).show();
                //TODO saving the user new note in a database/shared preference
            }
            hideSoftKeyboard();
            Intent intent=new Intent(AddNoteActivity.this,TourGuideActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void hideSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
