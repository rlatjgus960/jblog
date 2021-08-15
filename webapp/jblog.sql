--------------------------------------------------------
--  파일이 생성됨 - 월요일-8월-16-2021   
--------------------------------------------------------
drop table post;
drop table category;
drop table comments;
drop table blog;
drop table users;


----------------------------------------------------


create table users (
    userNo number not null,
    id varchar2(250) not null UNIQUE,
    userName varchar2(100) not null,
    password varchar2(50) not null,
    joinDate date,
    PRIMARY key(userNo)
);

insert into users values (seq_users_no.nextval, 'hijava', '서현', 12345678, sysdate);

select userNo, id, userName, password, joinDate
from users;

delete from users
where id = 'hijava';

commit;

----------------------------------------------------

create table blog (
    id varchar2(50),
    blogTitle varchar2(200) not null,
    logoFile varchar2(200),
    primary key (id),
    CONSTRAINT blog_id FOREIGN KEY(id)
    REFERENCES users(id)
);

select *
from blog b, users u
where b.id = u.id
and b.id = '1234';

----------------------------------------------------


create table category (
    cateNo number,
    id varchar2(50),
    cateName varchar2(200) not null,
    description varchar2(500),
    regDate varchar2(500) not null,
    primary key (cateNo),
    CONSTRAINT category_id FOREIGN KEY(id)
    REFERENCES blog(id)
);

select *
from category;

select cateNo, id, cateName, description, regDate
from category
where cateNo=1;

insert into category
values ( seq_category_no.nextval, 'aaaa', '미분류', '기본으로 생성되는 카테고리입니다.', SYSDATE );


----------------------------------------------------



create table post (
    postNo number,
    cateNo number,
    postTitle varchar2(300) not null,
    postContent varchar2(4000),
    regDate date not null,
    primary key (postNo),
    CONSTRAINT category_no FOREIGN KEY(cateNo)
    REFERENCES category(cateNo)
);

select p.postTitle, p.postContent, to_char(p.regDate, 'yyyy/mm/dd') regDate
from post p, category c, blog b, users u
where p.cateNo = c.cateNo
and c.id = b.id
and b.id = u.id
and u.id = '1234'
and c.cateNo = 3;



select c.cateName
from post p, category c
where p.cateNo = c.cateNo
and c.id = '1234';

select *
from post;

insert into post
values(SEQ_POST_NO.nextval, 1, '포스트 제목', '포스트 내용', sysdate);


select c.cateNo, c.id, c.cateName, c.description, c.regDate, p.postnum
from category c,
     (select cateNo, count(postTitle) postNum
      from post
      group by cateNo) p
where c.cateNo =  p.cateNo(+);

SELECT t.postNum,
COUNT(CASE WHEN t.postNum IS NULL THEN 0 ELSE t.postNum END) AS CNT 
FROM (select c.cateNo, c.id, c.cateName, c.description, c.regDate, p.postnum
      from category c,
           (select cateNo, count(postTitle) postNum
            from post
            group by cateNo) p
      where c.cateNo =  p.cateNo(+)) t
GROUP BY t.postNum;




select c.cateNo, c.id, c.cateName, c.description, c.regDate, p.postnum
from category c,
     (select cateNo, COUNT(CASE WHEN cateNo IS NULL THEN 0 ELSE cateNo END) AS postNum
      from post
      group by cateNo) p
where c.cateNo =  p.cateNo(+);





DELETE FROM (select  c.cateNo,
			         (select  count(case when p.cateNo = c.cateNo then 1 end)
			          from post p) pNum
			 from category c)
WHERE pNum = 0
and cateNo = 16;


rollback;

commit;

----------------------------------------------------


create table comments (
    cmtNo number,
    postNo number,
    userNo number,
    cmtContent varchar2(1000) not null,
    regDate date not null,
    PRIMARY KEY (cmtNo),
    CONSTRAINT comments_postNo FOREIGN KEY(postNo)
    REFERENCES post(postNo),
    CONSTRAINT comments_userNo FOREIGN KEY(userNo)
    REFERENCES users(userNo)
);



----------------------------------------------------


CREATE SEQUENCE seq_users_no
INCREMENT BY 1
START WITH 1
NOCACHE ;

CREATE SEQUENCE seq_category_no
INCREMENT BY 1
START WITH 1
NOCACHE ;

CREATE SEQUENCE seq_post_no
INCREMENT BY 1
START WITH 1
NOCACHE ;

CREATE SEQUENCE seq_comments_no
INCREMENT BY 1
START WITH 1
NOCACHE ;

