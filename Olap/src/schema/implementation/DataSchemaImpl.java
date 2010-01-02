/**
 * 
 */
package schema.implementation;

import java.util.Vector;

import schema.Attribute;
import schema.DataSchema;
import schema.Function;

/**
 * @author Reda
 *
 */
public class DataSchemaImpl implements DataSchema {
	
	private String name;
	private Vector<Attribute> attributeList;
	private Vector<Function> functionList;
	
	public DataSchemaImpl(){
		
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Vector<Attribute> getAttributes() {
		return attributeList;
	}

	@Override
	public Vector<Function> getFunctions() {
		return functionList;
	}

	@Override
	public void addAttribute(Attribute attribute) {
		this.attributeList.add(attribute);
	}

	@Override
	public void addFunction(Function function) {
		this.functionList.add(function);
	}
	
	
}
