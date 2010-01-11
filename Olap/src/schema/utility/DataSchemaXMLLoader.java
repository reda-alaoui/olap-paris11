/**
 * 
 */
package schema.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import schema.Attribute;
import schema.DataSchema;
import schema.Function;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.DataSchemaImpl;
import schema.implementation.FunctionImpl;

/**
 * @author Reda
 *
 */
public class DataSchemaXMLLoader {
	
	private DataSchema schema;
	
	/**
	 * @param The name of the data schema to generate
	 * @param The xml file
	 * @throws JDOMException
	 * @throws IOException
	 */
	public DataSchemaXMLLoader(String name,File xml_file) throws JDOMException, IOException {
		Document document=null;
		SAXBuilder sxb = new SAXBuilder();
		document = sxb.build(xml_file);
	   
		Iterator<?> element_list = document.getDescendants();
		Object current;
		Element element;
		
		ArrayList<Attribute> attribute_list = new ArrayList<Attribute>();
		ArrayList<Function> function_list = new ArrayList<Function>();
		
		while(element_list.hasNext()){
			current = element_list.next();
			if(current instanceof Element){
				element = (Element) current;
				if(element.getName().equals("Attribute")){
					attribute_list.add(generateAttribute(element));
				}
				else if(element.getName().equals("Function")){
					function_list.add(generateFunction(element));
				}
			}
		}
		schema = new DataSchemaImpl(name, attribute_list, function_list);
	}
	
	public DataSchema getSchema(){
		return schema;
	}
	
	private Attribute generateAttribute(Element element){
		return new schema.implementation.AttributeImpl(element.getAttributeValue("name"), 
				DataType.valueOf(element.getAttributeValue("datatype")));
	}
	
	private Function generateFunction(Element element){
		Attribute domain = new AttributeImpl(element.getParentElement().getAttributeValue("name"), 
				DataType.valueOf(element.getParentElement().getAttributeValue("datatype")));
		
		Attribute range = new AttributeImpl(element.getChild("Attribute").getAttributeValue("name"), 
				DataType.valueOf(element.getChild("Attribute").getAttributeValue("datatype")));
		
		return new FunctionImpl(element.getAttributeValue("name"), 
				domain,
				range);
	}

}
