/**
 * 
 */
package schema;

import java.util.Iterator;

/**
 * @author Reda
 *
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
	 * @param Name of the attribute to find
	 * @return The attribute having for name attribute_name
	 */
	Attribute getAttributeByName(String attribute_name);
	
	/**
	 * @param Range of the functions to find
	 * @return An iterator of functions having for range function_range
	 */
	Iterator<Function> getFunctionsByRange(Attribute function_range);
	
	/**
	 * @param Name of the function to find
	 * @return The function having for name function_name
	 */
	Function getFunctionByName(String function_name);
	
	/**
	 * @param Domain of the functions to find
	 * @return An iterator of functions having for domain function_domain
	 */
	Iterator<Function> getFunctionsByDomain(Attribute function_domain);
}
