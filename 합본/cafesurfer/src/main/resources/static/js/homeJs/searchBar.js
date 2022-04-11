'use strict';

list_f();

const $hashtag = document.querySelector('.hashtags');
const $inputtext = document.getElementById('inputtext');

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
//  `<label>
//     <input type="checkbox" autocomplete="off" class="hashtag_name" data-hashtag-id="${ele.hashtagId}">
//     [${ele.hashtagName}],
//   </label>`;
 `<label title="${ele.hashtagName}" class="hashtagList ${ele.hashtagName}" data-hashtag-Numbering="${ele.hashtagNumbering}" data-hashtag-name="${ele.hashtagName}" data-hashtag-id="${ele.hashtagId}">
     <input type="checkbox" autocomplete="off" class="hashtag_name" id="${ele.hashtagId}">
     #${ele.hashtagName}
   </label>`;
  });

  const $hashtagBox = document.querySelector('.sub_item');
  $hashtagBox.innerHTML = $hashtagList

//<div id="${ele.hashtagNumbering}"></div>
  document.querySelector('.sub_item').addEventListener('click', (evt) => {
    if(evt.target.tagName !== 'LABEL' ) return;
//    console.log(evt.target.dataset.hashtagName,evt.target.dataset.hashtagId);
//HASHTAG_NUMBERING

    const $hashtagNames = evt.target.dataset.hashtagName;
    console.log($hashtagNames);
    const $hashtagId = evt.target.dataset.hashtagId;
    console.log($hashtagId);
    const $hashtagNumbering = evt.target.dataset.hashtagNumbering;
    console.log($hashtagNumbering);



      newItem(
      evt.target.dataset.hashtagName,
       evt.target.dataset.hashtagId,
        evt.target.dataset.hashtagNumbering
       );
      console.log('체크1');


    function newItem(item1, item2, item3) {
      const $item1 = document.querySelector(item1);
      const $item2 = document.getElementById(item2);
//      const $item3 = document.getElementById(item3);

 console.log('ccccc');
// console.log($item3);

      if ($item2.checked == false) {
        console.log('bbbbb');

        const $Ele = makeElement('ul', { 'class': '', 'id': item3 },
          makeElement('li', { 'class': '', 'id': '' },
          makeElement('span', { 'class': 'fil_name' }, item1),
       ));
        $hashtag.appendChild($Ele);
//        $item1.style.color = "red";
        console.log('체크2');


        keyword.style.display = 'none';
        keyword.value = document.querySelector('.fil_name').textContent;
        searchType.options[0].selected = true;





      } else if ($item2.checked == true) {
        console.log('aaaaaaa');
        const $item3 = document.getElementById(item3);
        $hashtag.removeChild($item3);
        keyword.value = '';
        keyword.style.display = 'inline-block';
//        $item1.style.color = "black";
        console.log('체크해제');

      }
    }


  },true);


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