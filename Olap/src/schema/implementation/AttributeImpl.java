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
	private String dataType;
	private String name;
	
	@Override
	public String getDataType() {
		return dataType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
