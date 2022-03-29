-- 커피숍
insert into coffeeshop
values(coffeeshop_shop_id_seq.nextval, '카페 솔츠', '울산 남구 봉월로8번길 18 1층', '052-710-5252', default, default, default, 0, 1, default);
insert into coffeeshop
values(coffeeshop_shop_id_seq.nextval, 'ppl', '울산 남구 중앙로204번길 9 1층', '010-8510-8572', default, default, default, 0, 0, default);
insert into coffeeshop
values(coffeeshop_shop_id_seq.nextval, '모도', '울산 남구 중앙로156번길 27 101호', '010-2845-1335', default, default, default, 0, 0, default);
insert into coffeeshop
values(coffeeshop_shop_id_seq.nextval, '헤이스탁', '울산 남구 신정로68번길', '052-700-3700', default, default, default, 0, 0, default);
insert into coffeeshop
values(coffeeshop_shop_id_seq.nextval, 'VERSE 2 COFFEE', '울산 남구 삼산로 52 1층', '010-2548-2317', default, default, default, 0, 0, default);



-- 메뉴 해시태그  
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴1', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴2', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴3', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴4', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴5', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴6', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴7', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴8', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴9', default);
insert into hashtag_classification
values(HASHTAG_MENU_ID_SEQ.nextval, '메뉴10', default);


-- 지역 해시태그
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역1', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역2', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역3', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역4', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역5', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역6', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역7', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역8', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역9', default);
insert into hashtag_classification
values(HASHTAG_LOC_ID_SEQ.nextval, '지역10', default);

-- 분위기 해시태그
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기1', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기2', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기3', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기4', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기5', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기6', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기7', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기8', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기9', default);
insert into hashtag_classification
values(HASHTAG_MOOD_ID_SEQ.nextval, '분위기10', default);
-- 뷰 해시태그
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰1', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰2', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰3', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰4', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰5', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰6', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰7', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰8', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰9', default);
insert into hashtag_classification
values(HASHTAG_VIEW_ID_SEQ.nextval, '뷰10', default);

-- 커피숍, 해시태그 조인
insert into shop_hashtag
values(1, 1001);
insert into shop_hashtag
values(1, 1002);
insert into shop_hashtag
values(1, 1003);
insert into shop_hashtag
values(1, 1004);
insert into shop_hashtag
values(1, 1005);
insert into shop_hashtag
values(1, 1006);
insert into shop_hashtag
values(1, 1007);
insert into shop_hashtag
values(1, 1008);
insert into shop_hashtag
values(1, 1009);
insert into shop_hashtag
values(1, 2001);
insert into shop_hashtag
values(1, 3001);
insert into shop_hashtag
values(1, 3002);
insert into shop_hashtag
values(1, 4001);
insert into shop_hashtag
values(1, 4002);

insert into shop_hashtag
values(2, 1001);
insert into shop_hashtag
values(2, 1002);
insert into shop_hashtag
values(2, 1003);
insert into shop_hashtag
values(2, 1004);
insert into shop_hashtag
values(2, 1005);
insert into shop_hashtag
values(2, 1006);
insert into shop_hashtag
values(2, 1007);
insert into shop_hashtag
values(2, 2002);
insert into shop_hashtag
values(2, 3003);
insert into shop_hashtag
values(2, 3004);
insert into shop_hashtag
values(2, 3005);
insert into shop_hashtag
values(2, 4003);
insert into shop_hashtag
values(2, 4004);

insert into shop_hashtag
values(3, 1001);
insert into shop_hashtag
values(3, 1002);
insert into shop_hashtag
values(3, 1003);
insert into shop_hashtag
values(3, 1004);
insert into shop_hashtag
values(3, 1005);
insert into shop_hashtag
values(3, 1006);
insert into shop_hashtag
values(3, 1007);
insert into shop_hashtag
values(3, 1008);
insert into shop_hashtag
values(3, 1009);
insert into shop_hashtag
values(3, 1010);
insert into shop_hashtag
values(3, 2003);
insert into shop_hashtag
values(3, 3005);
insert into shop_hashtag
values(3, 3006);
insert into shop_hashtag
values(3, 3007);
insert into shop_hashtag
values(3, 4005);
insert into shop_hashtag
values(3, 4006);

insert into shop_hashtag
values(4, 1001);
insert into shop_hashtag
values(4, 1002);
insert into shop_hashtag
values(4, 1003);
insert into shop_hashtag
values(4, 1004);
insert into shop_hashtag
values(4, 1005);
insert into shop_hashtag
values(4, 1006);
insert into shop_hashtag
values(4, 1007);
insert into shop_hashtag
values(4, 2007);
insert into shop_hashtag
values(4, 3007);
insert into shop_hashtag
values(4, 3008);
insert into shop_hashtag
values(4, 3009);
insert into shop_hashtag
values(4, 4007);
insert into shop_hashtag
values(4, 4008);

insert into shop_hashtag
values(5, 1001);
insert into shop_hashtag
values(5, 1002);
insert into shop_hashtag
values(5, 1003);
insert into shop_hashtag
values(5, 1004);
insert into shop_hashtag
values(5, 1005);
insert into shop_hashtag
values(5, 1006);
insert into shop_hashtag
values(5, 1007);
insert into shop_hashtag
values(5, 1008);
insert into shop_hashtag
values(5, 1009);
insert into shop_hashtag
values(5, 1010);

insert into shop_hashtag
values(5, 2001);
insert into shop_hashtag
values(5, 3008);
insert into shop_hashtag
values(5, 3009);
insert into shop_hashtag
values(5, 3010);
insert into shop_hashtag
values(5, 4009);
insert into shop_hashtag
values(5, 4010);


commit;


