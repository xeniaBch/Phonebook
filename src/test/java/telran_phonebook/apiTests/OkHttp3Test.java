package telran_phonebook.apiTests;

import api.dto.AuthErrorDto;
import api.dto.AuthRequestDto;
import api.dto.AuthResponseDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttp3Test {
    private String email = "monketester13@gmail.com";
    private String wrongEmail = "monke@tester13@gmail.com";
    private String unregisteredEmail = "monketester666@gmail.com";
    private String password = "1q2W3e4R_";
    private String wrongPassword = "1q2W3e400";

    public static final MediaType JSON = MediaType.get("application/json");

    @Test
    public void loginNegativeTestWithWrongEmail() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email(wrongEmail)
                .password(password)
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        AuthErrorDto authErrorDto = gson.fromJson(responseJson, AuthErrorDto.class);
        Assert.assertEquals(response.code(), 400);
        Assert.assertTrue(authErrorDto.getMessage().contains("Wrong email format!"));
    }


    @Test
    public void loginTest() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email(email)
                .password(wrongPassword)
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if(response.isSuccessful()){
            AuthResponseDto responseDto = gson.fromJson(responseJson, AuthResponseDto.class);
            System.out.println(responseDto.getToken());
            Assert.assertEquals(response.code(), 200);
        } else{
            AuthErrorDto authErrorDto = gson.fromJson(responseJson, AuthErrorDto.class);
            System.out.println(authErrorDto.getMessage());
            Assert.assertEquals(response.code(), 400);
        }

    }
}
