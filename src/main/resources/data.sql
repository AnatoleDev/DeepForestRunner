CREATE TABLE IF NOT EXISTS organization
(
  id      INTEGER,
  name    VARCHAR(100) NOT NULL,
  number  INTEGER,
  captain VARCHAR(100) NOT NULL,
  phone   VARCHAR(100),
  PRIMARY KEY(id),
  UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS runners
(
  id      INTEGER,
  fio     VARCHAR(100) NOT NULL,
  gender  VARCHAR(100) NOT NULL,
  phone   VARCHAR(100),
  number  INTEGER,
  start   TIMESTAMP,
  finish  TIMESTAMP,
  place   INTEGER,
  org_id  INTEGER NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_organization FOREIGN KEY(org_id) REFERENCES organization(id),
  UNIQUE(fio)
);
