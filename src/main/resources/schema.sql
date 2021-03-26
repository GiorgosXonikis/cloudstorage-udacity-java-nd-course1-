CREATE TABLE IF NOT EXISTS USER (
  id INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  first_name VARCHAR(20),
  last_name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTE (
    id INT PRIMARY KEY auto_increment,
    note_title VARCHAR(20),
    note_description VARCHAR (1000),
    user_id INT,
    foreign key (user_id) references USER(id)
);

CREATE TABLE IF NOT EXISTS FILE (
    id INT PRIMARY KEY auto_increment,
    file_name VARCHAR,
    content_type VARCHAR,
    file_size VARCHAR,
    file_data BLOB,
    user_id INT,
    foreign key (user_id) references USER(id)
);

CREATE TABLE IF NOT EXISTS CREDENTIAL (
    id INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    user_id INT,
    foreign key (user_id) references USER(id)
);
