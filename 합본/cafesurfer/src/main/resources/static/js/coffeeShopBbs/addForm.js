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


const $coffeeShopBbs = document.querySelector('.coffeeShopBbs-wrap');
//const category = ($coffeeShopBbs?.dataset.code)? coffeeShopBbs.dataset.code : '';

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
//const $options = document.querySelectorAll('#bcategory option');
//[...$options].find(option=>option.value===category).setAttribute('selected','selected');