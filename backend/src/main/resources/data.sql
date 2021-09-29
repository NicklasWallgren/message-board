-- Allocate the first batch of sequences
select next value for user_sequence_generator;

-- The passwords is saved as plain text in DEMO purpose. See WebSecurityConfiguration#passwordEncoder
insert into users (id, username, password, created_by, created_timestamp) values(1, 'scott', '12345', 'system', NOW());
insert into users (id, username, password, created_by, created_timestamp) values(2, 'alex', '12345', 'system', NOW());
insert into users (id, username, password, created_by, created_timestamp) values(3, 'jennifer', '12345', 'system', NOW());

insert into messages (user_id, text, created_by, created_timestamp) values(1, 'a text', 'scott', NOW());
insert into messages (user_id, text, created_by, created_timestamp) values(2, 'a text', 'alex', NOW());
insert into messages (user_id, text, created_by, created_timestamp) values(3, 'a text', 'jennifer', NOW());


