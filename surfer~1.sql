select t1.shop_id, t3.shop_name
from bookmark t1, membership t2, coffeeshop t3
where t1.shop_id = t3.shop_id
and t1.member_id = t2.member_id;