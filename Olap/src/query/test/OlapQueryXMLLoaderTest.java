package query.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import query.OlapQuery;
import query.PathExpression;
import query.QueryFactory.AggregationFunction;
import query.implementation.QueryFactoryImpl;
import query.utility.OlapQueryXMLLoader;
import schema.Attribute;
import schema.Function;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.DataSchemaImpl;
import schema.implementation.FunctionImpl;
/**
 * Test {@link OlapQueryXMLLoader}
 * @author Julien.C
 */
public class OlapQueryXMLLoaderTest {
	
	private DataSchemaImpl schema;

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

	@Test
	public void testOlapQueryXMLLoader() {
		new OlapQueryXMLLoader(schema, new File("xml/sample_query.xml"));
	}

	@Test
	public void testGetQuery() {
		try {
			OlapQuery query = new OlapQueryXMLLoader(schema, new File("xml/sample_query.xml")).getQuery();
			QueryFactoryImpl q =new QueryFactoryImpl(schema);
			
			ArrayList<Attribute> attList1 = new ArrayList<Attribute>();
			attList1.add(new AttributeImpl("Supplier", DataType.STRING));
			attList1.add(new AttributeImpl("Category", DataType.STRING));
			ArrayList<Attribute> attList2 = new ArrayList<Attribute>();
			attList2.add(new AttributeImpl("Supplier", DataType.STRING));
			
			PathExpression classifier = q.composition(
											q.projection(attList1, attList2),
											q.pairing(
												q.composition(
														q.function("f1"),
														q.function("f")
												),
												q.composition(
														q.function("f2"),
														q.function("f")
												)
											)
										);
			
			PathExpression measure = q.function("q");
			OlapQuery query2 = q.olapQuery(classifier, measure, AggregationFunction.COUNT);
			
			assertEquals(query, query2);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
