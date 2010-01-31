package query.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import query.Projection;
import query.implementation.ProjectionImpl;
import schema.Attribute;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
/**
 * Test {@link ProjectionImpl}
 * This class tests the projection implementation
 * @author Sylvestre
 */
public class ProjectionImplTest {

	private List<Attribute> selectedAttribute;
	
	private List<Attribute> domain;
	
	private Projection projection;
	

	@Before
	/**
	 * Here we have created :
	 * 	- selectedAttribute - attributes to project on
	 * 	- domain - domain of the operator
	 */
	public void setUp() throws Exception {
		domain = new ArrayList<Attribute>();
		//Defining domain attributes list
		domain.add(new AttributeImpl("O", DataType.ID));
		domain.add(new AttributeImpl("Date",DataType.INTEGER));
		domain.add(new AttributeImpl("Store", DataType.STRING));
		domain.add(new AttributeImpl("Product", DataType.STRING));
		
		selectedAttribute = new ArrayList<Attribute>();
		//Defining selected attributes list
		selectedAttribute.add(new AttributeImpl("Store", DataType.STRING));
		selectedAttribute.add(new AttributeImpl("Product", DataType.STRING));
		
		projection = ProjectionImpl.createProjection(selectedAttribute, domain);
		
	}


	@Test
	/**
	 * Testing function getDomain of projection
	 */
	public void testGetDomain() {
		List<Attribute> list1 = new ArrayList<Attribute>();
		Iterator<Attribute> it1 = projection.getDomain();
		while(it1.hasNext()){
			list1.add(it1.next());
		}
		
		assertEquals(list1, domain);
	}

	@Test
	/**
	 * Testing function getRange of projection
	 * projection range must be include in domain
	 */
	public void testGetRange() {
		List<Attribute> list1 = new ArrayList<Attribute>();
		Iterator<Attribute> it1 = projection.getRange();
		while(it1.hasNext()){
			list1.add(it1.next());
		}
		
		assertTrue(domain.containsAll(list1));
		
	}

}
