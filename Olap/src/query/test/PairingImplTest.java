package query.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	
	private Pairing pairing3;
	
	
	@Before
	public void setUp() throws Exception {
		Attribute left_domain = new AttributeImpl("Store", DataType.STRING);
		Attribute left_range = new AttributeImpl("Product", DataType.STRING);
		Attribute right_domain = new AttributeImpl("O", DataType.ID);
		
		leftOperand = new FunctionReferenceImpl(
				new FunctionImpl("g", left_domain, left_range));
		rightOperand = new FunctionReferenceImpl(
				new FunctionImpl("h", right_domain, left_domain));
		
		pairing1 = PairingImpl.createPairing(leftOperand, rightOperand);
		
		pairing2 = PairingImpl.createPairing(leftOperand, rightOperand);
		
		pairing3 = PairingImpl.createPairing(leftOperand, rightOperand);
	}

	@Test
	public void testEqualsObject() {
		assertTrue(pairing1.equals(pairing2));
		assertFalse(pairing1.equals(pairing3));
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
		List<Attribute> list1 = new ArrayList<Attribute>();
		Iterator<Attribute> it1 = pairing1.getRange();
		while(it1.hasNext()){
			list1.add(it1.next());
		}
		
		List<Attribute> list2 = new ArrayList<Attribute>();
		Iterator<Attribute> it2 = leftOperand.getRange();
		while(it2.hasNext()){
			list2.add(it2.next());
		}
		
		assertEquals(list1, list2);
	}

}
