package pkg1;

import files.PayLoad;
import files.reUsableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
       String response= given().log().all().queryParam("key","qaclick123")
       .header("Content-Type","application/json").body(PayLoad.addPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .assertThat().body("scope",equalTo("APP"))
                .extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String placeid= js.getString("place_id");
        System.out.println(placeid);

        //ubdateplace
        String newAdress= "70 Summer walk, USA";
        given().log().all().queryParam("key", "qaclick")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeid + "\",\n" +
                        "\"address\":\""+newAdress+"\",\n" + // Added the missing double quote and removed the comma here
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //GetPlace after ubdated

      String getplaceResponse=  given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeid)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
JsonPath js1= reUsableMethod.rawToJson(getplaceResponse);
String actualaddress=js1.getString("address");
        System.out.println(actualaddress);
        //validation
 Assert.assertEquals(actualaddress,newAdress);


    }
}
