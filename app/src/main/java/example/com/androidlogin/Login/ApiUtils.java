package example.com.androidlogin.Login;
/**
 * Created by Heshan on 02-Apr-18.
 */

public class ApiUtils {


    public static final String BASE_URL="http://10.32.11.157:8080/";

    //http://192.248.32.211/
    public static UserService getUserService(){

        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
