package com.bwssystems.HABridge.api.hue;

import java.util.List;
import java.util.Arrays;

public class DeviceTypes {

	public static final String DEVICE_TYPE_SWITCH = "switch";
	public static final String DEVICE_TYPE_EXTENDED_COLOR_LIGHT = "extendedColorLight";
	public static final String DEVICE_TYPE_DIMMABLE_LIGHT = "dimmableLight";
	public static final String DEVICE_TYPE_ON_OFF_LIGHT = "onOffLight";

	protected static final String[] DEVICE_TYPE_ARRAY_LIGHTS = { 
		DEVICE_TYPE_SWITCH, 
		DEVICE_TYPE_EXTENDED_COLOR_LIGHT, 
		DEVICE_TYPE_DIMMABLE_LIGHT, 
		DEVICE_TYPE_ON_OFF_LIGHT
	};

	public static final List<String> DEVICE_TYPE_LIST_LIGHTS = Arrays.asList(DEVICE_TYPE_ARRAY_LIGHTS);
	
	private Boolean bridge;
	private String[] lights;
	public Boolean getBridge() {
		return bridge;
	}
	public void setBridge(Boolean bridge) {
		this.bridge = bridge;
	}
	public String[] getLights() {
		return lights;
	}
	public void setLights(String[] lights) {
		this.lights = lights;
	}

}
