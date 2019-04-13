package com.example.cc.retrofitpractice;

import android.graphics.ColorSpace;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Apiclient {// in interface we only declare the method


//    @GET("posts") //because we get  the data from server posts is the end url & Relative url
//    Call<List<Modelclass>> getModelclass(
//            @Query("userId")Integer[] userId,
////            @Query("userId")int userId2,
////            @Query("userId")int userId3,
//
//            @Query("_sort") String sort,
//            @Query("_order")String order  //In same way we quary multiple variable
//
//    );


    @GET("posts") //because we get  the data from server posts is the end url & Relative url
    Call<List<Modelclass>> getModelclass(@QueryMap Map<String,String> parameter);

    @GET("posts/{id}/comments") //We get the data another dynamic relative url so we create another ModelClassfo these
    Call<List<ModelComment>> getComment(@Path("id") int postId);
//for change the post id we use @path
    //Instead of 1 we put {id}  .id replace the postId and vice versa but annotation of @Path and {postId} is same


    //In  @Quary the relative url has ? to show the use of Quary


//In url mutiple parametr is edit so
    // In the same way we replace multiplae {} /{}/ and Annotated by sequences

    // But Many user id we make a Array Integer[]
    @GET
    Call<List<ModelComment>> getComment(@Url String url);


    @POST
    Call<Modelclass> createPost(@Body Modelclass modelclass);

    @FormUrlEncoded
    @POST("posts")
    Call<Modelclass> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body")  String text


    );

    @FormUrlEncoded
    @POST("posts")
    Call<Modelclass> createPost(@FieldMap Map<String ,String> fields);

}
//Post request is send to data to the server
