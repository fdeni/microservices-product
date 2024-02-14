CREATE TABLE user_data(
    ud_id int(11) NOT NULL AUTO_INCREMENT,
    ud_username varchar(255),
    ud_name varchar(255),
    ud_password varchar(255),
    ud_created_at datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ud_id)
);

CREATE TABLE mr_product (
    mp_id int(11) NOT NULL AUTO_INCREMENT,
    mp_name int(11),
    mp_description double(18,2),
    mp_price int(11),
    mp_created_at datetime DEFAULT CURRENT_TIMESTAMP,
    mp_created_by int(11),
    mp_updated_at datetime,
    mp_updated_by int(11),
    PRIMARY KEY(mp_id)
);