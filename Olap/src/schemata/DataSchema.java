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
	
	public void addAttribute(Attribute attribute);
	
	public Vector<Function> getFunctions();
	
    public void addFunction(Function function);
}
