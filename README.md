# retrieve_app_installed

  function retrieve(){
    window.apps.list(function(list) {
        alert(list);
    });
   }
   
This plugin retrieves a package name and label name of the apps installed.
I don't know how order the list of associated package names of label names. 
