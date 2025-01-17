package pkg1;

import files.PayLoad;
import groovy.json.JsonOutput;
import io.restassured.path.json.JsonPath;

public class ComplexJsonPath {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(PayLoad.coursePrice());
        int count = js.getInt("courses.size()");
        System.out.println(count)   ;
        //Print Purchase Amount
        int totalAmount= js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        // Print Title of the first course
       String titleFirstCourse= js.get("courses[1].title");
        System.out.println(titleFirstCourse);

// Print All course titles and their respective Prices
        for(int i =0;i<count;i++)
        {
         String courseTitles= js.get("courses["+i+"].title");
            System.out.println(js.get("courses["+i+"].price").toString());
            System.out.println(courseTitles);
        }
        //Print no of copies sold by RPA Course
        System.out.println("Print no of copies sold by RPA Course");
        for(int i =0;i<count;i++)
        {
            String courseTitles= js.get("courses["+i+"].title");
            if (courseTitles.equalsIgnoreCase("RPA"))
            {
              int copies=  js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }

        }

    }


}


