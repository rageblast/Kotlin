// Back Stack -> push and pop screen to stacks

// when user goes to new screen from older screen we store it in Stack

// Browser Screen -> BookMark Screen -> New BookMark Screen 

// when user clicks back button it will move back to the BookMark Screen 

// Browser Screen -> BookMark Screen 

// Tasks -> combines all the screen together in one unit 

// Launch mode tells us what to do when new activity push to back stack

//Single Top -> lets say we have an activity already open and when we click on a link in insta when redirects to that already open activity 

// it won't create new stack in back stack it will move us back to the already open activity won't create new one

// Single Task 

User clicks on Link in Another App -> BookMark Activity/Browser Activity 
                                   -> Browser Activity

we already have Browser activity page ok if we click on a link in insta we need to create a new Browser activity page not go to already opened browser activity page 

because when we click back we need to go back to insta not go to BookMark Activity

// Single Instance 

Same as Single Task but it is independent no other activity get interfear in it 

it will get its own space and no activity can do anything with it like paypal app where payments gets its own space 

no other page gets in its way  