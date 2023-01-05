BEGIN TRANSACTION;

DROP TABLE IF EXISTS message;

CREATE TABLE message (
    message_id serial NOT NULL,
    top_line varchar(16) NOT NULL,
    bottom_line varchar(16),

    CONSTRAINT pk_message_id PRIMARY KEY (message_id)
);

COMMIT;