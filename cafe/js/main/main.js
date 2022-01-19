'use strict';

// 경로 저장
const src1 = '../../cafeImg/썸네일1.png';
const src2 = '../../cafeImg/썸네일2.png';
const src3 = '../../cafeImg/썸네일3.png';
const src4 = '../../cafeImg/썸네일4.png';

// 경로를 배열에 담기
const cafeImgArray = [src1, src2, src3, src4];

// 인덱스 번호 랜덤 추출
let indexNum = Math.floor(Math.random() * 4);
console.log(indexNum);

const $cafeThumb = document.querySelector('.cafeThumb');

$cafeThumb.src = cafeImgArray[indexNum];
