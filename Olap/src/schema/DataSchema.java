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
	
	public String getName();
	
	public Iterator<Attribute> getAttributeIterator();
	
	public Iterator<Function> getFunctionIterator();
	
	public Attribute getAttributeByName(String attribute_name);
	
	public Function getFunctionByRange(Attribute function_range);
	
	public Function getFunctionByName(String function_name);
	
	public Function getFunctionByDomain(Attribute function_domain);
}
