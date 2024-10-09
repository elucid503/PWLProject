package Util.Misc;

import java.util.ArrayList;
import java.lang.reflect.Field;

public class Sorting {

    // This method is generic, which means that it can be used for any type of object in an ArrayList with any prop name which can be compared
    // It uses some more advanced concepts of Java to be able to access the properties of the objects

    public static <T> ArrayList<T> sortByObjectPropertyCount(ArrayList<T> list, String prop) {

        list.sort((o1, o2) -> {

            try {

                // Get the field specified by 'prop' by getting the class at 'runtime'

                Field field1 = o1.getClass().getDeclaredField(prop);
                Field field2 = o2.getClass().getDeclaredField(prop);

                // Get the values of the field for the two objects by accessing the runtime field values

                int value1 = field1.getInt(o1);
                int value2 = field2.getInt(o2);

                // Compare the values (sorting in descending order)

                return value2 - value1;

            } catch (IllegalAccessException | NoSuchFieldException e) {

                return 0; // Default to 0 if there's an error, which will keep the order the same

            }

        });

        return list;
    }

}