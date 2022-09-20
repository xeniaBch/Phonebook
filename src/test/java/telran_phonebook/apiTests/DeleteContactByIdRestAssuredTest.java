package telran_phonebook.apiTests;

import api.dto.ContactDto;
import api.dto.GetAllContactDto;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteContactByIdRestAssuredTest {

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1vbmtldGVzdGVyMTNAZ21haWwuY29tIn0.2mn74VPKLxAbMdSfjJlUyEwhbniQHC01dOCTJSMhwaI";
    int contact_id;

    @BeforeMethod
    public void ensurePreconditions(){
        System.err.close();
        System.setErr(System.out);
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath ="api";
        int i = (int) (System.currentTimeMillis()/1000/3600);
        ContactDto contactDto = ContactDto.builder()
                .name("John")
                .lastName("Snow")
                .address("Nord")
                .email("johnsnow" + i + "@throne.com")
                .phone("+12345" + i)
                .description("Targarien")
                .build();

        contact_id = given().header("Authorization", token)
                .contentType("application/json")
                .body(contactDto)
                .post("contact")
                .then()
                .assertThat().statusCode(200)
                .extract().path("id");
    }

    @Test
    public void deleteContactByIdPositive(){
        String status = given().header("Authorization", token)
                .delete("contact/" +
                        contact_id)
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);
    }


}
