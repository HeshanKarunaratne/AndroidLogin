package example.com.androidlogin.Gpa;


import com.google.gson.annotations.SerializedName;

import java.util.List;



/**
 * Created by applelab1 on 7/13/18.
 */

public class Gpa {
       private String message;
       private List<Data1> data;



    public String getMessage() {
        return message;
    }

    public List<Data1> getData() {
        return data;
    }

    class Data1{

        @SerializedName(value="ARMTotCredits",alternate ={"BIOTotCredits","CHETotCredits","CSCTotCredits","ECNTotCredits","EMFTotCredits","FSCTotCredits","FSTTotCredits","ICTTotCredits","MANTotCredits","MATTotCredits","MBLTotCredits","PBLTotCredits","PBTTotCredits","PHYTotCredits","PSTTotCredits","SSMTotCredits","STATotCredits","ZOOTotCredits","GrandTotCredits"} )
        private String noOfCredits;

        @SerializedName(value="ARMGPA",alternate = {"BIOGPA","CHEGPA","CSCGPA","ECNGPA","EMFGPA","FSCGPA","FSTGPA","ICTGPA","MANGPA","MATGPA","MBLGPA","PBLGPA","PBTGPA","PHYGPA","PSTGPA","SSMGPA","STAGPA","ZOOGPA","GrandGPA"})
        private String gpa;

        public String getNoOfCredits() {
            return noOfCredits;
        }

        public void setNoOfCredits(String noOfCredits) {
            this.noOfCredits = noOfCredits;
        }

        public String getGpa() {
            return gpa;
        }

        public void setGpa(String gpa) {
            this.gpa = gpa;
        }
    }
}
