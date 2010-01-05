/**
 * 
 */
package schema.implementation;

import schema.Attribute;

/**
 * @author Reda
 *
 */
public class AttributeImpl implements Attribute {
	private DataType dataType;
	private String name;
	
	public AttributeImpl(){

	}
	
	@Override
	public DataType getDataType() {
		return dataType;
	}

	@Override
	public String getName() {
		return name;
	}
}
