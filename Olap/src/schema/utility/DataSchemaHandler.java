/**
 * 
 */
package schema.utility;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import schema.Attribute;
import schema.Function;
import schema.Attribute.DataType;
import schema.implementation.AttributeImpl;
import schema.implementation.FunctionImpl;

/**
 * @author Reda
 *
 */
public class DataSchemaHandler extends DefaultHandler{
	
	private List<Attribute> attribute_list;
	private List<Function> function_list;
	private String name;
	
	public DataSchemaHandler(){
		super();
		attribute_list = new ArrayList<Attribute>();
		function_list = new ArrayList<Function>();
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Parsing started");
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended");
		System.out.println();
		System.out.println("Attributes parsed");
		for(Attribute att : attribute_list){
			System.out.println(att.getName());
		}
		System.out.println();
		System.out.println("Functions parsed");
		for(Function func : function_list){
			System.out.println(func.getName());
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(qName.equals("schema")){
			name = attributes.getValue("name");
		}
		else if(qName.equals("attribute")){
			attribute_list.add(new AttributeImpl(attributes.getValue("name"), 
					DataType.valueOf(attributes.getValue("type"))));
		}
		else if(qName.equals("function")){
			Attribute domain = null;
			Attribute range = null;
			for(Attribute att : attribute_list){
				if(att.getName().equals(attributes.getValue("domain"))){
					domain = att;
				}
				else if(att.getName().equals(attributes.getValue("range"))){
					range = att;
				}
			}
			
			function_list.add(new FunctionImpl(attributes.getValue("name"), domain, range));
		}
	}

	/**
	 * @return the attribute_list
	 */
	public List<Attribute> getAttribute_list() {
		return attribute_list;
	}

	/**
	 * @return the function_list
	 */
	public List<Function> getFunction_list() {
		return function_list;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
