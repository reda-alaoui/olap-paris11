package query.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import query.OlapQuery;
import query.QueryFactory.AggregationFunction;
import query.implementation.OlapQueryImpl;
import query.implementation.QueryFactoryImpl;
import query.utility.OlapQueryValidator;
import schema.Attribute;
import schema.DataSchema;
import schema.Function;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.DataSchemaImpl;
import schema.implementation.FunctionImpl;
/**
 * Test {@link OlapQueryValidator}
 * This class tests the validation of a given query
 * @author Sylvestre
 */
public class OlapQueryValidatorTest {

	private OlapQuery query1;
	private OlapQuery query2;
	private OlapQuery query3;
	private OlapQuery query4;

	private DataSchema schema;

	private boolean validation;

	private OlapQueryValidator oqv;


	@Before
	/**
	 * We create a schema and queries
	 */
	public void setUp() throws Exception {

		String name = "S";
		ArrayList<Attribute> att_list = new ArrayList<Attribute>();
		ArrayList<Function> func_list = new ArrayList<Function>();
		//Defining attributes
		att_list.add(new AttributeImpl("O", DataType.ID));
		att_list.add(new AttributeImpl("Product", DataType.STRING));
		att_list.add(new AttributeImpl("Date", DataType.STRING));
		att_list.add(new AttributeImpl("Store", DataType.STRING));
		att_list.add(new AttributeImpl("Quantity",DataType.INTEGER));
		att_list.add(new AttributeImpl("Supplier", DataType.STRING));
		att_list.add(new AttributeImpl("Category", DataType.STRING));

		//Defining functions
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(1)));
		func_list.add(new FunctionImpl("q", att_list.get(0), att_list.get(4)));
		func_list.add(new FunctionImpl("f", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("g", att_list.get(0), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(1), att_list.get(5)));
		func_list.add(new FunctionImpl("h1", att_list.get(1), att_list.get(6)));

		schema = new DataSchemaImpl(name,att_list,func_list);

		QueryFactoryImpl q = new QueryFactoryImpl(schema);

		ArrayList<Attribute> attList = new ArrayList<Attribute>();
		attList.add(new AttributeImpl("Quantity",DataType.INTEGER));
		attList.add(new AttributeImpl("Product", DataType.STRING));
		//selected attributes
		ArrayList<Attribute> attList2 = new ArrayList<Attribute>();
		attList2.add(new AttributeImpl("Product", DataType.STRING));
		
		ArrayList<Attribute> attList3 = new ArrayList<Attribute>();
		attList3.add(new AttributeImpl("Quantity",DataType.INTEGER));
		attList3.add(new AttributeImpl("Product", DataType.STRING));
		attList3.add(new AttributeImpl("Store", DataType.STRING));
		attList3.add(new AttributeImpl("Date", DataType.STRING));
		
		//selected attributes
		ArrayList<Attribute> attList4 = new ArrayList<Attribute>();
		attList4.add(new AttributeImpl("Store", DataType.STRING));


		query1 = OlapQueryImpl.createOlapQuery(
				q.composition(q.projection(attList, attList2),
							  q.pairing(q.function("q"),q.function("h"))), 
				q.function("q"), 
				AggregationFunction.SUM);
		
		query2 = OlapQueryImpl.createOlapQuery(	
				q.projection(attList3, attList4),
				q.function("q"), 
				AggregationFunction.COUNT);
		
		query3 = OlapQueryImpl.createOlapQuery(	
				q.projection(attList3, attList4),
				q.pairing(q.function("h"),q.function("g")),
				AggregationFunction.AVG);

		query4 = OlapQueryImpl.createOlapQuery(
				q.pairing(q.function("h"),q.function("f")),
				q.projection(attList3, attList4),
				AggregationFunction.MAX);
		
		validation = false;

	}


	@Test
	/**
	 * Check if the query is valid for the schema 
	 */
	public void testOlapQueryValidator1() {
		oqv = new OlapQueryValidator(query1, schema);
	}

	@Test
	/**
	 * Check if the query is valid for the schema 
	 */
	public void testOlapQueryValidator2() {
		oqv = new OlapQueryValidator(query2, schema);
	}
	
	@Test
	/**
	 * Check if the query is valid for the schema 
	 */
	public void testOlapQueryValidator3() {
		oqv = new OlapQueryValidator(query3, schema);
	}
	
	@Test
	/**
	 * Check if the query is valid for the schema 
	 */
	public void testOlapQueryValidator4() {
		oqv = new OlapQueryValidator(query4, schema);
	}
	
	@Test
	/**
	 * Get the validation of query for a schema 
	 */
	public void testGetValidation1() {
		oqv = new OlapQueryValidator(query1, schema);
		validation = oqv.getValidation();
		assertTrue(validation);
	}

	@Test
	/**
	 * Get the validation of query for a schema 
	 */
	public void testGetValidation2() {
		oqv = new OlapQueryValidator(query2, schema);
		validation = oqv.getValidation();
		assertFalse(validation);
	}
	
	@Test
	/**
	 * Get the validation of query for a schema 
	 */
	public void testGetValidation3() {
		oqv = new OlapQueryValidator(query3, schema);
		validation = oqv.getValidation();
		assertFalse(validation);
	}
	
	@Test
	/**
	 * Get the validation of query for a schema 
	 */
	public void testGetValidation4() {
		oqv = new OlapQueryValidator(query4, schema);
		validation = oqv.getValidation();
		assertFalse(validation);
	}
}
