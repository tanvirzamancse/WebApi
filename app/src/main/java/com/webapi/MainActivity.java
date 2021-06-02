package com.webapi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.webapi.Model.ModelClass;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserInfoAdapter adapter;

    public  static final String user_base_url="https://api.github.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(user_base_url).addConverterFactory(GsonConverterFactory.create())
                .build();

        userApiResponse apiResponse=retrofit.create(userApiResponse.class);
        apiResponse.userinfo().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                if (response.isSuccessful()){
                    List<ModelClass> mainresponse=response.body();
                    LinearLayoutManager lm=new LinearLayoutManager(MainActivity.this);
                    adapter=new UserInfoAdapter(MainActivity.this,mainresponse);
                    recyclerView.setLayoutManager(lm);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });



    }
}