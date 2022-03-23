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



// 초기화 버튼
if (evt.target.matches('.resetBtn')) {
  
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
}