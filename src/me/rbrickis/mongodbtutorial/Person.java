package me.rbrickis.mongodbtutorial;

import lombok.*;

/**
 * Created by Ryan on 12/21/2014
 * <p/>
 * Project: MongoDBTutorial
 *
 * About: This is a example class to explain MongoDB.
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {
    @NonNull String name;
    @NonNull int age;
    String placeOfWork;
}
