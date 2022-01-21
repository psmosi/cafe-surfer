const $body = document.querySelector('.body');
$body?.addEventListener('click', click_f, true);
const $detail = document.querySelector('.detail');
const $detailCHK = document.querySelector('.detailCHK');

const $tab2box = document.querySelector('.tab2box');
// const $tab3box = document.querySelector('.tab3box');


//테스트 함수
const $tab2 = document.getElementById('tab22');


function click_f(evt) {


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
  
  //지역 초기화
  if(evt.target.matches('.reset')){
   document.getElementById('selectArea1').innerHTML = null;
   document.getElementById('selectArea2').innerHTML = null;
  //  document.getElementById('selectArea3').innerHTML = null;        
   
   document.getElementById('btn tab1').click();
  }

  //상세메뉴 생성
  const tabList = document.querySelectorAll('.detailmenu .Area li');
  const contents = document.querySelectorAll('.detailmenu .Areabox .cont')
  let activeCont = ''; // 현재 활성화 된 컨텐츠 (기본:#tab1 활성화)

  for (var i = 0; i < tabList.length; i++) {
    tabList[i].querySelector('.btn').addEventListener('click', function (e) {
    e.preventDefault();
    for (var j = 0; j < tabList.length; j++) {
      // 나머지 버튼 클래스 제거
      tabList[j].classList.remove('is_on');

      // 나머지 컨텐츠 display:none 처리
      contents[j].style.display = 'none';
    }

    // 버튼 관련 이벤트
    this.parentNode.classList.add('is_on');

    // 버튼 클릭시 컨텐츠 전환
    activeCont = this.getAttribute('href');
    document.querySelector(activeCont).style.display = 'block';
    });
  }

  //상세메뉴 데이터 생성
  //tabs 1번
  if (evt.target.matches('.tabs')) {
   $toMove.addEventListener('click', (evt) => {
     document.getElementById('selectArea1').innerHTML = $toMove.textContent;
     document.getElementById('btn tab2').click();
   });
   if (evt.target.matches('.Ulsan')) {
     
     const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab22' },
     makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod21' }, '울산중구'),
     makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod22' }, '울산동구'),
     makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod23' }, '울산남구'),
     makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod24' }, '울산북구'),
     );
     $tab2box.appendChild($Ele);  
    //  $tab2box.removeChild($tab2);
    //  $tab2box.replaceChild($Ele,$tab2);
    //  console.log($tab2);   
   }
   if (evt.target.matches('.Busan')) {

     const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab22' },
      makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '부산중구'),
      makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '부산동구'),
      makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '부산남구'),
      makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '부산북구')
     );
     $tab2box.appendChild($Ele);
    
   }
   if (evt.target.matches('.Seoul')) {

    const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab22' },
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '서울 전체'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '강남구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '강동구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '강북구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '강서구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '관악구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '광진구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '구로구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '금천구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '노원구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '도봉구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '동대문구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '동작구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '마포구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '서대문구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '서초구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '성동구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '성북구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '송파구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '양천구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '영등포구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '용산구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '은평구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '종로구'),
    makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '중랑구')
   );

     $tab2box.appendChild($Ele);
    
   }
  }

  //tabs2 2번
  if (evt.target.matches('.tabs2')) {
   $toMove.addEventListener('click', (evt) => {
     document.getElementById('selectArea2').innerHTML = $toMove.textContent;
    //  document.getElementById('btn tab3').click();
   });
  
  }

  //tabs3번
  // if (evt.target.matches('.tabs3')) {
  //  $toMove.addEventListener('click', (evt) => {
  //    console.log($toMove.textContent);
  //    document.getElementById('selectArea3').innerHTML = $toMove.textContent;

  //  });
  // }
}



//해시테그 함수 
const $hashtagbox = document.querySelector('.hashtagbox');
$hashtagbox.addEventListener("click", handler, false);
const $input = document.querySelector('.search__input-box');

function handler(evt) {
  if (evt.target.tagName.toLowerCase() != 'p') return;
  const pressBtnOfText = evt.target.textContent.toLowerCase();

  if ($input.value.indexOf(evt.target.textContent) == -1) {
   if ($input.value == 0) {
     $input.value = null;
   }
   $input.value += (pressBtnOfText + " ");
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
 