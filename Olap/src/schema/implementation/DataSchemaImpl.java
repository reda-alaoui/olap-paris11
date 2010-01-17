/**
 * 
 */
package schema.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import schema.Attribute;
import schema.DataSchema;
import schema.Function;

/**
 * @author Reda
 *
 */
public class DataSchemaImpl implements DataSchema{

	private final String name;
	private final List<Attribute> attribute_list;
	private final List<Function> function_list;

	public DataSchemaImpl(String name, List<Attribute> attribute_list, List<Function> function_list) {
		this.name = name;
		this.attribute_list = new ArrayList<Attribute>(attribute_list);
		this.function_list = new ArrayList<Function>(function_list);
	}
	
	public String getName() {
		return name;
	}

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

	public Iterator<Function> getFunctionsByDomain(Attribute function_domain) {
		Iterator<Function> function_iterator = getFunctionIterator();
		List<Function> func_domain_iterator = new ArrayList<Function>();
		Function func;
		while(function_iterator.hasNext()){
			func = function_iterator.next();
			if(func.getDomain().equals(function_domain)){
				func_domain_iterator.add(func);
			}
		}
		return func_domain_iterator.iterator();
	}

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

	public Iterator<Function> getFunctionsByRange(Attribute function_range) {
		Iterator<Function> function_iterator = getFunctionIterator();
		List<Function> func_range_iterator = new ArrayList<Function>();
		Function func;
		while(function_iterator.hasNext()){
			func = function_iterator.next();
			if(func.getRange().equals(function_range)){
				func_range_iterator.add(func);
			}
		}
		return func_range_iterator.iterator();
	}

	public Iterator<Attribute> getAttributeIterator() {
		return attribute_list.iterator();
	}

	public Iterator<Function> getFunctionIterator() {
		return function_list.iterator();
	}
}
