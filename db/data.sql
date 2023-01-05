BEGIN TRANSACTION;
INSERT INTO
    message
    (top_line, bottom_line)
VALUES
    ('Test one', 'Test one'),
    ('Test two', 'Test two')
;
COMMIT TRANSACTION;