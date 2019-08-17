package variables;

import java.util.ArrayList;
import java.util.List;

public class VariableStore {

	private List<Variable> variables = new ArrayList<>();

	/** Add new {@link Variable<E>}  
	 * 
	 * @param var the variable
	 */
	public void addVariable(Variable var) {
		variables.add(var);
	}
	
	/** Get a {@link Variable<E>} based on its name.
	 * 
	 * @param varname the name of the variable
	 * @return variable
	 */
	public Variable getVariable(String varname) {
		for(Variable var : variables) 
			if(var.getName() != null)
				if(var.getName().equals(varname))
				return var;
		return null;
	}
	
	public List<Variable> getVariables() {
		return this.variables;
	}
}
