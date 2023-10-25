console.log("this is local-to-spring import check log");

// history 페이지의 요소를 가져와서 저장
const history = document.getElementById('history');

function getPrecHistory() {
    // 로컬 스토리지의 이름을 통해 가져와 , 을 기준으로 분리한 뒤 배열로 만들어 저장
    const precHistory = `${JSON.parse(localStorage.getItem('precList'))}`.split(',');

    // 이전의 내용이 있다면 지움
    history.innerHTML = '';

    if (precHistory[0] === 'null') {  //null 값이 요소로 들어가 있었음
        const panelBlock = document.createElement('a');
        //만든 빈 요소에 클래스를 추가하고 값 또한 추가(이 경우 안내 메시지를 추가)
        panelBlock.classList.add('panel-block');
        panelBlock.textContent = '검색 기록이 없습니다.';
        // history에 추가
        history.appendChild(panelBlock);
    } else {
        for (const result of precHistory) {
            //요소를 생성
            const panelBlock = document.createElement('a');
            //만든 빈 요소에 클래스를 추가하고 값 또한 추가
            panelBlock.classList.add('panel-block');
            panelBlock.textContent = result;
            // history에 추가
            history.appendChild(panelBlock);
        }
    }
}

function getClauHistory() {
    const clauHistory = `${JSON.parse(localStorage.getItem('clauList'))}`.split(',');

    history.innerHTML = '';

    if (clauHistory[0] === 'null') {
            const panelBlock = document.createElement('a');
            panelBlock.classList.add('panel-block');
            panelBlock.textContent = '검색 기록이 없습니다.';
            history.appendChild(panelBlock);
    } else {
        for (const result of clauHistory) {
            const panelBlock = document.createElement('a');
            panelBlock.classList.add('panel-block');
            panelBlock.textContent = result;

            history.appendChild(panelBlock);
        }
    }
}

function getQnaHistory() {
    const qnaHistory = `${JSON.parse(localStorage.getItem('qnaList'))}`.split(',');

    history.innerHTML = '';

    if (qnaHistory[0] === 'null') {
        const panelBlock = document.createElement('a');
        panelBlock.classList.add('panel-block');
        panelBlock.textContent = '검색 기록이 없습니다.';
        history.appendChild(panelBlock);
    } else {
        for (const result of qnaHistory) {
            const panelBlock = document.createElement('a');
            panelBlock.classList.add('panel-block');
            panelBlock.textContent = result;

            history.appendChild(panelBlock);
        }
    }
}




// 로컬 스토리지에서 각각의 검색 히스토리를 가져옴
// 함수로 통합되어 주석 처리함(1004)
//let clauList = localStorage.getItem('clauList');
//let precList = localStorage.getItem('precList');
//let qnaList = localStorage.getItem('qnaList');

//const clauHistory = JSON.parse(clauList);
//const precHistory = JSON.parse(precList);
//const qnaHistory = JSON.parse(qnaList);


//// 가져온 히스토리를 객체로 파싱하여 저장
//const parsedHistory = JSON.parse(clauList) + "," + JSON.parse(precList) + "," + JSON.parse(qnaList);
//let searchHistory = `${parsedHistory}`.split(',');

//========================================================================

// 서버로 보내지 않게 되었기에 사용할 필요가 없어 주석(1004)


// AJAX 요청을 사용하여 데이터를 서버로 전송
//const xmlHttpRequest = new XMLHttpRequest();
//xmlHttpRequest.open('POST', 'result', true);
//xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');


//서버로 보낼 이유가없었는데.............................................
//xmlHttpRequest.onreadystatechange = function () {
//    if (xmlHttpRequest.readyState === XMLHttpRequest.DONE) {
//        if (xmlHttpRequest.status === 200) {
//            const responseJson = JSON.parse(xmlHttpRequest.responseText);
//
//            const redirectUrl = responseJson.redirectUrl;
//            const searchResult = responseJson.searchResult;
//
//            console.log(redirectUrl);
//            window.location.href = redirectUrl;
//        } else {
//            console.error('Error:', xmlHttpRequest.status);
//        }
//    }
//};
//xmlHttpRequest.send(JSON.stringify({ history : searchHistory }));  // 이름 history
//console.log("xml request send");



