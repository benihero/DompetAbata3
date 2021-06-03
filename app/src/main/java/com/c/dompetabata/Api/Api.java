package com.c.dompetabata.Api;

import android.renderscript.Sampler;

import com.c.dompetabata.Helper.Respon;
import com.c.dompetabata.Model.MOtpVerif;
import com.c.dompetabata.Model.MRegisData;
import com.c.dompetabata.Model.MRegister;
import com.c.dompetabata.Model.Responphoto;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface Api {

    @Headers("Content-Type: application/json")
    @POST("signin")
    Call<Value> Login(@Body Value value);

    @Headers("Content-Type: application/json")
    @POST("signup")
    Call<MRegister> Register(@Body MRegister mRegister);

    @Headers("Content-Type: application/json")
    @POST("otp-email")
    Call<MRegisData> SendOTP(@Body MRegisData mRegisData);

    @Headers("Content-Type: application/json")
    @POST("otp-verify")
    Call<MOtpVerif> verifOTP(@Body MOtpVerif mOtpVerif);

    @Multipart
    @POST("ekyc-idcardselfie")
    Call<Responphoto> uploadImage(@PartMap Map<String, RequestBody> map);

    @GET("all-province?next=39")
    Call<Respon> getAllProvinsi();
}
