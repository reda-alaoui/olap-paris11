/**
 * 
 */
package schemata;

import java.util.Vector;

/**
 * @author Reda
 *
 */
public interface DataSchema {
	
	public String getName();
	
	public void setName(String name);
	
	public Vector<Attribute> getAttributes();
	
	public void setAttributeList(Vector<Attribute> attribute_list);
	
	public Vector<Function> getFunctions();
	
    public void setFunctionList(Vector<Function> function_list);
}
