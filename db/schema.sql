BEGIN TRANSACTION;

DROP TABLE IF EXISTS message;

CREATE TABLE message (
    message_id serial NOT NULL,
    text_message varchar(200) NOT NULL,

    CONSTRAINT pk_message_id PRIMARY KEY (message_id)
);

COMMIT;