package za.ac.cput.views.UIclasses;

import za.ac.cput.views.consoleapp.ConsoleApp;

public class fakeGUI
{
    public fakeGUI()
    {
    }

    public static void main(String[] args)
    {
        /*
        //1. An example venue. Try creating your own class's object, eg. Secretary
        DayCareVenue venue =
                DayCareVenueFactory.build
                        ("Imvelo Creche", "18 Cranberry", "0825558323", "idPrincipal1");


         */

        //2. urls for the DayCareVenue class. Try using your own class's links'
        String createUrl = "http://localhost:8080/api/v1/day-care/venue/save/";
        String deleteUrl = "http://localhost:8080/api/v1/day-care/classroom/delete/";
        String readUrl = "http://localhost:8080/api/v1/day-care/venue/read/";
        String allUrl = "http://localhost:8080/api/v1/day-care/venue/all/";



        //3. Here we are calling the methods in our ConsoleApp(using the urls above). Uncomment the method you want to use

        //3.1 Use the post method to create a record fof the object you created in step 1
        //new ConsoleApp().post(venue,createUrl);

        //3.2 Use the getAll method to read all items in your class's table
        //ConsoleApp.getAll(allUrl).toString();

        //3.3 Read one item using the unique identifier
        //System.out.println(new ConsoleApp().readOne("Imvelo Creche",readUrl)); //'Imvelo Creche' is my unique id

        //3.4 Delete one record using its unique id
        //new ConsoleApp().delete("A12", "http://localhost:8080/api/v1/day-care/classroom/delete/");


    }
}
