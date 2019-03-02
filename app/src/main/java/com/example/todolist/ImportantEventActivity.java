package com.example.todolist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class ImportantEventActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_event);
        Toolbar toolbar=(Toolbar)findViewById(R.id.main_toolbar);
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        TextView title=(TextView)findViewById(R.id.tool_bar_title);
        title.setText("重要事件");
        mDrawerLayout=(DrawerLayout)findViewById(R.id.main_drawer_layout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setCheckedItem(R.id.important_event);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                mDrawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.urgent_event:
                        intent=new Intent(ImportantEventActivity.this
                                ,UrgentEventActivity.class);
                        startActivity(intent);
                        ImportantEventActivity.this.finish();
                        break;
                    case R.id.all_event:
                        intent=new Intent(ImportantEventActivity.this
                                ,MainActivity.class);
                        startActivity(intent);
                        ImportantEventActivity.this.finish();
                        break;
                    case R.id.common_event:
                        intent=new Intent(ImportantEventActivity.this
                                ,CommonEventActivity.class);
                        startActivity(intent);
                        ImportantEventActivity.this.finish();
                        break;
                    default:
                }
                return true;
            }
        });
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ImportantEventActivity.this
                        ,EventAddActivity.class);
                startActivity(intent);
            }
        });
        LitePal.getDatabase();
    }
    @Override
    protected void onResume(){
        super.onResume();
        List<Event> eventList= DataSupport
                .where("eventgrade=?","2").find(Event.class);
//        Log.d("abtest",eventList.get(0).getEventTitle());
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.main_recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        EventAdapter adapter=new EventAdapter(eventList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}
