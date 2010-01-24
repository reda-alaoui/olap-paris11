/**
 * 
 */
package query.implementation;

import java.util.List;

import query.Composition;
import query.FunctionReference;
import query.OlapQuery;
import query.Pairing;
import query.PathExpression;
import query.Projection;
import query.QueryFactory;
import schema.Attribute;
import schema.DataSchema;

/**
 * @author Reda
 *
 */
public class QueryFactoryImpl implements QueryFactory {
	
	private DataSchema schema;
	
	public QueryFactoryImpl(DataSchema schema){
		this.schema = schema;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#composition(query.PathExpression, query.PathExpression)
	 */
	@Override
	public Composition composition(PathExpression p1, PathExpression p2) {
		return CompositionImpl.createComposition(p1, p2);
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#function(java.lang.String)
	 */
	@Override
	public FunctionReference function(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#olapQuery(query.PathExpression, query.PathExpression, query.QueryFactory.AggregationFunction)
	 */
	@Override
	public OlapQuery olapQuery(PathExpression c, PathExpression m,
			AggregationFunction op) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#pairing(query.PathExpression, query.PathExpression)
	 */
	@Override
	public Pairing pairing(PathExpression p1, PathExpression p2) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#projection(query.PathExpression, java.util.List)
	 */
	@Override
	public Projection projection(PathExpression p, List<Attribute> selectList) {
		// TODO Auto-generated method stub
		return null;
	}

}
