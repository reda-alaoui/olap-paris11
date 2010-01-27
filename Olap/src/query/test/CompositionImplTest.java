package query.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import query.Composition;
import query.PathExpression;
import query.implementation.CompositionImpl;
import query.implementation.FunctionReferenceImpl;
import schema.Attribute;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.FunctionImpl;
/**
 * @author Reda
 *
 */
public class CompositionImplTest {
	
	PathExpression leftOperand;
	
	PathExpression rightOperand;
	
	Composition composition1;
	
	Composition composition2;
	
	Composition composition3;

	@Before
	public void setUp() throws Exception {
		
		Attribute left_domain = new AttributeImpl("date", DataType.STRING);
		Attribute left_range = new AttributeImpl("month", DataType.STRING);
		Attribute right_domain = new AttributeImpl("O", DataType.ID);
		
		leftOperand = new FunctionReferenceImpl(
				new FunctionImpl("f1", left_domain, left_range));
		rightOperand = new FunctionReferenceImpl(
				new FunctionImpl("f", right_domain, left_domain));
		
		composition1 = CompositionImpl.createComposition(leftOperand, rightOperand);
		
		composition2 = CompositionImpl.createComposition(leftOperand, rightOperand);
		
		composition3 = CompositionImpl.createComposition(rightOperand, leftOperand);
		
	}

	@Test
	public void testEqualsObject() {
		assertTrue(composition1.equals(composition2));
		assertFalse(composition1.equals(composition3));
	}

	@Test
	public void testGetDomain() {
		List<Attribute> list1 = new ArrayList<Attribute>();
		Iterator<Attribute> it1 = composition1.getDomain();
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
	public void testGetLeftOperand() {
		assertEquals(composition1.getLeftOperand(), leftOperand);
	}

	@Test
	public void testGetRange() {
		List<Attribute> list1 = new ArrayList<Attribute>();
		Iterator<Attribute> it1 = composition1.getRange();
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

	@Test
	public void testGetRightOperand() {
		assertEquals(composition1.getRightOperand(), rightOperand);
	}

}
