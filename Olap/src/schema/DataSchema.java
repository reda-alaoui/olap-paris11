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
	
	String getName();
	
	Iterator<Attribute> getAttributeIterator();
	
	Iterator<Function> getFunctionIterator();
	
	Attribute getAttributeByName(String attribute_name);
	
	Function getFunctionByRange(Attribute function_range);
	
	Function getFunctionByName(String function_name);
	
	Function getFunctionByDomain(Attribute function_domain);
}
