package example.com.androidlogin.Attempt;

import com.google.gson.annotations.SerializedName;

/**
 * Created by applelab1 on 7/12/18.
 */

public class Attempt {

    @SerializedName("CourseCode")
    private String coursecode;

    @SerializedName("CourseDesc")
    private String coursedesc;

    @SerializedName("Attempt1")
    private String attempt1;

    @SerializedName("Attempt2")
    private String attempt2;

    @SerializedName("Attempt3")
    private String attempt3;

    @SerializedName("Attempt4")
    private String attempt4;

    @SerializedName("Attempt5")
    private String attempt5;

    public String getCoursecode() {
        return coursecode;
    }

    public String getCoursedesc() {
        return coursedesc;
    }

    public String getAttempt1() {
        return attempt1;
    }

    public String getAttempt2() {
        return attempt2;
    }

    public String getAttempt3() {
        return attempt3;
    }

    public String getAttempt4() {
        return attempt4;
    }

    public String getAttempt5() {
        return attempt5;
    }
}
