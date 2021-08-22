# ReviewSystem

Platform Review System

Note: Change log level to 'DEBUG' to see the details logs

Requirements :

- Add Users & Platform | line 38 & 50
  from [ReviewSystemApplication.java](src/main/java/com/tchyon/reviewsystem/ReviewSystemApplication.java).
- User to review Platform | line 55
  from [ReviewSystemApplication.java](src/main/java/com/tchyon/reviewsystem/ReviewSystemApplication.java).
- List top 'n' platforms by total review score by critics in particular Vertical | line 77
  from [ReviewSystemApplication.java](src/main/java/com/tchyon/reviewsystem/ReviewSystemApplication.java).
- Average Score of platforms | line 72
  from [ReviewSystemApplication.java](src/main/java/com/tchyon/reviewsystem/ReviewSystemApplication.java).

Platform :

- If release date not provided platform release date will be previous date.
- Average Score will List all the available platforms with average total score. if review not present 0.0 will be
  returned for that platform
- Average score for platform will give if average review score if present else 0.0
- Top 'n' Platform reviewed by 'CRITIC' level user for Vertical 'v' will exclude all the review's made by 'VIEWER' level
  user And Platform's which are not under Vertical 'v'

User :

- Only 1 user can exist with 1 username.
- System only support to user level 'Viewer' & 'Critic'.
- User level will only increase after user has published 3 (can be changed
  from [application.properties](src/main/resources/application.properties)) reviews.
- User can't review un-released platforms
- User can only review 1 platform 1 time only

Review -

- User can only rate between 1 - 5.
- 1 is minimum and 5 is maximum
- VIEWER level user score has weightage as 1x & 'CRITIC' level user has weightage as 2x. i.e if CRITIC gives review as 4
  => 8 & if VIEWER gives review as 5 => 5

Test :-

- Multiple Review for same platform from same user uncomment line : 60
  from [ReviewSystemApplication.java](src/main/java/com/tchyon/reviewsystem/ReviewSystemApplication.java)
- Early release review uncomment line : 63
  from [ReviewSystemApplication.java](src/main/java/com/tchyon/reviewsystem/ReviewSystemApplication.java)
- User Upgrade line : 57
  from [ReviewSystemApplication.java](src/main/java/com/tchyon/reviewsystem/ReviewSystemApplication.java) | user 'Adam'
  has published 3 review, so he is promoted

File [ActingDbUtility.java](src/main/java/com/tchyon/reviewsystem/utility/ActingDBUtility.java) is being used as
replacement for the database. Below is the list of variables present in the file with some info.

Variable Name | Data Type | Info
| :--- | ---: | :---:
userIdMap  | Map | Stores 'userName' as Key & it's 'userId' as value.
userMapList  | Map | Stores 'userId' as Key & [UserPojo.java](src/main/java/com/tchyon/reviewsystem/pojo/UserPojo.java) as value.
verticalIdMap  | Map | Stores 'verticalName' as Key & it's 'verticalId' as value.
platformIdMap  | Map | Stores 'platformName' as Key & it's 'platformId' as value.
platformVerticalMap  | Map | Stores 'platformId' as Key & 'verticalId' as value.
platformMapList  | Map | Stores 'platformId' as Key & it's [PlatformPojo.java](src/main/java/com/tchyon/reviewsystem/pojo/PlatformPojo.java) as value.
verticalMapList  | Map | Stores 'verticalId' as Key & it's [VerticalPojo.java](src/main/java/com/tchyon/reviewsystem/pojo/VerticalPojo.java) as value.
userReviewMap  | Map | Stores 'userId' as Key & List of 'reviewId' as value.
reviewPlatformMap  | Map | Stores 'reviewId' as Key & it's 'platformId' as value.
reviewMapList  | Map | Stores 'reviewId' as Key & [ReviewPojo.java](src/main/java/com/tchyon/reviewsystem/pojo/ReviewPojo.java) as value.
reviewerWeightage  | Map | Stores [ReviewerLevel.java](src/main/java/com/tchyon/reviewsystem/pojo/ReviewerLevel.java) as Key & it's scoring weightage as value.
