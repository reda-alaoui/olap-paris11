/**
 * 
 */
package schema.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import schema.Attribute;
import schema.DataSchema;
import schema.Function;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.DataSchemaImpl;
import schema.implementation.FunctionImpl;
import schema.utility.DataSchemaValidator;
import schema.utility.DataSchemaXMLLoader;


/**
 * @author Reda
 *
 */
public class DataSchemaValidatorTest {
	/**
	 * Test method for {@link schema.utility.DataSchemaValidator#DataSchemaValidator(schema.DataSchema)}.
	 */
	@Test
	public void testDataSchemaValidator() {
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
		new DataSchemaValidator(schema);
	}

	/**
	 * Test method for {@link schema.utility.DataSchemaValidator#getValidation()}.
	 */
	@Test
	public void testGetValidation1() {
		//Validation of a valid schema
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
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		DataSchemaValidator validator = new DataSchemaValidator(schema);
		
		assertTrue(validator.getValidation());
	}
	
	/**
	 * Test method for {@link schema.utility.DataSchemaValidator#getValidation()}.
	 */
	@Test
	public void testGetValidation2() {
		// Validation of a schema containing two attributes having the same name
		String name = "S";
		ArrayList<Attribute> att_list = new ArrayList<Attribute>();
		ArrayList<Function> func_list = new ArrayList<Function>();
		//Defining attributes
		att_list.add(new AttributeImpl("O", DataType.ID));
		att_list.add(new AttributeImpl("Quantity",DataType.INTEGER));
		att_list.add(new AttributeImpl("Product", DataType.STRING));
		att_list.add(new AttributeImpl("Supplier", DataType.STRING));
		att_list.add(new AttributeImpl("Category", DataType.STRING));
		att_list.add(new AttributeImpl("O", DataType.STRING));
		
		//Defining functions
		func_list.add(new FunctionImpl("q", att_list.get(0), att_list.get(1)));
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		DataSchemaValidator validator = new DataSchemaValidator(schema);
		
		assertFalse(validator.getValidation());
	}
	
	/**
	 * Test method for {@link schema.utility.DataSchemaValidator#getValidation()}.
	 */
	@Test
	public void testGetValidation3() {
		// Validation of a schema containing two functions having the same name
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
		func_list.add(new FunctionImpl("h2", att_list.get(0), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		DataSchemaValidator validator = new DataSchemaValidator(schema);
		
		assertFalse(validator.getValidation());
	}
	
	/**
	 * Test method for {@link schema.utility.DataSchemaValidator#getValidation()}.
	 */
	@Test
	public void testGetValidation4() {
		//Validation of a schema containing more than one root
		String name = "S";
		ArrayList<Attribute> att_list = new ArrayList<Attribute>();
		ArrayList<Function> func_list = new ArrayList<Function>();
		//Defining attributes
		att_list.add(new AttributeImpl("O", DataType.ID));
		att_list.add(new AttributeImpl("Quantity",DataType.INTEGER));
		att_list.add(new AttributeImpl("Product", DataType.STRING));
		att_list.add(new AttributeImpl("Supplier", DataType.STRING));
		att_list.add(new AttributeImpl("Category", DataType.STRING));
		att_list.add(new AttributeImpl("Second Root", DataType.ID));
		
		//Defining functions
		func_list.add(new FunctionImpl("q", att_list.get(0), att_list.get(1)));
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		func_list.add(new FunctionImpl("test", att_list.get(5), att_list.get(1)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		DataSchemaValidator validator = new DataSchemaValidator(schema);
		
		assertFalse(validator.getValidation());
	}
	/**
	 * Test method for {@link schema.utility.DataSchemaValidator#getValidation()}.
	 */
	@Test
	public void testGetValidation5() {
		//Validation of a cyclic schema
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
		func_list.add(new FunctionImpl("q1", att_list.get(1), att_list.get(3)));
		func_list.add(new FunctionImpl("g", att_list.get(3), att_list.get(2)));
		func_list.add(new FunctionImpl("u", att_list.get(2), att_list.get(1)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		DataSchemaValidator validator = new DataSchemaValidator(schema);
		
		assertFalse(validator.getValidation());
	}
	/**
	 * Test method for {@link schema.utility.DataSchemaValidator#getValidation()}.
	 */
	@Test
	public void testGetValidation6() {
		//Validation of a schema containing a node not linked to the root
		String name = "S";
		ArrayList<Attribute> att_list = new ArrayList<Attribute>();
		ArrayList<Function> func_list = new ArrayList<Function>();
		//Defining attributes
		att_list.add(new AttributeImpl("O", DataType.ID));
		att_list.add(new AttributeImpl("Quantity",DataType.INTEGER));
		att_list.add(new AttributeImpl("Product", DataType.STRING));
		att_list.add(new AttributeImpl("Supplier", DataType.STRING));
		att_list.add(new AttributeImpl("Category", DataType.STRING));
		att_list.add(new AttributeImpl("Disconnected node", DataType.ID));
		
		//Defining functions
		func_list.add(new FunctionImpl("q", att_list.get(0), att_list.get(1)));
		func_list.add(new FunctionImpl("h", att_list.get(0), att_list.get(2)));
		func_list.add(new FunctionImpl("h1", att_list.get(2), att_list.get(3)));
		func_list.add(new FunctionImpl("h2", att_list.get(2), att_list.get(4)));
		
		DataSchemaImpl schema = new DataSchemaImpl(name,att_list,func_list);
		DataSchemaValidator validator = new DataSchemaValidator(schema);
		
		assertFalse(validator.getValidation());
	}
	
	/**
	 * Test method for {@link schema.utility.DataSchemaValidator#getValidation()}.
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@Test
	public void testGetValidation7() {
		DataSchemaXMLLoader loader = new DataSchemaXMLLoader(new File("xml/sample_schema.xml"));
		DataSchema schema = loader.getSchema();
		DataSchemaValidator validator = new DataSchemaValidator(schema);
		
		assertFalse(validator.getValidation());
	}
}
