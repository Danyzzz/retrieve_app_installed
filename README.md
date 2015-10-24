# retrieve_app_installed

  function retrieve(){<br>
    window.apps.list(function(list) {<br>
        alert(list);<br>
    });<br>
   }
   
This plugin retrieves a package name and label name of the apps installed.
I don't know how order the list of associated package names of label names. 
