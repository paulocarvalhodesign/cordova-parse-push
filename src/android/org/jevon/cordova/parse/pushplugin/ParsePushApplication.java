package org.jevon.cordova.parse.pushplugin;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.parse.Parse;

public class ParsePushApplication extends Application {
	@Override
	public void onCreate() {
		Log.i("ParsePushApplication", "onCreate");
		super.onCreate();
		
		Context context = this.getApplicationContext();
		ApplicationInfo ai = null;
		try {
			ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		String appId = (String) ai.metaData.get("ParseAppId");
		String clientSecret = (String) ai.metaData.get("ParseClientSecret");
		Log.i("ParsePushApplication", "Initialising Parse SDK with '" + appId + "', '" + clientSecret + "'");
		
		if (appId == null || appId.isEmpty()) {
			throw new IllegalArgumentException("Parse AppId needs to be defined");
		}
		if (clientSecret == null || clientSecret.isEmpty()) {
			throw new IllegalArgumentException("Parse ClientSecret needs to be defined");
		}
		
		// initialize the Parse SDK
		Log.i("ParsePushApplication", "About to initialise Parse SDK");
		Parse.initialize(this, appId, clientSecret);
		Log.i("ParsePushApplication", "Parse SDK initialised");
		
	}
}
