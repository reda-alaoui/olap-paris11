/**
 * 
 */
package schema.test;

import static org.junit.Assert.*;
import org.junit.Test;

import schema.Attribute;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;


/**
 * Test case of {@link AttributeImpl}
 * @author Reda
 */
public class AttributeImplTest {
	/**
	 * Test method for {@link schema.implementation.AttributeImpl#AttributeImpl(java.lang.String, schema.Attribute.DataType)}.
	 */
	@Test
	public void testAttributeImpl() {
		new AttributeImpl("Quantity", DataType.INTEGER);
	}

	/**
	 * Test method for {@link schema.implementation.AttributeImpl#getDataType()}.
	 */
	@Test
	public void testGetDataType() {
		Attribute att = new AttributeImpl("City",DataType.STRING);
		assertEquals(DataType.STRING, att.getDataType());
		
		att = new AttributeImpl("City",null);
		assertEquals(null, att.getDataType());
	}

	/**
	 * Test method for {@link schema.implementation.AttributeImpl#getName()}.
	 */
	@Test
	public void testGetName() {
		Attribute att = new AttributeImpl("City",DataType.STRING);
		assertEquals("City", att.getName());
		
		att = new AttributeImpl(null,DataType.STRING);
		assertEquals(null, att.getName());
		
		att = new AttributeImpl("",DataType.STRING);
		assertEquals("", att.getName());
	}
}
