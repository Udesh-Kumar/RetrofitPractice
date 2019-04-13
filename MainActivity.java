package com.example.cc.retrofitpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private Apiclient apiclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        //In these we execute get request

        //Interface take only relative url and Retro take base url and append that

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")    //Base url hase always / because it connect the relativeurl
                .addConverterFactory(GsonConverterFactory.create())  // Gson to parse the response
                .build();


        //Through the retrofit object we create Apiclient
        apiclient = retrofit.create(Apiclient.class);


       // getModelClass();   //we not execute these method
       // getComment();
        createPost();

    }


    private void getModelClass(){

        //To execute our network request we use call object

        Map<String,String> parameter=new HashMap<>();
        parameter.put("userId","1");
        parameter.put("_sort","id");
        parameter.put("_order","desc");

        Call<List<Modelclass>> call=apiclient.getModelclass(parameter); // Retrofit create the implementation of the method

        call.enqueue(new Callback<List<Modelclass>>() {
            @Override
            public void onResponse(Call<List<Modelclass>> call, Response<List<Modelclass>> response) { //these method return when our req is success

                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code:"+response.code()); //When not succefull
                } //else
                List<Modelclass> modelclasses=response.body();

                for (Modelclass modelclass:modelclasses){
                    //We append that
                    String content = "";
                    content += "ID: " + modelclass.getId() + "\n";
                    content += "User ID: " + modelclass.getUserId() + "\n";
                    content += "Title: " + modelclass.getTitle() + "\n";
                    content += "Text: " + modelclass.getText() + "\n\n";


                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Modelclass>> call, Throwable t) { //Something went wrong in cmnication to the sever
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComment() {
        Call<List<ModelComment>> call1 = apiclient.getComment("https://jsonplaceholder.typicode.com/posts/3/comments");

        call1.enqueue(new Callback<List<ModelComment>>() {
            @Override
            public void onResponse(Call<List<ModelComment>> call, Response<List<ModelComment>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code()); //When not succefull
                } //else
                List<ModelComment> modelComments = response.body(); //Response dega body me se
                //Modal class  object        object of list
                for (ModelComment modelComment : modelComments) {
                    String content = "";
                    content += "ID: " + modelComment.getId() + "\n";
                    content += "Post ID: " + modelComment.getPostId() + "\n";
                    content += "Name: " + modelComment.getName() + "\n";
                    content += "Email: " + modelComment.getEmail() + "\n";
                    content += "Text: " + modelComment.getText() + "\n\n";

                    textViewResult.append(content);

                }


            }

            @Override
            public void onFailure(Call<List<ModelComment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });

    }
    private void createPost(){
         //Modelclass modelclass=new Modelclass(23,"New title","New text");

        Map<String ,String > fields=new HashMap<>();
        fields.put("userId","10");
        fields.put("title","Udesh");
        fields.put("text","NewText");

        Call<Modelclass>  call=apiclient.createPost(fields);

        call.enqueue(new Callback<Modelclass>() {
            @Override
            public void onResponse(Call<Modelclass> call, Response<Modelclass> response) {
                if (!response.isSuccessful())
                {
                    textViewResult.setText("Code:"+response.code()); //When not successfull
                }

                Modelclass modelclassResponse=response.body();
                String content = "";
                content +="Code: "+response.code()+"\n";
                content += "User ID: " + modelclassResponse.getUserId() + "\n";
                content += "Title: " + modelclassResponse.getTitle() + "\n";
                content += "Text: " + modelclassResponse.getText() + "\n\n";

                textViewResult.setText(content);
                Log.d("Response" ,content);


            }

            @Override
            public void onFailure(Call<Modelclass> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }

}

