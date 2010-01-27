/**
 * 
 */
package schema.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import schema.Attribute;
import schema.Function;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.DataSchemaImpl;
import schema.implementation.FunctionImpl;


/**
 * Test case of {@link DataSchemaImpl}
 * @author Reda
 */
public class DataSchemaImplTest {

	@Test
	public void testDataSchemaImpl() {
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
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		new DataSchemaImpl(name,att_list,func_list);
		
	}

	@Test
	public void testGetName() {
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
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		assertEquals(name, schema.getName());
	}

	@Test
	public void testGetAttributeByName() {
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
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		
		assertEquals(new AttributeImpl("O", DataType.ID), schema.getAttributeByName("O"));
		assertEquals(new AttributeImpl("Quantity", DataType.INTEGER), schema.getAttributeByName("Quantity"));
		assertEquals(new AttributeImpl("Product", DataType.STRING), schema.getAttributeByName("Product"));
		assertEquals(new AttributeImpl("Supplier", DataType.STRING), schema.getAttributeByName("Supplier"));
		assertEquals(new AttributeImpl("Category", DataType.STRING), schema.getAttributeByName("Category"));
	}

	@Test
	public void testGetFunctionsByDomain() {
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
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		
		
		assertEquals(func_list.get(0), schema.getFunctionsByDomain(att_list.get(0)).next());
		assertEquals(func_list.get(2), schema.getFunctionsByDomain(att_list.get(2)).next());
	}

	@Test
	public void testGetFunctionByName() {
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
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		
		assertEquals(func_list.get(0), schema.getFunctionByName("q"));
		assertEquals(func_list.get(1), schema.getFunctionByName("h"));
		assertEquals(func_list.get(2), schema.getFunctionByName("h1"));
		assertEquals(func_list.get(3), schema.getFunctionByName("h2"));
		
	}

	@Test
	public void testGetFunctionsByRange() {
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
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		
		assertEquals(func_list.get(0), schema.getFunctionsByRange(att_list.get(1)).next());
		assertEquals(func_list.get(1), schema.getFunctionsByRange(att_list.get(2)).next());
		assertEquals(func_list.get(2), schema.getFunctionsByRange(att_list.get(3)).next());
		assertEquals(func_list.get(3), schema.getFunctionsByRange(att_list.get(4)).next());
	}

	@Test
	public void testGetAttributeIterator() {
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
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		
		Iterator<Attribute> att_it = schema.getAttributeIterator();
		int i=0;
		while(att_it.hasNext()){
			assertEquals(att_list.get(i), att_it.next());
			i++;
		}
	}

	@Test
	public void testGetFunctionIterator() {
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
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		
		Iterator<Function> func_it = schema.getFunctionIterator();
		int i=0;
		while(func_it.hasNext()){
			assertEquals(func_list.get(i), func_it.next());
			i++;
		}
	}
}
