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

	@Override
	public boolean equals(Object obj) {
	    if ( this == obj ) return true;

	    if ( !(obj instanceof Attribute) ) return false;

	    Attribute att = (Attribute)obj;

	    return att.getDataType().equals(dataType) && att.getName().equals(name);
	}
	
}
