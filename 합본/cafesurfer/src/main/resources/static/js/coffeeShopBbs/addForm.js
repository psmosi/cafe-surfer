'use strict';

//textArea => ck_editor 대체
ClassicEditor
      .create( document.querySelector( '#bcontent' ), {
       plugin:['ListStyle','Markdown','MediaEmbed','MediaEmbedToolbar'],
         toolbar: {
            items: [
               'heading',
               '|',
               'underline',
               'bold',
               'italic',
               'link',
               'bulletedList',
               'numberedList',
               'todoList',
               '|',
               'outdent',
               'indent',
               '|',
               'imageInsert',
               'imageUpload',
               'blockQuote',
               'insertTable',
               'mediaEmbed',
               'markdown',
               'undo',
               'redo',
               '|',
               'highlight',
               'fontFamily',
               'fontColor',
               'fontBackgroundColor',
               'fontSize',
               '|',
               'htmlEmbed',
               'specialCharacters'
            ]
         },
         language: 'en',
         image: {
            toolbar: [
               'imageTextAlternative',
               'imageStyle:full',
               'imageStyle:side'
            ]
         },
         table: {
            contentToolbar: [
               'tableColumn',
               'tableRow',
               'mergeTableCells',
               'tableCellProperties',
               'tableProperties'
            ]
         },
      })
      .then( editor => {

         window.editor = editor;
      } )
      .catch( error => {
         console.error( error );
      } );


const $coffeeShopBbs1 = document.querySelector('.coffeeShopBbs-wrap1');
const category1 = ($coffeeShopBbs1?.dataset.code)? $coffeeShopBbs1.dataset.code : '';

const $coffeeShopBbs2 = document.querySelector('.coffeeShopBbs-wrap2');
const category2 = ($coffeeShopBbs2?.dataset.code)? $coffeeShopBbs2.dataset.code : '';

const $coffeeShopBbs3 = document.querySelector('.coffeeShopBbs-wrap3');
const category3 = ($coffeeShopBbs3?.dataset.code)? $coffeeShopBbs3.dataset.code : '';

const $coffeeShopBbs4 = document.querySelector('.coffeeShopBbs-wrap4');
const category4 = ($coffeeShopBbs4?.dataset.code)? $coffeeShopBbs4.dataset.code : '';
// 등록
writeBtn?.addEventListener('click', e=> {
    writeForm.submit();
});
// 목록
listBtn?.addEventListener("click", e => {
 const url = `/coffeeShopBbs/list`;
  location.href = url;
});
//분류자동 선택
const $options1 = document.querySelectorAll('#bcategoryB0101 option');
[...$options1].find(option=>option.value===category1).setAttribute('selected','selected');

const $options2 = document.querySelectorAll('#bcategoryB0102 option');
[...$options2].find(option=>option.value===category2).setAttribute('selected','selected');

const $options3 = document.querySelectorAll('#bcategoryB0103 option');
[...$options3].find(option=>option.value===category3).setAttribute('selected','selected');

const $options4 = document.querySelectorAll('#bcategoryB0104 option');
[...$options4].find(option=>option.value===category4).setAttribute('selected','selected');

