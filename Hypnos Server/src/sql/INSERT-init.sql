INSERT INTO UserData (userName, password)
VALUES
   ("andyroofoo", "p1"),
   ("audream", "p2"),
   ("2CHAINZ", "p3"),
   ("elsa", "p4"),
   ("arjuntina", "p5"),
   ("populinda", "p6"),
   ("TODDISGOD", "p7"),
   ("mypapersaysdanger", "p8"),
   ("howtowriteNGA", "p9"),
   ("thesystemshall", "p10"),
   ("velociraptor", "p11");

INSERT INTO LevelInfo (userId, level, isLocked)
VALUES
   ((SELECT userID FROM UserData WHERE userName = "andyroofoo"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "andyroofoo"), 2, false),
   ((SELECT userID FROM UserData WHERE userName = "audream"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "2CHAINZ"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "elsa"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "arjuntina"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "populinda"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "TODDISGOD"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "mypapersaysdanger"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "howtowriteNGA"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "thesystemshall"), 1, false),
   ((SELECT userID FROM UserData WHERE userName = "velociraptor"), 1, false);

INSERT INTO HighScores (userID, rank, score)
VALUES
   ((SELECT userID FROM UserData WHERE userName = "andyroofoo"), 11, 100),
   ((SELECT userID FROM UserData WHERE userName = "audream"), 10, 200),
   ((SELECT userID FROM UserData WHERE userName = "2CHAINZ"), 9, 300),
   ((SELECT userID FROM UserData WHERE userName = "elsa"), 8, 400),
   ((SELECT userID FROM UserData WHERE userName = "arjuntina"), 7, 500),
   ((SELECT userID FROM UserData WHERE userName = "populinda"), 6, 600),
   ((SELECT userID FROM UserData WHERE userName = "TODDISGOD"), 5, 700),
   ((SELECT userID FROM UserData WHERE userName = "mypapersaysdanger"), 4, 800),
   ((SELECT userID FROM UserData WHERE userName = "howtowriteNGA"), 3, 900),
   ((SELECT userID FROM UserData WHERE userName = "thesystemshall"), 2, 1000),
   ((SELECT userID FROM UserData WHERE userName = "velociraptor"), 1, 1100);