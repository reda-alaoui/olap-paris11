package query.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import query.FunctionReference;
import query.implementation.FunctionReferenceImpl;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.FunctionImpl;

/**
 * Test {@link FunctionReferenceImpl}
 * @author Julien.C
 */
public class FunctionReferenceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateFunctionReference() {
		FunctionReferenceImpl.createFunctionReference(
				new FunctionImpl(
					"q",
					new AttributeImpl("O", DataType.ID),
					new AttributeImpl("Quantity",DataType.INTEGER)
				)
		);
	}

	@Test
	public void testFunctionReferenceImpl() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testEqualsObject() {
		FunctionReference f1 = FunctionReferenceImpl.createFunctionReference(
				new FunctionImpl(
					"q",
					new AttributeImpl("O", DataType.ID),
					new AttributeImpl("Quantity",DataType.INTEGER)
				)
		);
		
		FunctionReference f2 = FunctionReferenceImpl.createFunctionReference(
				new FunctionImpl(
					"q",
					new AttributeImpl("O", DataType.ID),
					new AttributeImpl("Quantity",DataType.INTEGER)
				)
		);
		
		assertEquals(f1,f2);
		
		FunctionReference f3 = FunctionReferenceImpl.createFunctionReference(null);
		FunctionReference f4 = FunctionReferenceImpl.createFunctionReference(null);
		
		assertEquals(f3,f4);
	}

	@Test
	public void testGetDomain() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetRange() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetRefferedFunction() {
		fail("Not yet implemented"); // TODO
	}

}
