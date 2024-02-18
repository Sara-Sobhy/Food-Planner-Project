package com.example.foodapp.modules.details.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.database.LocalDataSource;
import com.example.foodapp.model.Meal;
import com.example.foodapp.model.MealRepository;
import com.example.foodapp.model.Plane;
import com.example.foodapp.modules.ListOfMeals.view.MealsNameRecycleerAdapter;
import com.example.foodapp.modules.details.presenter.DetailsPresenter;
import com.example.foodapp.network.AppRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Date;
import java.util.Calendar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DetailsMealFragment extends Fragment implements DetailsInterface {
    String id;
    WebView webView;
   TextView textViewCategory;
   TextView textViewArea;
   TextView textViewStruction;
   Button btnAdd;
   ImageView imageView;
   RecyclerView recyclerView;
   IngredientsAdapter ingredientsAdapter;
    DetailsPresenter detailsPresenter;
    String email;
    Button btnplane;

    Date date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_details_meal, container, false);
        recyclerView=view.findViewById(R.id.RCIngredient);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        webView=view.findViewById(R.id.webview);
        textViewCategory=view.findViewById(R.id.category);
        textViewArea=view.findViewById(R.id.area);
        textViewStruction=view.findViewById(R.id.tvInstruction);
        btnAdd=view.findViewById(R.id.btn_fav);
        btnplane=view.findViewById(R.id.btnPlane);
        imageView=view.findViewById(R.id.imageView4);
        SharedPreferences sp = getContext().getSharedPreferences("email", Context.MODE_PRIVATE);
        email= sp.getString("Email","");
        if (getArguments() != null) {
              id=getArguments().getString("IDMeal");
              detailsPresenter=new DetailsPresenter(this, MealRepository.getInstance(LocalDataSource.getInstance(this.getContext()), AppRemoteDataSource.getInstance()),id);
        }
        return view;
    }

    @Override
    public void showMealDetails(List<Meal> meal) {
        String currentUserEmail = null;
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            currentUserEmail = firebaseUser.getEmail();
        }

        Log.d("TAG", "showMealDetails: meallllllllllllllllllll"+meal);
        Meal meal1=meal.get(0);
        textViewCategory.setText(meal1.getStrCategory());
        textViewArea.setText(meal1.getStrArea());
        textViewStruction.setText(meal1.getStrInstructions());
        Glide.with(this)
                .load(meal1.getStrMealThumb())
                .into(imageView);

        ingredientsAdapter= new IngredientsAdapter(meal,this.getContext());
        recyclerView.setAdapter(ingredientsAdapter);
        setMealVideo(meal1.getStrYoutube());

        if(currentUserEmail!=null){
            btnAdd.setVisibility(View.VISIBLE);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                meal.get(0).setUserEmail(email);
                detailsPresenter.insert(meal.get(0));

            }
        });
        }else {
            btnAdd.setVisibility(View.GONE);
        }

        if(currentUserEmail!=null){
        btnplane.setOnClickListener(v -> {
            Calendar calendar= Calendar.getInstance();
            DatePickerDialog dialog=new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Log.d("TAG", "onDateSet: " + month);
                    //setText(String.valueOf(year)+"/"+String.valueOf((Calendar.MONTH+month)-1)+"/"+String.valueOf(dayOfMonth));
                    date=new Date(year-1900,(Calendar.MONTH+month)-2,dayOfMonth);
                    Log.d("TAG", "showPlanPopUp: "+ date);
                    String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    Log.d("TAG", "onDateSet: email " + meal1.getUserEmail());
                    Plane plane=new Plane(meal1.getIdMeal(),email,meal1.getStrMeal(),meal1.getStrMealThumb(),date);
                    detailsPresenter.insertPlan(plane);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });
        }else {
            btnplane.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMealDetailsError(Throwable throwable) {

    }
    public static String convertToEmbeddedUrl(String youtubeUrl) {
        String videoId = extractVideoId(youtubeUrl);
        return "https://www.youtube.com/embed/" + videoId;
    }

    private static String extractVideoId(String youtubeUrl) {
        String videoId = null;
        String regex = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2Fvideos%2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(youtubeUrl);
        if (matcher.find()) {
            videoId = matcher.group();
        }

        return videoId;
    }
    public void setMealVideo(String url)
    {
        Log.d("TAG", "setMealVideo: width " +webView.getX());
        String videoUrl = convertToEmbeddedUrl(url); // Replace VIDEO_ID with the actual video ID or embed URL
        String video="<iframe width='400' height=\"200\" src= '"+videoUrl+"' title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript (required for video playback)
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                adjustIframeWidth(view);
            }
        });
        webView.loadData(video, " text/html", "utf-8"); // Load the HTML content into the WebView

    }
    private void adjustIframeWidth(WebView webView) {
        webView.evaluateJavascript("javascript:(function() { " +
                "var iframes = document.getElementsByTagName('iframe');" +
                "for (var i = 0; i < iframes.length; i++) {" +
                "    var iframe = iframes[i];" +
                "    iframe.style.width = '100%';" +
                "}})();", null);
    }
}