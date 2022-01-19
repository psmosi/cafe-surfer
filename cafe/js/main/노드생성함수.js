/*-----------------------------------------------------------*
 * 노드 생성 함수
 * 주어진 태그 이름,
 *-----------------------------------------------------------*/

function makeElement(tagName, attributes, ...contents) {
  //태그생성
  const elementNode = document.createElement(tagName);

  //어트리뷰트 추가
  if (attributes) {
    for (let attr in attributes) {
      if (attributes.hasOwnProperty(attr)) {
        elementNode.setAttribute(attr, attributes[attr]);
      }
    }
  }

  for (let i = 0; i < contents.length; i++) {
    let child = contents[i];
    //3번째 매개값이 문자열이면 텍스트 노드생성
    if (typeof child === 'string') {
      child = document.createTextNode(child);
    }
    elementNode.appendChild(child);
  }

  return elementNode;
}
