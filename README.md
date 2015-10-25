# Retrieve App Installed from android cordova app

  function retrieve(){<br>
    &nbsp;&nbsp;window.apps.list(function(list) {<br>
    &nbsp;&nbsp;&nbsp;&nbsp;    alert(list);<br>
    &nbsp;&nbsp;});<br>
   }
   
This plugin retrieves a package name and label name of the apps installed.
I don't know how order the list of associated package names of label names. 
