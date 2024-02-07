package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hbm.inventory.control_panel.controls.Button;

public class ControlRegistry {
	
	private static Map<String, Control> registry = new HashMap<>();
	private static Map<Class<? extends Control>, String> classToName = new HashMap<>();
	
	private ControlRegistry(){
	}
	
	public static void init(){
		registry.put("button", new Button("Button", null));
		
		for(Entry<String, Control> e : registry.entrySet()){
			classToName.put(e.getValue().getClass(), e.getKey());
		}
	}
	
	public static List<Control> getAllControls(){
		List<Control> l = new ArrayList<>(registry.size());
		for(Control c : registry.values())
			l.add(c);
		return l;
	}

	public static Control getNew(String name, ControlPanel panel){
		return registry.get(name).newControl(panel);
	}

	public static String getName(Class<? extends Control> clazz){
		return classToName.get(clazz);
	}
}
