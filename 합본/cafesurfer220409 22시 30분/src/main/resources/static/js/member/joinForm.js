//'use strict';
//유효성 체크 상태
//  const validChkStatus = {
//         memberEmail: false
//       }
//       const validChkStatus1 = {
//         memberTel: false
//       }
//
//       const $email = document.getElementById('memberEmail');
//       const $emailDupChk = document.getElementById('emailDupChk');
//
//       const $tel = document.getElementById('memberTel');
//       const $telDupChk = document.getElementById('telDupChk');
//
//       $emailDupChk.addEventListener('click', e => {
//         const $errmsg = $emailDupChk.closest('li').querySelector('.errmsg');
//         if (!$email.value) {
//           $errmsg.textContent = '아이디를 입력하세요';
//           $email.select(); $email.focus();
//           return false;
//         }
//
//         const xmlHttpreq = new XMLHttpRequest();
//
//         const url = `/api/members/${$email.value}/existEmail`;
//
//         xmlHttpreq.open("GET", url);
//         xmlHttpreq.send();
//
//         xmlHttpreq.addEventListener('load', e => {
//           if (xmlHttpreq.status === 200) { //성공적으로 서버응답 받으면
//             console.log(xmlHttpreq.response);
//             const result = JSON.parse(xmlHttpreq.response); //Json포맷 문자열 => JS객체로변환
//             console.log(result);
//             const $errmsg = $emailDupChk.closest('li').querySelector('.errmsg');
//             if (result.rtcd === '00') {
//               //alert('이미 사용되고 있는 아이디 입니다.');
//               $errmsg.textContent = '이미 사용되고 있습니다 .';
//               $errmsg.style.display = 'block';
//             } else {
//               $errmsg.textContent = '';
//               $errmsg.style.display = 'none';
//               $emailDupChk.textContent = '사용가능';
//               $emailDupChk.disabled = 'disabled';
//               $email.readyOnly = 'readyOnly';
//               validChkStatus.email = true;
//             }
//           } else {
//             console.log('Error', xmlHttpreq.status, xmlHttpreq.statusText);
//           }
//         });
//       });
//
//
//
//
//
//       $telDupChk.addEventListener('click', e => {
//
//         const $errmsg1 = $telDupChk.closest('li').querySelector('.errmsg1');
//         if (!$tel.value) {
//           $errmsg1.textContent = '전화번호를 입력하세요';
//           $tel.select(); $tel.focus();
//           return false;
//         }
//
//         const xmlHttpreq1 = new XMLHttpRequest();
//
//         const url1 = `/api/members/${$tel.value}/existTel`;
//
//         xmlHttpreq1.open("GET", url1);
//         xmlHttpreq1.send();
//
//         xmlHttpreq1.addEventListener('load', e => {
//           if (xmlHttpreq1.status === 200) { //성공적으로 서버응답 받으면
//             console.log(xmlHttpreq1.response);
//             const result1 = JSON.parse(xmlHttpreq1.response); //Json포맷 문자열 => JS객체로변환
//             console.log(result1);
//             const $errmsg1 = $telDupChk.closest('li').querySelector('.errmsg1');
//             if (result1.rtcd === '00') {
//               //alert('이미 사용되고 있는 아이디 입니다.');
//               $errmsg1.textContent = '이미 사용되고 있습니다 .';
//               $errmsg1.style.display = 'block';
//             } else {
//               $errmsg1.textContent = '';
//               $errmsg1.style.display = 'none';
//               $telDupChk.textContent = '사용가능';
//               $telDupChk.disabled = 'disabled';
//               $tel.readyOnly = 'readyOnly';
//               validChkStatus1.tel = true;
//             }
//           } else {
//             console.log('Error', xmlHttpreq1.status, xmlHttpreq1.statusText);
//           }
//         });
//       });
//
//
//
//       //회원가입 버튼 클릭시
//       joinBtn.addEventListener('click', e => {
//
//         //아이디 중복체크 미이행시
//         if (!validChkStatus.email) {
//           alert('아이디 중복체크 바랍니다');
//           $email.focus();
//           $email.select();
//           return;
//         }
//
//         //아이디 중복체크 미이행시
//         if (!validChkStatus1.tel) {
//           alert('전화번호 중복체크 바랍니다');
//           $tel.focus();
//           $telDupChk.select();
//           return;
//         }
//
//         joinForm.submit();
//       });