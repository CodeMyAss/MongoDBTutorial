package me.rbrickis.mongodbtutorial;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by Ryan on 12/21/2014
 * <p/>
 * Project: MongoDBTutorial
 */
public class MongoDBTutorial {

    /**
     * This is the Mongo Database Object. You can interact with the database from here.
     *
     * Needs to be static in this context.
     *
     */
    static DB db = null;

    /**
     * The main method.
     */
    public static void main(String... args) {
        // Set up the database
        try {
            // Connect to localhost using the database persons. If the database does not exist it will create it when
            // An object is inserted.
            db = MongoClient.connect(new DBAddress("localhost", "persons"));
            // Authentication is optional, but recommended. I will help set this up.
            // db.authenticate("example_user", "password".toCharArray());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }

        // A collection is like a table (SQL Reference).
        // This how we insert the objects into the database.
        DBCollection dbCollection = db.getCollection("persons");

        // These are the objects we wish to insert into the database
        Person bill_gates = new Person("Bill Gates", 59);

        // DBObjects are like Rows in SQL.
        BasicDBObject bg_object = new BasicDBObject()
                // The "name" parameter is like a column
                .append("name", bill_gates.getName())
                .append("age", bill_gates.getAge());
        Person tim_cook = new Person("Tim Cook", 54, "Apple Inc.");
        BasicDBObject tc_object = new BasicDBObject()
                .append("name", tim_cook.getName())
                .append("age", tim_cook.getAge())
                .append("place_of_work", tim_cook.getPlaceOfWork());

        // The insert method takes a variadic argument of DBObjects
        dbCollection.insert(bg_object, tc_object);



        // Detect if a user exists.
        // This looks for a "column" in the collection/table with the name Tim Cook
        DBCursor cursor = dbCollection.find(new BasicDBObject("name", "Tim Cook"));

        // We know there is only one object with the name Tim Cook, if you have more
        // you would want to do while (cursor.hasNext())
        if (cursor.hasNext()) {
            // This is the Object that has the name Tim Cook
            DBObject object = cursor.next();

            String name = (String) object.get("name"); // Tim Cook
            assert name.equals("Tim Cook"); // true

            int age = (Integer) object.get("age"); // 54

            assert  age == 54; // true

            String place_of_work = (String) object.get("place_of_work"); // Apple Inc.

            assert place_of_work.equals("Apple Inc."); // true

            System.out.println("Name: \t" + name + "\nAge: \t" + age + "\nPlace Of Work: \t" + place_of_work);
            // We get our expected output here: http://rbrickis.me/display.php?i=0rOu6uVqMJ0zT7uN.png
        }

    }

}
