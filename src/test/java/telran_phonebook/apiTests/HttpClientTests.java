package telran_phonebook.apiTests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

public class HttpClientTests {

    private String email = "monketester13@gmail.com";
    private String wrongEmail = "monke@tester13@gmail.com";
    private String unregisterdEmail = "monketester666@gmail.com";
    private String password = "1q2W3e4R_";
    private String wrongPassword = "1q2W3e400";


    @Test
    public void loginHttpClientTest() throws IOException {


        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();

        //System.out.println(response);
        String responseJson = response.returnContent().asString();
        //System.out.println(responseJson);
        JsonElement element = JsonParser.parseString(responseJson);
        JsonElement token = element.getAsJsonObject().get("token");
        System.out.println(token.getAsString());
    }

    @Test
    public void loginHttpClientWrongPasswordTest() throws IOException {

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"password\": \"" + wrongPassword + "\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();

        String responseJson = response.returnResponse().toString();
        System.out.println(responseJson);
    }

    @Test
    public void loginHttpClientUnregistredEmailTest() throws IOException {

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"" + unregisterdEmail + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();

        String responseJson = response.returnResponse().toString();
        System.out.println(responseJson);
    }

    @Test
    public void loginHttpClientWrongEmailFormatTest() throws IOException {

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"" + wrongEmail + "\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();

        String responseJson = response.returnResponse().toString();
        System.out.println(responseJson);
    }
}
