package example.com.androidlogin.Login;
/**
 * Created by Heshan on 02-Apr-18.
 */

public class ApiUtils {


    public static final String BASE_URL="http://192.210.160.177:4001/";
                                        //http://10.32.11.3:8080/
    //192.210.160.177:4001
    public static UserService getUserService(){

        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
