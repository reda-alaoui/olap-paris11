package query.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import query.Pairing;
import query.PathExpression;
import query.implementation.FunctionReferenceImpl;
import query.implementation.PairingImpl;
import schema.Attribute;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.FunctionImpl;
/**
 * Test {@link PairingImpl}
 */
public class PairingImplTest {

	private PathExpression leftOperand;
	
	private PathExpression rightOperand;
	
	private Pairing pairing1;
	
	private Pairing pairing2;
	
	
	@Before
	public void setUp() throws Exception {
		Attribute domain = new AttributeImpl("O", DataType.STRING);
		Attribute left_range = new AttributeImpl("Store", DataType.STRING);
		Attribute right_range = new AttributeImpl("Product", DataType.ID);
		
		leftOperand = new FunctionReferenceImpl(
				new FunctionImpl("g", domain, left_range));
		rightOperand = new FunctionReferenceImpl(
				new FunctionImpl("h", domain, right_range));
		
		pairing1 = PairingImpl.createPairing(leftOperand, rightOperand);
		
		pairing2 = PairingImpl.createPairing(leftOperand, rightOperand);
	}

	@Test
	public void testEqualsObject() {
		assertTrue(pairing1.equals(pairing2));
	}

	@Test
	public void testGetLeftOperand() {
		assertEquals(pairing1.getLeftOperand(), leftOperand);
	}

	@Test
	public void testGetRightOperand() {
		assertEquals(pairing1.getRightOperand(), rightOperand);
	}

	@Test
	public void testGetDomain() {
		List<Attribute> list1 = new ArrayList<Attribute>();
		Iterator<Attribute> it1 = pairing1.getDomain();
		while(it1.hasNext()){
			list1.add(it1.next());
		}
		
		List<Attribute> list2 = new ArrayList<Attribute>();
		Iterator<Attribute> it2 = rightOperand.getDomain();
		while(it2.hasNext()){
			list2.add(it2.next());
		}
		
		assertEquals(list1, list2);
	}

	@Test
	public void testGetRange() {
		List<Attribute> list12 = new ArrayList<Attribute>();
		Iterator<Attribute> it1 = leftOperand.getRange();
		while(it1.hasNext()){
			list12.add(it1.next());
		}
		
		Iterator<Attribute> it2 = rightOperand.getRange();
		while(it2.hasNext()){
			list12.add(it2.next());
		}
		
		List<Attribute> list3 = new ArrayList<Attribute>();
		Iterator<Attribute> it3 = pairing1.getRange();
		while(it3.hasNext()){
			list3.add(it3.next());
		}
		
		assertEquals(list12, list3);
	}

}
