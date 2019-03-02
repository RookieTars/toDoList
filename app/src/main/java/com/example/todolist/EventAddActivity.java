package com.example.todolist;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hanks.library.AnimateCheckBox;

public class EventAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        final EditText titleAdd=(EditText)findViewById(R.id.title_add);
        final EditText contentAdd=(EditText)findViewById(R.id.content_add);
        final AnimateCheckBox finished=(AnimateCheckBox)findViewById(R.id.finished_check);
        final RadioButton urgentBt=(RadioButton)findViewById(R.id.urgent_radio_button);
        final RadioButton importantBt=(RadioButton)findViewById(R.id.important_radio_button);
        RadioButton commonBt=(RadioButton)findViewById(R.id.common_radio_button);
        Button addBt=(Button)findViewById(R.id.add_button);
        Button cancelBt=(Button)findViewById(R.id.cancel_button);
        commonBt.toggle();
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event=new Event();
                event.setEventTitle(titleAdd.getText().toString());
                event.setEventContent(contentAdd.getText().toString());
                if(urgentBt.isChecked()){
                    event.setEventGrade(1);
                }else if(importantBt.isChecked()){
                    event.setEventGrade(2);
                }else{
                    event.setEventGrade(3);
                }
                if(finished.isChecked()){
                    event.setFinished(true);
                }else{
                    event.setFinished(false);
                }
                event.save();
                Toast.makeText(EventAddActivity.this,event.getEventTitle()+"已保存",
                        Toast.LENGTH_SHORT).show();
                EventAddActivity.this.finish();
            }
        });
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventAddActivity.this.finish();
            }
        });
    }
}
