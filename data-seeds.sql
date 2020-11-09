INSERT INTO account_type
    (name, is_active, created_at, updated_at)
VALUES
    ('user', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('specialist', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO specialist_type
    (name, is_active, created_at, updated_at)
VALUES
    ('nutritionist', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('coach', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO account
    (type_id, email, first_name, last_name, password, salt, is_active, created_at, updated_at)
VALUES
    (2, 's@test.com', 'Sportacus', 'Helth', '$2a$10$PpeMuMYmKOYzh7bVlmTEieySEVWE.mwpwDfZ1uiBn/9kAOr/WuE8S', '$2a$10$PpeMuMYmKOYzh7bVlmTEie', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO specialist_account
    (account_id, specialist_type_id, document_number, description, is_active, created_at, updated_at)
VALUES
    (1, 1, '103212345', 'Doctor in nutrition', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO subscription_type (subscription_type_name,subscription_type_description,subscription_type_price,is_active, created_at, updated_at)
VALUES
    ('Premium','Usuario premium con acceso a rutinas de entrenamiento y dietas a seguir sugeridas por profesionales recomendados',20, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO physical_exercise_type
    (name, is_active, created_at, updated_at)
VALUES
    ('strength', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('intensive', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('stretching', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('balance', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('cardio', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO physical_exercise
    (type_id, name, description, is_active, created_at, updated_at)
VALUES
    (1, 'Pull Ups', 'Elbows flex and the shoulders adduct and extend to bring the elbows to the torso', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 'Push Ups', 'By raising and lowering the body using the arms, push-ups exercise the pectoral muscles, triceps, and anterior deltoids', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 'Pistol Squats', 'Increase single leg strength, balance, and improve movement mechanics of the lower body', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Pull Ups', 'Elbows flex and the shoulders adduct and extend to bring the elbows to the torso', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Knee rotation', 'Maintain flexibility', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Knee rotation', 'Maintain flexibility', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Knee rotation', 'Maintain flexibility Maintain flexibility Maintain flexibility', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Standing knee lift', 'Feel steadier on your feet and helps prevent falls', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Standing knee lift', 'feel steadier on your feet and helps prevent falls', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Outside Running', 'Get along with your body Get along with your body Get along with your body Get along with your body', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Outside Running 2', 'Get along with your body Get along with your body Get along with your body Get along with your body', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO week_day
    (name, is_active, created_at, updated_at)
VALUES
    ('monday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('tuesday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('wednesday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('thursday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('friday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('saturday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('sunday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;
INSERT INTO user_medical_data
    (id,  is_active, created_at, updated_at,height_in_centimeters,weight_in_kilograms,birth_day,user_account_user_account_id)
VALUES
    (2,true, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 150,54, TIMESTAMP '1998-05-01 11:00:00', 2)
;

INSERT INTO user_subscription
    (user_subscription_id, is_active, created_at, updated_at, expiration_date, subscription_type_id,user_account_id )
VALUES
    (2, true, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 1, 2)
;
UPDATE user_account SET payment_method= true
    WHERE user_account_id = 2;

UPDATE user_account SET specialist_account_id= 1
        WHERE user_account_id = 2;