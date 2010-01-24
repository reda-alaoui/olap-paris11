/**
 * 
 */
package query.implementation;

import java.util.Iterator;

import query.OlapQuery;
import query.PathExpression;
import query.QueryFactory.AggregationFunction;
import schema.Attribute;

/**
 * @author Reda
 *
 */
public class OlapQueryImpl implements OlapQuery {

	@Override
	public Iterator<Attribute> getDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Attribute> getRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AggregationFunction getAggregate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathExpression getClassifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathExpression getMeasure() {
		// TODO Auto-generated method stub
		return null;
	}

}
