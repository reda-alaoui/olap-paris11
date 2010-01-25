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
import query.utility.PathExpressionValidationException;
import query.utility.PathExpressionValidator;
import schema.Attribute;
import schema.DataSchema;
import schema.Function;

/**
 * Implementation of QueryFactory
 * @see query.QueryFactory
 */
public class QueryFactoryImpl implements QueryFactory {
	
	/**
	 * The schema on which apply search validation
	 */
	private DataSchema schema;
	
	public QueryFactoryImpl(DataSchema schema){
		this.schema = schema;
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#composition(query.PathExpression, query.PathExpression)
	 */
	@Override
	public Composition composition(PathExpression p1, PathExpression p2) throws PathExpressionValidationException {
		if(! new PathExpressionValidator(p1).getValidation()){
			throw new PathExpressionValidationException(p1);
		}else if(! new PathExpressionValidator(p2).getValidation()){
			throw new PathExpressionValidationException(p2);
		}
			
		return CompositionImpl.createComposition(p1, p2);
	}

	/* (non-Javadoc)
	 * @see query.QueryFactory#function(java.lang.String)
	 */
	@Override
	public FunctionReference function(String name) {
		Function f = schema.getFunctionByName(name);
		FunctionReference funcRef = new FunctionReferenceImpl(f);
		return funcRef;
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
