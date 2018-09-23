package example.com.androidlogin.Result;


import com.google.gson.annotations.SerializedName;

/**
 * Created by applelab1 on 7/9/18.
 */

public class Result {




    @SerializedName("Grade")
            private String grade;

            @SerializedName("CourseCode")
            private String coursecode;

            @SerializedName("CourseDesc")
            private String coursedesc;

    public Result(String grade, String courseCode, String courseDesc) {
        this.grade = grade;
        coursecode = courseCode;
        coursedesc = courseDesc;
    }

            public String getGrade() {
                return grade;
            }


            public String getCoursecode() {
                return coursecode;
            }


            public String getCoursedesc() {
                return coursedesc;
            }


    }









