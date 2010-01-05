/**
 * 
 */
package schema.utility;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import schema.Attribute;
import schema.DataSchema;
import schema.Function;

/**
 * @author Reda
 *
 */
public class DataSchemaValidator {
	
	public static Boolean validateSchema(DataSchema schema){
		boolean ok;
		// The label of each attribute in a schema is distinct
		Iterator<Attribute> attribute_iterator = schema.getAttributeIterator();
		Set<String> attribute_set = new HashSet<String>();
		while(attribute_iterator.hasNext()){
			ok = attribute_set.add(attribute_iterator.next().getName());
			if(!ok){
				return false;
			}
		}
		
		// The label of each function in a schema is distinct 
		Iterator<Function> function_iterator = schema.getFunctionIterator();
		Set<String> function_set = new HashSet<String>();
		while(function_iterator.hasNext()){
			ok = function_set.add(function_iterator.next().getName());
			if(!ok){
				return false;
			}
		}
		
		// A data schema has exactly one root
		
		
		// Each node in a data schema has at least one path from the root of the schema 
		
		
		return true;
	}
	
}
