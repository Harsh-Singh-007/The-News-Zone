package com.harsh.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.harsh.newsapp.Adapters.Adapter;
import com.harsh.newsapp.NewsModels.Articles;
import com.harsh.newsapp.NewsModels.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageView img;
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    final private String API_KEY = "Your API Key";
    String country;

    //Navigation Drawer
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        recyclerView = findViewById(R.id.recycleView);
        img = findViewById(R.id.menu_list);
        //draw = findViewById(R.id.img_drawer);

        //Navigation Drawer
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));//changing the color of navigation bar icor
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.terms:
                        String url = "https://thenewszonebyharshsingh.blogspot.com/2022/05/the-news-zone-terms-and-conditions.html";
                        Intent intent = new Intent(getApplicationContext(),Details.class);
                        intent.putExtra("url",url);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.aboutus:
                        String url2 = "https://thenewszonebyharshsingh.blogspot.com/2022/05/the-news-zone-about-us.html";
                        Intent intent2 = new Intent(getApplicationContext(),Details.class);
                        intent2.putExtra("url",url2);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.help:
                        Intent intent3 = new Intent(getApplicationContext(),Help.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        //Getting data from api
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        country = getCountries("");
        retriveJson(country,API_KEY);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retriveJson(country,API_KEY);
            }
        });

        //Menu
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }


    //Changing Country
    private void showMenu(View v) {

        PopupMenu popupMenu = new PopupMenu(MainActivity.this,v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_item,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId() == R.id.bydefault){
                    country = getCountries("");
                    retriveJson(country,API_KEY);
                }
                else if(item.getItemId() == R.id.uk){
                    country = getCountries("UK");
                    retriveJson(country,API_KEY);
                }
                else if(item.getItemId() == R.id.us){
                    country = getCountries("US");
                    retriveJson(country,API_KEY);
                }
                else if(item.getItemId() == R.id.canada){
                    country = getCountries("CANADA");
                    retriveJson(country,API_KEY);
                }
                else if(item.getItemId() == R.id.germany){
                    country = getCountries("GERMANY");
                    retriveJson(country,API_KEY);
                }
                else if(item.getItemId() == R.id.japan){
                    country = getCountries("JAPAN");
                    retriveJson(country,API_KEY);
                }
                else if(item.getItemId() == R.id.france){
                    country = getCountries("FRANCE");
                    retriveJson(country,API_KEY);
                }
                else if(item.getItemId() == R.id.italy){
                    country = getCountries("ITALY");
                    retriveJson(country,API_KEY);
                }
                else if(item.getItemId() == R.id.korea){
                    country = getCountries("KOREA");
                    retriveJson(country,API_KEY);
                }

                return true;
            }
        });
        popupMenu.show();
    }

    //Different Country
    private String getCountries(String s) {

        switch (s) {
            case "UK": {
                Locale locale = Locale.getDefault();
                country = locale.UK.getCountry();
                return country.toLowerCase();
            }
            case "US": {
                Locale locale = Locale.getDefault();
                country = locale.US.getCountry();
                return country.toLowerCase();
            }
            case "CANADA": {
                Locale locale = Locale.getDefault();
                country = locale.CANADA.getCountry();
                return country.toLowerCase();
            }
            case "GERMANY": {
                Locale locale = Locale.getDefault();
                country = locale.GERMANY.getCountry();
                return country.toLowerCase();
            }
            case "JAPAN": {
                Locale locale = Locale.getDefault();
                country = locale.JAPAN.getCountry();
                return country.toLowerCase();
            }
            case "FRANCE": {
                Locale locale = Locale.getDefault();
                country = locale.FRANCE.getCountry();
                return country.toLowerCase();
            }
            case "CHINA": {
                Locale locale = Locale.getDefault();
                country = locale.CHINA.getCountry();
                return country.toLowerCase();
            }
            case "ITALY": {
                Locale locale = Locale.getDefault();
                country = locale.ITALY.getCountry();
                return country.toLowerCase();
            }
            case "KOREA": {
                Locale locale = Locale.getDefault();
                country = locale.KOREA.getCountry();
                return country.toLowerCase();
            }
            default: {
                Locale locale = Locale.getDefault();
                country = locale.getCountry();
                return country.toLowerCase();
            }
        }
    }


    //Retriving data for that country
    public void retriveJson(String country, String apiKey){

        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country, apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getArticles() != null) {
                        swipeRefreshLayout.setRefreshing(false);
                        articles.clear();
                        articles = response.body().getArticles();
                        adapter = new Adapter(MainActivity.this, articles);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                System.out.println(t.getLocalizedMessage());
            }
        });
    }
}