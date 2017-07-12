-- Users table
CREATE TABLE UserProfile
(
UserProfile_ID INT NOT NULL AUTO_INCREMENT,
UserName VARCHAR(100) NOT NULL,
UserPassword VARCHAR(128) NOT NULL, -- SHA-512 password
Salt VARBINARY(32) NOT NULL, -- Used to generate a stronger SHA-512
UserRole VARCHAR(50) DEFAULT 'User',
PRIMARY KEY (UserProfile_ID)
);

-- UserProfile table
CREATE TABLE ProfileDetail
(
ProfileDetail_ID INT NOT NULL,
Email VARCHAR(100) NOT NULL,
TermsnAndConditions BIT (1) DEFAULT 0,
BirthDate DATE NOT NULL,
PRIMARY KEY (ProfileDetail_ID),
FOREIGN KEY (ProfileDetail_ID) REFERENCES UserProfile(UserProfile_ID) -- One-To-One with the Table UserProfile
);

-- Categories table
CREATE TABLE Category
(
Category_ID INT NOT NULL AUTO_INCREMENT,
CategoryName VARCHAR(100) NOT NULL,
PRIMARY KEY (Category_ID)
);

-- Words table
CREATE TABLE Word
(
Word_ID INT NOT NULL AUTO_INCREMENT,
UserProfile_ID INT,
Category_ID INT,
WordName VARCHAR(100) NOT NULL,
Definition VARCHAR(2048) NOT NULL,
Explanation VARCHAR(2048),
Example VARCHAR(2048),
NumberOfVisits INT DEFAULT 0,
PRIMARY KEY (Word_ID),
FOREIGN KEY (UserProfile_ID) REFERENCES UserProfile(UserProfile_ID),
FOREIGN KEY (Category_ID) REFERENCES Category(Category_ID)
);

-- Videos table
CREATE TABLE Video
(
Video_ID INT NOT NULL AUTO_INCREMENT,
TermYoutubeVideoID VARCHAR(100) NOT NULL,
DefinitionYoutubeVideoID VARCHAR(100) NOT NULL,
ExplanationYoutubeVideoID VARCHAR(100) NOT NULL,
ExampleYoutubeVideoID VARCHAR(100) NOT NULL,
PRIMARY KEY (Video_ID),
FOREIGN KEY (Video_ID) REFERENCES Word(Word_ID)
);

-- Request table
CREATE TABLE Request
(
Request_ID INT NOT NULL AUTO_INCREMENT,
UserProfile_ID INT,
WordName VARCHAR(100) NOT NULL,
Description VARCHAR(2048) NOT NULL,
PRIMARY KEY (Request_ID),
FOREIGN KEY (UserProfile_ID) REFERENCES UserProfile(UserProfile_ID)
);

-- PreferredWord table
CREATE TABLE PreferredWord
(
PreferredWord_ID INT NOT NULL AUTO_INCREMENT,
UserProfile_ID INT NOT NULL,
Word_ID INT NOT NULL,
PRIMARY KEY (PreferredWord_ID)
);

-- ********************************************* --