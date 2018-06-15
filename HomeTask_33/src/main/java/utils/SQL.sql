
drop TABLE  if exists phone_info ;
drop TABLE  if exists adress_info;
drop TABLE  if exists user_info ;


create table user_info (
user_id serial PRIMARY KEY not null,
user_name VARCHAR (255),
first_name VARCHAR (255)
);

create table phone_info(
phone_id serial PRIMARY KEY not null,
user_id integer REFERENCES user_info(user_id),
phone VARCHAR (50)
);

create table adress_info(
adress_id serial PRIMARY KEY not null,
user_id integer REFERENCES user_info(user_id),
adress VARCHAR (255)
);



create or replace function user_info_add_update_delete(
 _user_id INTEGER,
 _user_name VARCHAR (255),
 _first_name VARCHAR (255),
 _command int default 0
)
 returns integer as
 $$
 BEGIN
   IF _command = 0 then
     BEGIN
       IF (_user_id is null) or (_user_id = 0) THEN
          BEGIN
           insert into user_info (user_name, first_name) values (_user_name, _first_name) RETURNING user_id into _user_id;
           return _user_id;
          END;
        ELSE
          BEGIN
            update user_info set user_name = _user_name, first_name  = _first_name where user_id = _user_id;
            return _user_id;
          END;
       END IF;
      END;
    ELSE
      BEGIN
        DELETE FROM user_info WHERE user_id = _user_id;
        RETURN _user_id;
      END;
    END IF;
 END;

 $$LANGUAGE plpgsql;


select * from user_info;
