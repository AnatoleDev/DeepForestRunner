CREATE TABLE IF NOT EXISTS subdivision
(
  id      INTEGER,              
  name    VARCHAR(100) NOT NULL,
  number  INTEGER,
  captain VARCHAR(100) NOT NULL,
  phone   VARCHAR(20),
  PRIMARY KEY(id),
  UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS participant
(
  id       INTEGER,
  fio      VARCHAR(100) NOT NULL,
  gender   VARCHAR(10) NOT NULL,
  birthday TIMESTAMP,
  org_id   INTEGER NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_organization FOREIGN KEY(org_id) REFERENCES subdivision(id),
  UNIQUE(fio)
);

CREATE TABLE IF NOT EXISTS file
(
  id        INTEGER,
  name      VARCHAR(100) NOT NULL,
  created   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  content   BYTEA        NOT NULL,
  PRIMARY KEY(id)
);