CREATE TABLE UserData (
   userId INT PRIMARY KEY auto_increment,
   userName VARCHAR(32)
   password VARCHAR(32)
);

CREATE TABLE HighScores (
   userId INT references UserData(userId),
   rank INT,
   score INT
);

CREATE TABLE LevelInfo (
   userId INT references UserData(userId),
   level INT,
   isLocked BOOLEAN
);

CREATE TABLE Achievements (
   userId INT references UserData(userId),
   achievementName VARCHAR(64),
   isLocked BOOLEAN
);

CREATE TABLE Options (
   userId INT references UserData(userId),
   optionName VARCHAR(32),
   value INT
);
