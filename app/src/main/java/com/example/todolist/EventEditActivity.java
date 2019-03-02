package com.example.todolist;

import android.content.Intent;
import android.opengl.ETC1;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hanks.library.AnimateCheckBox;

import org.litepal.crud.DataSupport;

import java.util.List;

public class EventEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        Intent intent=getIntent();
        final String id=intent.getStringExtra("extra_event_id");
        Log.d("LLActivity",id);

        final Event event=DataSupport.find(Event.class,Long.parseLong(id));
//        String data=event.getEventTitle();
//        Log.d("LLActivity",data);
        final EditText eventTitle=(EditText)findViewById(R.id.title_add);
        final EditText eventContent=(EditText)findViewById(R.id.content_add);
        final AnimateCheckBox finished=(AnimateCheckBox)findViewById(R.id.finished_check);
        final RadioButton urgentBt=(RadioButton)findViewById(R.id.urgent_radio_button);
        final RadioButton importantBt=(RadioButton)findViewById(R.id.important_radio_button);
        RadioButton commonBt=(RadioButton)findViewById(R.id.common_radio_button);
        Button correct=(Button)findViewById(R.id.correct_event_button);
        Button cancel=(Button)findViewById(R.id.edit_cancel_button);
        eventTitle.setText(event.getEventTitle());
        eventContent.setText(event.getEventContent());
        switch (event.getEventGrade()){
            case 1:
                urgentBt.toggle();
                break;
            case 2:
                importantBt.toggle();
                break;
            case 3:
                commonBt.toggle();
                break;
                default:
        }
        finished.setChecked(event.getFinished());
        eventContent.setText(event.getEventContent());
        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.setEventTitle(eventTitle.getText().toString());
                event.setFinished(finished.isChecked());
                if(urgentBt.isChecked()){
                    event.setEventGrade(1);
                }else if(importantBt.isChecked()){
                    event.setEventGrade(2);
                }else{
                    event.setEventGrade(3);
                }
                event.setEventContent(eventContent.getText().toString());
                event.save();
                Toast.makeText(v.getContext(),"已修改",Toast.LENGTH_SHORT).show();
                EventEditActivity.this.finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventEditActivity.this.finish();
            }
        });
    }
}
