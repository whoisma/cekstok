package com.ma.display.network;

import com.ma.display.response.DefaultResponse;
import com.ma.display.response.ProductResponse;
import com.ma.display.response.SearchProductResponse;
import com.ma.display.response.UsersResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by MA on 08/01/2018.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Observable<UsersResponse> getLogin(
            @Field("phone") String phone
    );

    @GET("products")
    Observable<SearchProductResponse> getSearchProduct(
            @Query("qw") String query,
            @Query("offset") int offset
    );

    @GET("product/{toko}/{upc}")
    Observable<ProductResponse> getProduct(
            @Path("toko") String toko,
            @Path("upc") String upc
    );

    @FormUrlEncoded
    @POST("koreksi")
    Observable<DefaultResponse> editStock(
            @Field("toko") String toko,
            @Field("upc") String upc,
            @Field("jumlah_stock") String jml,
            @Field("lokasi") String lokasi
    );
}
