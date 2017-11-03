CREATE TABLE `aotiana` (
  `uid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tiem` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

/*
-- Query: select * from aotiana
-- Date: 2017-11-01 22:53
*/
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('a','apple','http://apple.com','2017-11-01 20:28:13');
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('b','apple','http://apple.com','2017-11-01 20:28:58');
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('c','apple','http://apple.com','2017-11-01 20:29:05');
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('c','huawei','http://huawei.com','2017-11-01 20:29:25');
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('b','huawei','http://huawei.com','2017-11-01 20:29:30');
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('a','huawei','http://huawei.com','2017-11-01 20:29:38');
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('a','huawei','http://huawei.com','2017-11-01 20:30:03');
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('a','huawei','http://huawei.com','2017-11-01 20:30:14');
INSERT INTO `aotiana` (`uid`,`bid`,`url`,`tiem`) VALUES ('b','huawei','http://huawei.com','2017-11-01 20:30:24');



CREATE TABLE `aotianb` (
  `uid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bid` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pv` int(11) DEFAULT NULL,
  `lastView` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci


/*
-- Query: SELECT * FROM stephen.aotianb
-- Date: 2017-11-01 23:02
*/
INSERT INTO `aotianb` (`uid`,`bid`,`pv`,`lastView`) VALUES ('m','os',1,'2017-11-01 21:12:53');
INSERT INTO `aotianb` (`uid`,`bid`,`pv`,`lastView`) VALUES ('a','huawei',1,'2017-11-01 01:01:01');


select * from aotiana;
select * from aotianb;
select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a  group by a.uid,a.bid;
select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a  group by a.uid,a.bid union select b.uid uid,b.bid bid,b.pv,b.lastView from aotianb b;
select uid,bid,sum(pv),max(lastView) from(select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a  group by a.uid,a.bid union select b.uid uid,b.bid bid,b.pv,b.lastView from aotianb b) a group by uid,bid;
-- 汇总a表数据，条件uid和bid不再b表
select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a where not exists(select 1 from aotianb b where a.uid=b.uid and a.bid = b.bid) group by a.uid,a.bid;
-- 不再直接汇总追加
-- insert into aotianb select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a where not exists(select 1 from aotianb b where a.uid=b.uid and a.bid = b.bid) group by a.uid,a.bid;

-- 汇总a表数据，条件uid和bid在b表已经有对应数据
select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a inner join aotianb b on a.uid =b.uid and a.bid =b.bid  group by a.uid,a.bid;

-- 存在的数据union结果,在update
select o.uid,o.bid,sum(o.pv) pv,max(lastView) lastView from (select b.uid,b.bid,b.pv,b.lastView from aotianb b where exists(select 1 from aotiana a where a.uid=b.uid and a.bid = b.bid) union select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a inner join aotianb b on a.uid =b.uid and a.bid =b.bid  group by a.uid,a.bid)o group by o.uid,o.bid ;

select n.pv from (select o.uid,o.bid,sum(o.pv) pv,max(lastView) lastView from (select b.uid,b.bid,b.pv,b.lastView from aotianb b where exists(select 1 from aotiana a where a.uid=b.uid and a.bid = b.bid) union select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a inner join aotianb b on a.uid =b.uid and a.bid =b.bid  group by a.uid,a.bid)o group by o.uid,o.bid ) n

update table aotianb m set 
pv= (select n.pv from (select o.uid,o.bid,sum(o.pv) pv,max(lastView) lastView from (select b.uid,b.bid,b.pv,b.lastView from aotianb b where exists(select 1 from aotiana a where a.uid=b.uid and a.bid = b.bid) union select a.uid,a.bid,count(a.uid) pv ,max(a.tiem) lastView from aotiana a inner join aotianb b on a.uid =b.uid and a.bid =b.bid  group by a.uid,a.bid)o group by o.uid,o.bid ) n) 
where n.uid = m.uid and n.bid = m.bid;

select * from aotianb;



