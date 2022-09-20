package telran_phonebook.apiTests;

import api.dto.AuthRequestDto;
import api.dto.AuthResponseDto;
import api.dto.ContactDto;
import api.dto.GetAllContactDto;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTest {

    private String email = "monketester13@gmail.com";
    private String wrongEmail = "monke@tester13@gmail.com";
    private String unregisteredEmail = "monketester666@gmail.com";
    private String password = "1q2W3e4R_";
    private String wrongPassword = "1q2W3e400";

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1vbmtldGVzdGVyMTNAZ21haWwuY29tIn0.2mn74VPKLxAbMdSfjJlUyEwhbniQHC01dOCTJSMhwaI";

    @BeforeMethod
    public void ensurePreconditions(){
        System.err.close();
        System.setErr(System.out);
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath ="api";
    }

    @Test
    public void loginPositiveTest(){
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email(email)
                .password(password)
                .build();
        /*AuthResponseDto responseDto = given().contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);
        System.out.println(responseDto.getToken());*/

        String token1 = given().contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("token"))
                .body("token", equalTo(token))
                .extract().path("token");

        System.out.println(token1);
    }

    @Test
    public void addNewContactPositiveTest(){
        int i = (int) (System.currentTimeMillis()/1000/3600);
        ContactDto contactDto = ContactDto.builder()
                .name("John")
                .lastName("Snow")
                .address("Nord")
                .email("johnsnow" + i + "@throne.com")
                .phone("+12345" + i)
                .description("Targarien")
                .build();

        int contact_id = given().header("Authorization", token)
                .contentType("application/json")
                .body(contactDto)
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");
        System.out.println(contact_id);

    }

    @Test
    public void getAllContactsPositiveTest() {

        GetAllContactDto responseDto = given().header("Authorization", token)
                .get("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(GetAllContactDto.class);

        for (ContactDto contactDto : responseDto.getContacts()) {
            System.out.println(contactDto.getId() + "***" + contactDto.getName() + "***");
            System.out.println("=============================================");
        }
    }
}
