package query.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import query.FunctionReference;
import query.implementation.FunctionReferenceImpl;
import schema.Attribute;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.FunctionImpl;

/**
 * Test {@link FunctionReferenceImpl}
 * @author Julien.C
 * @author Julien.T
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
		FunctionReference f1 = FunctionReferenceImpl.createFunctionReference(
				new FunctionImpl(
					"q",
					new AttributeImpl("O", DataType.ID),
					new AttributeImpl("Quantity",DataType.INTEGER)
				)
		);
		
		ArrayList<Attribute> domainList = new ArrayList<Attribute>();
		domainList.add(new AttributeImpl("O", DataType.ID));
		
		assertEquals(f1.getDomain().next(),domainList.iterator().next());
	}

	@Test
	public void testGetRange() {
		FunctionReference f1 = FunctionReferenceImpl.createFunctionReference(
				new FunctionImpl(
					"q",
					new AttributeImpl("O", DataType.ID),
					new AttributeImpl("Quantity",DataType.INTEGER)
				)
		);
		
		ArrayList<Attribute> rangeList = new ArrayList<Attribute>();
		rangeList.add(new AttributeImpl("Quantity",DataType.INTEGER));
		
		assertEquals(f1.getRange().next(),rangeList.iterator().next());
	}

	@Test
	public void testGetRefferedFunction() {
		FunctionReference f1 = FunctionReferenceImpl.createFunctionReference(
				new FunctionImpl(
					"q",
					new AttributeImpl("O", DataType.ID),
					new AttributeImpl("Quantity",DataType.INTEGER)
				)
		);
		
		FunctionImpl referredFunction = new FunctionImpl(
				"q",
				new AttributeImpl("O", DataType.ID),
				new AttributeImpl("Quantity",DataType.INTEGER)
		);
		
		assertEquals(f1.getRefferedFunction(),referredFunction);
	}

}
