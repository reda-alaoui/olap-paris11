package query.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import query.OlapQuery;
import query.QueryFactory.AggregationFunction;
import query.implementation.OlapQueryImpl;
import query.implementation.QueryFactoryImpl;
import query.utility.PathExpressionValidationException;
import schema.Attribute;
import schema.Function;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.DataSchemaImpl;
import schema.implementation.FunctionImpl;
/**
 * Test {@link OlapQueryImpl}
 * @author Julien.C
 */
public class OlapQueryImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private QueryFactoryImpl q;

	@Before
	public void setUp() throws Exception {
		String name = "S";
		ArrayList<Attribute> att_list = new ArrayList<Attribute>();
		ArrayList<Function> func_list = new ArrayList<Function>();
		//Defining attributes
		att_list.add(new AttributeImpl("O", DataType.ID));
		att_list.add(new AttributeImpl("Quantity",DataType.INTEGER));
		att_list.add(new AttributeImpl("Product", DataType.STRING));
		att_list.add(new AttributeImpl("Supplier", DataType.STRING));
		att_list.add(new AttributeImpl("Category", DataType.STRING));
		
		//Defining functions
		func_list.add(new FunctionImpl("q", att_list.get(0), att_list.get(1)));
		func_list.add(new FunctionImpl("f", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("f1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("f2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		
		q = new QueryFactoryImpl(schema);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateOlapQuery() {
		
		ArrayList<Attribute> attList = new ArrayList<Attribute>();
		attList.add(new AttributeImpl("Quantity",DataType.INTEGER));
		attList.add(new AttributeImpl("Product", DataType.STRING));
		//selected attributes
		ArrayList<Attribute> attList2 = new ArrayList<Attribute>();
		attList2.add(new AttributeImpl("Product", DataType.STRING));
		
		try {
			OlapQueryImpl.createOlapQuery(q.composition(	
					q.projection(attList, attList2),
					q.pairing(
							q.function("q"),
							q.function("f")
					)), q.function("q"), AggregationFunction.COUNT);
		} catch (PathExpressionValidationException e) {
			fail();
		}
		
	}

	@Test
	public void testGetAggregate() {
		ArrayList<Attribute> attList = new ArrayList<Attribute>();
		attList.add(new AttributeImpl("Quantity",DataType.INTEGER));
		attList.add(new AttributeImpl("Product", DataType.STRING));
		//selected attributes
		ArrayList<Attribute> attList2 = new ArrayList<Attribute>();
		attList2.add(new AttributeImpl("Product", DataType.STRING));
		
		try {
			OlapQuery query = OlapQueryImpl.createOlapQuery(q.composition(	
					q.projection(attList, attList2),
					q.pairing(
							q.function("q"),
							q.function("f")
					)), q.function("q"), AggregationFunction.COUNT);
			
			assertEquals(query.getAggregate(), AggregationFunction.COUNT);
			
			OlapQuery query2 = OlapQueryImpl.createOlapQuery(null,null,null);
			assertEquals(query2.getAggregate(),null);
			
		} catch (PathExpressionValidationException e) {
		}
	}

	@Test
	public void testGetClassifier() {
		ArrayList<Attribute> attList = new ArrayList<Attribute>();
		attList.add(new AttributeImpl("Quantity",DataType.INTEGER));
		attList.add(new AttributeImpl("Product", DataType.STRING));
		//selected attributes
		ArrayList<Attribute> attList2 = new ArrayList<Attribute>();
		attList2.add(new AttributeImpl("Product", DataType.STRING));
		
		try {
			OlapQuery query = OlapQueryImpl.createOlapQuery(
					q.composition(	
						q.projection(attList, attList2),
						q.pairing(
								q.function("q"),
								q.function("f")
						)
					), 
					q.function("q"), 
					AggregationFunction.COUNT);
			
			assertEquals(query.getClassifier(), 
					q.composition(	
						q.projection(attList, attList2),
						q.pairing(
							q.function("q"),
							q.function("f")
						)
					)
				);
			
			OlapQuery query2 = OlapQueryImpl.createOlapQuery(null,null,null);
			assertEquals(query2.getClassifier(),null);
			
		} catch (PathExpressionValidationException e) {
		}
	}

	@Test
	public void testGetMeasure() {
		ArrayList<Attribute> attList = new ArrayList<Attribute>();
		attList.add(new AttributeImpl("Quantity",DataType.INTEGER));
		attList.add(new AttributeImpl("Product", DataType.STRING));
		//selected attributes
		ArrayList<Attribute> attList2 = new ArrayList<Attribute>();
		attList2.add(new AttributeImpl("Product", DataType.STRING));
		
		try {
			OlapQuery query = OlapQueryImpl.createOlapQuery(
					q.composition(	
						q.projection(attList, attList2),
						q.pairing(
								q.function("q"),
								q.function("f")
						)
					), 
					q.function("q"), 
					AggregationFunction.COUNT);
			
			assertEquals(query.getMeasure(), q.function("q"));
			
			OlapQuery query2 = OlapQueryImpl.createOlapQuery(null,null,null);
			assertEquals(query2.getMeasure(), null);
			
		} catch (PathExpressionValidationException e) {
		}
	}

}
