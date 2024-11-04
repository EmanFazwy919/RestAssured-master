package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class DynamicJson {

    @Test(dataProvider = "BookData")

    public void addBook(String isbn, String aisle){
        RestAssured.baseURI="http://216.10.245.166";
      String response=  given().log().all().header("Content-Type","application/json")
        .body(PayLoad.addBook(isbn,aisle))
        .when()
        .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
     JsonPath js = reUsableMethod.rawToJson(response);
    String id= js.get("ID");
        System.out.println(id);
    }

   @DataProvider(name="BookData")
   public Object[][] getData(){
        return new Object[][]  {{"esraa","23"},{"esraaa","223"},{"esraaaa","2223"}};
   }
}
