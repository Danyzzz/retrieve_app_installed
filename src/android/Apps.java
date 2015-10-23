package so.doo.app.plugins;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

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
        ctx = cordova.getActivity().getApplicationContext();
    }

    private JSONArray list() {
        List<PackageInfo> apps = getPackageManager().getInstalledPackages(0)

        
        ArrayList<AppInfo> res = new ArrayList<AppInfo>();
            for(int i=0;i<apps.size();i++) {
                        PackageInfo p = apps.get(i);
         
                        AppInfo newInfo = new AppInfo();
                        newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
                        newInfo.pname = p.packageName;
                        newInfo.versionName = p.versionName;
                        newInfo.versionCode = p.versionCode;
                        newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
                        list.add(newInfo);
                        }
                    }
         
        class AppInfo {
            String appname = "";
            String pname = "";
            String versionName = "";
            int versionCode = 0;
            Drawable icon;
         
        }

        List<String> ulist = new ArrayList<String>(new HashSet<String>(list));
        
        return new JSONArray(ulist);
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
