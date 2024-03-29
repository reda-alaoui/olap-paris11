/**
 * 
 */
package schema.test;

import static org.junit.Assert.*;
import org.junit.Test;

import schema.Function;
import schema.Attribute.*;
import schema.implementation.FunctionImpl;
import schema.implementation.AttributeImpl;;


/**
 * Test case for {@link FunctionImpl}
 * @author Reda
 */
public class FunctionImplTest {
	/**
	 * Test method for {@link schema.implementation.FunctionImpl#FunctionImpl(java.lang.String, schema.Attribute, schema.Attribute)}.
	 */
	@Test
	public void testFunctionImpl() {
		new FunctionImpl("f", new AttributeImpl("O",DataType.ID), new AttributeImpl("Quantity",DataType.INTEGER));
	}

	/**
	 * Test method for {@link schema.implementation.FunctionImpl#getName()}.
	 */
	@Test
	public void testGetName() {
		Function func = new FunctionImpl("f", new AttributeImpl("O",DataType.ID), new AttributeImpl("Quantity",DataType.INTEGER));
		assertEquals("f", func.getName());
	}

	/**
	 * Test method for {@link schema.implementation.FunctionImpl#getDomain()}.
	 */
	@Test
	public void testGetDomain() {
		Function func = new FunctionImpl("f", new AttributeImpl("O",DataType.ID), new AttributeImpl("Quantity",DataType.INTEGER));
		assertEquals(new AttributeImpl("O",DataType.ID),func.getDomain());
	}

	/**
	 * Test method for {@link schema.implementation.FunctionImpl#getRange()}.
	 */
	@Test
	public void testGetRange() {
		Function func = new FunctionImpl("f", new AttributeImpl("O",DataType.ID), new AttributeImpl("Quantity",DataType.INTEGER));
		assertEquals(new AttributeImpl("Quantity",DataType.INTEGER),func.getRange());
	}
}
