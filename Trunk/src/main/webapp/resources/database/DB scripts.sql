-- Users table
CREATE TABLE UserProfile
(
ID INT NOT NULL AUTO_INCREMENT,
UserName VARCHAR(100) NOT NULL,
UserPassword VARCHAR(128) NOT NULL, -- SHA-512 password
Salt VARCHAR(16) NOT NULL, -- Used to generate a stronger SHA-512
PRIMARY KEY (ID)
);

-- UserProfile table
CREATE TABLE ProfileDetail
(
ID INT NOT NULL,
Email VARCHAR(100) NOT NULL,
TermsnAndConditions BIT (1) DEFAULT 0,
BirthDate DATE NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (ID) REFERENCES UserProfile(ID) -- One-To-One with the Table UserProfile
);

-- Words table
CREATE TABLE Word
(
ID INT NOT NULL AUTO_INCREMENT,
User_ID INT,
WordName VARCHAR(100) NOT NULL,
Description VARCHAR(2048) NOT NULL,
Explanation VARCHAR(2048),
Example VARCHAR(2048),
NumberOfVisits INT DEFAULT 0,
PRIMARY KEY (ID),
FOREIGN KEY (User_ID) REFERENCES UserProfile(ID)
);

-- UserWordsList table
CREATE TABLE UserWordList
(
ID INT NOT NULL AUTO_INCREMENT,
User_ID INT,
Word_ID INT,
PRIMARY KEY (ID),
FOREIGN KEY (User_ID) REFERENCES UserProfile(ID),
FOREIGN KEY (Word_ID) REFERENCES Word(ID)
);

-- Videos table
CREATE TABLE Video
(
ID INT NOT NULL AUTO_INCREMENT,
Word_ID INT,
URL VARCHAR(100) NOT NULL,
DirectoryPath VARCHAR(300) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (Word_ID) REFERENCES Word(ID)
);

-- Ratings table
CREATE TABLE Rating
(
ID INT NOT NULL AUTO_INCREMENT,
User_ID INT,
Word_ID INT,
Rating INT NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (User_ID) REFERENCES UserProfile(ID),
FOREIGN KEY (Word_ID) REFERENCES Word(ID)
);

-- WishList table
CREATE TABLE WishList
(
ID INT NOT NULL AUTO_INCREMENT,
User_ID INT,
Word_ID INT,
WordName VARCHAR(100) NOT NULL,
Description VARCHAR(2048) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (User_ID) REFERENCES UserProfile(ID),
FOREIGN KEY (Word_ID) REFERENCES Word(ID)
);

-- Categories table
CREATE TABLE Category
(
ID INT NOT NULL AUTO_INCREMENT,
CategoryName VARCHAR(100) NOT NULL,
PRIMARY KEY (ID)
);

-- WordsCategories table
CREATE TABLE WordCategory
(
ID INT NOT NULL AUTO_INCREMENT,
Category_ID INT,
Word_ID INT,
PRIMARY KEY (ID),
FOREIGN KEY (Category_ID) REFERENCES Category(ID),
FOREIGN KEY (Word_ID) REFERENCES Word(ID)
)

-- ********************************************* --




