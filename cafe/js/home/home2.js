	const $body = document.querySelector('.body');
    $body?.addEventListener('click', click_f, true);
    const $detail = document.querySelector('.detail');
    const $detailCHK = document.querySelector('.detailCHK');

    const $tab1 = document.querySelector('.tabs');
    const $tab2 = document.querySelector('.tabs2');
    const $tab3 = document.querySelector('.tabs3');
    const $tab2box = document.querySelector('.tab2box');
    const $tab3box = document.querySelector('.tab3box');

    const $Ulsan = document.querySelector('.Ulsan');
    const $Busan = document.querySelector('.Busan');
    const $Seoul = document.querySelector('.Seoul');



    function click_f(evt) {

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

      if (evt.target.matches('.tabs')) {
        $toMove.addEventListener('click', (evt) => {
          document.getElementById('selectArea1').innerHTML = $toMove.textContent;
          document.getElementById('btn tab2').click();
        });
        if (evt.target.matches('.Ulsan')) {

          const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab2' },
          makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod21' }, '울산중구'),
          makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod22' }, '울산동구'),
          makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod23' }, '울산남구'),
          makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod24' }, '울산북구'),
          );
          $tab2box.appendChild($Ele);

        }
        if (evt.target.matches('.Busan')) {

          const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab2' },
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '부산중구'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '부산동구'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '부산남구'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '부산북구')
          );
          $tab2box.appendChild($Ele);

        }
        if (evt.target.matches('.Seoul')) {

          const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab2' },
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '서울중구'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '서울동구'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '서울남구'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs2 cod00' }, '서울북구')
          );
          $tab2box.appendChild($Ele);

        }
      }


      if (evt.target.matches('.tabs2')) {
        $toMove.addEventListener('click', (evt) => {
          document.getElementById('selectArea2').innerHTML = $toMove.textContent;
          document.getElementById('btn tab3').click();
        });
        if (evt.target.matches('.cod21')) {

          const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab3' },
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod201' }, '울산중구1번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod202' }, '울산중구2번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod203' }, '울산중구3번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod204' }, '울산중구4번지')
          );
          $tab3box.appendChild($Ele);

        }
        if (evt.target.matches('.cod22')) {

          const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab3' },
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod201' }, '울산동구1번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod202' }, '울산동구2번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod203' }, '울산동구3번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod204' }, '울산동구4번지')
          );
          $tab3box.appendChild($Ele);

        }
        if (evt.target.matches('.cod23')) {

          const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab3' },
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod201' }, '울산남구1번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod202' }, '울산남구2번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod203' }, '울산남구3번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod204' }, '울산남구4번지')
          );
          $tab3box.appendChild($Ele);

        }
        if (evt.target.matches('.cod24')) {

          const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab3' },
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod201' }, '울산북구1번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod202' }, '울산북구2번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod203' }, '울산북구3번지'),
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod204' }, '울산북구4번지')
          );
          $tab3box.appendChild($Ele);

        }
        if (evt.target.matches('.cod00')) {

          const $Ele = makeElement('div', { 'class': 'cont', 'id': 'tab3' },
            makeElement('a', { 'href': 'javascript:void(0)', 'class': 'tabs3 cod000' }, '미구현'),

          );
          $tab3box.appendChild($Ele);

        }
      }



      if (evt.target.matches('.tabs3')) {
        $toMove.addEventListener('click', (evt) => {
          console.log($toMove.textContent);
          document.getElementById('selectArea3').innerHTML = $toMove.textContent;

        });
      }
    }

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