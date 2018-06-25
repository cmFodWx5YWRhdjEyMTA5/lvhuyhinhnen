package huy.com.karaoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import huy.com.karaoke.Model.Example;
import huy.com.karaoke.Model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Item> itemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rccview);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .build();
        BaseInterface dataInterface = retrofit.create(BaseInterface.class);
        dataInterface.getData().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                itemArrayList = (ArrayList<Item>) response.body().getItems();
                AdapterKaraoke adapter = new AdapterKaraoke(MainActivity.this, itemArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });


    }
}
