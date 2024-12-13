package com.alyndroid.facebookv2.data;

import com.alyndroid.facebookv2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts")
    public Single<List<PostModel>> getPosts();
}
