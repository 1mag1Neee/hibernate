CREATE TABLE company
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(32) NOT NULL UNIQUE,
    created_at TIMESTAMP,
    created_by VARCHAR(32)
);

CREATE TABLE profile
(
    id         BIGSERIAL PRIMARY KEY,
    firstname  VARCHAR(128),
    lastname   VARCHAR(128),
    birth_date DATE,
    language   VARCHAR(32)
);

CREATE TABLE chat
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(32)
);

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    role       VARCHAR(32),
    info       JSONB,
    username   VARCHAR(128) UNIQUE         NOT NULL,
    company_id INT REFERENCES company (id) NOT NULL,
    profile_id BIGINT REFERENCES profile (id) NOT NULL UNIQUE
);

create table payment
(
    id BIGSERIAL PRIMARY KEY,
    amount INT NOT NULL,
    receiver_id BIGINT REFERENCES users (id) NOT NULL,
    version BIGINT NOT NULL default 1
);


CREATE TABLE users_chat
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id) NOT NULL,
    chat_id    BIGINT REFERENCES chat (id)  NOT NULL,
    created_at TIMESTAMP,
    created_by VARCHAR(32)
);

CREATE TABLE company_locale
(
    company_id  INT REFERENCES company (id) NOT NULL,
    lang        CHAR(2)                     NOT NULL,
    description VARCHAR(128)                NOT NULL,
    PRIMARY KEY (company_id, lang)
);