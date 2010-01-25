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
	
	private PathExpression classifier;
	private PathExpression measure;
	private AggregationFunction aggregate;
	
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
		return aggregate;
	}

	@Override
	public PathExpression getClassifier() {
		return classifier;
	}

	@Override
	public PathExpression getMeasure() {
		return measure;
	}

}
