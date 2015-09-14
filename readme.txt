* I did not use any additional libraries for this application so the appication can be run as a normal Android studio
* Design:
- use one main activity to hold all fragments:
+ ClippingDetailFragment: contains details about a particular clipping
+ CLippingListFragment: contains a list view to display all clippings in a collection
+ CollectionListFragment: contains a list view to display all collections
+ CreateClippingFragment:is used to create or update a clipping
- use one class (DatabaseContract) to store all information (field names, database names, table names) 
about the database 
- use ScrapbookModel class to directly interact with the database
- use a custom application class to hold an instance of ScrapbookModel
- use context menu to allow the user edit and delete collection and clipping from a list. Users can achieve the same effect by using menu items in detail pages.  
* Bonus mark:
I used an intent with action ACTION_SEND to allow other application to access details of a clipping
The clipping can be shared via Gmail or Skype.
