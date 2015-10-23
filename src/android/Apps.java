package so.doo.app.plugins;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

@SuppressLint("HandlerLeak")
public class Apps extends CordovaPlugin {
    
    private Context ctx;

     public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
       // ctx = cordova.getActivity().getApplicationContext();
    }

    private JSONArray list() {
       PackageManager pm = this.cordova.getActivity().getPackageManager();
            List<ApplicationInfo> packages = pm.getInstalledApplications( PackageManager.GET_PROVIDERS );
            ArrayList<JSONObject> res = new ArrayList<JSONObject>();

            for (ApplicationInfo packageInfo : packages) {
                try {
                    JSONObject json = new JSONObject();
                    json.put( "package", packageInfo.packageName );
                    json.put( "name", pm.getApplicationLabel( pm.getApplicationInfo( packageInfo.packageName, 0 )).toString() );
                    res.add( json );
                } catch (NameNotFoundException e) {}

            }

            JSONArray ar = new JSONArray( res );
        
        return new JSONArray(ar);
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false otherwise.
     * @throws JSONException 
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        // list
        if (action.equals("list")) {
            JSONArray json = list();
            callbackContext.success(json.toString());
            
            return true;
        }

        return false;
    }
}
