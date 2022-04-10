'use strict';





  //취소
  cancelBtn?.addEventListener('click',e=>{
    const url = `/coffeeShop/${shopId.value}`;
    location.href= url;
  });

//저장
//const $saveBtn = document.getElementById('saveBtn');
//$saveBtn?.addEventListener("click", e=>{
//  editForm.action = `${editForm.action}`;
//  editForm.submit();
//});

saveBtn?.addEventListener('click', e=> {

    editForm.submit();
});

  //목록
  listBtn?.addEventListener('click',e=>{
      const url = `/coffeeShop/list`;
      console.log('url='+url);
      location.href=url;
  });

//첨부파일삭제
document.querySelector('.content').addEventListener('click', (evt) => {
    const [...classValues] = evt.target.classList;
    if(classValues.includes('fa-trash-can')) {
      if(evt.target.tagName != 'I') return;
        if(!confirm('삭제하시겠습니까?')) return;

        const $i = evt.target;
        const url = `/attach/${$i.dataset.fid}`;
        fetch(url,{
          method:'DELETE'
        }).then(res=>res.json())
          .then(res=>{
            if(res.rtcd == '00'){
              //첨부파일 정보 화면에서 제거
              removeAttachFileFromView(evt);
            }else{
              console.log(res.rtmsg);
            }
          })
          .catch(err=>console.log(err));

//          location.reload();
    }
});

  //삭제
    const $delBtn = document.getElementById('delBtn');
 $delBtn?.addEventListener('click',e=>{
    if(confirm('삭제하시겠습니까?')){

    const url = `/coffeeShop/${shopId.value}/del`;
    location.href= url;
    }
  });

//$files?.addEventListener('click',evt=>{
//  if(evt.target.tagName != 'I') return;
//  if(!confirm('삭제하시겠습니까?')) return;
//
//  const $i = evt.target;
//  const url = `/attach/${$i.dataset.fid}`;
//  fetch(url,{
//    method:'DELETE'
//  }).then(res=>res.json())
//    .then(res=>{
//      if(res.rtcd == '00'){
//        //첨부파일 정보 화면에서 제거
//        removeAttachFileFromView(evt);
//      }else{
//        console.log(res.rtmsg);
//      }
//    })
//    .catch(err=>console.log(err));
//});
function removeAttachFileFromView(evt){
    const $parent = document.getElementById('attachFiles');
    const $child = evt.target.closest('.attachFile');
    $parent.removeChild($child);
}