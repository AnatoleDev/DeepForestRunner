CREATE TABLE IF NOT EXISTS organization
(
  id      INTEGER,
  name    VARCHAR(100) NOT NULL,
  number  INTEGER,
  captain VARCHAR(100) NOT NULL,
  phone   VARCHAR(20),
  PRIMARY KEY(id),
  UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS customer
(
  id       INTEGER,
  fio      VARCHAR(100) NOT NULL,
  gender   VARCHAR(10) NOT NULL,
  phone    VARCHAR(20),
  birthday TIMESTAMP,
  org_id   INTEGER NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_organization FOREIGN KEY(org_id) REFERENCES organization(id),
  UNIQUE(fio)
);

CREATE TABLE IF NOT EXISTS files
(
  id        INTEGER,
  name      VARCHAR(100) NOT NULL,
  created   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  content   BYTEA        NOT NULL,
  PRIMARY KEY(id)
);