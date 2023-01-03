CREATE TABLE IF NOT EXISTS Url
(
    id   UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    hash char(14) DEFAULT nanoid(14) NOT NULL,
    url  TEXT NOT NULL
);

CREATE UNIQUE INDEX url_hash on Url (url, id)