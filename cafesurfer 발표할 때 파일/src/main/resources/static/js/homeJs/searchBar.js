'use strict';

const $hashtag = document.getElementById('hashtags');
const $hashtagBox = document.getElementById('sub_item');

let cnt=0;
function backspace() {
    if($inputtext.value == ""){
      if (window.event.keyCode == 8) {
      $hashtag.lastChild.querySelector('.reset').click();
      }
    }
  }
//해시태그 검색 카운트 증가



//해시태그 검색 카운트 감소


//해시태그 탭 클릭 이벤트
const $coffee = 'http://localhost:9080/search/hashtag/coffee';
const $beverage = 'http://localhost:9080/search/hashtag/beverage';
const $dessert = 'http://localhost:9080/search/hashtag/dessert';
const $goods = 'http://localhost:9080/search/hashtag/goods';
const $mood = 'http://localhost:9080/search/hashtag/mood';
const $view = 'http://localhost:9080/search/hashtag/view';
const $location = 'http://localhost:9080/search/hashtag/loc';

list_f($coffee);

//해시태그 목록 출력
function list_f(url) {
  fetch(url , {
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
 `<label title="${ele.hashtagName}" class="hashtagList ${ele.hashtagName}" style="margin:5px 5px;"
         data-hashtag-Numbering="${ele.hashtagNumbering}" data-hashtag-name="${ele.hashtagName}" data-hashtag-id="${ele.hashtagId}">
     <input type="checkbox" autocomplete="off" class="hashtag_name" id="${ele.hashtagId}">
     #${ele.hashtagName}
   </label>`;
  });


  $hashtagBox.innerHTML = $hashtagList
//해시태그 목록 출력 끝
}

$hashtagBox.addEventListener('click', (evt) => {
  if(evt.target.tagName !== 'LABEL' ) return;
  const {hashtagName, hashtagId, hashtagNumbering} =  evt.target.dataset;
  newItem(hashtagName, hashtagId, hashtagNumbering);
});

function newItem(item1, item2, item3) {
  const $item1 = document.querySelector(item1);
  const $item2 = document.getElementById(item2);

//  console.log("newItem", item2, $item2);
//    console.log("newItem", $item2.checked);
  if ($item2.checked == false) {

    const $Ele = makeElement('ul', { 'class': '', 'id': item3 },
                 makeElement('li', { 'class': '', 'id': '' },
                 makeElement('span', { 'class': 'search_tag_name' }, item1),
   ));
    $hashtag.appendChild($Ele);

    //해시태그가 선택되면 키워드 검색 비활성화
    if ($hashtag.childElementCount >= 1) {
      searchType.options[1].selected = true; //select option value 해시태그로 바뀜
      keyword.value = '';
      keyword.style.display = 'none';
    }

    //해시태그 검색 시 태그 개수 제한
    if ($hashtag.childElementCount > 4) {
      alert('해시태그를 이용한 검색은 최대 4개까지 허용됩니다.');
      const $lastChild = $hashtag.lastChild;
      $hashtag.removeChild($lastChild);
    }

  } else if ($item2.checked == true) {
    const $item3 = document.getElementById(item3);
    $hashtag.removeChild($item3);

    //해시태그 선택이 0일때 키워드 검색 활성화
    if ($hashtag.childElementCount == 0) {
      searchType.options[0].selected = true; //select option value 키워드로 바뀜
      keyword.value = '';
      keyword.style.display = 'inline-block';
    }
  }
  console.log($hashtag.getElementsByClassName('tag_name'));
}

  //검색
  const $searchType = document.getElementById('searchType');
  const $keyword = document.getElementById('keyword');
  const $searchBtn = document.getElementById('searchBtn');

  //검색 버튼 클릭시
  $searchBtn?.addEventListener('click', search_f);

  //키워드 입력창 엔터키 눌렀을때 검색
  $keyword?.addEventListener('keydown', e => {
     if(e.key === 'Enter') {
       search_f(e);
     }
  });

  function search_f(e){
    //해시태그 검색
    if ($hashtag.childElementCount >= 1) {
      let $selectedTags = $hashtag.getElementsByClassName('search_tag_name');
      $keyword.value = ``;
      for (let i = 0; i < $selectedTags.length; i++) {
        $keyword.value += $selectedTags[i].textContent;
        if (i != $selectedTags.length-1) {
          $keyword.value += ', ';
        }
      }
      const url = `/coffeeShop/list/1/${$searchType.value}/${$keyword.value}`;
      location.href= url;
    }

    //키워드 검색
    //입력값 유무 체크
    if($keyword.value.trim().length === 0 ){
      alert('검색어를 입력하세요');
      $keyword.focus(); $keyword.select(); //커서이동
      return false;
    }

    const url = `/coffeeShop/list/1/${$searchType.value}/${$keyword.value}`;
    location.href= url;
  }


  //노드생성 함수
  function makeElement(tagName, attributes, ...contents){
    //태그생성
    const elementNode = document.createElement(tagName);

    //어트리뷰트 추가
    if(attributes){
      for(let attr in attributes){
        if(attributes.hasOwnProperty(attr)) {
          elementNode.setAttribute(attr,attributes[attr] );
        }
      }
    }

  //컨텐츠 생성
  for(let i=0; i < contents.length; i++){
    let child = contents[i];
    //3번째 매개값이 문자열이면 텍스트 노드생성
    if(typeof child === 'string' ) {
      child = document.createTextNode(child);
    }
    elementNode.appendChild(child);
  }

  return elementNode;
} //노드생성함수 끝

