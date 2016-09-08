package com.shop.actvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kobe.shop4.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static final String SEARCHINFO = "search";//共有字符串

    private Toolbar toolbar;

    private EditText edText;//获取输入的编辑框

    private String search;

    private Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//设置主ACTIVITY

        init();//初始化各个配置文件，内容文件


    }


    public void init() {


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        edText = (EditText) findViewById(R.id.ed_search);//获取EDITTEXT

        btnSearch = (Button) findViewById(R.id.btn_search);//搜索按钮


        btnSearch.setOnClickListener(new View.OnClickListener() {//搜索点击按钮 设置监听器
            @Override

            public void onClick(View v) {

                search = edText.getText().toString();//获取搜索的内容

                if (edText.equals("")) {
                    Toast.makeText(MainActivity.this, "请输入搜索关键词", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(MainActivity.this.SEARCHINFO, search);//设置bundle 这是一种类似于MAP的数据结构 ，传递数据
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this, ActivitySearch.class);//设置信实，打开宁外一个ACTIVIT，

                    startActivity(intent);

                }


            }
        });


        setSupportActionBar(toolbar);

        //抽屉布局，设置滑动
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//创建设置菜单

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//设置菜单

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            Toast.makeText(this, "click setting button", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_camera) {

            startActivity(new Intent(this, ActivityScanMain.class));
            Toast.makeText(this, "start camera", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
