package Util.Misc;

import java.util.ArrayList;
import java.lang.reflect.Field;

/**
 * This class contains miscellaneous sorting methods
 * */

public class Sorting {

    // NOTE: This method uses some more advanced concepts of Java to be able to access the properties of the objects
    // The <T> in front of the return type is a type generic which means any type of obj passed in will be used as the type for the list returned

    /**
     * Sorts an ArrayList of objects by a specified property of the object
     * @param list The list of objects to sort
     * @param prop The property of the object to sort by
     * @param <T> The generic type of object in the list
     * @return The sorted list in descending order
     * */

    public static <T> ArrayList<T> sortObjectByIntPropertyCount(ArrayList<T> list, String prop) {

        list.sort((o1, o2) -> { // ArrayLists by default have .sort method which takes a lambda function

            try {

                // Get the field specified by 'prop' by getting the class at 'runtime'

                Field field1 = o1.getClass().getDeclaredField(prop);
                Field field2 = o2.getClass().getDeclaredField(prop);

                // Get the values of the field for the two objects by accessing the runtime field values

                int value1 = field1.getInt(o1);
                int value2 = field2.getInt(o2);

                return Integer.compare(value2, value1); // Compare the two values and return the result

            } catch (IllegalAccessException | NoSuchFieldException e) {

                e.printStackTrace();

                return 0; // Default to 0 if there's an error, which will keep the order the same

            }

        });

        return list;
    }

    /**
     * This does the exact same thing as the previous method, but for float properties (because Java)
     * @param list The list of objects to sort
     * @param prop The property of the object to sort by
     * @param <T> The generic type of object in the list
     * @return The sorted list in descending order
     * */
    
    public static <T> ArrayList<T> sortObjectByFloatProperyCount(ArrayList<T> list, String prop) {

        list.sort((o1, o2) -> { // ArrayLists by default have .sort method which takes a lambda function

            try {

                // Get the field specified by 'prop' by getting the class at 'runtime'

                Field field1 = o1.getClass().getDeclaredField(prop);
                Field field2 = o2.getClass().getDeclaredField(prop);

                // Get the values of the field for the two objects by accessing the runtime field values

                float value1 = field1.getFloat(o1);
                float value2 = field2.getFloat(o2);

                return Float.compare(value2, value1); // Compare the two values and return the result
                
            } catch (IllegalAccessException | NoSuchFieldException e) {

                e.printStackTrace();

                return 0; // Default to 0 if there's an error, which will keep the order the same

            }

        });

        return list;
    }


}