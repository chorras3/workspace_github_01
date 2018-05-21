package ordering002_reflective;

import java.lang.reflect.Field;
import java.util.Comparator;


/** 
 *   
 * Dynamic Comparator (versionated from <code>Kevin</code> (from <code>StackOverflow</code>) 
 * solution, see references below). Uses Reflection to dynamically select which object
 * field must be selected to compare.
 * </p>  
 * </p>
 * Usage:
 * </p>
 * 
 * </p> 
 *  // We have a List called "listaCertificadoBean", of objects PersonBean.
 *  // E.g. The Object has fields looking like -> PersonBean.surname="Chigurgh"; String s = PersonBean.surname().   
 * </p>
 * (...)
 * </p>
 * </p>
 * // Choose field to compare: </p>
 * String FIELD_NAME ="surname";   
 * </p>
 * // Create comparator (e.g. if exists PersonBean.surname field, param will be: "surname").
 * </p> 
 * ReflectiveComparator.ListComparator listReflectiveComparator = new ReflectiveComparator().new ListComparator(FIELD_NAME);   
 * </p> 
 * // Order list ascending:
 * </p>
 * Collections.sort(    listaCertificadoBean,  listReflectiveComparator  );
 * </p>
 * // Order descending:
 * </p>
 * Collections.sort(  listaCertificadoBean, Collections.reverseOrder(listReflectiveComparator)  );    
 * </p>
 *
 *
 * </p>
 * </p>
 * Class modification based on solutions:
 * </p>
 * -References:
 * </p> 
 *   ** "Sort ArrayList of custom Objects by property", "User: Kevin (http://stackoverflow.com/users/568508/kevin)", "answered Apr 25 '12 at 19:28", stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
 * </p>   
 *
 * @author fgomezaparicio
 * @since Java1.6
 * 
 */
public class ReflectiveComparator {
    public class FieldComparator implements Comparator<Object> {
        private String fieldName;

        public FieldComparator(String fieldName){
            this.fieldName = fieldName;
        }


        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public int compare(Object object1, Object object2) {
            try {
                Field field = object1.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);

                Comparable object1FieldValue = (Comparable) field.get(object1);
                Comparable object2FieldValue = (Comparable) field.get(object2);

                return object1FieldValue.compareTo(object2FieldValue);
            }catch (Exception e){
            	return 0;
            }

//            return 0;
        }
    }

    public class ListComparator implements Comparator<Object> {
        private String fieldName;

        public ListComparator(String fieldName) {
            this.fieldName = fieldName;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public int compare(Object object1, Object object2) {
            try {
                Field field = object1.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Comparable o1FieldValue = (Comparable) field.get(object1);
                Comparable o2FieldValue = (Comparable) field.get(object2);

                if (o1FieldValue == null){ return -1;}
                if (o2FieldValue == null){ return 1;}
                return o1FieldValue.compareTo(o2FieldValue);
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException("Field doesn't exist", e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Field inaccessible", e);
            }
        }
    }
}
