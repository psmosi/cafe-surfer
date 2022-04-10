'use strict';

list_f();

//해시태그 검색 카운트 증가



//해시태그 검색 카운트 감소



//해시태그 전체 목록 출력
function list_f(evt) {
  fetch(`http://localhost:9080/search/hashtag`, {
    method: 'GET',
  })
    .then((res) => res.json())
    .then((res) => {
      console.log(res);
      displayItem(res.data);
    })
    .catch((err) => {
      console.error('Err:', err);
    });
}

//해시태그 목록 html
function displayItem(data) {
  let $hashtagList =``;
  data.forEach( ele => {
  $hashtagList +=
  `<label>
     <input type="checkbox" autocomplete="off" class="hashtag_name">
     [${ele.hashtagName}],
   </label>`;
  });

  const $hashtagBox = document.querySelector('.sub_item');
  $hashtagBox.innerHTML = $hashtagList
}