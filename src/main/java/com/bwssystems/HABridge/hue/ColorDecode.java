package com.bwssystems.HABridge.hue;

import java.util.List;
import java.util.regex.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bwssystems.HABridge.hue.ColorData;

public class ColorDecode {
	
	private static final Logger log = LoggerFactory.getLogger(ColorDecode.class);

	private static String handleCTRequest(Integer ct, String request, String deviceID) {

		String ctParam = String.format("%s.ct=%s", deviceID, ct.toString());
	
		return String.format("%s?%s", getHostFromRequest(request), ctParam);

	}


	private static String handleHSRequest(HueSatBri hs, String request, String deviceID) {

		float hue = (float)hs.getHue() / (float)182.04;
		float sat = (float)hs.getSat();

		String hueParam = String.format("%s.hue=%f", deviceID, hue);
		String satParam = String.format("%s.sat=%f", deviceID, sat);
	
		return String.format("%s?%s&%s", getHostFromRequest(request), hueParam, satParam);

	}


	private static String handleCIERequest(List<Double> cie, String request, String deviceID) {

		String xyParam = String.format("%s.xy=%f,%f", deviceID, cie.get(0), cie.get(1));
	
		return String.format("%s?%s", getHostFromRequest(request), xyParam);

	}


	private static String getDeviceIDFromRequest(String request){

		// request format: "http://localhost:8087/setBulk?${color.deviceID=deconz.0.Lights.1}";

		Pattern pattern = Pattern.compile("\\$\\{color.deviceID=(.*)}");
		Matcher matcher = pattern.matcher(request);

		String deviceID = "";

		if (matcher.find()){
			deviceID = matcher.group(1);
		}		

		return deviceID;
  
	}


	private static String getHostFromRequest(String request) {
	
		String[] splitRequest = request.split("\\?");

		return splitRequest[0];	
	}


	@SuppressWarnings("unchecked")
	public static String replaceColorData(String request, ColorData colorData, int setIntensity, boolean isHex) {
	
		if (request == null || colorData == null) {
			return request;
		}

		String deviceID = getDeviceIDFromRequest(request);
		if (deviceID.isEmpty()) {
			return request;
		}

		ColorData.ColorMode colorMode = colorData.getColorMode();

		if (colorMode == ColorData.ColorMode.XY) {
			return handleCIERequest((List<Double>) colorData.getData(), request, deviceID);
		} else if (colorMode == ColorData.ColorMode.CT) {
			return handleCTRequest((Integer) colorData.getData(), request, deviceID);
		} else if (colorMode == ColorData.ColorMode.HS) {
			return handleHSRequest((HueSatBri) colorData.getData(), request, deviceID);
		} else {
			log.debug("ColorMode:" + colorMode + " not handled, request failed!");
			return "";
		}
		
	}

	/*private static String getMilightV5FromRgb(List<Integer> rgb, int group) {
		double r = (double) rgb.get(0);
		double g = (double) rgb.get(1);
		double b = (double) rgb.get(2);
		if (r > 245 && g > 245 && b > 245) { // it's white
			String retVal = "";
			if (group == 0) {
				retVal += "C2";
			} else if (group == 1) {
				retVal += "C5";
			} else if (group == 2) {
				retVal += "C7";
			} else if (group == 3) {
				retVal += "C9";
			} else if (group == 4) {
				retVal += "CB";
			}
			log.debug("Convert RGB to Milight. Result: WHITE. RGB Values: " + rgb.get(0) + " " + rgb.get(1) + " "
					+ rgb.get(2));
			return retVal + "0055";
		} else { // normal color
			r /= (double) 0xFF;
			g /= (double) 0xFF;
			b /= (double) 0xFF;
			double max = Math.max(Math.max(r, g), b), min = Math.min(Math.min(r, g), b);
			double h = 0;
			double d = max - min;

			if (max == min) {
				h = 0;
			} else {
				if (max == r) {
					h = ((g - b) / d + (g < b ? 6 : 0));
				} else if (max == g) {
					h = ((b - r) / d + 2);
				} else if (max == b) {
					h = ((r - g) / d + 4);
				}
				h = Math.round(h * 60);
			}
			int milight = (int) ((256 + 176 - Math.floor(h / 360.0 * 255.0)) % 256);
			log.debug("Convert RGB to Milight. Result: " + milight + " RGB Values: " + rgb.get(0) + " " + rgb.get(1)
					+ " " + rgb.get(2));
			return "40" + String.format("%02X", milight) + "55";
		}
	}*/

	
	@SuppressWarnings("unchecked")
	public static int getIntRGB(ColorData colorData, int setIntensity) {
		return -1;
	}

}
