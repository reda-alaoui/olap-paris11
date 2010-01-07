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
	private String name;
	private DataType dataType;
	
	public AttributeImpl(String name, DataType dataType) {
		this.name = name;
		this.dataType = dataType;
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
