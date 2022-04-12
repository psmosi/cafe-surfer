'use strict';

list_f();

const $hashtag = document.getElementById('hashtags');

function backspace() {
    if($inputtext.value == ""){
      if (window.event.keyCode == 8) {
      $hashtag.lastChild.querySelector('.reset').click();
      }
    }
  }
//해시태그 검색 카운트 증가



//해시태그 검색 카운트 감소



//해시태그 전체 목록 출력
function list_f(evt) {
  fetch(`http://localhost:9080/search/hashtag/all`, {
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
 `<label title="${ele.hashtagName}" class="hashtagList ${ele.hashtagName}"
         data-hashtag-Numbering="${ele.hashtagNumbering}" data-hashtag-name="${ele.hashtagName}" data-hashtag-id="${ele.hashtagId}">
     <input type="checkbox" autocomplete="off" class="hashtag_name" id="${ele.hashtagId}">
     #${ele.hashtagName}
   </label>`;
  });

  const $hashtagBox = document.querySelector('.sub_item');
  $hashtagBox.innerHTML = $hashtagList
//해시태그 목록 출력 끝


  document.querySelector('.sub_item').addEventListener('click', (evt) => {
    if(evt.target.tagName !== 'LABEL' ) return;

    const $hashtagNames = evt.target.dataset.hashtagName;
    const $hashtagId = evt.target.dataset.hashtagId;
    const $hashtagNumbering = evt.target.dataset.hashtagNumbering;

    newItem(evt.target.dataset.hashtagName, evt.target.dataset.hashtagId, evt.target.dataset.hashtagNumbering);
  }, true);
}

function newItem(item1, item2, item3) {
  const $item1 = document.querySelector(item1);
  const $item2 = document.getElementById(item2);

  if ($item2.checked == false) {

    const $Ele = makeElement('ul', { 'class': '', 'id': item3 },
                 makeElement('li', { 'class': '', 'id': '' },
                 makeElement('span', { 'class': 'tag_name' }, item1),
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
  const $searchTypeBySearch = document.getElementById('searchType');
  const $keywordBySearch = document.getElementById('keyword');
  const $searchBtnBySearch = document.getElementById('searchBtn');

  //검색 버튼 클릭시
  $searchBtnBySearch?.addEventListener('click', search_f);

  //키워드 입력창 엔터키 눌렀을때 검색
  $keywordBySearch?.addEventListener('keydown', e => {
     if(e.key === 'Enter') {
       search_f(e);
     }
  });

  function search_f(e){
    //해시태그 검색
    if ($hashtag.childElementCount >= 1) {
      let $selectedTags = $hashtag.getElementsByClassName('tag_name');
      $keywordBySearch.value = ``;
      for (let i = 0; i < $selectedTags.length; i++) {
        $keywordBySearch.value += $selectedTags[i].textContent;
        if (i != $selectedTags.length-1) {
          $keywordBySearch.value += ', ';
        }
      }
      const url = `/coffeeShop/list/1/${$searchTypeBySearch.value}/${$keywordBySearch.value}`;
      location.href= url;
    }

    //키워드 검색
    //입력값 유무 체크
    if($keywordBySearch.value.trim().length === 0 ){
      alert('검색어를 입력하세요');
      $keywordBySearch.focus(); $keywordBySearch.select(); //커서이동
      return false;
    }

    const url = `/coffeeShop/list/1/${$searchTypeBySearch.value}/${$keywordBySearch.value}`;
    location.href= url;
  }

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
  }