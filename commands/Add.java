package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import variables.Variable;
import variables.NumberVariable;

public class Add extends Command {
	
	private Variable storeVar;
	private Number operand_l;
	private Number operand_r;

	
	public Add(List<Variable> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);
		if(parameters.size() != 3) 
			throw new InvalidArgumentAmountException("add", 3);
		
		checkParameters(parameters);
		
		this.storeVar = parameters.get(0);
		this.operand_l = (Number) parameters.get(1).getValue();
		this.operand_r = (Number) parameters.get(2).getValue();
	}
	
	/** Checks if the second and third parameters are {@link NumberVariable} variables.
	 *
	 * @param the parameters of {@link Add}.
	 * @throws InvalidArgumentTypeException if one of the parameters isn't a number variable.
	 */
	private void checkParameters(List<Variable> parameters) throws InvalidArgumentTypeException  {
			if( !(parameters.get(1) instanceof NumberVariable) && !(parameters.get(2) instanceof NumberVariable) ) {
				throw new InvalidArgumentTypeException("Last two parameters for Add should be Number Variables");
			}
	}
	
	@Override
	public void execute() {
		List<Variable> parameters = new ArrayList<>(Arrays.asList(storeVar, new NumberVariable(null, operand_l.doubleValue() + operand_r.doubleValue())));
		(new Set(parameters)).execute();
	}
}
