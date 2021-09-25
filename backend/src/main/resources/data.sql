-- Allocate the first batch of sequences
select next value for user_sequence_generator;

-- The passwords is saved as plain text in DEMO purpose. See WebSecurityConfiguration#passwordEncoder
insert into users (id, username, password, created_by, created_timestamp) values(1, 'scott', '12345', 'system', NOW());
insert into users (id, username, password, created_by, created_timestamp) values(2, 'alex', '12345', 'system', NOW());
insert into users (id, username, password, created_by, created_timestamp) values(3, 'jennifer', '12345', 'system', NOW());

insert into messages (user_id, subject, text, created_by, created_timestamp) values(1, 'Brunch this weekend?', 'I''ll be in your neighborhood doing errands this…', 'scott', NOW());
insert into messages (user_id, subject, text, created_by, created_timestamp) values(2, 'Summer BBQ', 'Wish I could come, but I''m out of town this…', 'alex', NOW());
insert into messages (user_id, subject, text, created_by, created_timestamp) values(3, 'Oui Oui', 'Do anyone have Paris recommendations?', 'jennifer', NOW());


