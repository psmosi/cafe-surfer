const $body = document.querySelector('.body');
$body?.addEventListener('click', click_f, true);

const $detail = document.querySelector('.detail');
const $detailCHK = document.querySelector('.detailCHK');

// const $cont = document.querySelector('.scroll');




const $tab1box = document.querySelector('.asd1');
const $tab2box = document.querySelector('.asd2');
const $tab3box = document.querySelector('.asd3');

const $cont_area1 = document.querySelector('.cont_area1');
const $cont_area2 = document.querySelector('.cont_area2');
const $cont_area3 = document.querySelector('.cont_area3');

flagUlsan = false;
flagUlsan2 = false;

flagBusan = false;
flagBusan2 = false;

flagSeoul = false;
flagSeoul2 = false;

let count = 0;

const $hashtag = document.querySelector('.hashtags');
const $inputtext = document.getElementById('inputtext');




function backspace() { 
  if($inputtext.value == ""){  
	if (window.event.keyCode == 8) {
    $hashtag.lastChild.querySelector('.reset').click();  
    }
  }
}

function click_f(evt) {
  
// 해시태그
if (evt.target.matches('.bbb')) {
  newItem('.bbb', searchMakerRep8888, 'filter_list2', '.filter_list2', '에이수스', 'ccccc reset', 'prodbtn_del_small2', 'filterItem_searchMaker8888'
    );
  console.log('체크에이수스');
}

if (evt.target.matches('.ccccc')) {
  newItemBtn('.ccccc', '.prodbtn_del_small2', '.bbb',);
}

if (evt.target.matches('.aaa')) {
  newItem('.aaa', searchMakerRep6792, 'filter_list1', '.filter_list1', '레노버', 'aaaaa reset', 'prodbtn_del_small1', 'filterItem_searchMaker6792'
    );
  console.log('체크레노버');
}

if (evt.target.matches('.aaaaa')) {
  newItemBtn('.aaaaa', '.prodbtn_del_small1', '.aaa',);
}


function newItem(item1, item2, item3, item4, item5, item6, item7, item8) {

  const $item1 = document.querySelector(item1);

  if (item2.checked == false) {

    const $Ele = makeElement('ul', { 'class': item3, 'id': '' },
      makeElement('li', { 'class': 'fil_item', 'id': item8 },
        makeElement('span', { 'class': 'fil_name' }, item5),
        makeElement('button', { 'class': item7, 'type': 'button' },
          makeElement('span', { 'class': item6 }, "+")
        )));
    $hashtag.appendChild($Ele);
    $item1.style.color = "red";
    console.log('체크');

  } else if (item2.checked == true) {
    const $item4 = document.querySelector(item4);
    $hashtag.removeChild($item4);
    $item1.style.color = "black";
    console.log('체크해제');
    
  }
}

function newItemBtn(itemBtn1, itemBtn2, itemBtn3) {
  console.log('체크체크2222222222');

  if (evt.target.matches(itemBtn1)) {
    console.log('체크해제33333333333');
    document.querySelector(itemBtn3).click();
  }
  if (evt.target.matches(itemBtn2)) {
    console.log('체크해제44444444444');
    document.querySelector(itemBtn3).click();
  }
}

//지역 체크박스
if (evt.target.matches('.Ulsan100')) {
  combox('.Ulsan100', Ulsan10);
  console.log('울산중구');
}
if (evt.target.matches('.Ulsan110')) {
  combox('.Ulsan110', Ulsan11);
  console.log('울산북구');
}
if (evt.target.matches('.Ulsan120')) {
  combox('.Ulsan120', Ulsan12);
  console.log('울산동구');
}
function combox(item1,item2) {
  const $item1 = document.querySelector(item1);
  
  if (item2.checked == false) {    
    $item1.style.color = "red";
    console.log(item2.checked);
    item2.checked == true;
    
  } else if (item2.checked == true) {    
    $item1.style.color = "black";
    console.log(item2.checked);    
  }
  console.log(item2.checked);
}


// if (evt.target.matches('.resetBtn')) {
//   comboxBtn('.Ulsan100', '.Ulsan100');
//   console.log('울산중구');
// }
// function comboxBtn(itemBtn1, itemBtn2) {
//   console.log('체크체크지역2222222222');

//   if (evt.target.matches(itemBtn1)) {
//     console.log('체크해제지역33333333333');
//     document.querySelector(itemBtn2).click();
//   }  
// }
// 



// 초기화 버튼
if (evt.target.matches('.resetBtn')) {
//   console.log('초기화 버튼');
//   document.getElementById('selectArea1').innerHTML = null;
//  document.getElementById('selectArea2').innerHTML = null;
//  document.getElementById('selectArea3').innerHTML = null;    
  
comboxBtn('.Ulsan100',Ulsan10);
comboxBtn('.Ulsan110',Ulsan11);
comboxBtn('.Ulsan120',Ulsan12);
function comboxBtn(itemBtn,itemBtn2) {
  if (itemBtn2.checked == true) {  
      document.querySelector(itemBtn).click();
    }  
  }  
  for(i=0; i<10; i++){
    document.querySelector('.reset').click();  
  }
}





  //상세버튼 출력
  const $toMove = evt.target.closest('a');

  if (evt.target.matches('.detail')) {

   if ($detailCHK.checked == true) {
     $detail.addEventListener('click', (evt) => {
      $detailCHK.checked = false;
     });
   } else if ($detailCHK.checked == false) {
     $detail.addEventListener('click', (evt) => {
      $detailCHK.checked = true;
     });
   }
  }     

    // //입력창 내용 초기화(삭제)
    // if(evt.target.matches('.search-button__delete')){
    //   console.log('삭제버튼 클릭됨!');
      
    //   const inputText = document.querySelector('.search__input-box'); 
    //   inputText.value = null;  
    //  }
  
// //지역 초기화
// if(evt.target.matches('.reset')){
//    document.getElementById('selectArea1').innerHTML = null;
//    document.getElementById('selectArea2').innerHTML = null;
//    document.getElementById('selectArea3').innerHTML = null;        
   
// }
  //지역 시 초기화
  // if(evt.target.matches('.btn')){
  //  document.getElementById('selectArea1').innerHTML = null;
  //  document.getElementById('selectArea2').innerHTML = null;
  //  document.getElementById('selectArea3').innerHTML = null;        
   
  //  document.getElementById('btn tab1').click();
  // }
  // if(evt.target.matches('.btn1')){
  //    document.getElementById('selectArea3').innerHTML = null;             
  //   }
  //   if(evt.target.matches('.btn2')){
  //     document.getElementById('selectArea3').innerHTML = null;             
  //    }
    //  if(evt.target.matches('.btn3')){
    //   document.getElementById('selectArea3').innerHTML = null;             
    //  }
     

 
  // const $toMove = evt.target.closest('a');
  
  if (evt.target.matches('.btn')) {
    $toMove.addEventListener('click', (evt) => {
      
      //  document.getElementById('selectArea1').innerHTML = $toMove.textContent;
      //  document.getElementById('btn tab2').click();
    });
    if (evt.target.matches('.Ulsan') && !flagUlsan) {
     
      const $Ele = makeElement('ul', { 'class': 'com1 list3', 'id': '' },
        makeElement('li', {},
        makeElement('label', {'class': 'com3 btn3 Ulsan2 Ulsan100', 'id' : 'Ulsan100'},"울산중구", makeElement('input', { 'type': 'checkbox', 'id' : 'Ulsan10'}))),
        makeElement('li', {},
        makeElement('label', {'class': 'com3 btn3 Ulsan2 Ulsan110', 'id' : 'Ulsan110'},"울산북구", makeElement('input', { 'type': 'checkbox', 'id' : 'Ulsan11'}))),makeElement('li', {},
        makeElement('label', {'class': 'com3 btn3 Ulsan2 Ulsan120', 'id' : 'Ulsan120'},"울산동구", makeElement('input', { 'type': 'checkbox', 'id' : 'Ulsan12'})))
      );
      $tab3box.appendChild($Ele);
      flagUlsan = true;
    }
    if (evt.target.matches('.Busan') && !flagBusan) {

      const $Ele = makeElement('ul', { 'class': 'com1 list2', 'id': '' },
        makeElement('li', {},
          makeElement('a', { 'href': '#tab12', 'class': 'com3 btn2 Busan2' }, "부산중구")),
        makeElement('li', {},
          makeElement('a', { 'href': '#tab22', 'class': 'com3 btn2 Busan2' }, "부산북구")),
        makeElement('li', {},
          makeElement('a', { 'href': '#tab32', 'class': 'com3 btn2 Busan2' }, "부산동구"))
      );
      $tab2box.appendChild($Ele);
      flagBusan = true;
    }
    if (evt.target.matches('.Seoul') && !flagSeoul) {

      const $Ele = makeElement('ul', { 'class': 'com1 list1', 'id': '' },
        makeElement('li', {},
          makeElement('a', { 'href': '#tab11', 'class': 'com3 btn1 Seoul1' }, "서울중구")),
        makeElement('li', {},
          makeElement('a', { 'href': '#tab21', 'class': 'com3 btn1 Seoul1' }, "서울북구")),
        makeElement('li', {},
          makeElement('a', { 'href': '#tab31', 'class': 'com3 btn1 Seoul1' }, "서울동구"))
      );
      $tab1box.appendChild($Ele);
      flagSeoul = true;
    }
  }
  
  // //tabs2 2번
  // if (evt.target.matches('.btn3')) {
  //   $toMove.addEventListener('click', (evt) => {
  //       document.getElementById('selectArea2').innerHTML = $toMove.textContent;

  // //     //  document.getElementById('btn tab3').click();
  //   });
  //   if (evt.target.matches('.Ulsan2') && !flagUlsan2) {

  //     const $Ele3 = makeElement('div', { 'class': 'com4 area3' },
     
  //       makeElement('a', { 'href': '#', 'id': 'tab13', 'class': 'com3 com2 cont3 test' }, '울산중구3'),
  //       makeElement('a', { 'href': '#', 'id': 'tab23', 'class': 'com3 com2 cont3 test' }, '울산북구3'),
  //       makeElement('a', { 'href': '#', 'id': 'tab33', 'class': 'com3 com2 cont3 test' }, '울산동구3')
  //     ); 
  //     $cont_area3.appendChild($Ele3); 
  //     flagUlsan2 = true; 
    // } 
  // } 
 
  // if (evt.target.matches('.btn2')) { 
  //   $toMove.addEventListener('click', (evt) => { 
  //      document.getElementById('selectArea2').innerHTML = $toMove.textContent; 
  //     //  document.getElementById('btn tab3').click(); 
  //   }); 
  //   if (evt.target.matches('.Busan2') && !flagBusan2) { 
 
  //     const $Ele2 = makeElement('div', { 'class': 'com4 area2' }, 
       
  //       makeElement('a', { 'href': '#', 'id': 'tab12', 'class': 'com3 com2 cont2 test' }, '부산중구2'),
  //       makeElement('a', { 'href': '#', 'id': 'tab22', 'class': 'com3 com2 cont2 test' }, '부산북구2'),
  //       makeElement('a', { 'href': '#', 'id': 'tab32', 'class': 'com3 com2 cont2 test' }, '부산동구2')
  //     ); 
  //     $cont_area2.appendChild($Ele2); 
  //     flagBusan2 = true; 
    // } 
  // } 
 
 
  // if (evt.target.matches('.btn1')) { 
  //   $toMove.addEventListener('click', (evt) => { 
  //      document.getElementById('selectArea2').innerHTML = $toMove.textContent; 
  //     //  document.getElementById('btn tab3').click(); 
  //   }); 
  //   if (evt.target.matches('.Seoul1') && !flagSeoul2) { 
  //     const $Ele2 = makeElement('div', { 'class': 'com4 area1' }, 
     
  //       makeElement('a', { 'href': '#', 'id': 'tab11', 'class': 'com3 com2 cont1 test' }, '서울중구2'),
  //       makeElement('a', { 'href': '#', 'id': 'tab21', 'class': 'com3 com2 cont1 test' }, '서울북구2'),
  //       makeElement('a', { 'href': '#', 'id': 'tab31', 'class': 'com3 com2 cont1 test' }, '서울동구2')
  //     );
  //     $cont_area1.appendChild($Ele2);
  //     flagSeoul2 = true;
    // }


  // }
  // if (evt.target.matches('.test')) {
  //   $toMove.addEventListener('click', (evt) => {
  //      document.getElementById('selectArea3').innerHTML = $toMove.textContent;
  //     //  document.getElementById('btn tab3').click();
  //   });  
  // }  


  /**
   * ================================================================================
   * **/
  // 시
  newFunction('.list li', '.cont', '.btn', 'com6');
  // 서울
  // newFunction('.list1 li', '.cont1', '.btn1', 'com6');
  // //부산
  // newFunction('.list2 li', '.cont2', '.btn2', 'com6');
  // //울산
  // newFunction('.list3 li', '.cont3', '.btn3', 'com6');

  function newFunction(attr1, attr2, attr3, attr4) {

    const tabList = document.querySelectorAll(attr1);
    const contents = document.querySelectorAll(attr2);
    let activeCont3 = ''; // 현재 활성화 된 컨텐츠 (기본:#tab1 활성화)

    for (var i = 0; i < tabList.length; i++) {
      tabList[i].querySelector(attr3).addEventListener('click', function (e) {
        e.preventDefault();
        for (var j = 0; j < tabList.length; j++) {
          // 나머지 버튼 클래스 제거
          tabList[j].classList.remove(attr4);

          // 나머지 컨텐츠 display:none 처리
          contents[j].style.display = 'none';
        }

        // 버튼 관련 이벤트
        this.parentNode.classList.add(attr4);

        // 버튼 클릭시 컨텐츠 전환
        activeCont3 = this.getAttribute('href');
        document.querySelector(activeCont3).style.display = 'inline-block';
      });
    }
  }
}



// //해시테그 함수 
// const $hashtagbox = document.querySelector('.hashtagbox');
// $hashtagbox.addEventListener("click", handler, false);
// const $input = document.querySelector('.search__input-box');

// function handler(evt) {
//   if (evt.target.tagName.toLowerCase() != 'p') return;
//   const pressBtnOfText = evt.target.textContent.toLowerCase();


//   if ($input.value.indexOf(evt.target.textContent) == -1) {
//    if ($input.value == 0) {
//      $input.value = null;
//    }
//    console.log('aa');
//    $input.value += (pressBtnOfText + " ");
//   }
// }




/*-------------------------------------------------------------*
 * 노드생성 함수
 * 주어진 태그이름, 속성, 컨텐츠를 포함하는 요소를 만들어 주는 함수
 *-------------------------------------------------------------*/
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
 