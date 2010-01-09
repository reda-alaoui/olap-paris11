/**
 * 
 */
package schema.implementation;

import java.util.ArrayList;
import java.util.Iterator;

import schema.Attribute;
import schema.DataSchema;
import schema.Function;

/**
 * @author Reda
 *
 */
public class DataSchemaImpl implements DataSchema {

	private String name;
	private ArrayList<Attribute> attribute_list;
	private ArrayList<Function> function_list;

	public DataSchemaImpl(String name, ArrayList<Attribute> attribute_list, ArrayList<Function> function_list) {
		this.name = name;
		this.attribute_list = attribute_list;
		this.function_list = function_list;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Attribute getAttributeByName(String attribute_name) {
		Iterator<Attribute> attribute_iterator = getAttributeIterator();
		Attribute att;
		while(attribute_iterator.hasNext()){
			att = attribute_iterator.next();
			if(att.getName().equals(attribute_name)){
				return att;
			}
		}
		return null;
	}

	@Override
	public Function getFunctionByDomain(Attribute function_domain) {
		Iterator<Function> function_iterator = getFunctionIterator();
		Function func;
		while(function_iterator.hasNext()){
			func = function_iterator.next();
			if(func.getDomain().equals(function_domain)){
				return func;
			}
		}
		return null;
	}

	@Override
	public Function getFunctionByName(String function_name) {
		Iterator<Function> function_iterator = getFunctionIterator();
		Function func;
		while(function_iterator.hasNext()){
			func = function_iterator.next();
			if(func.getName().equals(function_name)){
				return func;
			}
		}
		return null;
	}

	@Override
	public Function getFunctionByRange(Attribute function_range) {
		Iterator<Function> function_iterator = getFunctionIterator();
		Function func;
		while(function_iterator.hasNext()){
			func = function_iterator.next();
			if(func.getRange().equals(function_range)){
				return func;
			}
		}
		return null;
	}

	@Override
	public Iterator<Attribute> getAttributeIterator() {
		return attribute_list.iterator();
	}

	@Override
	public Iterator<Function> getFunctionIterator() {
		return function_list.iterator();
	}


}
