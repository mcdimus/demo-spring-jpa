DELETE FROM customer;
INSERT INTO customer (id, first_name, last_name, info) VALUES
  ('dcbb96ac-a5aa-4969-bf73-9719e3f45bed', 'Frodo', 'Baggins',
   '{"age":51,"premium":true,"map":{"race":"hobbit","nickname":"The Ringbearer"},"list":["ring","sting","shire"]}');
INSERT INTO customer (id, first_name, last_name, info) VALUES ('e97df039-111b-4564-abd0-c0429f5964bf', 'Sam', 'Gamgee',
                                                               '{"age":59,"premium":false,"map":{"race":"hobbit","nickname":"The Gardener"},"list":["no swimming","elvish bread","shire"]}');
INSERT INTO customer (id, first_name, last_name, info) VALUES
  ('752c1efb-af4b-4d85-9840-cc4cfe936f98', 'Gandalf', 'Grey',
   '{"age":2000,"premium":true,"map":{"race":"mage","nickname":"Mithrandirr"},"list":["wizard","staff","fireworks"]}');