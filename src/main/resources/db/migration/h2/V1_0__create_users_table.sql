CREATE TABLE IF NOT EXISTS users (
	id UUID DEFAULT random_uuid() PRIMARY KEY,
	username varchar(50),
	password varchar(200),
	role varchar(50)
);

