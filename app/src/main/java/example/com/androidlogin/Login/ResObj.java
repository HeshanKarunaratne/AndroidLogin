package example.com.androidlogin.Login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Heshan on 04-Jul-18.
 */

public class ResObj {
    private String message;
    private String token;
    private List<Data> data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Data> getData() {
        return data;
    }

    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }



    class Data{


        @SerializedName("StdName")
        private String StdName;

        @SerializedName("Combination")
        private String Combination;

        @SerializedName("Subject2")
        private String Subject2;

        @SerializedName("Subject3")
        private String Subject3;

        @SerializedName("Subject1")
        private String Subject1;




        public String getSubject2() {
            return Subject2;
        }

        public void setSubject2(String subject2) {
            Subject2 = subject2;
        }

        public String getSubject3() {
            return Subject3;
        }

        public void setSubject3(String subject3) {
            Subject3 = subject3;
        }

        public String getSubject1() {
            return Subject1;
        }

        public void setSubject1(String subject1) {
            Subject1 = subject1;
        }

        public String getCombination() {
            return Combination;
        }

        public void setCombination(String combination) {
            Combination = combination;
        }

        public String getStdName() {
            return StdName;
        }

        public void setStdName(String stdName) {
            StdName = stdName;
        }
    }

}