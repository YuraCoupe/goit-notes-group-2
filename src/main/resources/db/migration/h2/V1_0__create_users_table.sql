CREATE TABLE IF NOT EXISTS users (
	id UUID DEFAULT random_uuid() PRIMARY KEY,
	username varchar(50),
	password varchar(200),
	user_role varchar(50)
);

CREATE TABLE notes (
	id UUID DEFAULT random_uuid() PRIMARY KEY,
	title VARCHAR(200) NOT NULL,
	text VARCHAR(10000) NOT NULL,
	access_type VARCHAR(100) NOT NULL,
	user_id UUID,
	FOREIGN KEY (user_id) REFERENCES users
);