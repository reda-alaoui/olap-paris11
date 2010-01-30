/**
 * 
 */
package schema.implementation;

import schema.Attribute;

/**
 * Implements {@link Attribute}
 * @author Reda 
 */
public class AttributeImpl implements Attribute {
	private final String name;
	private final DataType dataType;
	/**
	 * Attribute's constructor
	 * @param name : Attribute's name
	 * @param dataType : Attribute's data type
	 * @see DataType
	 */
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
	public int hashCode() {
		return (31 + name.hashCode()) * 31 + dataType.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	    if ( this == obj ) return true;

	    if ( !(obj instanceof Attribute) ) return false;

	    Attribute att = (Attribute)obj;

	    return att.getDataType().equals(dataType) && att.getName().equals(name);
	}
	
}
