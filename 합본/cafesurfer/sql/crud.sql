
-- 데이터 CRUD

 -- 회원정보
 -- insert
 insert into membership
 values(membership_member_id_seq.nextval, '홍길순', 'hong3@kh.com', '여', 20, '010-5678-7891', default);

 -- update
 update membership set member_tel='010-4567-8901'
 where member_id = 1;

 -- delete
 delete from membership
 where member_id = 1;

-- read
 select * from membership;

 -- 커피숍 정보
 -- insert
 insert into coffeeshop
 values(coffeeshop_shop_id_seq.nextval, '카페 솔츠', '울산 남구 봉월로8번길 18 1층', '052-710-5252', default, default, default, 1, 0, default);

 -- update
 update coffeeshop set shop_name = '스타벅스'
 where shop_id = 1;

 -- delete
 delete from coffeeshop
 where shop_id = 1;

 -- read
 select * from coffeeshop;

 -- 리뷰
 -- insert
 insert into review
 values(review_review_id_seq.nextval, '홍길순이 카페 솔츠에 남긴 댓글2', default, default, 2, 2);

 -- delete
 delete from review
 where review_id = 1;

 select * from review;

 -- 신고 유형 (값 고정)
 -- insert
 insert into report_type
 values(0, '스팸(부적절한 홍보)');

 insert into report_type
 values(1, '음란물');

 insert into report_type
 values(2, '혐오표현');

 insert into report_type
 values(3, '욕설 및 비방');

 --read
 select * from report_type;

 -- 리뷰 신고
 -- insert
 insert into report
 values(5, 0);

 -- 신고를 받을 경우 review테이블의 repot_count증가

 -- read (신고된 리뷰의 내용과 신고 유형 확인)
 select t1.review_content "리뷰 내용", t2.report_type_name "신고 사유"
 from review t1, report_type t2, report t3
 where t1.review_id = t3.review_id
 and t3.report_type_code_id = t2.report_type_code;

 -- 찜하기
 -- insert
 insert into bookmark
 values(0, null, 2, 2);

 -- update
 update bookmark set bookmark_checked = 1, bookmark_chkdate = systimestamp
 where coffeeshop_shop_id = 1
 and membership_member_id = 1;

 select * from bookmark;

 -- 해시태그 식별
 -- (메뉴 1001-2000)
 -- insert
 insert into hashtag_classification
 values(hashtag_menu_id_seq.nextval, '캬라멜 마끼아또', default);

 -- read
 select * from hashtag_classification;

 -- (지역 2001-3000)
 insert into hashtag_classification
 values(hashtag_loc_id_seq.nextval, '울산 울주군', default);

 -- (분위기 3001-4000)
 insert into hashtag_classification
 values(hashtag_mood_id_seq.nextval, '따뜻함', default);

 -- (뷰 4001-5000)
 insert into hashtag_classification
 values(hashtag_view_id_seq.nextval, '오션뷰', default);

  -- read
 select * from hashtag_classification
 order by hashtag_id;

 -- 커피숍 해시태그
 -- insert
 insert into shop_hashtag
 values(1, 1002);
        