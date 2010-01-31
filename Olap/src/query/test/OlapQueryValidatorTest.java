package query.test;

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
 *
 */
public class OlapQueryValidatorTest {

	private OlapQuery query;

	private DataSchema schema;

	private boolean validation;

	private OlapQueryValidator oqv;


	@Before
	public void setUp() throws Exception {

		String name = "S";
		ArrayList<Attribute> att_list = new ArrayList<Attribute>();
		ArrayList<Function> func_list = new ArrayList<Function>();
		//Defining attributes
		att_list.add(new AttributeImpl("O", DataType.ID));
		att_list.add(new AttributeImpl("Product", DataType.STRING));
		att_list.add(new AttributeImpl("Quantity",DataType.INTEGER));
		att_list.add(new AttributeImpl("Supplier", DataType.STRING));
		att_list.add(new AttributeImpl("Category", DataType.STRING));

		//Defining functions
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(1)));
		func_list.add(new FunctionImpl("q", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h2", att_list.get(1), att_list.get(3)));
		func_list.add(new FunctionImpl("h1", att_list.get(1), att_list.get(4)));

		schema = new DataSchemaImpl(name,att_list,func_list);

		QueryFactoryImpl q = new QueryFactoryImpl(schema);

		ArrayList<Attribute> attList = new ArrayList<Attribute>();
		attList.add(new AttributeImpl("Quantity",DataType.INTEGER));
		attList.add(new AttributeImpl("Product", DataType.STRING));
		//selected attributes
		ArrayList<Attribute> attList2 = new ArrayList<Attribute>();
		attList2.add(new AttributeImpl("Product", DataType.STRING));


		query = OlapQueryImpl.createOlapQuery(q.composition(	
				q.projection(attList, attList2),
				q.pairing(
						q.function("q"),
						q.function("h")
				)), q.function("q"), AggregationFunction.SUM);

		validation = false;

	}


	@Test
	public void testOlapQueryValidator() {
		oqv = new OlapQueryValidator(query, schema);
	}

	@Test
	public void testGetValidation() {
		oqv = new OlapQueryValidator(query, schema);
		validation = oqv.getValidation();
		assertTrue(validation);
	}

}
