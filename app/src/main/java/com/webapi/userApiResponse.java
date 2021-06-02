package com.webapi;

import com.webapi.Model.ModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface userApiResponse {

   @GET("users")
    Call <List<ModelClass> >userinfo();

}
