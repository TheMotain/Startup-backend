DROP TABLE message IF EXISTS;

CREATE TABLE message (
  id         INTEGER PRIMARY KEY,
  sender VARCHAR(50),
  content  VARCHAR(50)
);