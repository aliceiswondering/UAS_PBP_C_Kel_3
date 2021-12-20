package com.patriciadevitasamara.uas_pyb.api;

import com.patriciadevitasamara.uas_pyb.model.assignment.AssignmentResponse;
import com.patriciadevitasamara.uas_pyb.model.assignment.addedit.AssignmentAddResponse;
import com.patriciadevitasamara.uas_pyb.model.course.CourseItem;
import com.patriciadevitasamara.uas_pyb.model.course.CourseResponse;
import com.patriciadevitasamara.uas_pyb.model.course.addedit.CourseAddEditResponse;
import com.patriciadevitasamara.uas_pyb.model.login.LoginResponse;
import com.patriciadevitasamara.uas_pyb.model.register.RegisterResponse;
import com.patriciadevitasamara.uas_pyb.model.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<RegisterResponse> register(
            @Field("name") String name,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("course")
    Call<CourseResponse> getCourse(@Header("Authorization") String authorization);

    @GET("course/{id}")
    Call<CourseAddEditResponse> getCourseById(
            @Header("Authorization") String authorization,
            @Path("id") int id
    );

    @POST("course")
    @FormUrlEncoded
    Call<CourseAddEditResponse> createCourse(
            @Header("Authorization") String authorization,
            @Field("nama_kelas") String nama_kelas,
            @Field("mata_pelajaran") String mata_pelajaran,
            @Field("kapasitas") int kapasitas,
            @Field("kode") String kode
    );

    @PUT("course/{id}")
    @FormUrlEncoded
    Call<CourseAddEditResponse> updateCourse(
            @Header("Authorization") String authorization,
            @Path("id") int id,
            @Field("nama_kelas") String nama_kelas,
            @Field("mata_pelajaran") String mata_pelajaran,
            @Field("kapasitas") int kapasitas,
            @Field("kode") String kode
    );

    @DELETE("course/{id}")
    Call<CourseAddEditResponse> deleteCourse(
            @Header("Authorization") String authorization,
            @Path("id") int id
    );

    @GET("assignment")
    Call<AssignmentResponse> getAssignment(@Header("Authorization") String authorization);

    @GET("assignment/{id}")
    Call<AssignmentAddResponse> getAssignmentById(
            @Header("Authorization") String authorization,
            @Path("id") int id
    );

    @POST("assignment")
    @FormUrlEncoded
    Call<AssignmentAddResponse> createAssignment(
            @Header("Authorization") String authorization,
            @Field("judul") String judul,
            @Field("materi") String materi,
            @Field("deskripsi") String deskripsi
    );

    @PUT("assignment/{id}")
    @FormUrlEncoded
    Call<AssignmentAddResponse> updateAssignment(
            @Header("Authorization") String authorization,
            @Path("id") int id,
            @Field("judul") String judul,
            @Field("materi") String materi,
            @Field("deskripsi") String deskripsi
    );

    @DELETE("assignment/{id}")
    Call<AssignmentAddResponse> deleteAssignment(
            @Header("Authorization") String authorization,
            @Path("id") int id
    );

    @PUT("user/{id}")
    @FormUrlEncoded
    Call<UserResponse> editUser(
            @Header("Authorization") String authorization,
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("username") String username,
            @Field("address") String address,
            @Field("phoneNumber") String phoneNumber
//            @Field("photoProfile") Object photoProfile
    );

    @GET("user/{id}")
    @FormUrlEncoded
    Call<UserResponse> getUserLogin(
            @Header("Authorization") String authorization,
            @Path("id") int id
    );

}
