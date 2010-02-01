/**
 * 
 */
package schema;

import java.util.Iterator;

/**
 * A data schema is defined by a name, a list of attributes and a list of functions
 * @author Reda
 * @see Attribute
 * @see Function
 */
public interface DataSchema {
	
	/**
	 * @return DataSchema's name
	 */
	String getName();
	
	/**
	 * @return An iterator of all attributes contained in the data schema
	 */
	Iterator<Attribute> getAttributeIterator();
	
	/**
	 * @return An iterator of all functions contained in the data schema
	 */
	Iterator<Function> getFunctionIterator();
	
	/**
	 * @param attribute_name Name of the attribute to find
	 * @return The attribute having for name attribute_name
	 */
	Attribute getAttributeByName(String attribute_name);
	
	/**
	 * @param function_range Range of the functions to find
	 * @return An iterator of functions having for range function_range
	 */
	Iterator<Function> getFunctionsByRange(Attribute function_range);
	
	/**
	 * @param function_name Name of the function to find
	 * @return The function having for name function_name
	 */
	Function getFunctionByName(String function_name);
	
	/**
	 * @param function_domain Domain of the functions to find
	 * @return An iterator of functions having for domain function_domain
	 */
	Iterator<Function> getFunctionsByDomain(Attribute function_domain);
}
