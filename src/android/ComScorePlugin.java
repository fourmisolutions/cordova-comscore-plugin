package com.comscoreplugin;

import com.comscore.*;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONArray;

public class ComScorePlugin extends CordovaPlugin {

	public static final String TAG = "ComScorePlugin";
	
	// comscore sdk v5.X
	public static final String INITCLIENT = "initClient";
	public static final String NOTIFYENTERFOREGROUND = "notifyEnterForeground";
	public static final String NOTIFYEXITFOREGROUND = "notifyExitForeground";
	

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		boolean result = false;
		
		if (INITCLIENT.equals(action)) {
			this.initClient(args.getString(0), args.getString(1), callbackContext);
			result = true;
		}
		else if (NOTIFYENTERFOREGROUND.equals(action)) {
			this.notifyEnterForeground(callbackContext);
			result = true;
		}
		else if (NOTIFYEXITFOREGROUND.equals(action)) {
			this.notifyExitForeground(callbackContext);
			result = true;
		}
		
		return result;
	}

    @Override
    protected void pluginInitialize() {
		Log.v(TAG, "pluginInitialize");
    }
    
    private void initClient(String customerID, String customerKey, CallbackContext callbackContext) {
    	PublisherConfiguration myConfig = 
    			new PublisherConfiguration.Builder()
	    			.publisherId(customerID)
	    			.publisherSecret(customerKey)
	    			.usagePropertiesAutoUpdateMode(UsagePropertiesAutoUpdateMode.FOREGROUND_ONLY)
	    			.build();
    	
    	Log.v(TAG, "publisherId: " + customerID);
		Log.v(TAG, "publisherSecret: " + customerKey);

    	Analytics.getConfiguration().addClient(myConfig);
    	
    	Analytics.start(this.webView.getContext());

		Log.v(TAG, "start");
    }
    
    private void notifyEnterForeground(CallbackContext callbackContext) {
    	Analytics.notifyEnterForeground();
		Log.v(TAG, "notifyEnterForeground");
		callbackContext.success("ok");
	}

	private void notifyExitForeground(CallbackContext callbackContext) {
		Analytics.notifyExitForeground();
		Log.v(TAG, "notifyExitForeground");
		callbackContext.success("ok");
	}


}
