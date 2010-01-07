/**
 * 
 */
package schema.utility;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import schema.Attribute;
import schema.DataSchema;
import schema.Function;

/**
 * @author Reda
 *
 */
public class DataSchemaValidator {
	private DataSchema schema;
	private boolean validation;
	
	public DataSchemaValidator(DataSchema schema){
		this.schema = schema;
		this.validation = validate();
	}
	
	public boolean getValidation(){
		return validation;
	}
	
	private Boolean validate(){
		return isAttributeNameDistinct()&&isFunctionNameDistinct()&&isExactlyOneRoot()&&isOnePathDistant();
	}
	
	// The label of each attribute in a schema is distinct
	private boolean isAttributeNameDistinct(){
		boolean ok;
		Iterator<Attribute> attribute_iterator = schema.getAttributeIterator();
		Set<String> attribute_set = new HashSet<String>();
		while(attribute_iterator.hasNext()){
			ok = attribute_set.add(attribute_iterator.next().getName());
			if(!ok){
				return false;
			}
		}
		return true;
	}
	
	// The label of each function in a schema is distinct 
	private boolean isFunctionNameDistinct(){
		boolean ok;
		Iterator<Function> function_iterator = schema.getFunctionIterator();
		Set<String> function_set = new HashSet<String>();
		while(function_iterator.hasNext()){
			ok = function_set.add(function_iterator.next().getName());
			if(!ok){
				return false;
			}
		}
		return true;
	}
	
	// A data schema has exactly one root
	private boolean isExactlyOneRoot(){
		int i=0;
		Iterator<Attribute> attribute_iterator = schema.getAttributeIterator();
		Attribute att;
		while(attribute_iterator.hasNext()){
			att = attribute_iterator.next();
			if(schema.getFunctionByDomain(att)!= null && schema.getFunctionByRange(att) == null){
				i++;
			}
		}
		return i==1;
	}
	
	// Each node in a data schema has at least one path from the root of the schema 
	private boolean isOnePathDistant(){
		// First, we find the root
		boolean root_found = false;
		Iterator<Attribute> attribute_iterator = schema.getAttributeIterator();
		Attribute att;
		Attribute root = null;
		
		while(attribute_iterator.hasNext()&&!root_found){
			att = attribute_iterator.next();
			if(schema.getFunctionByDomain(att)!= null && schema.getFunctionByRange(att) == null){
				root=att;
				root_found = true;
			}
		}
		
		// Now we test that each node is linked to the root
		boolean root_reached;
		attribute_iterator = schema.getAttributeIterator();
		Function func;
		
		while(attribute_iterator.hasNext()){
			att = attribute_iterator.next();
			if(!att.equals(root)){
				root_reached = false;
				
				while(!root_reached){
					func = schema.getFunctionByRange(att);
					if(func == null){
						return false;
					}
					else if(func.getDomain().equals(root)){
						root_reached = true;
					}
					else{
						att = func.getDomain();
					}
				}
			}
		}
		
		return true;
	}
}
