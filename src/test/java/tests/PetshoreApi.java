package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Category;
import pojo.Root;
import pojo.Tag;
import utilities.ConfigReader;
import utilities.TestBaseRapor;
import java.util.ArrayList;


import static io.restassured.RestAssured.given;

public class PetshoreApi extends TestBaseRapor {
    Response response;
    static long petID;
    String petname;


    RequestSpecification spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseUrl")).build();
    ;

    @Test(priority = 1)
    public void positivePostRequest() {
        extentTest=extentReports.createTest("Petstore TestNG Report ","Post Request");
        extentTest.info("Post Request Icin RequestBody Pojo Class Ile Olusturulur");
        spec.pathParam("1", "pet");
        Category category = new Category(0, "poncik");
        Tag tag = new Tag(0, "minik");
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag);
        ArrayList<String> photo = new ArrayList<>();
        photo.add("petin fotografı");
        Root requestBody = new Root(0, category, "poncik", photo, tags, "avaible");

        extentTest.info("Post Request Icin Gerekli Pathparam Ayarlari Yapilir ve Request Yollanir");
        response = given().spec(spec).contentType(ContentType.JSON)
                .body(requestBody).when().post("/{1}");

        extentTest.info("Request Sonrasi Gelen Responce Ekrana Yazdirilir.");
        response.prettyPeek();

        extentTest.pass("Post Request Sonrasi Yeni Kayit Olusturulmus Olunur");


        extentTest.pass("Post Request Sonrasi Petname ve Status Code Assert Edilir");
        petname = response.getBody().jsonPath().getString("name");
        Assert.assertEquals("poncik", petname);
        Assert.assertEquals(200, response.getStatusCode());


    }

    @Test(priority = 2)
    public void getRequest() {
        extentTest=extentReports.createTest("Petstore TestNG Report ","Get Request");

        extentTest.info("Post Request Yapilarak Olusturulan Kayidin ID Numarasi alinir");
        positivePostRequest();
        petID = Long.valueOf(response.getBody().jsonPath().getString("id"));

        extentTest.info("Get Request Icin Gerekli Pathparam Ayarlari Yapilir");
        spec.pathParams("1", "pet", "2", petID);
        response = given().spec(spec).accept(" application/json").when().get("/{1}/{2}");

        extentTest.pass("Get Request Sonrasi Gelen Kayit Ekrana Yazdirilir");
        response.prettyPeek();

    }

    @Test(priority = 3)
    public void putRequest() {
        extentTest=extentReports.createTest("Petstore TestNG Report ","Put Request");

        extentTest.info("Put Request Icın RequestBody Hazirlanir");
        Category category = new Category(1, "Pasa");
        Tag tag = new Tag(0, "kücük");
        ArrayList<Tag> tags = new ArrayList<>();
        ArrayList<String> photo = new ArrayList<>();
        photo.add("asaa");
        Root requestBody = new Root(1, category, "bacaksiz", photo, tags, "available");

        extentTest.info("Put Request Icın Gerekli Pathparam Ayarlari Yapilir ve Request Gonderilir");
        spec.pathParam("1", "pet");
        response = given().spec(spec).
                accept("application/json").
                contentType("application/json").
                body(requestBody).
                put("/{1}");


        extentTest.pass("Put Request Sonrasi Gelen Response Konsola Yazdirilir");
        response.prettyPeek();

        extentTest.pass("Post Request Sonrasi Petname ve Status Code Assert Edilir");
        petname = response.getBody().jsonPath().getString("name");
        Assert.assertEquals("bacaksiz", petname);
        Assert.assertEquals(200, response.getStatusCode());


    }

    @Test(priority = 4)
    public void deleteRequest() {
        extentTest=extentReports.createTest("Petstore TestNG Report ","Delete Request");

        extentTest.info("Delete Request Yapilacak Kaydin ID'si Alinir");
      positivePostRequest();
        petID = Long.valueOf(response.getBody().jsonPath().getString("id"));


      extentTest.info("Delete Request Icın Gerekli Pathparam Ayarlari Yapilir ve Request Gonderilir");
        spec.pathParams("1", "pet", "2", petID);
        response = given().spec(spec).auth().basic("api_key", "12345").
                accept("application/json").when().delete("/{1}/{2}");


        extentTest.pass("Request sonucu gelen Response da Silme İslemi 404 Hata Mesaji ile Assert Edilir");
        Assert.assertEquals(200, response.getStatusCode());


    }


}
