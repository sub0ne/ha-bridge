package com.bwssystems.HABridge.api.hue;

public class DeviceTypes {

	public static final String DEVICE_TYPE_SWITCH = "switch";
	public static final String DEVICE_TYPE_EXTENDED_COLOR_LIGHT = "extendedColorLight";
	public static final String DEVICE_TYPE_DIMMABLE_LIGHT = "dimmableLight";
	public static final String DEVICE_TYPE_ON_OFF_LIGHT = "onOffLight";
	
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
