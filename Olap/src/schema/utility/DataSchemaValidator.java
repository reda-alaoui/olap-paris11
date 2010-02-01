/**
 * 
 */
package schema.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import schema.Attribute;
import schema.DataSchema;
import schema.Function;
import schema.implementation.DataSchemaImpl;

/**
 * Validates a {@link DataSchema}
 * @author Reda
 */
public class DataSchemaValidator {
	private final DataSchema schema;
	private final boolean validation;
	
	/** 
	 * @param schema The schema to validate
	 */
	public DataSchemaValidator(DataSchema schema){
		this.schema = schema;
		this.validation = validate();
	}
	
	/**
	 * @return Schema's validation
	 */
	public boolean getValidation(){
		return validation;
	}
	
	/**
	 * Validates the {@link DataSchema} schema
	 * @return True if schema is valid, False if not
	 */
	private Boolean validate(){
		return isAttributeNameDistinct()&&isFunctionNameDistinct()&&isExactlyOneRoot()&&isAcyclic()&&isOnePathDistant();
	}
	
	/**
	 * @return True if the label of each attribute in the schema is distinct, False if not
	 */
	private boolean isAttributeNameDistinct(){
		boolean ok;
		Iterator<Attribute> attribute_iterator = schema.getAttributeIterator();
		Set<String> attribute_set = new HashSet<String>();
		while(attribute_iterator.hasNext()){
			ok = attribute_set.add(attribute_iterator.next().getName());
			if(!ok){
				System.out.println("Two attributes have the same label");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return True if the label of each function in the schema is distinct, False if not
	 */
	private boolean isFunctionNameDistinct(){
		boolean ok;
		Iterator<Function> function_iterator = schema.getFunctionIterator();
		Set<String> function_set = new HashSet<String>();
		while(function_iterator.hasNext()){
			ok = function_set.add(function_iterator.next().getName());
			if(!ok){
				System.out.println("Two functions have the same label");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return True if the schema has exactly one root, False if not
	 */
	private boolean isExactlyOneRoot(){
		int i=0;
		Iterator<Attribute> attribute_iterator = schema.getAttributeIterator();
		Attribute att;
		while(attribute_iterator.hasNext()){
			att = attribute_iterator.next();
			if(schema.getFunctionsByDomain(att).hasNext() && !schema.getFunctionsByRange(att).hasNext()){
				i++;
			}
		}
		if(i!=1){
			System.out.println("Wrong number of roots");
		}
		return i==1;
	}
	
	/**
	 * @return True if the schema is acyclic, False if not
	 */
	private boolean isAcyclic(){
		//	First, we create a temporary schema, clone of the genuine schema
		Iterator<Attribute> att_iterator = schema.getAttributeIterator();
		Iterator<Function> func_iterator = schema.getFunctionIterator();
		List<Attribute> att_list = new ArrayList<Attribute>();
		List<Function> func_list = new ArrayList<Function>();
		Attribute att;
		Function func;
		
		while(att_iterator.hasNext()){
			att_list.add(att_iterator.next());
		}
		while(func_iterator.hasNext()){
			func_list.add(func_iterator.next());
		}
		//	Creation of the temporary schema
		DataSchema temp_schema = new DataSchemaImpl("", att_list, func_list);
		
		att_iterator = temp_schema.getAttributeIterator();
		
		//	While schema's list of attributes is not empty
		while(temp_schema.getAttributeIterator().hasNext()){
			
			//	If the Attribute iterator has next value
			if(att_iterator.hasNext()){
				att = att_iterator.next();
				
				//	If the attribute parsed is a leaf (it has no target)
				if(!temp_schema.getFunctionsByDomain(att).hasNext()){
					
					/*	Removing the attribute and all functions
					targeting it from the temporary schema*/
					func_iterator = temp_schema.getFunctionIterator();
					while(func_iterator.hasNext()){
						func = func_iterator.next();
						if(func.getRange().equals(att)){
							func_iterator.remove();
						}
					}
					att_iterator.remove();
					
					//	Reinitializing the Attribute iterator
					att_iterator = temp_schema.getAttributeIterator();
				}
			}
			/* 
			 * If Attribute iterator end is reached, the temporary schema contains
			 * remaining attributes which are domains of the remaining functions.
			 * Therefore the temporary schema is cyclic and so is the genuine schema.
			 */
			else{
				System.out.println("The schema is cyclic");
				return false;
			}
		}
		/*	
		 * The loop stopped therefore there is no more attribute in the temporary schema
		 * and that proves that the genuine schema is acyclic
		 */
		
		return true;
	}
	
	/**
	 * @return True if each node of the schema is at least one path from the root, False if not
	 */
	private boolean isOnePathDistant(){
		// First, we find the root
		boolean root_found = false;
		Iterator<Attribute> attribute_iterator = schema.getAttributeIterator();
		Attribute att;
		Attribute root = null;
		
		while(attribute_iterator.hasNext()&&!root_found){
			att = attribute_iterator.next();
			if(schema.getFunctionsByDomain(att).hasNext() && !schema.getFunctionsByRange(att).hasNext()){
				root=att;
				root_found = true;
			}
		}
		
		// Now we test that each node is linked to the root
		boolean root_reached;
		attribute_iterator = schema.getAttributeIterator();
		Iterator<Function> func_iterator;
		Function func;
		
		while(attribute_iterator.hasNext()){
			att = attribute_iterator.next();
			if(!att.equals(root)){
				root_reached = false;
				
				while(!root_reached){
					func_iterator = schema.getFunctionsByRange(att);
					if(func_iterator.hasNext()){
						func = func_iterator.next();
					    if(func.getDomain().equals(root)){
							root_reached = true;
						}
						else{
							att = func.getDomain();
						}
					}
					else{
						System.out.println("At least one node is not linked to the root");
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
