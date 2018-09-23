package example.com.androidlogin.Login;

import java.util.List;


import example.com.androidlogin.Attempt.Attempt;
import example.com.androidlogin.Gpa.Gpa;
import example.com.androidlogin.Rank.Rank;
import example.com.androidlogin.Result.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Heshan on 02-Apr-18.
 */

public interface UserService {

    @POST("login/{username}/{password}")
    Call<ResObj> login(@Path("username")String username,@Path("password")String password);

    @GET("getSubjects/{Number}")
    Call<ResObj>getSubjects(@Path("Number")String Number, @Header("Authorization")String authToken);

    @GET("getResults/year/{GetYear}")
    Call<List<Result>> getResultByYear(@Path("GetYear") String GetYear,@Header("Authorization")String authToken);

    @GET("getResults/subject/{GetSubject}")
    Call<List<Result>> getResultBySubject(@Path("GetSubject") String GetSubject,@Header("Authorization")String authToken);

    @GET("getResults/subject/{GetSubject}/year/{GetYear}")
    Call<List<Result>> getResultByYearAndSubject(@Path("GetSubject") String GetSubject,@Path("GetYear") String GetYear,@Header("Authorization")String authToken);

    @GET("getAttempts/year/{GetYear}")
    Call<List<Attempt>> getAttemptByYear(@Path("GetYear") String GetYear,@Header("Authorization")String authToken);

    @GET("getAttempts/subject/{GetSubject}")
    Call<List<Attempt>> getAttemptBySubject(@Path("GetSubject") String GetSubject,@Header("Authorization")String authToken);

    @GET("getAttempts/subject/{GetSubject}/year/{GetYear}")
    Call<List<Attempt>> getAttemptByYearAndSubject(@Path("GetSubject") String GetSubject,@Path("GetYear") String GetYear,@Header("Authorization")String authToken);

    @GET("getGPA/subject/{GetSubject}")
    Call<Gpa> getGpaBySubject(@Path("GetSubject") String GetSubject,@Header("Authorization")String authToken);

    @GET("getRank/subject/{GetSubject}")
    Call<Rank> getRankBySubject(@Path("GetSubject") String GetSubject,@Header("Authorization")String authToken);

}

