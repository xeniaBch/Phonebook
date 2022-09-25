package telran_phonebook.apiTests;

import api.dto.AuthErrorDto;
import api.dto.AuthRequestDto;
import api.dto.AuthResponseDto;
import api.dto.ContactDto;
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

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1vbmtldGVzdGVyMTNAZ21haWwuY29tIn0.2mn74VPKLxAbMdSfjJlUyEwhbniQHC01dOCTJSMhwaI";


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

    @Test
    public void contactEditTest() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        int i = (int) (System.currentTimeMillis()/1000/3600);
        ContactDto contact = ContactDto.builder()
                .id(6649)
                .name("JohnEdited")
                .lastName("Doe" + i)
                .email("johndoe"+i+"@gmail.com")
                .phone("+100000" + i)
                .address("New York")
                .description("edited")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(contact),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact")
                .put(requestBody)
                .addHeader("Authorization", token)
                .build();

        Response response = client.newCall(request).execute();
        String responseJson = response.body().string();
        if(response.isSuccessful()){
            ContactDto contactEdited = gson.fromJson(responseJson, ContactDto.class);
            System.out.println(contactEdited.getName());
            System.out.println(contactEdited.getId());
            Assert.assertEquals(response.code(), 200);
            Assert.assertEquals(contactEdited.getName(), "JohnEdited");
        } else{
            AuthErrorDto authErrorDto = gson.fromJson(responseJson, AuthErrorDto.class);
            System.out.println(authErrorDto.getMessage());
            Assert.assertEquals(response.code(), 404);
        }
    }
}
