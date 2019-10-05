package parameter.variables;

import bareBonesInterpreter.CommandParser;
import parameter.Parameter;

public abstract class Variable<E> implements Parameter {
	
	private String varName;
	private E varValue;
	
	public Variable(String varName, E varValue) {
		this.varValue = varValue;
		this.varName = varName;
		
		if(varName != null)
			addToStore();
		
	}
	
	/** Add to {@link VariableStore} if the name is the unique. If the name is already used then replace the value of the already-existing variable.
	 */
	private void addToStore() {
		if(CommandParser.getVariableStore().getVariable(varName) == null) {
			CommandParser.getVariableStore().addVariable(this);
		}
		//replace it
		else {
			Variable var = CommandParser.getVariableStore().getVariable(varName);
			var.setValue(varValue);
		}
	}

	/** Gets the name of the variable
	 * 
	 * @return varName (Could be null)
	 */
	public String getName() {
		return varName;
	}
	
	public E getValue() {
		return varValue;
	}
	
	public void setName(String newName) {
		this.varName = newName;
	}
	
	public void setValue(E newValue) {
		this.varValue = newValue;
	}

}