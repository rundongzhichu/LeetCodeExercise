select distinct l3.num ConsecutiveNums from
(select l1.num, l2.id from Logs l1 left join Logs l2 on l1.id = l2.id-1 where l1.num = l2.num) l3
left join
Logs l4 on l3.id = l4.id-1
where l3.num = l4.num group by l3.num having count(l3.num) >= 1;


#官方解答
SELECT *
FROM
    Logs l1,
    Logs l2,
    Logs l3
WHERE
    l1.Id = l2.Id - 1
    AND l2.Id = l3.Id - 1
    AND l1.Num = l2.Num
    AND l2.Num = l3.Num
;
