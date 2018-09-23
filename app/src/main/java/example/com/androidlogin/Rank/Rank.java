package example.com.androidlogin.Rank;

import java.util.List;

import example.com.androidlogin.Gpa.Gpa;

/**
 * Created by applelab1 on 7/14/18.
 */

public class Rank {

    private String message;
    private List<Data2> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data2> getData() {
        return data;
    }

    public void setData(List<Data2> data) {
        this.data = data;
    }

    class Data2{

        private String rank;

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
