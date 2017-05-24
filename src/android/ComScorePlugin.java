package com.comscoreplugin;

import com.comscore.*;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONArray;

public class ComScorePlugin extends CordovaPlugin {

	public static final String TAG = "ComScorePlugin";

	// comscore sdk v5.X
	public static final String INITCLIENT = "initClient";
	public static final String INITCLIENTWITHNAME = "initClientWithName";
	public static final String NOTIFYENTERFOREGROUND = "notifyEnterForeground";
	public static final String NOTIFYEXITFOREGROUND = "notifyExitForeground";
	public static final String NOTIFYSCREENVIEW = "notifyScreenView";

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		boolean result = false;

		final JSONArray finalArgs = args;
		final CallbackContext finalCallbackContext = callbackContext;

		if (INITCLIENT.equals(action)) {
			final Context finalContext = this.webView.getContext();
			cordova.getThreadPool().execute(new Runnable() {
				public void run() {
					try {
						initClient(finalArgs.getString(0), finalArgs.getString(1), finalCallbackContext, finalContext);
					} catch (Exception e) {
						Log.e(TAG, e.getMessage(), e);
					}
				}
			});

			//this.initClient(args.getString(0), args.getString(1), callbackContext);
			result = true;
		}
		else if (INITCLIENTWITHNAME.equals(action)) {
			final Context finalContext = this.webView.getContext();
			cordova.getThreadPool().execute(new Runnable() {
				public void run() {
					try {
						initClientWithName(finalArgs.getString(0), finalArgs.getString(1), finalArgs.getString(2), finalCallbackContext, finalContext);
					} catch (Exception e) {
						Log.e(TAG, e.getMessage(), e);
					}
				}
			});

			//this.initClient(args.getString(0), args.getString(1), args.getString(2), callbackContext);
			result = true;
		}
		else if (NOTIFYENTERFOREGROUND.equals(action)) {
			cordova.getThreadPool().execute(new Runnable() {
				public void run() {
					try {
						notifyEnterForeground(finalCallbackContext);
					} catch (Exception e) {
						Log.e(TAG, e.getMessage(), e);
					}
				}
			});

			//this.notifyEnterForeground(callbackContext);
			result = true;
		}
		else if (NOTIFYEXITFOREGROUND.equals(action)) {
			cordova.getThreadPool().execute(new Runnable() {
				public void run() {
					try {
						notifyExitForeground(finalCallbackContext);
					} catch (Exception e) {
						Log.e(TAG, e.getMessage(), e);
					}
				}
			});

			//this.notifyExitForeground(callbackContext);
			result = true;
		}
		else if (NOTIFYSCREENVIEW.equals(action)) {
			cordova.getThreadPool().execute(new Runnable() {
				public void run() {
					try {
						notifyScreenView(finalArgs.getString(0), finalCallbackContext);
					} catch (Exception e) {
						Log.e(TAG, e.getMessage(), e);
					}
				}
			});

			//this.notifyScreenView(args.getString(0), callbackContext);
			result = true;
		}

		return result;
	}

	@Override
	protected void pluginInitialize() {
		Log.v(TAG, "pluginInitialize");
	}

	private static void initClient(String customerID, String customerKey, CallbackContext callbackContext, Context finalContext) {
		PublisherConfiguration myConfig = 
				new PublisherConfiguration.Builder()
				.publisherId(customerID)
				.publisherSecret(customerKey)
				.usagePropertiesAutoUpdateMode(UsagePropertiesAutoUpdateMode.FOREGROUND_ONLY)
				.build();

		Log.v(TAG, "publisherId: " + customerID);
		Log.v(TAG, "publisherSecret: " + customerKey);

		Analytics.getConfiguration().addClient(myConfig);

		Analytics.start(finalContext);

		Log.v(TAG, "start");
	}

	private static void initClientWithName(String customerID, String customerKey, String appName, CallbackContext callbackContext, Context finalContext) {
		PublisherConfiguration myConfig = 
				new PublisherConfiguration.Builder()
				.publisherId(customerID)
				.publisherSecret(customerKey)
				.applicationName(appName)
				.usagePropertiesAutoUpdateMode(UsagePropertiesAutoUpdateMode.FOREGROUND_ONLY)
				.build();

		Log.v(TAG, "publisherId: " + customerID);
		Log.v(TAG, "publisherSecret: " + customerKey);
		Log.v(TAG, "applicationName: " + appName);

		Analytics.getConfiguration().addClient(myConfig);

		Analytics.start(finalContext);

		Log.v(TAG, "start");
	}

	private static void notifyEnterForeground(CallbackContext callbackContext) {
		Analytics.notifyEnterForeground();
		Log.v(TAG, "notifyEnterForeground");
		callbackContext.success("ok");
	}

	private static void notifyExitForeground(CallbackContext callbackContext) {
		Analytics.notifyExitForeground();
		Log.v(TAG, "notifyExitForeground");
		callbackContext.success("ok");
	}

	private static void notifyScreenView(String screenName, CallbackContext callbackContext) {
		EventInfo newEvent = new EventInfo();
		newEvent.setLabel("name", screenName);

		Analytics.notifyViewEvent(newEvent);
		Log.v(TAG, "notifyScreenView: " + screenName);
		callbackContext.success("ok");
	}


}
