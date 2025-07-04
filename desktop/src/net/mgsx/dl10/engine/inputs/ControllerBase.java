package net.mgsx.dl10.engine.inputs;

import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

import net.mgsx.dl10.engine.inputs.InputManager.Command;
import net.mgsx.dl10.engine.inputs.gamepad.GamepadAxisTrigger;
import net.mgsx.dl10.engine.inputs.gamepad.GamepadButtonTrigger;
import net.mgsx.dl10.engine.inputs.gamepad.GamepadPovTrigger;
import net.mgsx.dl10.engine.inputs.keyboard.KeyboardTrigger;

public abstract class ControllerBase {


	public final ObjectMap<TriggerBase, Command> triggers = new ObjectMap<TriggerBase, Command>();

	public abstract TriggerBase learn();

	public void clear(Command cmd) {
		Array<TriggerBase> toRemove = new Array<TriggerBase>();
		for(Entry<TriggerBase, Command> e : triggers){
			if(e.value == cmd){
				toRemove.add(e.key);
			}
		}
		for(TriggerBase t : toRemove){
			triggers.remove(t);
		}
	}

	abstract public void learnStart(Command cmd);
	abstract public void learnStop();

	public boolean isOn(Object command) {
		for(Entry<TriggerBase, Command> e : triggers){
			if(e.value.cmd == command){
				if(e.key.isOn(this)){
					return true;
				}
			}
		}
		return false;
	}

	public TriggerBase parseTrigger(String data) {
		String[] params = data.split("\\|");
		if(params[0].equals("POV")){
			return new GamepadPovTrigger(Integer.parseInt(params[1]), PovDirection.valueOf(params[2]));
		}
		if(params[0].equals("BUTTON")){
			return new GamepadButtonTrigger(Integer.parseInt(params[1]));
		}
		if(params[0].equals("KEY")){
			return new KeyboardTrigger(Integer.parseInt(params[1]));
		}
		if(params[0].equals("AXIS")){
			return new GamepadAxisTrigger(Integer.parseInt(params[1]), "+".equals(params[2]));
		}
		// TODO sliders...
		return null;
	}
}