//탭 클릭시 클릭된 탭 백그라운드 컬러 바꾸기
const $tabColor = document.querySelectorAll('.align-hashtag');
const $tagTabBtn = document.querySelectorAll('.tagTabBtn');
// 클릭 이벤트 생성
$tagTabBtn[0].addEventListener('click', (evt) => {
  $tabColor[0].style.backgroundColor = '#8cc841';
  $tabColor[1].style.backgroundColor = '#29Aeec';
  $tabColor[2].style.backgroundColor = '#29Aeec';
  $tabColor[3].style.backgroundColor = '#29Aeec';
  $tabColor[4].style.backgroundColor = '#29Aeec';
  $tabColor[5].style.backgroundColor = '#29Aeec';
  $tabColor[6].style.backgroundColor = '#29Aeec';
});
$tagTabBtn[1].addEventListener('click', (evt) => {
  $tabColor[0].style.backgroundColor = '#29Aeec';
  $tabColor[1].style.backgroundColor = '#8cc841';
  $tabColor[2].style.backgroundColor = '#29Aeec';
  $tabColor[3].style.backgroundColor = '#29Aeec';
  $tabColor[4].style.backgroundColor = '#29Aeec';
  $tabColor[5].style.backgroundColor = '#29Aeec';
  $tabColor[6].style.backgroundColor = '#29Aeec';
});
$tagTabBtn[2].addEventListener('click', (evt) => {
  $tabColor[0].style.backgroundColor = '#29Aeec';
  $tabColor[1].style.backgroundColor = '#29Aeec';
  $tabColor[2].style.backgroundColor = '#8cc841';
  $tabColor[3].style.backgroundColor = '#29Aeec';
  $tabColor[4].style.backgroundColor = '#29Aeec';
  $tabColor[5].style.backgroundColor = '#29Aeec';
  $tabColor[6].style.backgroundColor = '#29Aeec';
});
$tagTabBtn[3].addEventListener('click', (evt) => {
  $tabColor[0].style.backgroundColor = '#29Aeec';
  $tabColor[1].style.backgroundColor = '#29Aeec';
  $tabColor[2].style.backgroundColor = '#29Aeec';
  $tabColor[3].style.backgroundColor = '#8cc841';
  $tabColor[4].style.backgroundColor = '#29Aeec';
  $tabColor[5].style.backgroundColor = '#29Aeec';
  $tabColor[6].style.backgroundColor = '#29Aeec';
});
$tagTabBtn[4].addEventListener('click', (evt) => {
  $tabColor[0].style.backgroundColor = '#29Aeec';
  $tabColor[1].style.backgroundColor = '#29Aeec';
  $tabColor[2].style.backgroundColor = '#29Aeec';
  $tabColor[3].style.backgroundColor = '#29Aeec';
  $tabColor[4].style.backgroundColor = '#8cc841';
  $tabColor[5].style.backgroundColor = '#29Aeec';
  $tabColor[6].style.backgroundColor = '#29Aeec';
});
$tagTabBtn[5].addEventListener('click', (evt) => {
  $tabColor[0].style.backgroundColor = '#29Aeec';
  $tabColor[1].style.backgroundColor = '#29Aeec';
  $tabColor[2].style.backgroundColor = '#29Aeec';
  $tabColor[3].style.backgroundColor = '#29Aeec';
  $tabColor[4].style.backgroundColor = '#29Aeec';
  $tabColor[5].style.backgroundColor = '#8cc841';
  $tabColor[6].style.backgroundColor = '#29Aeec';
});
$tagTabBtn[6].addEventListener('click', (evt) => {
  $tabColor[0].style.backgroundColor = '#29Aeec';
  $tabColor[1].style.backgroundColor = '#29Aeec';
  $tabColor[2].style.backgroundColor = '#29Aeec';
  $tabColor[3].style.backgroundColor = '#29Aeec';
  $tabColor[4].style.backgroundColor = '#29Aeec';
  $tabColor[5].style.backgroundColor = '#29Aeec';
  $tabColor[6].style.backgroundColor = '#8cc841';
});
// </필터링 리스트 버튼 활성화 시 배경색 변경>
