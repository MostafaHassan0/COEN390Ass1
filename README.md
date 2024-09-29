Student Name: Mostafa HASSAN
ID: 40228863


Run the app as normal.
You need to set settings first. Otherwise, every time you click the button you will be redirected to SettingsActivity.

I will submit 2 version of the application:
As-Teacher-wants: The app is exactly as the teacher wants it.

Master:
In this version, I made some changes to the UI and code.

-	Added an icon
-	The listView in DataActivity will show the data in descending order, meaning newest at the top and oldest at the bottom.
 
-	Added Reset button in DataActivity that resets the event counts in sharedpreferences.
DataActivity:
 
SharedPrefrencesHelper :
 
Activity_Data.xml:
 
-	The MenuItems are now set to appear in toolbar if there is room because we only have one menu item:
 
-	The default value of Max Count is 5, as it is the minimum. And since 200 is maximum, we allow only 3 digits to be entered:
 

Please note that the original name of the project was try200, but we have changed it to Counter Boy in a later stage of the project, hence the naming of the folders is still try200, while the app is Counter boy.

The following are some stuff we would like to improve even if it is tolerated y the corrector.
bugs:
-Clicking Buttons without saving the settings first crashes the app,
-Save button doesn't want to disappear after the first run of the settings activity.

Bug area:
-SharedPrefrenceshelper
-onCreat in SettingsActivity
Sources of bugs:
My limited understanding of the SharedPrefrences class and how it operates. As well as the lifecycle of an activity.
expected behaviour:
-The Counter to increment normally without crashing, even if there is no settings saved yet.
-Save button to appear when it's the first time SettingsActivity is running, then it disappears and only become visible when Edit is clicked.


