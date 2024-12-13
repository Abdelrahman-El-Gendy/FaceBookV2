package com.alyndroid.facebookv2.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alyndroid.facebookv2.data.PostsClient;
import com.alyndroid.facebookv2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();
    private static final String TAG = "postViewModel";

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void getPosts() {
        Single<List<PostModel>> observable = PostsClient.getINSTANCE().getPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(c -> postsMutableLiveData.setValue(c), e -> Log.d(TAG, "onError: " + e)));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
