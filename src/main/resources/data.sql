CREATE TABLE IF NOT EXISTS subdivision
(
  id      INTEGER,
  name    VARCHAR(100) NOT NULL,
  number  INTEGER,
  captain VARCHAR(100) NOT NULL,
  phone   VARCHAR(20),
  PRIMARY KEY (id),
  UNIQUE (name),
  UNIQUE (number)
);

CREATE TABLE IF NOT EXISTS participant
(
  id             INTEGER,
  fio            VARCHAR(100) NOT NULL,
  gender         VARCHAR(10)  NOT NULL,
  birthday       TIMESTAMP,
  subdivision_id INTEGER,
  PRIMARY KEY (id),
  UNIQUE (fio),
  CONSTRAINT fk_subdivision
    FOREIGN KEY (subdivision_id) REFERENCES subdivision (id) ON UPDATE SET NULL
);

CREATE TABLE IF NOT EXISTS file
(
  id INTEGER, name VARCHAR(100) NOT NULL, created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, content BYTEA NOT NULL, PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS runner
(
  id             INTEGER,
  participant_id INTEGER NOT NULL,
  number         INTEGER,
  start          TIMESTAMP,
  finish         TIMESTAMP,
  total          TIMESTAMP,
  kp             INTEGER,
  PRIMARY KEY (id),
  CONSTRAINT fk_participant
    FOREIGN KEY (participant_id) REFERENCES participant (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS activity
(
  id INTEGER, participant_id INTEGER NOT NULL, type VARCHAR(10) NOT NULL, PRIMARY KEY (id), CONSTRAINT fk_participant
  FOREIGN KEY (participant_id) REFERENCES participant (id) ON UPDATE SET NULL
);