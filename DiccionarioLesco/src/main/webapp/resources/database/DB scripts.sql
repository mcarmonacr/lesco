-- Users table
CREATE TABLE UserProfile
(
UserProfile_ID INT NOT NULL AUTO_INCREMENT,
UserName VARCHAR(100) NOT NULL,
UserPassword VARCHAR(128) NOT NULL, -- SHA-512 password
Salt VARBINARY(32) NOT NULL, -- Used to generate a stronger SHA-512
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
YoutubeVideoID VARCHAR(100) NOT NULL,
PRIMARY KEY (Video_ID),
FOREIGN KEY (Video_ID) REFERENCES Word(Word_ID)
);

-- UserWordsList table
CREATE TABLE UserWordList
(
UserProfile_ID NOT NULL,
Word_ID NOT NULL,
PRIMARY KEY (UserProfile_ID, Word_ID),
FOREIGN KEY (UserProfile_ID) REFERENCES UserProfile(UserProfile_ID),
FOREIGN KEY (Word_ID) REFERENCES Word(Word_ID)
);

-- Ratings table
CREATE TABLE Rating
(
Rating_ID INT NOT NULL AUTO_INCREMENT,
UserProfile_ID INT,
Word_ID INT,
Rating INT NOT NULL,
PRIMARY KEY (Rating_ID),
FOREIGN KEY (UserProfile_ID) REFERENCES UserProfile(UserProfile_ID),
FOREIGN KEY (Word_ID) REFERENCES Word(Word_ID)
);

-- WishList table
CREATE TABLE WishList
(
WishList_ID INT NOT NULL AUTO_INCREMENT,
UserProfile_ID INT,
Word_ID INT,
WordName VARCHAR(100) NOT NULL,
Description VARCHAR(2048) NOT NULL,
PRIMARY KEY (WishList_ID),
FOREIGN KEY (UserProfile_ID) REFERENCES UserProfile(UserProfile_ID),
FOREIGN KEY (Word_ID) REFERENCES Word(ID)
);




-- ********************************************* --




