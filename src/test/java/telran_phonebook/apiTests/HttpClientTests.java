package telran_phonebook.apiTests;

import api.dto.AuthErrorDto;
import api.dto.AuthRequestDto;
import api.dto.AuthResponseDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientTests {

    private String email = "monketester13@gmail.com";
    private String wrongEmail = "monke@tester13@gmail.com";
    private String unregisteredEmail = "monketester666@gmail.com";
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
    public void loginHttpClientUnregisteredEmailTest() throws IOException {

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"" + unregisteredEmail + "\",\n" +
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

    @Test
    public void loginHttpClientPositiveTest1() throws IOException {
        Gson gson = new Gson();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email(email)
                .password(password)
                .build();

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();
        String responseJson = response.returnContent().asString();
        AuthResponseDto responseDto = gson.fromJson(responseJson, AuthResponseDto.class);
        System.out.println(responseDto);
    }

    @Test
    public void loginHttpClientNegativeTest1() throws IOException {
        Gson gson = new Gson();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email(email)
                .password(wrongPassword)
                .build();

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();
        HttpResponse httpResponse = response.returnResponse();
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        InputStream content = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
        AuthErrorDto authErrorDto = gson.fromJson(stringBuilder.toString(), AuthErrorDto.class);
        System.out.println(authErrorDto.getDetails());
        System.out.println(authErrorDto.getMessage());
        System.out.println(authErrorDto.getTimestamp());
    }
}
