/**
 * 
 */
package query.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import query.Composition;
import query.FunctionReference;
import query.Pairing;
import query.Projection;
import query.implementation.CompositionImpl;
import query.implementation.FunctionReferenceImpl;
import query.implementation.PairingImpl;
import query.implementation.ProjectionImpl;
import query.implementation.QueryFactoryImpl;
import query.utility.PathExpressionValidationException;
import schema.Attribute;
import schema.Function;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.DataSchemaImpl;
import schema.implementation.FunctionImpl;

/**
 * Test each method of QueryFactoryImpl
 */
public class QueryFactoryImplTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private DataSchemaImpl schema;

	/**
	 * @throws java.lang.Exception
	 */
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
		
		schema = new DataSchemaImpl(name,att_list,func_list);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link query.implementation.QueryFactoryImpl#QueryFactoryImpl(schema.DataSchema)}.
	 */
	@Test
	public void testQueryFactoryImpl() {
		new QueryFactoryImpl(schema);
	}

	/**
	 * Test method for {@link query.implementation.QueryFactoryImpl#composition(query.PathExpression, query.PathExpression)}.
	 */
	@Test
	public void testComposition() {
		try {
			QueryFactoryImpl q = new QueryFactoryImpl(schema);
			Composition c = q.composition(q.function("f1"), q.function("f"));
			Composition c2 = CompositionImpl.createComposition(
					q.function("f1"), q.function("f"));
			assertEquals(c, c2);
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link query.implementation.QueryFactoryImpl#function(java.lang.String)}.
	 * @throws PathExpressionValidationException 
	 */
	@Test
	public void testFunction() throws PathExpressionValidationException {
		QueryFactoryImpl q = new QueryFactoryImpl(schema);
		FunctionReference fr = q.function("f1");
		
		FunctionReference fr2 = FunctionReferenceImpl.createFunctionReference(schema.getFunctionByName("f1"));
		assertEquals(fr, fr2);
	}

	/**
	 * Test method for {@link query.implementation.QueryFactoryImpl#olapQuery(query.PathExpression, query.PathExpression, query.QueryFactory.AggregationFunction)}.
	 */
	@Test
	public void testOlapQuery() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link query.implementation.QueryFactoryImpl#pairing(query.PathExpression, query.PathExpression)}.
	 */
	@Test
	public void testPairing() {
		try {
			QueryFactoryImpl q = new QueryFactoryImpl(schema);			
			Pairing p = q.pairing(q.function("q"), q.function("f"));
			Pairing p2 = PairingImpl.createPairing(
										q.function("q"),
										q.function("f")
									);
			assertEquals(p, p2);
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link query.implementation.QueryFactoryImpl#projection(query.PathExpression, java.util.List)}.
	 * @throws PathExpressionValidationException 
	 */
	@Test
	public void testProjection() throws PathExpressionValidationException {
		QueryFactoryImpl q = new QueryFactoryImpl(schema);
		ArrayList<Attribute> attList = new ArrayList<Attribute>();
		attList.add(new AttributeImpl("Quantity",DataType.INTEGER));
		attList.add(new AttributeImpl("Product", DataType.STRING));
		
		Projection proj = q.projection(attList,null);
		Projection proj2 = ProjectionImpl.createProjection(attList,null);
		
		assertEquals(proj, proj2);
	}

}